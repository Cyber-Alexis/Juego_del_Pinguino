package Modelo;

public class CasillaAgujero {

    private int posicion;

    public CasillaAgujero(int posicion) {
        this.posicion = posicion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void caer(Jugador jugador) {
        // Este apartado servira para enviar al jugador al agujero anterior
        jugador.setPosicion(jugador.getPosicion() - 1); 
	}
}