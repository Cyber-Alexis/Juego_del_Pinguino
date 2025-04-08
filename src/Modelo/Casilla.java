package Modelo;

import java.util.ArrayList;

import java.util.List;

public class Casilla {

	 private int posicion;

	    public int getPosicion() {
	        return posicion;
	    }

	    public void setPosicion(int posicion) {
	        this.posicion = posicion;
	    }

	    private List<Jugador> jugadores = new ArrayList<>();

	    public List<Jugador> getJugadores() {
	        return jugadores;
	    }

}
