package com.example.demo;

public class Parqueadero {
	private int celdasCarro;
    private int celdasMoto;
    private int precioHoraCarro;
    private int precioHoraMoto;
    private int precioDiaCarro;
    private int precioDiaMoto;

    public Parqueadero() {
        this.celdasCarro = 20;
        this.celdasMoto = 10;
        this.precioHoraCarro = 1000;
        this.precioHoraMoto = 500;
        this.precioDiaCarro = 8000;
        this.precioDiaMoto = 4000;
    }

    public int getCeldasCarro() {
        return celdasCarro;
    }

    public int getCeldasMoto() {
        return celdasMoto;
    }

    public void setCeldasCarro(int celdasCarro) {
		this.celdasCarro = celdasCarro;
	}

	public void setCeldasMoto(int celdasMoto) {
		this.celdasMoto = celdasMoto;
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
}
