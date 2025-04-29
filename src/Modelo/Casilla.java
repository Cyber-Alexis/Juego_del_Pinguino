package Modelo; 

import java.util.ArrayList;
import java.util.List; 


public class Casilla {


    private int posicion; // Posición de la casilla en el tablero
    private int indice;   // Identificador único de la casilla
    private String tipo;  // Tipo de la casilla (por defecto "normal")
    private List<Pinguino> jugadores = new ArrayList<>(); // Lista de pingüinos (jugadores) en esta casilla

    // Constructor que inicializa una casilla con un índice específico
    public Casilla(int indice) {
        this.indice = indice;
        this.tipo = "normal"; // Por defecto todas las casillas son normales
    }

    // Método getter que devuelve el índice de la casilla
    public int getIndice() {
        return indice;
    }

    // Método getter que devuelve el tipo de la casilla
    public String getTipocasilla() {
        return tipo;
    }

    // Método setter que permite cambiar el tipo de la casilla
    public void setTipocasilla(String tipo) {
        this.tipo = tipo;
    }

    // Método getter que devuelve la posición de la casilla en el tablero
    public int getPosicion() {
        return posicion;
    }

    // Método setter que permite establecer la posición de la casilla
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    // Método getter que devuelve la lista de pingüinos en la casilla
    public List<Pinguino> getJugadores() {
        return jugadores;
    }

		public void activar(Pinguino jugador) {
			// TODO Auto-generated method stub
			
		}

			
		}

