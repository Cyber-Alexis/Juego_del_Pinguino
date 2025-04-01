package Controlador;

public class ControladordeTablero {

	 public ControladordeTablero(Tablero tablero) {
	        this.tablero = tablero;
	    }
	    
	    public void actualizarTablero() {
	     
	        tablero.generarCasillas();
	        System.out.println("Tablero actualizado.");
	    }
	}