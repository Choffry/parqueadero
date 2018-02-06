package com.parqueadero.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.entities.FacturaEntity;

@Repository("facturaReposiory")
public interface FacturaReposiory extends JpaRepository<FacturaEntity, Serializable> {

}
