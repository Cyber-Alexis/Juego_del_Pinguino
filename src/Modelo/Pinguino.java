package Modelo;

public class Pinguino {
    private String nombre;
    private String color;
    private Inventario inventario;
	private Casilla casilladelPingu;
	private int posicion;
	
    public Pinguino(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.posicion = 0;
        this.inventario = new Inventario();
    }

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
    
    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Casilla getCasillaActual() {
        return casilladelPingu;
    }

    public void setCasillaActual(Casilla casillaActual) {
        this.casilladelPingu = casillaActual;
      
        this.posicion = casillaActual.getPosicion();
    }
    
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
}


