package com.choza.pequenines.vscovid.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.repositories.entities.UserEntitie;

@Repository
public interface CitizenRepository extends CrudRepository<CitizenEntitie, Long> {

	Optional<CitizenEntitie> findByUser(UserEntitie userEntitie);

}
