package com.choza.pequenines.vscovid.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.choza.pequenines.vscovid.repositories.CitizenRepository;
import com.choza.pequenines.vscovid.repositories.FamilyRepository;
import com.choza.pequenines.vscovid.repositories.HealthHistoryRepository;
import com.choza.pequenines.vscovid.repositories.HealthStatusRepository;
import com.choza.pequenines.vscovid.repositories.HistoryLocationRepository;
import com.choza.pequenines.vscovid.repositories.LocationRepository;
import com.choza.pequenines.vscovid.repositories.UserRepository;
import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.repositories.entities.FamilyEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HealthHistoryEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HistoryLocationEntitie;
import com.choza.pequenines.vscovid.repositories.entities.LocationEntitie;
import com.choza.pequenines.vscovid.repositories.entities.UserEntitie;
import com.choza.pequenines.vscovid.rest.vos.AddFamilyMemberReqVO;
import com.choza.pequenines.vscovid.rest.vos.AddNewHistoryLocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
import com.choza.pequenines.vscovid.rest.vos.FamilyMemberResVO;
import com.choza.pequenines.vscovid.rest.vos.LocationHistoryResVO;
import com.choza.pequenines.vscovid.rest.vos.LocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.PaginateResultResVO;
import com.choza.pequenines.vscovid.rest.vos.SignUpReqVO;
import com.choza.pequenines.vscovid.rest.vos.UpdateFamilyMemberHealthStatusReqVO;
import com.choza.pequenines.vscovid.security.SecurityConstant;
import com.choza.pequenines.vscovid.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private CitizenRepository citizenRepository;
	private HealthStatusRepository healthStatusRepository;
	private HealthHistoryRepository healthHistoryRepository;
	private LocationRepository locationRepository;
	private FamilyRepository familyRepository;
	private HistoryLocationRepository historyLocationRepository;

	public UserServiceImpl(UserRepository userRepository, 
						   CitizenRepository citizenRepository,
						   HealthStatusRepository healthStatusRepository, 
						   HealthHistoryRepository healthHistoryRepository,
						   LocationRepository locationRepository, 
						   FamilyRepository familyRepository,
						   HistoryLocationRepository historyLocationRepository) {
		this.userRepository = userRepository;
		this.citizenRepository = citizenRepository;
		this.healthStatusRepository = healthStatusRepository;
		this.healthHistoryRepository = healthHistoryRepository;
		this.locationRepository = locationRepository;
		this.familyRepository = familyRepository;
		this.historyLocationRepository = historyLocationRepository;
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
		Optional<HealthStatusEntitie> healthStatusOptional = healthStatusRepository
				.findByStatus(user.getHealthStatus());
		if (!healthStatusOptional.isPresent()) {
			log.error(" - Health status not found");
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Estado invalido");
		}

		HealthStatusEntitie healthStatusEntitie = healthStatusOptional.get();

		log.info(" - setting up user...");
		UserEntitie userEntitie = new UserEntitie();
		userEntitie.setUsername(user.getEmail());
		userRepository.save(userEntitie);
		
		log.info(" - setting up location...");
		LocationEntitie userLocation = new LocationEntitie();
		userLocation.setLatitude(user.getLocation().getLat());
		userLocation.setLongitude(user.getLocation().getLng());
		userLocation.setAddress(user.getLocation().getAddress());
		userLocation.setDescription(user.getLocation().getDescription());
		locationRepository.save(userLocation);

		Date dateCreation = new Date();
		log.info(" - setting up citizen...");
		CitizenEntitie citizenEntitie = new CitizenEntitie();
		citizenEntitie.setAge(user.getAge());
		citizenEntitie.setGender(user.getGender());
		citizenEntitie.setName(user.getName());
		citizenEntitie.setUser(userEntitie);
		citizenEntitie.setLocation(userLocation);
		citizenEntitie.setDateCreated(dateCreation);
		citizenRepository.save(citizenEntitie);

		log.info(" - setting up health history...");
		HealthHistoryEntitie healthHistoryEntitie = new HealthHistoryEntitie();
		healthHistoryEntitie.setCitizen(citizenEntitie);
		healthHistoryEntitie.setDateCreation(dateCreation);
		healthHistoryEntitie.setStatus(healthStatusEntitie);
		healthHistoryRepository.save(healthHistoryEntitie);

		log.info("signUp(): ending method");
		return userEntitie.getId();
	}

	@Override
	public Boolean setupLocation(Long userId, LocationReqVO location) {
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

	@Override
	public Long addNewMemberFamily(CitizenEntitie citizen, AddFamilyMemberReqVO member) {
		log.info("addNewMemberFamily(): starting method");
		log.debug(" - [citizen: {}, member: {}]", citizen, member);

		log.info(" - healthStatusRepository[findByStatus]");
		Optional<HealthStatusEntitie> healthStatusOptional = healthStatusRepository
				.findByStatus(member.getHealthStatus());
		if (!healthStatusOptional.isPresent()) {
			log.error(" - Health status not found");
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Estado invalido");
		}

		HealthStatusEntitie healthStatusEntitie = healthStatusOptional.get();

		Date dateCreated = new Date();
		log.info(" - setting up new member...");
		CitizenEntitie newMember = new CitizenEntitie();
		newMember.setAge(member.getAge());
		newMember.setName(member.getName());
		newMember.setGender(member.getGender());
		newMember.setDateCreated(dateCreated);
		citizenRepository.save(newMember);

		log.info(" - setting up health history...");
		HealthHistoryEntitie healthHistoryEntitie = new HealthHistoryEntitie();
		healthHistoryEntitie.setCitizen(newMember);
		healthHistoryEntitie.setStatus(healthStatusEntitie);
		healthHistoryEntitie.setDateCreation(dateCreated);
		healthHistoryRepository.save(healthHistoryEntitie);

		log.info(" - setting up new family member...");
		FamilyEntitie familyEntitie = new FamilyEntitie();
		familyEntitie.setPrincipal(citizen);
		familyEntitie.setMember(newMember);
		familyRepository.save(familyEntitie);

		log.info("addNewMemberFamily(): ending method");
		return newMember.getId();
	}

	@Override
	public CitizenEntitie getCitizen(Long userId) {
		log.info("getCitizen(): starting method");
		log.info(" - [userId: {}]", userId);

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

		log.info("getCitizen(): ending method");
		return citizenOptional.get();
	}

	@Override
	public PaginateResultResVO<FamilyMemberResVO> getFamilyMembers(CitizenEntitie citizen, Pageable pageable) {
		log.info("getFamilyMembers(): starting method");

		log.info(" - [citizen: {}]", citizen);
		PaginateResultResVO<FamilyMemberResVO> paginateResultResVO = new PaginateResultResVO<FamilyMemberResVO>();

		log.info(" - familyRepository[findAllByPrincipal]");
		Page<FamilyEntitie> family = familyRepository.findAllByPrincipal(citizen, pageable);
		paginateResultResVO.setTotalPages(family.getTotalPages());
		paginateResultResVO.setPage(family.getPageable().getPageNumber());

		List<FamilyMemberResVO> familyMemberResVOs = family.stream().map($0 -> {
			FamilyMemberResVO familyMemberResVO = new FamilyMemberResVO();
			familyMemberResVO.setId($0.getMember().getId());
			familyMemberResVO.setName($0.getMember().getName());
			familyMemberResVO.setGender($0.getMember().getGender());
			familyMemberResVO.setAge($0.getMember().getAge());
			HealthHistoryEntitie healthHistoryEntitie = healthHistoryRepository
					.findFirstByCitizenOrderByDateCreationDesc($0.getMember());
			familyMemberResVO.setHealthStatus(healthHistoryEntitie.getStatus().getStatus());
			return familyMemberResVO;
		}).collect(Collectors.toList());

		log.info(" - familyMemberResVOs[size: {}]", familyMemberResVOs.size());
		paginateResultResVO.setResult(familyMemberResVOs);

		log.info("getFamilyMembers(): ending method");
		return paginateResultResVO;
	}

	@Override
	public Boolean updateHealthStatusOfMember(UpdateFamilyMemberHealthStatusReqVO memberHealthStatus,
			CitizenEntitie citizen, Long memberId) {
		log.info("updateHealthStatusOfMember(): starting method");
		log.debug(" - [citizen: {}, memberId: {}]", memberId, memberId);
		log.info(" - [memberId: {}]", memberId);

		log.info(" - citizenRepository[findById]");
		Optional<CitizenEntitie> citizenOptional = citizenRepository.findById(memberId);
		if (!citizenOptional.isPresent()) {
			log.error(" - Member as citizen not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudadano no existe");
		}
		CitizenEntitie citizenMember = citizenOptional.get();

		log.info(" - familyRepository[findByPrincipalAndMember]");
		Optional<FamilyEntitie> familyMemberOptional = familyRepository.findByPrincipalAndMember(citizen,
				citizenMember);
		if (!familyMemberOptional.isPresent()) {
			log.error(" - member is not in family");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudadano no es familiar");
		}

		log.info(" - healthStatusRepository[findByStatus]");
		Optional<HealthStatusEntitie> healthStatusOptional = healthStatusRepository
				.findByStatus(memberHealthStatus.getHealthStatus());
		if (!healthStatusOptional.isPresent()) {
			log.error(" - Health status not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado invalido");
		}
		HealthStatusEntitie healthStatusEntitie = healthStatusOptional.get();

		HealthHistoryEntitie healthHistoryEntitie = new HealthHistoryEntitie();
		healthHistoryEntitie.setCitizen(citizenMember);
		healthHistoryEntitie.setStatus(healthStatusEntitie);
		healthHistoryEntitie.setDateCreation(new Date());
		healthHistoryRepository.save(healthHistoryEntitie);

		log.info("updateHealthStatusOfMember(): ending method");
		return true;
	}

	@Override
	public Long addNewHistoryLocation(CitizenEntitie citizen, AddNewHistoryLocationReqVO location) {
		log.info("addNewHistoryLocation(): starting method");
		log.debug(" - [citizen: {}]", citizen);
		log.info(" - [location: {}]", location);
		
		log.info(" - setting up new location...");
		LocationEntitie locationEntitie = new LocationEntitie();
		locationEntitie.setLatitude(location.getLat());
		locationEntitie.setLongitude(location.getLng());
		locationRepository.save(locationEntitie);
		
		log.info(" - setting up location history...");
		HistoryLocationEntitie historyLocationEntitie = new HistoryLocationEntitie();
		historyLocationEntitie.setCitizen(citizen);
		if (location.getDate() != null)
			historyLocationEntitie.setDateCreation(location.getDate());
		else 
			historyLocationEntitie.setDateCreation(new Date());
		historyLocationEntitie.setLocation(locationEntitie);
		historyLocationRepository.save(historyLocationEntitie);
		
		log.info("addNewHistoryLocation(): ending method");
		return locationEntitie.getId();
	}
	
	@Override
	public PaginateResultResVO<LocationHistoryResVO> getHistoryLocations(CitizenEntitie citizen, Pageable pageable) {
		log.info("getHistoryLocations(): starting method");
		log.debug(" - [citizen: {}]", citizen);
		
		PaginateResultResVO<LocationHistoryResVO> paginateResultResVO = new PaginateResultResVO<LocationHistoryResVO>();
		
		log.info(" - historyLocationRepository[findAllByCitizen]");
		Page<HistoryLocationEntitie> historyLocationPage = historyLocationRepository.findAllByCitizen(citizen, pageable);
		paginateResultResVO.setPage(historyLocationPage.getPageable().getPageNumber());
		paginateResultResVO.setTotalPages(historyLocationPage.getTotalPages());
		
		List<LocationHistoryResVO> locationHistoryResVOs = historyLocationPage.stream()
			.map($0 -> {
				LocationHistoryResVO locationHistoryResVO = new LocationHistoryResVO();
				locationHistoryResVO.setLat($0.getLocation().getLatitude());
				locationHistoryResVO.setLng($0.getLocation().getLongitude());
				locationHistoryResVO.setDate($0.getDateCreation());
				
				return locationHistoryResVO;
			}).collect(Collectors.toList());
		log.info(" - locationHistoryResVOs[size: {}]", locationHistoryResVOs.size());
		paginateResultResVO.setResult(locationHistoryResVOs);
		
		log.info("getHistoryLocations(): ending method");
		return paginateResultResVO;
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
