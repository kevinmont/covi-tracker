package com.choza.pequenines.vscovid.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.LocationEntitie;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntitie, Long> {

}
