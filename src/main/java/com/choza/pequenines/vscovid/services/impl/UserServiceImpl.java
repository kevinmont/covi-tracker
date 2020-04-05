package com.choza.pequenines.vscovid.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.choza.pequenines.vscovid.repositories.UserRepository;
import com.choza.pequenines.vscovid.repositories.entities.UserEntitie;
import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
import com.choza.pequenines.vscovid.security.SecurityConstant;
import com.choza.pequenines.vscovid.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public AuthResVO doLogin(AuthReqVO credentials) {
		log.info("doLogin(): starting method");
		
		Optional<UserEntitie> userOptional = this.userRepository.findByUsername(credentials.getUsername());
		if (!userOptional.isPresent()) {
			log.error(" - user not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no registrado");
		}
		
		UserEntitie user = userOptional.get();
		
		log.info(" - generatin token");
		String token = generateToken(user.getId(), user.getUsername());

		AuthResVO authResVO = new AuthResVO();
		authResVO.setToken(token);
		
		log.info("doLogin(): ending method");
		return authResVO;
	}
	
	private String generateToken(Long userId, String username) {
			String token = Jwts.builder().setId("softtekJWT")
					.setSubject(username)
					.claim("userId", userId)
					.claim("authorities", AuthorityUtils.NO_AUTHORITIES)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET.getBytes()).compact();

		return SecurityConstant.TOKEN_PREFIX + token;
		
	}

}
