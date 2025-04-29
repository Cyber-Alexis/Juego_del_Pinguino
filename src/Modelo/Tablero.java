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
	    
	    private int buscarAgujeroAnterior(int posicion) {
	    	for (int i = posicion - 1; i >= 0; i--) {
	            if ("agujero".equals(casillas.get(i).getTipocasilla())) {
	                return i;
	            }
	        }
	        return 0; // Si no hay agujero anterior, vuelve al inicio
	    }	
	    
	    private int buscarSiguienteTrineo(int posicion) {
	        for (int i = posicion + 1; i < casillas.size(); i++) {
	            if ("trineo".equals(casillas.get(i).getTipocasilla())) {
	                return i;
	            }
	        }
	        return casillas.size() - 1; // Si no hay más trineos, va al final
	    }
	    
	    public void moverJugador(Pinguino jugador, int nuevaPosicion) {
	    	if (nuevaPosicion >= numCasillas) {
	            nuevaPosicion = numCasillas - 1; // No puede pasar del final del tablero
	        }
	    	
	    	jugador.setPosicion(nuevaPosicion); // Mueve al jugador
	    	
	    	 Casilla casillaActual = casillas.get(nuevaPosicion);
	    	 String tipo = casillaActual.getTipocasilla();
	    	 
	    	 switch (tipo) {
	    	 	case "agujero":
	    	 		// Busca el agujero anterior
	                int agujeroAnterior = buscarAgujeroAnterior(nuevaPosicion);
	                jugador.setPosicion(agujeroAnterior);
	                System.out.println(jugador.getNombre() + " cayó en un AGUJERO y retrocede a la casilla " + agujeroAnterior);
	                break;
	    	 	case "oso":
	                jugador.setPosicion(0);
	                System.out.println(jugador.getNombre() + " fue atrapado por un OSO y vuelve al INICIO.");
	                break;
	    	 	case "trineo":
	                int siguienteTrineo = buscarSiguienteTrineo(nuevaPosicion);
	                jugador.setPosicion(siguienteTrineo);
	                System.out.println(jugador.getNombre() + " usó un TRINEO y avanza hasta la casilla " + siguienteTrineo);
	                break;
	            case "interrogante":
	                System.out.println(jugador.getNombre() + " cayó en una casilla de INTERROGANTE. Se activa un evento aleatorio.");
	                // Aquí se puede enlazar con lógica de eventos
	                break;
	            default:
	                System.out.println(jugador.getNombre() + " se movió a la casilla " + nuevaPosicion + ".");
	        }
	    }
	}
