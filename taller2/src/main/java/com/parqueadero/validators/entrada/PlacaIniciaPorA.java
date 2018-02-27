package com.parqueadero.validators.entrada;

import com.parqueadero.exception.ExceptionValidaciones;
import com.parqueadero.models.Calendario;
import com.parqueadero.models.VehiculoModel;

public class PlacaIniciaPorA implements ValidacionesEntrada{
	
	private static final int DOMINGO = 1;
	private static final int LUNES = 2;

	@Override
	public void validar(VehiculoModel vehiculoModel) {
		int dia = new Calendario().getActualDay();
	
		if(!verificarPlaca(vehiculoModel, dia)) {
			throw new ExceptionValidaciones("Accesso denegado por placa");
		}
	}
	
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia){
		String placa = vehiculoModel.getPlaca();
        char primeraLetra = placa.charAt(0);
        if (primeraLetra == 'A') { 
        	return (DOMINGO == dia) || (LUNES == dia);        	
        }
        return true;
    }	
}
