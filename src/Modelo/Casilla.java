package Modelo;

import java.util.ArrayList;

import java.util.List;

public class Casilla {

	 private int posicion;
	 private int indice;
	 private String tipo;

		    public Casilla(int indice) {
		        this.indice = indice;
		        this.tipo = "normal"; 
		    }

		    public int getIndice() {
		        return indice;
		    }

		    public String getTipocasilla() {
		        return tipo;
		    }

		    public void setTipocasilla(String tipo) {
		        this.tipo = tipo;
		    }

	    public int getPosicion() {
	        return posicion;
	    }

	    public void setPosicion(int posicion) {
	        this.posicion = posicion;
	    }

	    private List<Pinguino> jugadores = new ArrayList<>();

	    public List<Pinguino> getJugadores() {
	        return jugadores;
	    }

}
