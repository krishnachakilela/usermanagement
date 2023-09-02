package com.revision.usermanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
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

import com.revision.usermanagment.dto.LoginDTO;
import com.revision.usermanagment.dto.UserDTO;
import com.revision.usermanagment.exception.UserException;
import com.revision.usermanagment.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/revision")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws UserException {
		String successMessage = userService.createUser(userDTO);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) throws UserException {
		String successMessage = userService.login(loginDTO);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@GetMapping("/userByEmailId/{emailId}")
	public ResponseEntity<UserDTO> getUserByEmailId(@PathVariable String emailId) throws UserException {
	return new ResponseEntity<>(userService.getUserByEmailId(emailId),HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() throws UserException {
	return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable Integer userId,@RequestBody UserDTO userDTO ) throws UserException {
	String successMessage=userService.updateUser(userId,userDTO);
	return new ResponseEntity<>(successMessage,HttpStatus.OK);
	}

	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId ) throws UserException {
	String successMessage=userService.deleteUser(userId);
	return new ResponseEntity<>(successMessage,HttpStatus.OK);
	}
}
