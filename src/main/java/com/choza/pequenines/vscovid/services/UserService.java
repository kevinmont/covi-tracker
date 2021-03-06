package com.choza.pequenines.vscovid.services;

import org.springframework.data.domain.Pageable;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.rest.vos.AddFamilyMemberReqVO;
import com.choza.pequenines.vscovid.rest.vos.AddNewHistoryLocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthReqVO;
import com.choza.pequenines.vscovid.rest.vos.AuthResVO;
import com.choza.pequenines.vscovid.rest.vos.FamilyMemberResVO;
import com.choza.pequenines.vscovid.rest.vos.GetNearestCitizenResVO;
import com.choza.pequenines.vscovid.rest.vos.LocationHistoryResVO;
import com.choza.pequenines.vscovid.rest.vos.LocationReqVO;
import com.choza.pequenines.vscovid.rest.vos.PaginateResultResVO;
import com.choza.pequenines.vscovid.rest.vos.SignUpReqVO;
import com.choza.pequenines.vscovid.rest.vos.UpdateFamilyMemberHealthStatusReqVO;

public interface UserService {

	AuthResVO doLogin(AuthReqVO credentials);

	Long signUp(SignUpReqVO user);

	Boolean setupLocation(Long userId, LocationReqVO location);

	Long addNewMemberFamily(CitizenEntitie citizen, AddFamilyMemberReqVO member);

	CitizenEntitie getCitizen(Long userId);

	PaginateResultResVO<FamilyMemberResVO> getFamilyMembers(CitizenEntitie citizen, Pageable pageable);

	Boolean updateHealthStatusOfMember(UpdateFamilyMemberHealthStatusReqVO memberHealthStatus, CitizenEntitie citizen,
			Long memberId);

	Long addNewHistoryLocation(CitizenEntitie citizen, AddNewHistoryLocationReqVO location);

	PaginateResultResVO<LocationHistoryResVO> getHistoryLocations(CitizenEntitie citizen, Pageable pageable);

	PaginateResultResVO<GetNearestCitizenResVO> getNearestCitizens(Double lat, Double lng, Double radio,
			Pageable pageable);

}
