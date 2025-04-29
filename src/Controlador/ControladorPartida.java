package Controlador;

import Modelo.Pinguino;
import Modelo.Partida;
import java.util.List;

public class ControladorPartida {
    private Partida partida; // Instancia de la clase Partida que gestiona la partida

    // Constructor que inicializa una nueva partida
    public ControladorPartida() {
        this.partida = new Partida();
    }

    // Agrega un jugador a la partida con su nombre y color
    public void agregarJugador(String nombre, String color) {
        partida.getJugadores().add(new Pinguino(nombre, color)); // Se añade el nuevo jugador a la lista
        System.out.println("Jugador " + nombre + " añadido con color " + color);
    }

    // Inicia la partida mostrando un mensaje
    public void iniciarPartida() {
        System.out.println("¡La partida ha comenzado!");
    }

    // Maneja el turno de un jugador, avanzando según el valor del dado
    public void turnoJugador(Pinguino jugador, int dado) {
        System.out.println("Turno de " + jugador.getNombre());
        int nuevaPosicion = jugador.getPosicion() + dado; // Calcula la nueva posición del jugador
        System.out.println(jugador.getNombre() + " lanza el dado y avanza " + dado + " casillas.");
        partida.getTablero().moverJugador(jugador, nuevaPosicion); // Mueve al jugador en el tablero
    }

    // Devuelve la lista de jugadores de la partida
    public List<Pinguino> obtenerJugadores() {
        return partida.getJugadores();
    }
}