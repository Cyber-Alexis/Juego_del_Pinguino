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
		 if (partidaTerminada) return;
	        
	        Pinguino jugadorActual = jugadores.get(turnoActual);
	        System.out.println("Turno de " + jugadorActual.getNombre());
	        
	        if (jugadorActual.isEsCPU()) {
	            turnoCPU(jugadorActual);
	        } else {
	            int resultadoDado = lanzarDado();
	            System.out.println(jugadorActual.getNombre() + " lanzó un " + resultadoDado);
	            turnoJugadorHumano(jugadorActual, resultadoDado);
	        }
	        
	        // Actualizar estado de la partida para persistencia
	        actualizarEstadoPartida();
	        
	        // Pasar al siguiente turno
	        turnoActual = (turnoActual + 1) % jugadores.size();
	    }
	    
	    private void turnoJugadorHumano(Pinguino jugador, int resultadoDado) {
	        int nuevaPosicion = jugador.getPosicion() + resultadoDado;
	        
	        // Mover jugador y aplicar efectos de casilla
	        tablero.moverJugador(jugador, nuevaPosicion);
	        
	        // Verificar si llegó al final
	        if (jugador.getPosicion() >= tablero.getNumCasillas() - 1) {
	            finalizarPartida(jugador);
	        }
	    }
	    
	    private void turnoCPU(Pinguino cpu) {
	        int resultadoDado = lanzarDado();
	        int nuevaPosicion = cpu.getPosicion() + resultadoDado;
	        
	        System.out.println("CPU lanzó un " + resultadoDado);
	        tablero.moverJugador(cpu, nuevaPosicion);
	        
	        // La CPU puede atacar a jugadores humanos
	        for (Pinguino jugador : jugadores) {
	            if (!jugador.isEsCPU() && jugador.getPosicion() == cpu.getPosicion()) {
	                atacarJugador(cpu, jugador);
	            }
	        }
	    }
	    
	    private void atacarJugador(Pinguino atacante, Pinguino objetivo) {
	        System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre());
	        
	        if (objetivo.getInventario().getPeces() > 0) {
	            objetivo.getInventario().setPeces(objetivo.getInventario().getPeces() - 1);
	            System.out.println(objetivo.getNombre() + " usa un pez para defenderse");
	        } else {
	            objetivo.setPosicion(0); // Vuelve al inicio
	            System.out.println(objetivo.getNombre() + " es atacado y vuelve al inicio");
	        }
	    }
	    
	    private int lanzarDado() {
	        Random aleatorio = new Random();
	        return aleatorio.nextInt(6) + 1; // Dado de 1 a 6
	    }
	    
	    private void finalizarPartida(Pinguino ganador) {
	        partidaTerminada = true;
	        estado = "completada";
	        System.out.println(ganador.getNombre() + " ha ganado la partida!");
	    }
	    
	    private void actualizarEstadoPartida() {
	        // Actualiza los campos para persistencia
	        Pinguino jugadorActual = jugadores.get(turnoActual);
	        this.idCasillaActual = jugadorActual.getPosicion();
	        this.tipoCasillaActual = tablero.getCasillas().get(idCasillaActual).getTipocasilla();
	    }
	    
	    // Métodos para persistencia
	    public void guardarPartida() {
	        actualizarEstadoPartida();
	        GestorBaseDeDatos.actualizarPartida(numeroPartida, estado, idCasillaActual, tipoCasillaActual);
	        
	        // Guardar inventarios de los jugadores
	        for (Pinguino jugador : jugadores) {
	            if (!jugador.isEsCPU()) {
	                Inventario inv = jugador.getInventario();
	                GestorBaseDeDatos.actualizarInventario(
	                    jugador.getIdInventario(), 
	                    inv.getDados(), 
	                    inv.getPeces(), 
	                    inv.getBolasDeNieve()
	                );
	            }
	        }
	    }
	    
	    public void cargarPartida(int numeroPartida) {
	        // Implementar carga de partida desde BD
	    }
 
}