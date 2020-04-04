package com.choza.pequenines.vscovid.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choza.pequenines.vscovid.rest.vos.PingResVO;
import com.choza.pequenines.vscovid.services.PingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PingController {
	
	@Autowired
	private PingService pingService;
	
	@GetMapping(value = "/ping")
	public ResponseEntity<PingResVO> doPing() {
		log.info("doPing(): starting method");
		
		log.info(" - calling to pingService[doPing]");
		PingResVO pingResVO = pingService.doPing();
		
		log.info(" - [pingResVO: {}]", pingResVO);
		
		log.info("doPing(): ending method");
		return ResponseEntity.ok(pingResVO);
	}
	
}
