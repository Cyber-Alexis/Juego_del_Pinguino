package Modelo;

public class Inventario {
	private int idInventario;
    private int dados; // Cantidad de dados que tiene
	    private int DadoRapido;
	    private int DadoLento;
    private int peces; // Cantidad de peces que tiene
    private int bolasDeNieve; // Cantidad de bolas de nieve que tiene
    
    // Devuelve la cantidad de dados
    public int getDados() {
        return dados;
    }

    // Establece la cantidad de dados
    public void setDados(int dados) {
        this.dados = dados;
    }
    
    public int getDadoRapido() {
        return DadoRapido;
    }
    
    public void setDadoRapido(int DadoRapi) {
    	this.DadoRapido = DadoRapi;
    }

    public void obtenerDadoRapido(int cantidad) {
        this.DadoRapido += cantidad;
    }
    public int getDadoLento() {
        return DadoLento;
    }
    
    public void setDadoLento(int DadoLent) {
    	this.DadoLento = DadoLent;
    }
    
    public void obtenerDadoLento(int cantidad) {
        this.DadoLento += cantidad;
    }
    
    // Devuelve la cantidad de peces
    public int getPeces() {
        return peces;
    }

    // Establece la cantidad de peces
    public void setPeces(int peces) {
        this.peces = peces;
    }
    
    // Incrementa en 1 el número de peces
    public void obtenerPez() {
        this.peces++;
    }

    // Devuelve la cantidad de bolas de nieve
    public int getBolasDeNieve() {
        return bolasDeNieve;
    }

    // Establece la cantidad de bolas de nieve
    public void setBolasDeNieve(int bolasDeNieve) {
        this.bolasDeNieve = bolasDeNieve;
    }
    
    // Aumenta las bolas de nieve en la cantidad indicada
    public void obtenerbolasdeNieve(int cantidad) {
        this.bolasDeNieve += cantidad;
    }
    
    // Constructor que inicializa el inventario vacío (sin peces ni bolas de nieve)
    public Inventario() {
        this.peces = 0;
        this.bolasDeNieve = 0;
        this.DadoRapido = 0;
        this.DadoLento = 0;
    }
    
    // Devuelve la cantidad de dados
    public int getIdInventario() {
    	return idInventario;
    }
    
    // Establece la cantidad de dados
    public void setIdInventario(int idInventario) {
    	this.idInventario = idInventario;
    }
    
    // Gasta una bola de nieve si hay disponible
    public void gastarboladeNieve(int i) {
        if (bolasDeNieve > 0) {
            bolasDeNieve--;
            System.out.println("¡Bola de nieve usada!");
        } else {
            System.out.println("No tienes bolas de nieve para usar.");
        }
    }

	
	
		
	}
