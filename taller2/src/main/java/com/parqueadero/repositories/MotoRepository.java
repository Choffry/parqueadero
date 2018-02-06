package com.parqueadero.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.entities.MotoEntity;

@Repository("motoRepository")
public interface MotoRepository extends JpaRepository<MotoEntity, Serializable>{

}
