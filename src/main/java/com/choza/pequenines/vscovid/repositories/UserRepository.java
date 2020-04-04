package com.choza.pequenines.vscovid.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.UserEntitie;

@Repository
public interface UserRepository extends CrudRepository<UserEntitie, Long> {

}
