package com.choza.pequenines.vscovid.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choza.pequenines.vscovid.repositories.PingRepository;
import com.choza.pequenines.vscovid.repositories.entities.PingEntitie;
import com.choza.pequenines.vscovid.rest.vos.PingResVO;
import com.choza.pequenines.vscovid.services.PingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PingServiceImpl implements PingService {
	
	@Autowired
	private PingRepository pingRepository;

	@Override
	public PingResVO doPing() {
		log.info("doPing(): starting method");
		
		log.info(" - creating new ping");
		PingEntitie pingEntitie = new PingEntitie();
		
		log.info(" - pingRepository[save]");
		pingRepository.save(pingEntitie);
		log.info(" - [pingEntitie: {}]", pingEntitie);
		
		PingResVO pingResVO = new PingResVO();
		pingResVO.setId(pingEntitie.getId());
		
		log.info("doPing(): ending method");
		return pingResVO;
	}

}
