package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.UserDTO;

public class UserForm extends BaseForm {

	@NotEmpty(message = "firstName is required")
	// @Pattern( regexp = "^[^-\\s][\\p{L} .'-]+$",message = "FirstName Must be
	// alphanumeric")
	private String firstName;

	@NotEmpty(message = "lastName is required")
	// @Pattern( regexp = "^[^-\\s][\\p{L} .'-]+$",message = "LastName Must be
	// alphanumeric")
	private String lastName;

	@NotEmpty(message = "loginId is required")
	// @Pattern( regexp = "^[^-\\s][\\p{L} .'-]+$",message = "LoginId Must be
	// alphanumeric")
	private String loginId;

	@NotEmpty(message = "password is required")
	// @Pattern( regexp = "^[^-\\s][\\p{L} .'-]+$",message = " password Must be
	// alphanumeric")
	private String password;

	// @NotNull(message = "dob is required")
	private Date dob;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
  @Override
public BaseDTO getDto() {
	  UserDTO dto = initDTO(new UserDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLoginId(loginId);
		dto.setPassword(password);
		dto.setDob(dob);
		
		
		return dto;
	
}


}
