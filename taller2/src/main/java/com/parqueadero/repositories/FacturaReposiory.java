package com.parqueadero.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.models.FacturaModel;

@Repository("facturaReposiory")
public interface FacturaReposiory extends JpaRepository<FacturaEntity, Serializable> {
	public FacturaEntity findByPlacaAndEstado(String placa, boolean estado);

	public List<FacturaEntity> findByEstado(boolean estado);
	
	public default FacturaModel entity2model(FacturaEntity facturaEntity) {
		
		return new FacturaModel(
				facturaEntity.getCilindraje(),
				facturaEntity.getHoraIngreso(),
				facturaEntity.getHoraSalida(),
				facturaEntity.getPagoTotal(),
				facturaEntity.getPlaca()
				);
			
	}
}
