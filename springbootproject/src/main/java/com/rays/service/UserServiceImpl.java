package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.doa.UserDAOInt;
import com.rays.dto.UserDTO;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInt {

	@Autowired
	public UserDAOInt userDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(UserDTO dto) {
		long pk = userDao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) {
		userDao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		UserDTO dto = findById(id);
		userDao.delete(dto);

	}

	public UserDTO findById(long pk) {
		UserDTO dto = userDao.findByPk(pk);
		return dto;
	}

	public UserDTO authenthicate(String loginId, String password) {
		UserDTO dto = userDao.findByUniqueKey(loginId, "loginId");

		if (dto != null) {
			if (dto.getPassword().equals(password)) {
				return dto;
			}
		}
		return null;

	}

	public List search(UserDTO dto, int pageNo, int pageSize) {
		List list = userDao.search(dto, pageNo, pageSize);

		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(UserDTO dto) {

		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) {

		UserDTO dto = userDao.findByUniqueKey("loginId", login);
		return dto;
	}

}
