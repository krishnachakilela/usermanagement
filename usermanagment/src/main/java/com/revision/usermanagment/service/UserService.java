package com.revision.usermanagment.service;

import java.util.List;

import com.revision.usermanagment.dto.LoginDTO;
import com.revision.usermanagment.dto.UserDTO;
import com.revision.usermanagment.exception.UserException;

public interface UserService {
    public String createUser(UserDTO userDTO) throws UserException;
    public String login(LoginDTO loginDTO) throws UserException;
    public String updateUser(Integer userId,UserDTO userDTO) throws UserException;
    public String deleteUser(Integer userId) throws UserException;
    public List<UserDTO> getAllUsers() throws UserException;
    public UserDTO getUserByEmailId(String emailId) throws UserException;
	
}
