package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Dado {

	private List<Integer> valores;
	private Random aleatorio;
	
	public Dado(List<Integer> valores) {
		this.valores = valores;
		this.aleatorio = new Random();
	}

    public List<Integer> getValores() {
        return valores;
    }

    public void setValores(List<Integer> valores) {
        this.valores = valores;
    }
    
    // Para lanzar el dado
    public int lanzar() {
    	if (valores == null || valores.isEmpty()) {
    		return 0; // Devuelve 0 si en la lista no hay valores
    	}
    	
    	// Aleatoriamente seleccionamos un número entre 0 y el tamaño de la lista 'valores'.
    	int index = aleatorio.nextInt(valores.size());
    	return valores.get(index); // Se devuelve el numero que esta en la posición aleatoria de lista anterior
    }
    
    // Métodos para crear dados comunes
    public static Dado crearDadoComun() {
    	List<Integer> valoresComun = new ArrayList<>();
    	for (int i = 1; i <= 6; i++) { // El dado normal tiene un valor de entre el 1 y el 6
    		valoresComun.add(i);
    	}
    	// Creamos un nuevo Dado con la lista de valores y lo devolvemos
    	return new Dado(valoresComun);
    }
    
    // Métodos para crear dados rapidos
    public static Dado crearDadoRapido() {
    	List<Integer> valoresRapido = new ArrayList<>();
    	for (int i = 5; i <= 10; i++) { // El dado rapido tiene un valor de entre el 5 y el 10
    		valoresRapido.add(i);
    	}
    	// Creamos un nuevo Dado con la lista de valores y lo devolvemos
    	return new Dado(valoresRapido);
    }
    
    // Métodos para crear dados lentos
    public static Dado crearDadoLento() {
    	List<Integer> valoresLento = new ArrayList<>();
    	for (int i = 1; i <= 3; i++) { // El dado lento tiene un valor de entre el 1 y el 3
    		valoresLento.add(i);
    	}
    	// Creamos un nuevo Dado con la lista de valores y lo devolvemos
    	return new Dado(valoresLento);
    }

}
