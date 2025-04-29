package Controlador;

import Modelo.Pinguino;
import Modelo.Inventario;

public class ControladordeJugador {
    // Mueve al jugador una cantidad determinada de casillas
    public void moverJugador(Pinguino jugador, int casillas) {
        jugador.setPosicion(jugador.getPosicion() + casillas); // Actualiza la posición del jugador
        System.out.println(jugador.getNombre() + " se ha movido a la casilla " + jugador.getPosicion());
    }
    
    // Permite al jugador usar un dado si tiene disponibles
    public void gastarDadoRapi(Pinguino jugador) {
        if (jugador.getInventario().getDadoRapido() > 0) { // Verifica si tiene dados disponibles
            jugador.getInventario().gastarDadoRapido(-1); // Se usa un dado
            System.out.println(jugador.getNombre() + " ha usado un dado.");
        } else {
            System.out.println("No tienes dados disponibles."); // Mensaje si no hay dados
        }
    }

    public void gastarDadoLent(Pinguino jugador) {
        if (jugador.getInventario().getDadoLento() > 0) { // Verifica si tiene dados disponibles
            jugador.getInventario().gastarDadoLento(-1); // Se usa un dado
            System.out.println(jugador.getNombre() + " ha usado un dado.");
        } else {
            System.out.println("No tienes dados disponibles."); // Mensaje si no hay dados
        }
    }
    
    // Permite al jugador lanzar una bola de nieve a otro jugador
    public void lanzarBolaDeNieve(Pinguino atacante, Pinguino objetivo) {
        if (atacante.getInventario().getBolasDeNieve() > 0) { // Verifica si el atacante tiene bolas de nieve
            atacante.getInventario().gastarboladeNieve(-1); // Resta una bola de nieve al atacante
            int nuevaPosicion = objetivo.getPosicion() - 2; // Retrocede al objetivo 2 casillas
            if (nuevaPosicion < 0) {
                nuevaPosicion = 0;  // Evita que la posición sea negativa
            }
            objetivo.setPosicion(nuevaPosicion); // Actualiza la posición del objetivo
            System.out.println(atacante.getNombre() + " lanza una bola de nieve a " + objetivo.getNombre() +
                    ", quien retrocede 2 casillas.");
        } else {
            System.out.println("No tienes bolas de nieve disponibles."); // Mensaje si no tiene bolas de nieve
        }
    }
}
