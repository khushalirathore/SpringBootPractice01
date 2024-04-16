package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl {

	@Autowired
	public UserServiceInt userService;

	@Autowired
	public AttachmentServiceInt attachmentService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		System.out.println("inside save");
		if (!res.isSuccess()) {
			return res;
		}

		// UserDTO dto = new UserDTO();
//
//		dto.setFirstName(form.getFirstName());
//		dto.setLastName(form.getLastName());
//		dto.setLoginId(form.getLoginId());
//		dto.setPassword(form.getPassword());
//		dto.setDob(form.getDob());

		UserDTO dto = (UserDTO) form.getDto();
		if (dto.getId() != null && dto.getId() > 0) {
			userService.update(dto);
			res.addData(dto.getId());
			res.addMessage("data Updated Succesfully");

		} else {

			System.out.println("inside save method");
			long pk = userService.add(dto);
			res.addData(pk);
			res.addMessage("Data Added Successfiulllllllllllll");

		}
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		userService.delete(id);
		res.addMessage("data delete successfully ");
		return res;

	}

	@PostMapping("search/{pageNo}")
	public ORSResponse searh(@RequestBody UserForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = (UserDTO) form.getDto();

		List list = userService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.setSuccess(false);
			res.addMessage("result not fund ");

		} else {
			res.setSuccess(true);
			res.addData(list);

		}

		return res;

	}

	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();
		if (!res.isSuccess()) {
			return res;
		}
		UserDTO dto = (UserDTO) form.getDto();

		long pk = userService.add(dto);

		res.addData(pk);
		res.addMessage("User Register succesfully......>>>>>>>>>>.");

		return res;

	}

	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		UserDTO userDTO = userService.findById(userId);

		AttachmentDTO attachmentDTO = new AttachmentDTO(file);
		attachmentDTO.setDescription("profile pic");
		attachmentDTO.setUserId(userId);
		if (userDTO.getImageId() != null && userDTO.getImageId() > 0) {
			attachmentDTO.setUserId(userDTO.getImageId());

		}
		Long imageId = attachmentService.save(attachmentDTO);
		if (userDTO.getImageId() == null) {
			userDTO.setImageId(imageId);
			userService.update(userDTO);
		}

		ORSResponse res = new ORSResponse();
		res.addResult("imageId", imageId);

		return res;

	}

	@GetMapping("profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse respone) {

		try {
			UserDTO userDTO = userService.findById(userId);
			AttachmentDTO attachmentDTO = null;

			if (userDTO != null) {
				attachmentDTO = attachmentService.findById(userDTO.getImageId());

			}
			if (attachmentDTO != null) {
				respone.setContentType(attachmentDTO.getType());
				OutputStream out = respone.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				respone.getWriter().write("Error: file note founf");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		UserDTO dto = userService.findById(id);
		res.addData(dto);
		return res;
	}

}
