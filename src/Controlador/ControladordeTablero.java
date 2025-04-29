package Controlador;

import Modelo.Tablero;
import Modelo.Pinguino;
import Modelo.Casilla;
import java.util.List;

public class ControladordeTablero {
    private Tablero tablero; // Instancia de la clase Tablero que gestiona las casillas y el movimiento

    // Constructor que recibe un tablero y lo asigna
    public ControladordeTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    // Inicializa el tablero generando las casillas
    public void inicializarTablero() {
        System.out.println("Inicializando el tablero con 50 casillas...");
        tablero.generarCasillas(); // Genera las casillas en el tablero
    }

    // Mueve al jugador a una nueva posición y activa la casilla correspondiente
    public void moverJugador(Pinguino jugador, int nuevaPosicion) {
        if (nuevaPosicion >= 50) { // Si el jugador llega al final del tablero, gana
            System.out.println(jugador.getNombre() + " ha llegado al final y gana la partida.");
        } else {
            jugador.setPosicion(nuevaPosicion); // Actualiza la posición del jugador
            System.out.println(jugador.getNombre() + " se mueve a la casilla " + nuevaPosicion);
            Casilla casilla = tablero.getCasillas().get(nuevaPosicion); // Obtiene la casilla
            casilla.activar(jugador); // Activa la casilla y aplica su efecto
        }
    }
}