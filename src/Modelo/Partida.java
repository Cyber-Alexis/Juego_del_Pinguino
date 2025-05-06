package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
	
	 // Atributos del modelo de dominio
	 private Tablero tablero;
	 private List<Pinguino> jugadores;
	 private int turnoActual;
	 private boolean partidaTerminada;
	 
	 // Atributos para persistencia en BD
	 private int numeroPartida;
	 private LocalDateTime fechaHora;
	 private String estado;
	 private int idCasillaActual;
	 private String tipoCasillaActual;
	 
	 // Constantes
	 private static final int NUM_CASILLAS = 50; // Según requerimientos del juego
	    
	 public Partida() {
		 this.jugadores = new ArrayList<>();
	     this.tablero = new Tablero(NUM_CASILLAS);
	     this.turnoActual = 0;
	     this.partidaTerminada = false;
	     this.estado = "en_progreso";
	     this.idCasillaActual = 0;
	     this.tipoCasillaActual = "inicio";
	    }
	    
	  // Constructor para cargar partida existente
	  public Partida(int numeroPartida, LocalDateTime fechaHora, String estado, int idCasilla, String tipoCasilla) {
		  this();
	      this.numeroPartida = numeroPartida;
	      this.fechaHora = fechaHora;
	      this.estado = estado;
	      this.idCasillaActual = idCasilla;
	      this.tipoCasillaActual = tipoCasilla;
	  }
	 
	 // Getters y Setters
	  
		 // Tablero
		 public Tablero getTablero() {
		     return tablero;
		 }
		
		 public void setTablero(Tablero tablero) {
		     this.tablero = tablero;
		 }
		 
		 // Jugadores
		 public List<Pinguino> getJugadores() {
		     return jugadores;
		 }
		
		 public void setJugadores(List<Pinguino> jugadores) {
		     this.jugadores = jugadores;
		 }
		 
		 // Turno Actual
		 public int getTurnoActual() {
		     return turnoActual;
		 }
		 
		 // Partida Terminada
		 public boolean isPartidaTerminada() {
		     return partidaTerminada;
		 }
		
		 // Numero Partida
		 public int getNumeroPartida() {
		     return numeroPartida;
		 }
		 
		 // Fecha y Hora
		 public LocalDateTime getFechaHora() {
		     return fechaHora;
		 }
		 
		 // Estado de la partida
		 public String getEstado() {
		     return estado;
		 }
		 
		 // Id de la Casilla actual
		 public int getIdCasillaActual() {
		     return idCasillaActual;
		 }
		 
		 // Tipo de casilla actual
		 public String getTipoCasillaActual() {
		     return tipoCasillaActual;
		 }
	 
	 //Funciones
	 public void agregarJugador(String nombre, String color) {
		 	Pinguino jugador = new Pinguino(nombre, color);
	        jugadores.add(jugador);
	        tablero.agregarJugador(jugador);
	        
	        // Asignar inventario inicial
	        Inventario inventario = new Inventario();
	        inventario.setDados(1); // 1 dado inicial
	        jugador.setInventario(inventario);
	 }
	 
	 public void agregarCPU() {
	        Pinguino cpu = new Pinguino("CPU", "GRIS");
	        cpu.setEsCPU(true);
	        jugadores.add(cpu);
	        tablero.agregarJugador(cpu);
	 }
	 
	 
	 public void jugarTurno() {
	        Random aleatorio = new Random();

	        for (Pinguino j : jugadores) {
	            int avance = aleatorio.nextInt(6) + 1;
	            int nuevaPos = j.getPosicion() + avance;

	            if (nuevaPos >= tablero.getNumCasillas()) {
	                nuevaPos = tablero.getNumCasillas() - 1;
	                System.out.println(j.getNombre() + " llegó al final con un avance de " + avance + " casillas.");
	            } else {
	                System.out.println(j.getNombre() + " avanzó " + avance + " casillas hasta la casilla " + nuevaPos + ".");
	            }

	            j.setPosicion(nuevaPos);
	        }
	    }
 
}