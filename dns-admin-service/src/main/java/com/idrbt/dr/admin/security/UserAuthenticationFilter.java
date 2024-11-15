package com.idrbt.dr.admin.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idrbt.dr.admin.dto.RoleDTO;
import com.idrbt.dr.admin.dto.UserDTO;
import com.idrbt.dr.admin.model.ClientRequestDetails;
import com.idrbt.dr.admin.model.UserLoginRequestModel;
import com.idrbt.dr.admin.service.UserService;
import com.idrbt.dr.admin.util.EmailService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UserService service;

	private Environment environment;

	private ModelMapper mapper = new ModelMapper();
	
	private EmailService emailService;
	
	private BCryptPasswordEncoder encoder;
	
	private static final String TOKEN_EXPIRATION_PROPERTY = "token.expiration_time";
	private static final String LOGIN_ATTEMPTS = "loginAttempts";

	public UserAuthenticationFilter(UserService service, Environment environment, AuthenticationManager authManager,
			EmailService emailService, BCryptPasswordEncoder encoder) {
		log.info("UserAuthenticationFilter() constructor entered with args - UserService,Environment and AuthenticationManager Objects.");
		this.service = service;
		this.environment = environment;
		this.emailService = emailService;
		this.encoder = encoder;
		super.setAuthenticationManager(authManager);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("Location"+request.getHeader("loginCity"));
		log.info("attemptAuthentication() entered with args - HttpRequest and HttpResponse Objects.");
		//String clientIP = request.getRemoteAddr();
		String forwardedIP = request.getHeader("X-Forwarded-For");
		//String forwardedIP2 = request.getHeader("X-Real-IP");
		log.info("Login request received from "+forwardedIP);
		//String clientName = request.getRemoteHost();
		String deviceType = request.getHeader("User-Agent");
        // Define the regular expression pattern
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        // Match the pattern against the input string
        Matcher matcher = pattern.matcher(deviceType);
        // Find the substring within brackets
        String orginalDeviceType = "";
        if (matcher.find()) {
        	orginalDeviceType = matcher.group(1);
        }
		var i = 1;
		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(),
					UserLoginRequestModel.class);
			UserDTO loadedUser = service.getUserByUsername(creds.getEmail());
			if (loadedUser != null) {
				boolean isActive = loadedUser.isActive();
				UserDTO loginAttemptedUser = service.getUserDetailsByUserId(creds.getEmail());
				//get request details of client
				// get user datails and update login attempts
				loginAttemptedUser.setLoginAttemptedClientIP(forwardedIP);
				loginAttemptedUser.setLoginAttemptedClientDeviceType(orginalDeviceType);
				loginAttemptedUser.setLoginAttemptedDateTime(LocalDateTime.now());
				loginAttemptedUser.setLoginAttempts(loginAttemptedUser.getLoginAttempts() + i);
				var updatedUserWithLogginAttempts = service.updateUser(loginAttemptedUser);
				var dbEncPassword = updatedUserWithLogginAttempts.getEncryptedPassword();
				var isPasswordMatched = encoder.matches(creds.getPassword(), dbEncPassword);
				if(!isPasswordMatched) {
					var remainingLoginAttempts = 3;
					//send email to user on an unsuccessful login attempt
					if(updatedUserWithLogginAttempts.getLoginAttempts() == 1) {
						remainingLoginAttempts = remainingLoginAttempts-1;
					}else if(updatedUserWithLogginAttempts.getLoginAttempts() == 2) {
						remainingLoginAttempts = remainingLoginAttempts-2;
					}else {
						remainingLoginAttempts = 0;
					}
					LocalDateTime timestamp = updatedUserWithLogginAttempts.getLoginAttemptedDateTime();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd hh:mm a");
			        String formattedDateTime = timestamp.format(formatter);
			        //get request details like country, city, location from 3rd party API
			        RestTemplate rt = new RestTemplate();
			        HttpHeaders headers = new HttpHeaders();
			        ClientRequestDetails requestdetails = new ClientRequestDetails();
			        headers.setContentType(MediaType.APPLICATION_JSON);
			        String clientRequestDetailsURL = environment.getProperty("client.request-details.url");
			        try {
			        	 ResponseEntity<ClientRequestDetails> ccrResponse = rt.exchange(clientRequestDetailsURL, 
			        			 HttpMethod.GET, null, ClientRequestDetails.class);
			        	 log.error("Request sent to "+clientRequestDetailsURL);
					     requestdetails = ccrResponse.getBody();
					     log.info("Client Request details: "+requestdetails.toString());
			        }catch (Exception e) {
						log.error("Request details could not be captured due to an error: "+ e.getMessage(), e);
					}
					//get employee details
					String emailbody = "Dear User, \r\n"+"We've detected an unauthorized login attempt on your UMS account: \r\n \r\n"
					+ "   •Device: "+updatedUserWithLogginAttempts.getLoginAttemptedClientDeviceType()+"\r\n"+
					  "   •IP Address: "+requestdetails.getQuery()+"\r\n"+
					  "   •Time: "+formattedDateTime+"\r\n"+
					  "   •Location: "+requestdetails.getCity()+", "+requestdetails.getRegionName()+", "+requestdetails.getCountry()+"\r\n"+
					  "   •Number of Login Attempts: "+updatedUserWithLogginAttempts.getLoginAttempts()+"\r\n \r\n \r\n"
					+"Your account has "+remainingLoginAttempts+" attempts remaining before automatic lockout.\r\n"
					+ "Please secure your account immediately by resetting your password or by enabling Two Factor Authentication. \r\n"+
					"Thank you for your attention. \r\n \r\n";
					String subject = "Security Alert: Unauthorized Login Attempt";
					log.info(emailbody);
					emailService.sendMail(updatedUserWithLogginAttempts.getEmail(), subject, emailbody, false);
				}
				response.addHeader(LOGIN_ATTEMPTS, updatedUserWithLogginAttempts.getLoginAttempts().toString());
				String active = String.valueOf(isActive);
				if (!isActive) {
					log.error(" attemptAuthentication() Error occured while attempting to Login , UserInactiveException : User is inactive - Cannot login");
					response.addHeader(LOGIN_ATTEMPTS, updatedUserWithLogginAttempts.getLoginAttempts().toString());
					response.addHeader("userActive", active);
				} else if (isActive && updatedUserWithLogginAttempts.getLoginAttempts() > 3) {
					updatedUserWithLogginAttempts.setActive(false);
					service.updateUser(updatedUserWithLogginAttempts);
					response.addHeader(LOGIN_ATTEMPTS, updatedUserWithLogginAttempts.getLoginAttempts().toString());
					response.addHeader("userActive", active);
					log.error(" attemptAuthentication() Error occured while attempting to Login , LoginAttemptsExceededException : User login attempts exceeded more then 3.");
				}
			}else {
				response.addHeader("userNotFound",String.valueOf(true));
			}
			log.info("attemptAuthentication() excuted succesfully.");
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} 
		catch (IOException ioe) {
			log.error("attemptAuthentication() Exception occured while Login ."+ioe.getMessage(), ioe);
			//System.out.println("----"+ioe+"----");
			throw new RuntimeException(ioe);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("UserAuthenticationFilter.successfulAuthentication() entered with args - HttpRequest , HttpResponse , FilterChain & Authentication objects.");
		String userName = ((User) authResult.getPrincipal()).getUsername();
		var loadedUser = service.getUserByUsername(userName);
		//loadedUser.setLastSuccessfulLoginAt(LocalDateTime.now());
		// on sucessful auth set login attempts to 0
		UserDTO loggedInUser = service.getUserDetailsByUserId(userName);
//		String emailbody = "Dear User, A login detected to your UMS account : "+ loggedInUser.getEmail()+
//				"\r\n Login detected from Device : "
//							+ ""+loggedInUser.getLoginAttemptedClientDeviceType()+" \r\n Device IP : "
//									+ ""+loggedInUser.getLoginAttemptedClientIP()+"\r\n Login Attempted Time : "
//									+loggedInUser.getLoginAttemptedDateTime();
//				emailService.sendMail(loggedInUser.getEmail(), "UMS Login Success", emailbody, false);
		loggedInUser.setLoginAttempts(0);
		loggedInUser.setLastSuccessfulLoginAt(LocalDateTime.now());
		service.updateUser(loggedInUser);
		log.info("successfulAuthentication() : Login attempts reset to 0 on successful login.");
		var userRoleMenuItemsPermissionMap = getUserRoleMenuItemPermissions(loggedInUser);
		String webToken = Jwts.builder().setSubject(loadedUser.getEmail())
				.setExpiration(new Date(
						System.currentTimeMillis() + Long.parseLong(environment.getProperty(TOKEN_EXPIRATION_PROPERTY))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				.setIssuer(request.getRequestURL().toString()).claim("role", loadedUser.getUserRoles().iterator().next().getRoleName())
				.compact();

		String refreshToken = Jwts.builder().setSubject(loadedUser.getEmail())
				.setExpiration(new Date(
						System.currentTimeMillis() + Long.parseLong(environment.getProperty(TOKEN_EXPIRATION_PROPERTY))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				.setIssuer(request.getRequestURL().toString()).claim("role", loadedUser.getUserRoles().iterator().next().getRoleName())
				.compact();
		log.info("successfulAuthentication() : Jwt Token created for the user "+userName);
		log.info("successfulAuthentication() JWT TOKEN: "+webToken);
		response.addHeader("token", webToken);
		response.addHeader("refreshToken", refreshToken);
		Iterator<RoleDTO> itr = loadedUser.getUserRoles().iterator();
		RoleDTO role = null;
		while (itr.hasNext()) {
			role = itr.next();
		}
		response.addHeader("userRole", role.getRoleName());
		response.addHeader("email", loadedUser.getEmail());
		response.addHeader("twoFactorAuth", Boolean.toString(loadedUser.isTwoFactorAuthentication()));
		response.addHeader("jwtExpiry",
				new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty(TOKEN_EXPIRATION_PROPERTY)))
						.toString());
		//optional:
		String userRoleMenuItemMapJsonString = new ObjectMapper().writeValueAsString(userRoleMenuItemsPermissionMap);
		response.addHeader("userRoleMenuItemsPermissionMap", userRoleMenuItemMapJsonString);
		log.info("successfulAuthentication() : User Role Menu Item Permission Map retrived for the user "+userName+" and returned in response object to client application.");
		var tokenData = new HashMap<String, String>();
		tokenData.put("token", webToken);
		tokenData.put("refreshToken", refreshToken);
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), tokenData);
		log.info("successfulAuthentication() executed successfully.");
		log.info("successfulAuthentication() User Authentication sucessfull.");
	}

	public Map<String, String> getUserRoleMenuItemPermissions(UserDTO loggedInUser) {
		log.info("getUserRoleMenuItemPermissions() entered with args - user object.");
		var userRoleMenuItemPermissionMap = new HashMap<String, String>();			
			//loggedInUser.getUserRoleMenuItemPermissionMap().forEach(rmpDTO -> {
				//userRoleMenuItemPermissionMap.put(rmpDTO.getMenuItemIdList(), rmpDTO.getPermissionIdList());
			//});
		log.info("getUserRoleMenuItemPermissions() - UserRoleMenuItemPermission Map object created.");
		log.info("getUserRoleMenuItemPermissions() executed successfully.");
		return userRoleMenuItemPermissionMap;
	}
}
