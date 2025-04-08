package Controlador;

import Modelo.Pinguino;
import Modelo.Inventario;

public class ControladordeJugador {
    public void moverJugador(Pinguino jugador, int casillas) {
        jugador.setPosicion(jugador.getPosicion() + casillas);
        System.out.println(jugador.getNombre() + " se ha movido a la casilla " + jugador.getPosicion());
    }
    
    public void usarDado(Pinguino jugador) {
        if (jugador.getInventario().getDados() > 0) {
            jugador.getInventario().agregarDado();
            System.out.println(jugador.getNombre() + " ha usado un dado.");
            
        } else {
            System.out.println("No tienes dados disponibles.");
        }
    }

    public void lanzarBolaDeNieve(Pinguino atacante, Pinguino objetivo) {
        if (atacante.getInventario().getBolasDeNieve() > 0) {
            atacante.getInventario().agregarBolasDeNieve(-1);
            int nuevaPosicion = objetivo.getPosicion() - 2;
            if (nuevaPosicion < 0) {
                nuevaPosicion = 0;  // No puede moverse a una posicion negativa
            }
            objetivo.setPosicion(nuevaPosicion);
            System.out.println(atacante.getNombre() + " lanza una bola de nieve a " + objetivo.getNombre() +
                    ", quien retrocede 2 casillas.");
            
        } else {
            System.out.println("No tienes bolas de nieve disponibles.");
        }
    }
}