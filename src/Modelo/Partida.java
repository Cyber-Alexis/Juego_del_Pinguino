package Modelo;

import java.util.ArrayList;

public class Partida {
	
 private Tablero tablero;
 private List<Jugador> jugadores;

 public Partida() {
     this.tablero = new Tablero();
     this.jugadores = new ArrayList<>();
 }

 public Tablero getTablero() {
     return tablero;
 }

 public void setTablero(Tablero tablero) {
     this.tablero = tablero;
 }

 public List<Jugador> getJugadores() {
     return jugadores;
 }

 public void setJugadores(List<Jugador> jugadores) {
     this.jugadores = jugadores;
     
 }
 
}