package Modelo;

import java.util.Random;

public class CasillaInterrogante {
    private int posicion;

    // Constructor que asigna la posición de la casilla interrogante
    public CasillaInterrogante(int posicion) {
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
}