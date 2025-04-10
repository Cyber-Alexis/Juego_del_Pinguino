package Modelo;

import java.util.ArrayList;
import java.util.List;
public class Tablero {

	    private int numCasillas;
	    private List<Casilla> casillas;
	    private List<Pinguino> jugadores;
	    
	    // Constructor Tablero
	    public Tablero(int numCasillas) {
	        this.numCasillas = numCasillas;
	        this.casillas = new ArrayList<>();
	        this.jugadores = new ArrayList<>();
	        inicializarCasillas();
	    }
	    
	    //Getters y Setters
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

	    public List<Pinguino> getJugadores() {
	        return jugadores;
	    }

	    public void setJugadores(List<Pinguino> jugadores) {
	        this.jugadores = jugadores;
	    }
	    
	    //Funciones
	    
	    public void inicializarCasillas() {
	        for (int i = 0; i < numCasillas; i++) {
	        	 Casilla casilla = new Casilla(i);  // Supone que Casilla tiene un constructor con índice
	            
	         // Asignar tipos de casillas especiales según el índice
	            if (i % 10 == 3) {
	                casilla.setTipocasilla("agujero");
	            } else if (i % 15 == 5) {
	                casilla.setTipocasilla("oso");
	            } else if (i % 12 == 7) {
	                casilla.setTipocasilla("interrogante");
	            } else if (i % 20 == 9) {
	                casilla.setTipocasilla("trineo");
	            }

	            casillas.add(casilla);
	        }
	        
	    }

	    public void agregarJugador(Pinguino jugador) {
	        jugadores.add(jugador);
	        jugador.setPosicion(0);  // Todos empiezan en la primera casilla
	    }

	    public void mostrarTablero() {
	        System.out.println("Jugadores en el tablero:");
	        for (Pinguino j : jugadores) {
	            int pos = j.getPosicion();
	            if (pos == 0) {
	                System.out.println("- " + j.getNombre() + " está al inicio.");
	            } else if (pos == numCasillas - 1) {
	                System.out.println("- " + j.getNombre() + " llegó al final.");
	            } else {
	                System.out.println("- " + j.getNombre() + " está en la casilla " + pos + ".");
	            }
	        }
	    }
	}
