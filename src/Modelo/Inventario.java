package Modelo;

public class Inventario {
    private int dados;
    private int peces;
    private int bolasDeNieve;

    public int getDados() {
        return dados;
    }

    public void setDados(int dados) {
        this.dados = dados;
    }

    public int getPeces() {
        return peces;
    }

    public void setPeces(int peces) {
        this.peces = peces;
    }
    
    public void obtenerPez() {
        this.peces++;
    }

    public int getBolasDeNieve() {
        return bolasDeNieve;
    }

    public void setBolasDeNieve(int bolasDeNieve) {
        this.bolasDeNieve = bolasDeNieve;
    }
    
    public void obtenerbolasdeNieve(int cantidad) {
        this.bolasDeNieve += cantidad;
    }
    
    public Inventario() {
        this.peces = 0;
        this.bolasDeNieve = 0;
    }
    
    public void gastarboladeNieve() {
        if (bolasDeNieve > 0) {
            bolasDeNieve--;
            System.out.println("¡Bola de nieve usada!");
        } else {
            System.out.println("No tienes bolas de nieve para usar.");
        }
        
    }
    
}
