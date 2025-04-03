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
    
    public int lanzar() {
    	if (valores == null || valores.isEmpty()) {
    		return 0; // Devuelve 0 si no hay valores definidos
    	}
    	int index = aleatorio.nextInt(valores.size());
    	return valores.get(index);
    }
    
    // Métodos para crear dados comunes
    public static Dado crearDadoComun() {
    	List<Integer> valoresComun = new ArrayList<>();
    	for (int i = 1; i <= 6; i++) {
    		valoresComun.add(i);
    	}
    	return new Dado(valoresComun);
    }
    
    // Métodos para crear dados rapidos
    public static Dado crearDadoRapido() {
    	List<Integer> valoresRapido = new ArrayList<>();
    	for (int i = 5; i <= 10; i++) {
    		valoresRapido.add(i);
    	}
    	return new Dado(valoresRapido);
    }
    
    // Métodos para crear dados lentos
    public static Dado crearDadoLento() {
    	List<Integer> valoresLento = new ArrayList<>();
    	for (int i = 1; i <= 3; i++) {
    		valoresLento.add(i);
    	}
    	return new Dado(valoresLento);
    }

}
