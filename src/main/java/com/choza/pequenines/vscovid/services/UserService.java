package com.choza.pequenines.vscovid.services;

import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;

public interface UserService {
	
	AuthResVO doLogin(AuthReqVO credentials);

}
