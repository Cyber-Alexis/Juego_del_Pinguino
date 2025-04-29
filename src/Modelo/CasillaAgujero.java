package Modelo;

public class CasillaAgujero {
    private int posicion;

    // Constructor que asigna la posición de la casilla agujero
    public CasillaAgujero(int posicion) {
        this.posicion = posicion;
    }

    // Devuelve la posición de la casilla
    public int getPosicion() {
        return posicion;
    }

    // Establece una nueva posición para la casilla
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    // Hace que el jugador retroceda una posición al caer en el agujero
    public void afectarJugador(Pinguino jugador) {
        int nuevaPosicion = jugador.getPosicion() - 1; // Calcula la nueva posición (una casilla menos)
        if (nuevaPosicion >= 0) { // Se asegura de que no sea una posición negativa
            jugador.setPosicion(nuevaPosicion); // Actualiza la posición del jugador
            System.out.println(jugador.getNombre() + " ha caído en un agujero y retrocede a la casilla " + nuevaPosicion);
        }
    }
}