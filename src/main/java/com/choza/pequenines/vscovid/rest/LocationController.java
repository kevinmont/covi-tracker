package com.choza.pequenines.vscovid.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.rest.vos.AddNewHistoryLocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.CreatedResVO;
import com.choza.pequenines.vscovid.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LocationController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/history-location")
	public ResponseEntity<?> addNewHistoryLocation(@RequestBody @Valid AddNewHistoryLocationReqVO location,
												   @RequestAttribute("userId") Long userId) {
		log.info("addNewHistoryLocation(): starting method");
		
		log.info(" - calling to userService[getCitizen]");
		CitizenEntitie citizen = userService.getCitizen(userId);
		
		log.info(" - calling to userService[getCitizen]");
		Long locationId = userService.addNewHistoryLocation(citizen, location);
		
		CreatedResVO createdResVO = new CreatedResVO();
		createdResVO.setId(locationId);
		log.info(" [createdResVO: {}] ", createdResVO);
		
		log.info("addNewHistoryLocation(): ending method");
		return ResponseEntity.ok().build();
	}
	
}
