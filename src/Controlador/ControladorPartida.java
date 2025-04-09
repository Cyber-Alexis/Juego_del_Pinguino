package Controlador;

import Modelo.Pinguino;
import Modelo.Partida;
import java.util.List;

public class ControladorPartida {
    private Partida partida;

    public ControladorPartida() {
        this.partida = new Partida();
    }

    public void agregarJugador(String nombre, String color) {
        partida.getJugadores().add(new Jugador(nombre, color));
        System.out.println("Jugador " + nombre + " añadido con color " + color);
    }

    public void iniciarPartida() {
        System.out.println("¡La partida ha comenzado!");
    }

    public void turnoJugador(Jugador jugador, int dado) {
        System.out.println("Turno de " + jugador.getNombre());
        int nuevaPosicion = jugador.getPosicion() + dado;
        System.out.println(jugador.getNombre() + " lanza el dado y avanza " + dado + " casillas.");
        partida.getTablero().moverJugador(jugador, nuevaPosicion);
    }

    public List<Jugador> obtenerJugadores() {
        return partida.getJugadores();
    }
}