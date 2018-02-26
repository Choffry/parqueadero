package com.parqueadero.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.FacturaModel;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.services.VigilanteService;

@Service("vigilanteService")
public class VigilanteServiceImpl implements VigilanteService{
	
	private static final Log LOG = LogFactory.getLog(VigilanteServiceImpl.class);
	private static final int DOMINGO = 1;
	private static final int LUNES = 2;
	private static final String CARRO = "Carro";
	private static final int MAX_CILINDRAJE = 500;
	private static final int ADDICION_CILINDRAJE = 2000;
	private static final int HORAS_DEL_DIA = 24;
	private static final int HORAS_MAX = 9;
	private static final int MILISEGUNDOS_EN_HORA = 3600000;
	
	@Autowired
	@Qualifier("facturaReposiory")
	private FacturaReposiory facturaReposiory;
	
	@Autowired
	@Qualifier("parqueaderoRepository")
	private ParqueaderoRepository parqueaderoRepository;

	@Override
	public void agregarVehiculo(VehiculoModel vehiculoModel, int idParqueadero) {
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		
		if(vehiculoModel.getTipoVehiculo().equals(CARRO)) {
			parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()-1);
		}else {
			parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()-1);
		}
		
		parqueaderoRepository.save(parqueadero);
		comenzarFactura(vehiculoModel);
		
		/*LOG.info("METHOD: addContact() -- PARAMS: " + vehiculoModel.toString());
		if(vigilanteEntrada.verificarPlaca(vehiculoModel, dia) && vigilanteEntrada.verificarDisponibilidad(CARRO)) {		
			vigilanteEntrada.addVehiculo(vehiculoModel, PARQUEADERO);
			//LOG.info("Carro ingresado");
		}else {
			//LOG.info("Acceso denegado");
		}*/
		
		/*LOG.info("METHOD: addVehiculo() inicia");
		
		vehiculoModel.parquearVehiculo(parqueadero, vehiculoModel);
		
		LOG.info("termina");
		*/
	}
	
	@Override
	public FacturaModel sacarVehiculo(VehiculoModel vehiculoModel, int idParqueadero) {
				
		LOG.info("METHOD: sacarVehiculo() inicializado");
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		Date horaSalida = new Date();
		
		FacturaEntity factura = buscarfactura(vehiculoModel);
		factura.setHoraSalida(horaSalida);
		
		int precio = precioTotal(factura, parqueadero);
		
		FacturaEntity facturaActualizada = actualizarFactura(factura, horaSalida, precio);
		
		LOG.info("Con la factura actualizada. " + facturaActualizada);
		
		if(factura.getTipoVehiculo().equals(CARRO)) {
			parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()+1);
		}else {
			parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()+1);
		}
		
		parqueaderoRepository.save(parqueadero);
		
		LOG.info(facturaReposiory.entity2model(facturaActualizada));
		
		return facturaReposiory.entity2model(facturaActualizada);
	}
	
	@Override
	public List<VehiculosAdentro> listarTodosLosVehiculos(){
		List<FacturaEntity> facturas = facturaReposiory.findByEstado(true);
		List<VehiculosAdentro> vehiculosAdentro = new ArrayList<>();
		for(FacturaEntity factura : facturas) {
			VehiculosAdentro vehiculo = new VehiculosAdentro
					(factura.getPlaca(), factura.getTipoVehiculo(), factura.getHoraIngreso());
			vehiculosAdentro.add(vehiculo);
		}
		return vehiculosAdentro;
	}
	
	@Override
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia){
		String placa = vehiculoModel.getPlaca();
        char primeraLetra = placa.charAt(0);
        if (primeraLetra == 'A') { 
        	return (DOMINGO == dia) || (LUNES == dia);        	
        }
        return true;
    }
	
	@Override
	public boolean verificarDisponibilidad(String tipoVehiculo) {
		return (numCeldasParqueadero(1, tipoVehiculo)!=0);
	}
	
	public int numCeldasParqueadero(int idParqueadero, String tipoVehiculo) {		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		if(CARRO.equals(tipoVehiculo)) {
			return parqueadero.getNumCeldasCarro();
		}else {
			return parqueadero.getNumCeldasMoto();
		}
		
	}
	
	@Override
	public void comenzarFactura(VehiculoModel vehiculoModel) {
		String tipoVehiculo = vehiculoModel.getTipoVehiculo(); 
		Date fechaEntrada = new Date();
		FacturaEntity factura = new FacturaEntity();
		factura.setEstado(true);
		factura.setPlaca(vehiculoModel.getPlaca());
		factura.setTipoVehiculo(tipoVehiculo);
		factura.setCilindraje(vehiculoModel.getCilindraje());
		factura.setHoraIngreso(fechaEntrada);
		facturaReposiory.save(factura);
	}

	@Override
	public int precioTotal(FacturaEntity factura, ParqueaderoEntity parqueaderoEntity) {
		int pagoTotal = 0;
		Date fechaEntrada = factura.getHoraIngreso();
		Date fechaSalida = factura.getHoraSalida();
		int timepoEstadia =  horasDeEstadia(fechaEntrada, fechaSalida);
		LOG.info("METHOD: horas que se introducen al metodo " + timepoEstadia);
		if(factura.getTipoVehiculo().equals(CARRO)) {
			pagoTotal += parqueaderoEntity.getPrecioDiaCarro()*numeroDiasQueEstuvo(timepoEstadia);
			pagoTotal += parqueaderoEntity.getPrecioHoraCarro()*numeroHorasExtra(timepoEstadia);
		}else {
			if(factura.getCilindraje() >= MAX_CILINDRAJE) {
				pagoTotal += ADDICION_CILINDRAJE;
			}
			pagoTotal += parqueaderoEntity.getPrecioDiaMoto()*numeroDiasQueEstuvo(timepoEstadia);
			pagoTotal += parqueaderoEntity.getPrecioHoraMoto()*numeroHorasExtra(timepoEstadia);
		}
		return pagoTotal;
	}
	
	public int numeroDiasQueEstuvo(int horas){
        int aux = 0;
        if (horas%HORAS_DEL_DIA >= HORAS_MAX){
            aux = 1;
        }
        LOG.info("METHOD: numero de dias que estuvo: " + (horas/HORAS_DEL_DIA) + "-" + aux);
        return (horas/HORAS_DEL_DIA) + aux;
	}

	public int numeroHorasExtra(int horas){
        if (horas%HORAS_DEL_DIA < HORAS_MAX){
        	LOG.info("METHOD: numero de horas por fuera de dias: " + horas %HORAS_DEL_DIA	);
            return horas %HORAS_DEL_DIA;
        }else{
            return 0;
        }
	}
	
	public FacturaEntity buscarfactura(VehiculoModel vehiculoModel) {
		String placa = vehiculoModel.getPlaca();
		return facturaReposiory.findByPlacaAndEstado(placa, true);
	}
	
	public FacturaEntity actualizarFactura(FacturaEntity facturaEntity, Date horaSalida, int pagoTotal) {		
		facturaEntity.setHoraSalida(horaSalida);
		facturaEntity.setEstado(false);
		facturaEntity.setPagoTotal(pagoTotal);
		facturaReposiory.save(facturaEntity);
		return facturaEntity;
	}
	
	public int horasDeEstadia(Date fechaEntrada, Date fechaSalida) {
		LOG.info("METHOD: tiempoEstadia() -- PARAMS: fecha entrada" + fechaEntrada);
		LOG.info("METHOD: tiempoEstadia() -- PARAMS: fecha salida" + fechaSalida);
		Long fechaEntradaMilisegundos = fechaEntrada.getTime();
		Long fechaSalidaMilisegundos = fechaSalida.getTime();
		Long horasAdentro = (fechaSalidaMilisegundos - fechaEntradaMilisegundos)/MILISEGUNDOS_EN_HORA;
		
		Long horasModulo = (fechaSalidaMilisegundos - fechaEntradaMilisegundos)%MILISEGUNDOS_EN_HORA;
		
		if(horasModulo != 0) {
			horasAdentro++;
		}
		
		return horasAdentro.intValue();
		
	}
	
	
}
