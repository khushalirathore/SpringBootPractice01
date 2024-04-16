package com.rays.common;

import com.rays.dto.UserDTO;

public class BaseForm {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public BaseDTO getDto() {
		return null;
	}

	public <T extends BaseDTO> T initDTO(T dto) {
		if (id != null && id > 0) {
			dto.setId(id);
		} else {
			dto.setId(null);
		}
		return dto;
	}


}
