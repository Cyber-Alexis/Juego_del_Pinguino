package Controlador;

import Modelo.Tablero;
import Modelo.Pinguino;
import Modelo.Casilla;
import java.util.List;

public class ControladordeTabla {
    private Tablero tablero;

    public ControladordeTabla(Tablero tablero) {
        this.tablero = tablero;
    }

    public void inicializarTablero() {
        System.out.println("Inicializando el tablero con 50 casillas...");
        tablero.generarCasillas();
    }

    public void moverJugador(Pinguino jugador, int nuevaPosicion) {
        if (nuevaPosicion >= 50) {
            System.out.println(jugador.getNombre() + " ha llegado al final y gana la partida.");
        } else {
            jugador.setPosicion(nuevaPosicion);
            System.out.println(jugador.getNombre() + " se mueve a la casilla " + nuevaPosicion);
            Casilla casilla = tablero.getCasillas().get(nuevaPosicion);
            casilla.activar(jugador);
        }
    }
}