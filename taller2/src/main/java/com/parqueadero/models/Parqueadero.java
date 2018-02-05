package com.parqueadero.models;

public class Parqueadero {
	
	private int numCeldasCarro;
    private int numCeldasMoto;
    private int precioHoraCarro;
    private int precioHoraMoto;
    private int precioDiaCarro;
    private int precioDiaMoto;
    
    public Parqueadero() {
		
	}

    public Parqueadero(int numCeldasCarro, int numCeldasMoto, int precioHoraCarro, int precioHoraMoto, int precioDiaCarro,
			int precioDiaMoto) {
		super();
		this.numCeldasCarro = numCeldasCarro;
		this.numCeldasMoto = numCeldasMoto;
		this.precioHoraCarro = precioHoraCarro;
		this.precioHoraMoto = precioHoraMoto;
		this.precioDiaCarro = precioDiaCarro;
		this.precioDiaMoto = precioDiaMoto;
	}

	public int getCeldasCarro() {
        return numCeldasCarro;
    }

    public int getCeldasMoto() {
        return numCeldasMoto;
    }

    public void setCeldasCarro(int numCeldasCarro) {
		this.numCeldasCarro = numCeldasCarro;
	}

	public void setCeldasMoto(int numCeldasMoto) {
		this.numCeldasMoto = numCeldasMoto;
	}

	public int getPrecioHoraCarro() {
        return precioHoraCarro;
    }

    public int getPrecioHoraMoto() {
        return precioHoraMoto;
    }

    public int getPrecioDiaCarro() {
        return precioDiaCarro;
    }

    public int getPrecioDiaMoto() {
        return precioDiaMoto;
    }

	@Override
	public String toString() {
		return "Parqueadero [numCeldasCarro=" + numCeldasCarro + ", numCeldasMoto=" + numCeldasMoto
				+ ", precioHoraCarro=" + precioHoraCarro + ", precioHoraMoto=" + precioHoraMoto + ", precioDiaCarro="
				+ precioDiaCarro + ", precioDiaMoto=" + precioDiaMoto + "]";
	}
    
}
