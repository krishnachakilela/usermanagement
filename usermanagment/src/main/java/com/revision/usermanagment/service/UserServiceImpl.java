package com.revision.usermanagment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revision.usermanagment.dto.LoginDTO;
import com.revision.usermanagment.dto.UserDTO;
import com.revision.usermanagment.entity.User;
import com.revision.usermanagment.exception.UserException;
import com.revision.usermanagment.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String createUser(UserDTO userDTO) throws UserException {

		if (userDTO == null) {
			throw new UserException("User Details Are manadatory!");
		}
		Optional<User> userOptional = userRepository.findByEmailIdAndUserName(userDTO.getEmailId(),userDTO.getUserName());
		if (userOptional.isEmpty()) {
			Integer userId = userRepository.save(UserDTO.convetDTOtoEntity(userDTO)).getUserId();
			return "User is created Successfully with User Id = " + userId;
		}
		throw new UserException("User Already exist, try with different Email Id and UserName");

	}

	@Override
	public String login(LoginDTO loginDTO) throws UserException {
		if (loginDTO.getUserName().isEmpty() || loginDTO.getUserName().isBlank() || loginDTO.getPassword().isEmpty()
				|| loginDTO.getPassword().isBlank()) {
			throw new UserException("UserName and Password details are Mandatory for login!");
		}
		Optional<User> userOptional = userRepository.findByUserNameAndPassword(loginDTO.getUserName(),
				loginDTO.getPassword());
		userOptional.orElseThrow(() -> new UserException("Incorrect UserName and Password"));
		return "User Login is Successful";
	}

	@Override
	public String updateUser(Integer userId, UserDTO userDTO) throws UserException {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setEmailId(userDTO.getEmailId());
			user.setUserName(userDTO.getUserName());
			user.setPassword(userDTO.getPassword());
			return "User Details are Updated Successfully";
		}
		throw new UserException("UserID is Incorrect");
	}

	@Override
	public String deleteUser(Integer userId) throws UserException {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserException("UserID is Incorrect");
		}
		userRepository.deleteById(userId);
		return "The Details with "+userId+" are Deleted Successfully ";
	}

	@Override
	public List<UserDTO> getAllUsers() throws UserException {
		Iterable<User> userList = userRepository.findAll();
		if (userList == null) {
			throw new UserException("No Users Present in the DB");
		}
		List<UserDTO> userDTOList = new ArrayList<>();
		userList.forEach(user -> {
			UserDTO userDTO = UserDTO.convetEntitytoDTO(user);
			userDTOList.add(userDTO);
		});
		return userDTOList;
	}

	@Override
	public UserDTO getUserByEmailId(String emailId) throws UserException {
		Optional<User> optionalUser = userRepository.findByEmailId(emailId);
		User user = optionalUser.orElseThrow(() -> new UserException("User Not Availale, Try with different Email ID"));
		return UserDTO.convetEntitytoDTO(user);
	}

}
