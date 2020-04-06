package com.choza.pequenines.vscovid.services.impl;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.choza.pequenines.vscovid.repositories.CitizenRepository;
import com.choza.pequenines.vscovid.repositories.HealthHistoryRepository;
import com.choza.pequenines.vscovid.repositories.HealthStatusRepository;
import com.choza.pequenines.vscovid.repositories.LocationRepository;
import com.choza.pequenines.vscovid.repositories.UserRepository;
import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HealthHistoryEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEntitie;
import com.choza.pequenines.vscovid.repositories.entities.LocationEntitie;
import com.choza.pequenines.vscovid.repositories.entities.UserEntitie;
import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
import com.choza.pequenines.vscovid.rest.vos.LocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.SignUpReqVO;
import com.choza.pequenines.vscovid.security.SecurityConstant;
import com.choza.pequenines.vscovid.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private CitizenRepository citizenRepository;
	private HealthStatusRepository healthStatusRepository;
	private HealthHistoryRepository healthHistoryRepository;
	private LocationRepository locationRepository;
	
	public UserServiceImpl(UserRepository userRepository,
						   CitizenRepository citizenRepository,
						   HealthStatusRepository healthStatusRepository,
						   HealthHistoryRepository healthHistoryRepository,
						   LocationRepository locationRepository) {
		this.userRepository = userRepository;
		this.citizenRepository = citizenRepository;
		this.healthStatusRepository =  healthStatusRepository;
		this.healthHistoryRepository = healthHistoryRepository;
		this.locationRepository = locationRepository;
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
	
	@Override
	public Long signUp(SignUpReqVO user) {
		log.info("signUp(): starting method");
		log.info(" - [user: {}]", user);
		
		log.info(" - userRepository[findByUsername]");
		Optional<UserEntitie> userOptional = userRepository.findByUsername(user.getEmail());
		if (userOptional.isPresent()) {
			log.error(" - user alredy exists");
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario ya registrado");
		}
		
		log.info(" - healthStatusRepository[findByStatus]");
		Optional<HealthStatusEntitie> healthStatusOptional = healthStatusRepository.findByStatus(user.getHealthStatus());
		if (!healthStatusOptional.isPresent()) {
			log.error(" - Health status not found");
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Estado invalido");
		}
		
		HealthStatusEntitie healthStatusEntitie = healthStatusOptional.get();
		
		log.info(" - setting up user...");
		UserEntitie userEntitie = new UserEntitie();
		userEntitie.setUsername(user.getEmail());
		userRepository.save(userEntitie);
		
		Date dateCreation = new Date();
		log.info(" - setting up citizen...");
		CitizenEntitie citizenEntitie = new CitizenEntitie();
		citizenEntitie.setAge(user.getAge());
		citizenEntitie.setGender(user.getGender());
		citizenEntitie.setName(user.getName());
		citizenEntitie.setUser(userEntitie);
		citizenEntitie.setDateCreated(dateCreation);
		
		citizenRepository.save(citizenEntitie);
		
		log.info(" - setting up health history...");
		HealthHistoryEntitie healthHistoryEntitie =  new HealthHistoryEntitie();
		healthHistoryEntitie.setCitizen(citizenEntitie);
		healthHistoryEntitie.setDateCreation(dateCreation);
		healthHistoryEntitie.setStatus(healthStatusEntitie);
		healthHistoryRepository.save(healthHistoryEntitie);
		
		log.info("signUp(): ending method");
		return userEntitie.getId();
	}
	
	@Override
	public Boolean setupLocation(Long userId, @Valid LocationReqVO location) {
		log.info("setupLocation(): starting method");
		log.info(" - [userId: {}, location: {} ]", userId, location);
		
		log.info(" - calling to userRepository[findById]");
		Optional<UserEntitie> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			log.error(" - Usuario not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no existe");
		}
		UserEntitie userEntitie = userOptional.get();
		
		log.info(" - calling to citizenRepository[findByUser]");
		Optional<CitizenEntitie> citizenOptional = citizenRepository.findByUser(userEntitie);
		if (!citizenOptional.isPresent()) {
			log.error(" - Ciudadano not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudadano no existe");
		}
		
		CitizenEntitie citizenEntitie = citizenOptional.get();
		LocationEntitie userLocation = null;
		
		if (citizenEntitie.getLocation() != null) {
			userLocation = citizenEntitie.getLocation();
		} else {
			userLocation = new LocationEntitie();
		}
		
		userLocation.setLatitude(location.getLat());
		userLocation.setLongitude(location.getLng());
		userLocation.setAddress(location.getAddress());
		userLocation.setDescription(location.getDescription());
		log.info(" - setting up location...");
		locationRepository.save(userLocation);
		
		log.info(" - setting up citizen...");
		citizenEntitie.setLocation(userLocation);
		citizenRepository.save(citizenEntitie);
		
		log.info("setupLocation(): ending method");
		return true;
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
