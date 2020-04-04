package com.choza.pequenines.vscovid.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.PingEntitie;

@Repository
public interface PingRepository extends CrudRepository<PingEntitie, Long>{

}
