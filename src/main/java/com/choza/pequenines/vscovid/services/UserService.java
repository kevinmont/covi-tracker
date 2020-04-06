package com.choza.pequenines.vscovid.services;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.rest.vos.AddFamilyMemberReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
import com.choza.pequenines.vscovid.rest.vos.LocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.SignUpReqVO;

public interface UserService {
	
	AuthResVO doLogin(AuthReqVO credentials);

	Long signUp(SignUpReqVO user);

	Boolean setupLocation(Long userId, LocationReqVO location);

	Long addNewMemberFamily(CitizenEntitie citizen, AddFamilyMemberReqVO member);
	
	CitizenEntitie getCitizen(Long userId);

}
