package com.choza.pequenines.vscovid.services;

import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
import com.choza.pequenines.vscovid.rest.vos.SignUpReqVO;

public interface UserService {
	
	AuthResVO doLogin(AuthReqVO credentials);

	Long signUp(SignUpReqVO user);

}
