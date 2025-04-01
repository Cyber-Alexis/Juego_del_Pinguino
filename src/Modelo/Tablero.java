package Modelo;

import java.util.ArrayList;

public class Tablero {

	    private int numCasillas;
	    private List<Casilla> casillas;
	    private List<Jugador> jugadores;

	    public int getNumCasillas() {
	        return numCasillas;
	    }

	    public void setNumCasillas(int numCasillas) {
	        this.numCasillas = numCasillas;
	    }

	    public List<Casilla> getCasillas() {
	        return casillas;
	    }

	    public void setCasillas(List<Casilla> casillas) {
	        this.casillas = casillas;
	    }

	    public List<Jugador> getJugadores() {
	        return jugadores;
	    }

	    public void setJugadores(List<Jugador> jugadores) {
	        this.jugadores = jugadores;
	    }
	}
