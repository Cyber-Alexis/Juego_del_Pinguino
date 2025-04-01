package Controlador;

class ControladorPartida {

	    private Partida partida;
	    
	    public ControladorPartida(Partida partida) {
	        this.partida = partida;
	    }
	    
	    public int obtenerNumeroJugadoresRegistrados() {
	        return partida.getNumeroJugadores();
	    }
	    
	    public int obtenerNumeroCasillasGeneradas() {
	        return partida.getTablero().getNumeroCasillas();
	    }
}