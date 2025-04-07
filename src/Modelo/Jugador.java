package Modelo;

public class Jugador {

	    private String nombre;
	    private String color;
	    private int posicion;
	    private Inventario inventario;

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getColor() {
	        return color;
	    }

	    public void setColor(String color) {
	        this.color = color;
	    }

	    public int getPosicion() {
	        return posicion;
	    }

	    public void setPosicion(int posicion) {
	        this.posicion = posicion;
	    }

	    public Inventario getInventario() {
	        return inventario;
	    }

	    public void setInventario(Inventario inventario) {
	        this.inventario = inventario;
	    }
	    
	    private Casilla casilladelPingu;

	    public Casilla getCasillaActual() {
	        return casilladelPingu;
	    }

	    public void setCasillaActual(Casilla casillaActual) {
	        this.casilladelPingu = casillaActual;
	      
	        this.posicion = casillaActual.getPosicion();
	    }

	}