package Modelo;

import java.util.List;

public class CasillaTrineo extends Casilla
{
    // Constructor que inicializa la casilla trineo usando el constructor de Casilla
    public CasillaTrineo(int posicion)
    {
        super(posicion);
    }

    // Método que se activa cuando un pingüino cae en un trineo
    @Override
    public void activar(Pinguino jugador)
    {
        System.out.println(jugador.getNombre() + " usa un trineo para avanzar.");
    }

    // Permite al jugador usar el trineo para avanzar al siguiente trineo más adelante en el tablero
    public void usar(Pinguino jugador, List<CasillaTrineo> trineos)
    {
        for (int i = 0; i < trineos.size(); i++)
        {
            CasillaTrineo t = trineos.get(i);
            if (t.getPosicion() > this.getPosicion()) // Busca un trineo en una posición mayor
            {
                jugador.setPosicion(t.getPosicion()); // Mueve al jugador a ese trineo
                System.out.println(jugador.getNombre() + " avanza al siguiente trineo en la posición " + t.getPosicion());
                break; // Solo avanza al primer trineo que encuentre
            }
        }
    }
}