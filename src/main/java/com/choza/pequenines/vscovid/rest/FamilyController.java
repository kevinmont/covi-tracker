package com.choza.pequenines.vscovid.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.rest.vos.AddFamilyMemberReqVO;
import com.choza.pequenines.vscovid.rest.vos.CreatedResVO;
import com.choza.pequenines.vscovid.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FamilyController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/family")
	public ResponseEntity<?> addFamilyMember(@RequestAttribute("userId") Long userId,
											 @RequestBody @Valid AddFamilyMemberReqVO member) {
		log.info("addFamilyMember(): starting method");
		
		log.info(" - calling to userService[getCitizen]");
		CitizenEntitie citizen = userService.getCitizen(userId);
		
		log.info(" - calling to userService[addNewMemberFamily]");
		Long memberId = userService.addNewMemberFamily(citizen, member);
		
		CreatedResVO createdResVO = new CreatedResVO();
		createdResVO.setId(memberId);
		log.info(" [createdResVO: {}] ", createdResVO);
		
		log.info("addFamilyMember(): ending method");
		return ResponseEntity.ok(createdResVO);
	}
}
