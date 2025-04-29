package Modelo;

public class CasillaInterrogante extends Casilla {
    public CasillaInterrogante(int posicion) {
        super(posicion);
        setTipo("interrogante");
    }

    @Override
    public void activar(Pinguino jugador) {
        System.out.println(jugador.getNombre() + " ha caído en una casilla de interrogante...");
        Evento evento = Evento.generarEventoAleatorio();
        evento.aplicar(jugador);
    }
}