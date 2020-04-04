package com.choza.pequenines.vscovid.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;

@Repository
public interface CitizenRepository extends CrudRepository<CitizenEntitie, Long> {

}
