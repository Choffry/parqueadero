package com.parqueadero.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.entities.ParqueaderoEntity;

@Repository("parqueaderoRepository")
public interface ParqueaderoRepository extends JpaRepository<ParqueaderoEntity, Serializable>{

}
