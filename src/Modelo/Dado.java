package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dado {
	
	public Inventario inventario; // Accedemos al inventario
	
    private List<Integer> valores; // Lista de valores posibles que puede sacar el dado
    private Random aleatorio; // Generador de números aleatorios
    
    // Constructor que recibe una lista de valores para el dado
    public Dado(List<Integer> valores) {
        this.valores = valores;
        this.aleatorio = new Random();
    }

    // Devuelve la lista de valores del dado
    public List<Integer> getValores() {
        return valores;
    }

    // Establece una nueva lista de valores para el dado
    public void setValores(List<Integer> valores) {
        this.valores = valores;
    }

    // Lanza el dado y devuelve un valor aleatorio de la lista
    public int lanzar() {
        if (valores == null || valores.isEmpty()) {
            return 0; // Devuelve 0 si no hay valores disponibles
        }
        int index = aleatorio.nextInt(valores.size()); // Genera un índice aleatorio
        return valores.get(index); // Devuelve el valor correspondiente al índice
    }

    // Crea un dado normal con valores del 1 al 6
    public static Dado crearDadoComun() {
        List<Integer> valoresComun = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            valoresComun.add(i);
        }
        return new Dado(valoresComun);
    }

    // Crea un dado rápido con valores del 5 al 10
    public static Dado crearDadoRapido() {
        List<Integer> valoresRapido = new ArrayList<>();
        for (int i = 5; i <= 10; i++) {
            valoresRapido.add(i);
        }
        return new Dado(valoresRapido);
    }

    // Crea un dado lento con valores del 1 al 3
    public static Dado crearDadoLento() {
        List<Integer> valoresLento = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            valoresLento.add(i);
        }
        return new Dado(valoresLento);
    }
}
