package com.rays.doa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dto.AttachmentDTO;

@Repository
public class AttachmentDAOImpl implements AttachmentDAOInt {

	@PersistenceContext
	public EntityManager entityManager;

	
	public long add(AttachmentDTO dto) {
		entityManager.persist(dto);

		return dto.getUserId();
	}

	
	public void update(AttachmentDTO dto) {
		// TODO Auto-generated method stub

		entityManager.merge(dto);
	}

	
	public void delete(AttachmentDTO dto) {
		// TODO Auto-generated method stub

		entityManager.remove(dto);
	}

	public AttachmentDTO findByPk(long pk) {
		// TODO Auto-generated method stub
		AttachmentDTO dto = entityManager.find(AttachmentDTO.class, pk);

		return dto;
	}

}