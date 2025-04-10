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

    public void afectarJugador(Pinguino jugador) {
        // Si cae en un agujero retrocede un puesto
        int nuevaPosicion = jugador.getPosicion() - 1;
        if (nuevaPosicion >= 0) {
            jugador.setPosicion(nuevaPosicion);
            System.out.println(jugador.getNombre() + " ha caído en un agujero y retrocede a la casilla " + nuevaPosicion);
        }
    }
}