package com.parqueadero.services.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.parqueadero.converters.CarroConverter;
import com.parqueadero.converters.MotoConverter;
import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.repositories.CarroRepository;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.MotoRepository;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.services.VigilanteService;

@Service("meterVehiculoService")
public class MeterVehiculoService implements VigilanteService{
	
	private static final Log LOG = LogFactory.getLog(MeterVehiculoService.class);
	private static final int DOMINGO = 1;
	private static final int LUNES = 2;
	private static final String CARRO = "Carro";
	private static final int MAX_CILINDRAJE = 500;
	private static final int ADDICION_CILINDRAJE = 2000;
	
	@Autowired
	@Qualifier("carroRepository")
	private CarroRepository carroRepository;
	
	@Autowired
	@Qualifier("motoRepository")
	private MotoRepository motoRepository;
	
	@Autowired
	@Qualifier("facturaReposiory")
	private FacturaReposiory facturaReposiory;
	
	@Autowired
	@Qualifier("parqueaderoRepository")
	private ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	@Qualifier("carroConverter")
	private CarroConverter carroConverter;
	
	@Autowired
	@Qualifier("motoConverter")
	private MotoConverter motoConverter;

	@Override
	public void addVehiculo(VehiculoModel vehiculoModel, int idParqueadero) {
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		
		if(vehiculoModel.getTipoVehiculo().equals(CARRO)) {
			parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()-1);
			carroRepository.save(carroConverter.model2entity(vehiculoModel));
		}else {
			parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()-1);
			motoRepository.save(motoConverter.model2entity(vehiculoModel));
		}
		
		parqueaderoRepository.save(parqueadero);
		comenzarFactura(vehiculoModel);
		
		/*LOG.info("METHOD: addVehiculo() inicia");
		
		vehiculoModel.parquearVehiculo(parqueadero, vehiculoModel);
		
		LOG.info("termina");
		*/
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
		return (celdasParqueadero(1, tipoVehiculo)!=0);
	}
	
	public int celdasParqueadero(int idParqueadero, String tipoVehiculo) {		
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
		factura.setHoraIngreso(fechaEntrada);
		facturaReposiory.save(factura);
	}

	@Override
	public int precioTotal(VehiculoModel vehiculoModel, ParqueaderoEntity parqueaderoEntity) {
		int pagoTotal = 0;
		FacturaEntity factura = buscarfactura(vehiculoModel);
		Date fechaEntrada = factura.getHoraIngreso();
		Date fechaSalida = new Date();

		pagoTotal = generarCobro(fechaEntrada, fechaSalida, vehiculoModel, parqueaderoEntity);
		return pagoTotal;
	}
	
	public int generarCobro(Date fechaEntrada, Date fechaSalida, 
			VehiculoModel vehiculoModel, ParqueaderoEntity parqueaderoEntity) {
		int pagoTotal = 0;
		int timepoEstadia =  horasDeEstadia(fechaEntrada, fechaSalida);
		LOG.info("METHOD: horas que se introducen al metodo " + timepoEstadia);
		if(vehiculoModel.getTipoVehiculo().equals(CARRO)) {
			pagoTotal += parqueaderoEntity.getPrecioDiaCarro()*numeroDiasQueEstuvo(timepoEstadia);
			pagoTotal += parqueaderoEntity.getPrecioHoraCarro()*numeroHorasExtra(timepoEstadia);
		}else {
			if(vehiculoModel.getCilindraje() >= MAX_CILINDRAJE) {
				pagoTotal += ADDICION_CILINDRAJE;
			}
			pagoTotal += parqueaderoEntity.getPrecioDiaMoto()*numeroDiasQueEstuvo(timepoEstadia);
			pagoTotal += parqueaderoEntity.getPrecioHoraMoto()*numeroHorasExtra(timepoEstadia);
		}
		return pagoTotal;
	}
	
	public int numeroDiasQueEstuvo(int horas){
        int aux = 0;
        if (horas%24 > 9){
            aux = 1;
        }
        LOG.info("METHOD: numero de dias que estuvo: " + (horas/24) + aux);
        return (horas/24) + aux;
	}

	public int numeroHorasExtra(int horas){
        if (horas%24 < 9){
        	LOG.info("METHOD: numero de horas por fuera de dias: " + horas %24	);
            return horas %24;
        }else{
            return 0;
        }
	}
	
	public FacturaEntity buscarfactura(VehiculoModel vehiculoModel) {
		String placa = vehiculoModel.getPlaca();
		return facturaReposiory.findByPlacaAndEstado(placa, true);
	}
	
	public int horasDeEstadia(Date fechaEntrada, Date fechaSalida) {
		LOG.info("METHOD: tiempoEstadia() -- PARAMS: fecha entrada" + fechaEntrada);
		LOG.info("METHOD: tiempoEstadia() -- PARAMS: fecha salida" + fechaSalida);
		Long fechaEnHorasIngreso = date2hours(fechaEntrada);
		Long fechaEnHorasSalida = date2hours(fechaSalida);
		return fechaEnHorasSalida.intValue() - fechaEnHorasIngreso.intValue();
	}
	
	public Long date2hours(Date fecha) {
		LOG.info("METHOD: date2hours() inicia");
		Long fechaEnHoras;
		Long fechaEnMilisegundos = fecha.getTime();
		if(fechaEnMilisegundos%3600000 == 0){
			fechaEnHoras = fechaEnMilisegundos/3600000;
		}else {
			fechaEnHoras = (fechaEnMilisegundos/3600000) + 1;
		}
		LOG.info("METHOD: date2hours() -- PARAMS: fecha en milisegundos:" + fechaEnMilisegundos);
		LOG.info("METHOD: date2hours() -- PARAMS: fecha en horas:" + fechaEnHoras);
		return fechaEnHoras;
	}

}
