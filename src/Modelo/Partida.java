package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
	
	 private Tablero tablero;
	 private List<Pinguino> jugadores;
	
	 public Partida() {
	     jugadores = new ArrayList<Pinguino>();
	     tablero = new Tablero(20); //Tablero de 20 casillas
	 }
	 
	 //Getters y Setters
	 public Tablero getTablero() {
	     return tablero;
	 }
	
	 public void setTablero(Tablero tablero) {
	     this.tablero = tablero;
	 }
	
	 public List<Pinguino> getJugadores() {
	     return jugadores;
	 }
	
	 public void setJugadores(List<Pinguino> jugadores) {
	     this.jugadores = jugadores;
	     
	 }
	 
	 //Funciones
	 public void agregarJugador(String nombre) {
		 	Pinguino jugador = new Pinguino(nombre, nombre);
	        jugadores.add(jugador);
	        tablero.agregarJugador(jugador);
	    }
	 
	 public void jugarTurno() {
	        Random aleatorio = new Random();

	        for (Pinguino j : jugadores) {
	            int avance = aleatorio.nextInt(6) + 1;
	            int nuevaPos = j.getPosicion() + avance;

	            if (nuevaPos >= tablero.getNumCasillas()) {
	                nuevaPos = tablero.getNumCasillas() - 1;
	                System.out.println(j.getNombre() + " llegó al final con un avance de " + avance + " casillas.");
	            } else {
	                System.out.println(j.getNombre() + " avanzó " + avance + " casillas hasta la casilla " + nuevaPos + ".");
	            }

	            j.setPosicion(nuevaPos);
	        }
	    }
 
}