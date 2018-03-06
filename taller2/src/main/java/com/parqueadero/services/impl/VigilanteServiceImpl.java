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
import com.parqueadero.validators.entrada.ValidacionesEntrada;
import com.parqueadero.validators.salida.ValidacionesSalida;

@Service("vigilanteService")
public class VigilanteServiceImpl implements VigilanteService{
	
	private static final Log LOG = LogFactory.getLog(VigilanteServiceImpl.class);
	private static final String CARRO = "Carro";
	private static final int MAX_CILINDRAJE = 500;
	private static final int ADDICION_CILINDRAJE = 2000;
	private static final int HORAS_DEL_DIA = 24;
	private static final int HORAS_MAX = 9;
	private static final int MILISEGUNDOS_EN_HORA = 3600000;
	
	List<ValidacionesEntrada> validacionesEntrada;
	List<ValidacionesSalida> validacionesSalida;
	
	@Autowired
	@Qualifier("facturaReposiory")
	private FacturaReposiory facturaReposiory;
	
	@Autowired
	@Qualifier("parqueaderoRepository")
	private ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	public VigilanteServiceImpl(List<ValidacionesEntrada> validacionesEntrada, List<ValidacionesSalida> validacionesSalida) {
		this.validacionesEntrada = validacionesEntrada;
		this.validacionesSalida = validacionesSalida;
	}

	@Override
	public void agregarVehiculo(VehiculoModel vehiculoModel, int idParqueadero) {
		
		validacionesEntrada.stream().forEach(validacion -> validacion.validar(vehiculoModel));
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		
		if(vehiculoModel.getTipoVehiculo().equals(CARRO)) {
			parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()-1);
		}else {
			parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()-1);
		}
		
		parqueaderoRepository.save(parqueadero);
		comenzarFactura(vehiculoModel);
		
	}
	
	@Override
	public FacturaModel sacarVehiculo(VehiculoModel vehiculoModel, int idParqueadero) {
		
		LOG.info("comienza la verificacion");
				
		Date horaSalida = new Date();
		
		validacionesSalida.stream().forEach(validacion -> validacion.validar(vehiculoModel));
		LOG.info("sale de la verificacion");
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);		
		
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
	public void comenzarFactura(VehiculoModel vehiculoModel) {
 
		Date fechaEntrada = new Date();
		
		FacturaEntity factura = new FacturaEntity();
		
		factura.setEstado(true);
		factura.setPlaca(vehiculoModel.getPlaca());
		factura.setTipoVehiculo(vehiculoModel.getTipoVehiculo());
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
