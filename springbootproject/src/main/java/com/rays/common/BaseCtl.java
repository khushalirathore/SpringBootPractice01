package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BaseCtl {

	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse();
		res.setSuccess(true);

		if (bindingResult.hasErrors()) {
			System.out.println("inside validate method....");
			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		System.out.println("out validate");
		return res;
	}

}
