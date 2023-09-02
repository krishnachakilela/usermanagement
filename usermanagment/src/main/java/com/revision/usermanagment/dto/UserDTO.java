package com.revision.usermanagment.dto;

import com.revision.usermanagment.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
	Integer userId;
	@NotNull(message = "First Name should not be null")
	String firstName;
	String lastName;
	@Email(message = "Email ID is mandatory")
	String emailId;
	@NotNull(message="User Name should not be null")
	String userName;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password does not met the criteria")
	String password;
	
    public static User convetDTOtoEntity(UserDTO userDTO){
        User user=new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailId(userDTO.getEmailId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO convetEntitytoDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }    
}
