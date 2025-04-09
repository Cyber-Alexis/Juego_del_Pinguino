package Modelo;

import java.util.List;

public class CasillaTrineo extends Casilla
{
    public CasillaTrineo(int posicion)
    {
        super(posicion);
    }

    @Override
    public void activar(Pinguino jugador)
    {
        System.out.println(jugador.getNombre() + " usa un trineo para avanzar.");
    }

    public void usar(Pinguino jugador, List<CasillaTrineo> trineos)
    {
        for (int i = 0; i < trineos.size(); i++)
        {
            CasillaTrineo t = trineos.get(i);
            if (t.getPosicion() > this.getPosicion())
            {
                jugador.setPosicion(t.getPosicion());
                System.out.println(jugador.getNombre() + " avanza al siguiente trineo en la posición " + t.getPosicion());
                break;
            }
        }
    }
}