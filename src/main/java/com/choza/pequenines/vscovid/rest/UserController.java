package com.choza.pequenines.vscovid.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
import com.choza.pequenines.vscovid.rest.vos.CreatedResVO;
import com.choza.pequenines.vscovid.rest.vos.LocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.SignUpReqVO;
import com.choza.pequenines.vscovid.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> doLogin(@RequestBody @Valid AuthReqVO credentials) {
		log.info("doLogin(): starting method");
		
		log.info(" - calling to userService[doLogin]");
		AuthResVO authResVO = userService.doLogin(credentials);
		
		log.info("doLogin(): ending method");
		return ResponseEntity.ok(authResVO);
	}
	
	@PostMapping(value = "/user") 
	public ResponseEntity<CreatedResVO> signUp(@RequestBody @Valid SignUpReqVO user) {
		log.info("signUp(): starting method");
		
		log.info(" - calling to userService[signUp]");
		Long userId = userService.signUp(user);
				
		CreatedResVO createdResVO = new CreatedResVO();
		log.info(" - [createdResVO: {}]", createdResVO);
		createdResVO.setId(userId);
		
		log.info("signUp(): ending method");
		return new ResponseEntity<CreatedResVO>(createdResVO, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/location")
	public ResponseEntity<?> updateUserLocation(@RequestAttribute("userId") Long userId,
			@RequestBody @Valid LocationReqVO location) {
		log.info("updateUserLocation(): starting method");
		
		log.info(" - calling to userService[setupLocation]");
		userService.setupLocation(userId, location);
		
		log.info("updateUserLocation(): ending method");
		return ResponseEntity.noContent().build();
	}
}
