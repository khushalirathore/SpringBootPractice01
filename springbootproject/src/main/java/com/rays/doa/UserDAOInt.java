package com.rays.doa;

import java.util.List;

import com.rays.dto.UserDTO;

public interface UserDAOInt {
	
	public long add(UserDTO dto);
	
	public void update(UserDTO dto );
	
	public void delete(UserDTO dto);
	
	public UserDTO findByPk(long pk);
	
	public UserDTO findByUniqueKey (String attribute , String value);
	
	public List search (UserDTO dto , int pageNo , int pageSize);

}
