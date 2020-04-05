package com.choza.pequenines.vscovid.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
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
}
