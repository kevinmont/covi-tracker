package com.choza.pequenines.vscovid.repositories;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.LocationEntitie;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntitie, Long> {

	@Query("select m from LocationEntitie m where distance(m.cordinate, ?1) <= ?2")
    Page<LocationEntitie> findWithin(Geometry filter, Double radio ,Pageable pageable);
	
}
