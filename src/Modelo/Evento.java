package Modelo;

import java.util.Random;

public class Evento {

    public static void activarEventoAleatorio(Pinguino jugador) {
        Random random = new Random();
        int evento = random.nextInt(100); // Usamos 100 para manejar probabilidades

        if (evento < 25) {
            // 25% de probabilidad  Obtener un pez
            jugador.getInventario().obtenerPez(1);
            System.out.println(jugador.getNombre() + " ha encontrado un pez!");
            
        } else if (evento < 50) {
            // 25%  Obtener entre 1 y 3 bolas de nieve
            int bolas = 1 + random.nextInt(3);
            jugador.getInventario().obtenerbolasdeNieve(bolas);
            System.out.println(jugador.getNombre() + " ha obtenido " + bolas + " bolas de nieve.");
            
        } else if (evento < 60) {
            // 10%  Obtener un dado rápido (avanza 5 a 10 casillas)
            int avance = 5 + random.nextInt(6); // 5 a 10
            jugador.setPosicion(jugador.getPosicion() + avance);
            System.out.println(jugador.getNombre() + " ha obtenido un dado rápido y avanza " + avance + " casillas.");
            
        } else {
            // 40%  Obtener un dado lento (1 a 3 casillas)
            int avance = 1 + random.nextInt(3); // 1 a 3
            jugador.setPosicion(jugador.getPosicion() + avance);
            System.out.println(jugador.getNombre() + " ha obtenido un dado lento y avanza " + avance + " casillas.");
        }
    }
}