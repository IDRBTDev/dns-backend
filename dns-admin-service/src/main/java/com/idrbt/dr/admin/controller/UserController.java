package com.idrbt.dr.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idrbt.dr.admin.dto.UserDTO;
import com.idrbt.dr.admin.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dr/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
	
		log.info("UserController.saveUser() entered with args - user " + user);
		
		if(user == null) {
			log.info("UserController().saveUser() user object is null.");
			//TODO: Add Exception Handling
		}
		try {
			log.info("UserController.saveUser() is under execution...");
			var savedUser = userService.saveUser(user);
			log.info("UserController.saveUser() executed successfully.");
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (EntityNotFoundException businesException) { //TODO: Change the Exception to Business Exception
			log.error("UserController.saveUser() exited with exception "+businesException.getMessage(), businesException);
			throw businesException;
		}catch (Exception e) {
			log.error("updateUser() exited with exception : Exception occured while updating user."+ e.getMessage(), e);
			throw e;
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
		
		log.info("UserController.updateUser() entered with args - user");
		
		if(user == null) {
			log.info("UserController.updateUser() : User object is null ");
		}
		
		try {
			log.info("UserController.updateUser() is under execution...");
			var updatedUser = userService.updateUser(user);
			log.info("UserController.updateUser() executed successfully.");
			return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
		} 
		catch (EntityNotFoundException businesException) {
			log.error("UserController.updateUser() exited with exception :Business Exception occured while updating user. "+businesException.getMessage(), businesException);
			throw businesException;
		}catch (Exception e) {
			log.error("UserController.updateUser() exited with exception : Exception occured while updating user."+ e.getMessage(), e);
			throw e;
		}
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Boolean> deleteUserByUserId(@PathVariable("userId") String userId){
		
		log.info("UserController.deleteUserByUserId() entered with args - emailId");
		if(userId == null || userId.isEmpty()) {
			log.info("UserController.deleteUserByUserId() EmptyInputException : emailid is empty");
		}
		try {
			log.info("UserController.deleteUserByUserId() is under execution...");
			userService.deleteUserByUserId(userId);
			log.info("UserController.deleteUserByUserId() executed successfully");
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		}catch (Exception e) {
			log.error("UserController.deleteUserByUserId() exited with exception : Exception occured while deleting user."+ e.getMessage(), e);
			throw e;
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		
		log.info("UserController.getAllUser() is entered");
		try {
			log.info("UserController.getAllUser() is under execution");
			var userList = userService.getAllUsers();
			log.info("UserController.getAllUser() executed successfully");
			return new ResponseEntity<>(userList,HttpStatus.OK);
		}catch (Exception e) {
			log.error("UserController.getAllUser() exited with exception :Exception occured while updating user. "+e.getMessage(), e);
			throw e;
		}
	}
	
}
