package com.parqueadero.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.entities.CarroEntity;

@Repository("carroRepository")
public interface CarroRepository extends JpaRepository<CarroEntity, Serializable> {
	public CarroEntity findByPlaca(String placa);
}
