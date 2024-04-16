package com.rays.service;

import java.util.List;

import org.apache.catalina.User;

import com.rays.dto.UserDTO;

public interface UserServiceInt {
	
	public long add(UserDTO dto);
	
	public void update(UserDTO dto);
	
	public void delete(long id);
	
	public UserDTO findById	(long pk);
	
	public UserDTO authenthicate(String loginId , String password );
	
	public List search (UserDTO dto , int pageNo , int pageSize );
	
	public long save(UserDTO dto );
	
	public UserDTO findByLogin(String login);
	
	
	
	

}
