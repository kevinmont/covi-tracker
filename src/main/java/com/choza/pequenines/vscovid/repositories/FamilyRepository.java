package com.choza.pequenines.vscovid.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.repositories.entities.FamilyEntitie;
import com.choza.pequenines.vscovid.repositories.entities.FamilyEntitiePK;

@Repository
public interface FamilyRepository extends CrudRepository<FamilyEntitie, FamilyEntitiePK> {

	Page<FamilyEntitie> findAllByPrincipal(CitizenEntitie citizen, Pageable pageable);

}
