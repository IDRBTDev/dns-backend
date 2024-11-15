package com.idrbt.dr.admin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.idrbt.dr.admin.dto.UserDTO;
import com.idrbt.dr.admin.entity.User;

public interface UserService extends UserDetailsService {

	UserDTO getUserDetailsByUserId(String userId);
    boolean updatePassword(String userId, String newPassword);
    boolean isEmailValid(String emailId);
    boolean updateTwoFactorAuthStatus(String emailId, boolean enabled);
    UserDTO getUserByUsername(String username);
    UserDTO getUserProfileByUsername(String username);
    User updateProfilePicture(String emailId, MultipartFile profilePicture) throws IOException;
    UserDTO saveUser(UserDTO user);
    UserDTO updateUser(UserDTO user);
    void deleteUserByUserId(String userId);
    List<UserDTO> getAllUsers();
    void deleteUserProfilePicture(String emailId);
    boolean deactivateUser(String emailId);
    boolean deactivateAllUsers(List<UserDTO> users);
    boolean isOldPasswordValid(String emailId, String oldPassword);

}
