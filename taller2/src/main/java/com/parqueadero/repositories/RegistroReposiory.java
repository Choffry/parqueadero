package com.parqueadero.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.entities.RegistroEntity;

@Repository("registroReposiory")
public interface RegistroReposiory extends JpaRepository<RegistroEntity, Serializable> {

}
