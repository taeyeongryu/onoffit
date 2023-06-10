package com.ssafy.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.User;
import com.ssafy.board.model.service.UserService;
import com.ssafy.board.util.JwtUtil;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserService uService;

	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	
	@GetMapping("/getuser")
	public void check(@RequestParam("user_id")String user_id, HttpServletResponse response) throws Exception{
		System.out.println(user_id);
		User tmp=uService.selectOne(user_id);
		System.out.println(tmp);
		if(tmp!= null) {
			response.setStatus(401);
		}
	}
	

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpStatus status = null;
		System.out.println(user.getUser_id());
		User tmp = uService.selectOne(user.getUser_id());
		System.out.println(tmp);
		try {
			if (tmp != null && tmp.getUser_password().equals(user.getUser_password())) {
				result.put("access-token", jwtUtil.createToken("id", user.getUser_id()));
				result.put("message", SUCCESS);
				result.put("loginUser", tmp);
				status = HttpStatus.ACCEPTED;
			} else {
				result.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (UnsupportedEncodingException e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@PostMapping("/regist")
	public ResponseEntity<String> regist(@RequestBody User user) {
		System.out.println(user);
		uService.insertUser(user);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
