package Modelo;

import java.util.ArrayList;
import java.util.List;
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
	    
	    public Tablero(int numCasillas) {
	        this.numCasillas = numCasillas;
	        this.casillas = new ArrayList<>();
	        this.jugadores = new ArrayList<>();
	        inicializarCasillas();
	    }
	    
	    private void inicializarCasillas() {
	        for (int i = 0; i < numCasillas; i++) {
	            casillas.add(new Casilla(i));  // Supone que Casilla tiene un constructor con índice
	        }
	    }

	    public void agregarJugador(Jugador jugador) {
	        jugadores.add(jugador);
	        jugador.setPosicion(0);  // Todos empiezan en la primera casilla
	    }

	    public void mostrarTablero() {
	        System.out.println("Jugadores en el tablero:");
	        for (Jugador j : jugadores) {
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
