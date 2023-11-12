package com.student.demo.resource.user;

import com.student.demo.dto.LoginReqDto;
import com.student.demo.dto.UserReqDto;
import com.student.demo.dto.UserResDto;
import com.student.demo.entity.User;
import com.student.demo.mapper.UserMapper;
import com.student.demo.repository.user.UserRepository;
import com.student.demo.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@Validated
@RestController
@RequestMapping("v1/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/add")
	public ResponseEntity<User> userRegistration(@Valid @RequestBody UserReqDto userReqDto) throws AuthenticationException {
		userReqDto.setUsername(userReqDto.getUsername().toLowerCase());
		User user = UserMapper.INSTANCE.toUser(userReqDto);
		User userDetails = userService.create(user);
		if (userDetails.getUserId() > 0) {
			return new ResponseEntity<>(userDetails, HttpStatus.OK);
		}
		return new ResponseEntity<>(userDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@Valid @RequestBody LoginReqDto loginReqDto) throws AuthenticationException {
		loginReqDto.setUsername(loginReqDto.getUsername().toLowerCase());
		User user = UserMapper.INSTANCE.LoginReqDtoToUser(loginReqDto);
		User userLogin = userService.login(user);
		UserResDto userResDto = UserMapper.INSTANCE.toDto(userLogin);
		if (userResDto.getUserId() > 0) {
			return new ResponseEntity<>(userLogin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userLogin, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
