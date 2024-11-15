package com.idrbt.dr.admin.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.idrbt.dr.admin.dto.UserDTO;
import com.idrbt.dr.admin.entity.User;
import com.idrbt.dr.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserDetailsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailValid(String emailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTwoFactorAuthStatus(String emailId, boolean enabled) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserProfileByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfilePicture(String emailId, MultipartFile profilePicture) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserByUserId(String uerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserProfilePicture(String emailId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deactivateUser(String emailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deactivateAllUsers(List<UserDTO> users) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOldPasswordValid(String emailId, String oldPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
