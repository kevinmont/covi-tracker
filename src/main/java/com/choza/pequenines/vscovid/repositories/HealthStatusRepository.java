package com.choza.pequenines.vscovid.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEnum;

@Repository
public interface HealthStatusRepository extends CrudRepository<HealthStatusEntitie, Long>{

	Optional<HealthStatusEntitie> findByStatus(HealthStatusEnum healthStatus);

}
