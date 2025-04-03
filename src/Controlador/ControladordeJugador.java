package Controlador;

public class ControladordeJugador {

	    private Jugador jugador;
	    
	    public ControladorJugador(Jugador jugador) {
	        this.jugador = jugador;
	    }
	    
	    public void guardarPartida() {
	    
	        System.out.println("Partida guardada para el jugador: " + jugador.getNombre());
	    }
	    
	    public void cargarPartida() {
	      
	        System.out.println("Partida cargada para el jugador: " + jugador.getNombre());
	    }
	    
	    public void iniciarPartida() {
	   
	        System.out.println("Partida iniciada para el jugador: " + jugador.getNombre());
	    }
	}