package Vista;

import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class pantallaJuegoController {
	
	private int numBolasNieve = 0;
	private int numPeces = 0;
	private final int MAX_BOLAS_NIEVE = 6;
	private final int MAX_PECES = 6;
	
    // Menu items
    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    // Buttons
    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    // Texts
    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    // Game board and player pieces
    @FXML private GridPane tablero;
    @FXML private Circle P1;
    @FXML private Circle P2;
    @FXML private Circle P3;
    @FXML private Circle P4;

    // Variables del juego
    private int p1Position = 0;
    private final int COLUMNS = 5;
    private boolean tienePez = false;

    private static final int NUMERO_CASILLAS = 50;
    private TipoCasilla[] casillas = new TipoCasilla[NUMERO_CASILLAS]; // 5x10 = 50 casillas
    private IntegerProperty numDadosRapidos = new SimpleIntegerProperty(0);
    private IntegerProperty numDadosLentos = new SimpleIntegerProperty(0);

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");
        inicializarCasillas();
    }

    private void inicializarCasillas() {
        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = TipoCasilla.NORMAL;
        }
        
        //Hacer lo mismo con las demas
        colocarCasillas(TipoCasilla.INTERROGANTE,4);
        colocarCasillas(TipoCasilla.OSO,2);
        colocarCasillas(TipoCasilla.TRINEO,5);
        colocarCasillas(TipoCasilla.AGUJERO,5);
        casillas[0] = TipoCasilla.NORMAL;
        casillas[49] = TipoCasilla.META;
        
        mostrarImagenes();
    }
    private Random r= new Random();
    private void colocarCasillas (TipoCasilla tipo, int cantidad) {
    	for (int i = 0; i < cantidad; i++) {
    		int posicion;
    		
    		do {
    			posicion = r.nextInt(casillas.length -1) + 1;
    		}while(casillas[posicion] != TipoCasilla.NORMAL);
    		casillas[posicion] = tipo;
    	}
    }
    
    @FXML
    private void handleDado(ActionEvent event) {
        Random rand = new Random();
        int diceResult = rand.nextInt(6) + 1;
        dadoResultText.setText("Ha salido: " + diceResult);
        moveP1(diceResult);
    }

    private void moveP1(int steps) {
        p1Position += steps;
        if (p1Position >= 50) p1Position = 49;

        TipoCasilla tipo = casillas[p1Position];

        switch (tipo) {
        case AGUJERO:
            int nuevaPos = 0;
            for (int i = p1Position - 1; i >= 0; i--) {
                if (casillas[i] == TipoCasilla.AGUJERO) {
                    nuevaPos = i;
                    break;
                }
            }
            eventos.setText("¡Caíste en un agujero! Retrocedes hasta el agujero anterior.");
            p1Position = nuevaPos;
            break;
            
        case INTERROGANTE:
            int prob = r.nextInt(100); // Valor entre 0 y 99

            if (prob < 35) { // 0-34: dado lento (35%)
                if (numDadosLentos.get() < 3) {
                    numDadosLentos.set(numDadosLentos.get() + 1);
                    eventos.setText("¡Interrogante! Obtuviste un dado lento. 🐢 Total: " + numDadosLentos.get());
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir un dado lento, pero ya tienes el máximo.");
                }

            } else if (prob < 60) { // 35-59: bola de nieve (25%)
                if (numBolasNieve < MAX_BOLAS_NIEVE) {
                    numBolasNieve++;
                    eventos.setText("¡Interrogante! Encontraste una bola de nieve. ❄️ Total: " + numBolasNieve);
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir una bola de nieve, pero ya tienes el máximo.");
                }

            } else if (prob < 85) { // 60-84: pez (25%)
                if (numPeces < MAX_PECES) {
                    numPeces++;
                    tienePez = true;
                    eventos.setText("¡Interrogante! Obtuviste un pez. 🐟 Total: " + numPeces);
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir un pez, pero ya tienes el máximo.");
                }

            } else { // 85-99: dado rápido (15%)
                if (numDadosRapidos.get() < 3) {
                    numDadosRapidos.set(numDadosRapidos.get() + 1);
                    eventos.setText("¡Interrogante! Obtuviste un dado rápido. 🎲💨 Total: " + numDadosRapidos);
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir un dado rápido, pero ya tienes el máximo.");
                }
            }
            break;

            case OSO:
                if (tienePez) {
                    eventos.setText("¡Te salvaste del oso con un pez! 🐟");
                    tienePez = false;
                } else {
                    eventos.setText("¡El oso te atrapó! Vuelves al inicio.");
                    p1Position = 0;
                }
                break;

            case TRINEO:
                int nuevaPosTrineo = p1Position; // por defecto se queda donde está
                for (int i = p1Position + 1; i < casillas.length; i++) {
                    if (casillas[i] == TipoCasilla.TRINEO) {
                        nuevaPosTrineo = i;
                        break;
                    }
                }
                if (nuevaPosTrineo != p1Position) {
                    eventos.setText("¡Subiste a un trineo! Avanzas hasta el siguiente trineo.");
                    p1Position = nuevaPosTrineo;
                } else {
                    eventos.setText("¡Subiste a un trineo, pero no hay más adelante!");
                }
                break;                  
                
            case META:
                eventos.setText("¡Felicidades! Has llegado a la meta 🏁");
                break;

            default:
                eventos.setText("Avanzaste a una casilla normal.");
                break;
        }

        int row = p1Position / COLUMNS;
        int col = p1Position % COLUMNS;
        GridPane.setRowIndex(P1, row);
        GridPane.setColumnIndex(P1, col);
    }

    @FXML
    private void handlePeces() {
        tienePez = true;
        eventos.setText("¡Conseguiste un pez! 🐟");
    }

    @FXML
    private void handleRapido() {
        Random rand = new Random();
        if (numDadosRapidos.get() > 0 && numDadosRapidos.get() < 3) {
            int diceResult = rand.nextInt(3) + 4; // 4, 5 o 6
            dadoResultText.setText("Ha salido: " + diceResult);
            moveP1(diceResult);
            numDadosRapidos.set(numDadosRapidos.get() - 1);
        } else {
            eventos.setText("¡No tienes dados rápidos disponibles! 🎲💨");
        }
    }

    @FXML
    private void handleLento() {
    	Random rand = new Random();
    	if(numDadosLentos.get() > 0 && numDadosLentos.get() < 3) {
    		int diceResult = rand.nextInt(3) + 1; // 1, 2 o 3
            dadoResultText.setText("Ha salido: " + diceResult);
            moveP1(diceResult);
            numDadosLentos.set(numDadosLentos.get() - 1);
    	 } else {
             eventos.setText("¡No tienes dados lentos disponibles! 🎲💨");
         }
     }
    @FXML
    private void handleNieve() {
        eventos.setText("Usaste el botón nieve.");
    }

    @FXML
    private void handleNewGame() {
        eventos.setText("Nuevo juego iniciado.");
        p1Position = 0;
        tienePez = false;
        inicializarCasillas();
        GridPane.setRowIndex(P1, 0);
        GridPane.setColumnIndex(P1, 0);
    }

    @FXML
    private void handleSaveGame() {
        eventos.setText("Guardado de partida (no implementado).");
    }

    @FXML
    private void handleLoadGame() {
        eventos.setText("Cargado de partida (no implementado).");
    }

    @FXML
    private void handleQuitGame() {
        eventos.setText("Saliendo del juego...");
        System.exit(0);
    }

    // Enum interno (incluido en el mismo archivo)
    private enum TipoCasilla {
        NORMAL,
        AGUJERO,
        INTERROGANTE,
        OSO,
        TRINEO,
        META
    }
    private void mostrarImagenes(){
        for(int i = 0; i < casillas.length; i++) {
            if(casillas[i] == TipoCasilla.AGUJERO){
                int row = i / COLUMNS;
                int col = i % COLUMNS;
                Image image = new Image(getClass().getResource("/Resources/agujero.png").toExternalForm());
                ImageView imageAgujero = new ImageView(image);
                imageAgujero.setFitWidth(40);
                imageAgujero.setFitHeight(40);
                imageAgujero.setPreserveRatio(true);
                tablero.add(imageAgujero, col, row);
            } else if(casillas[i] == TipoCasilla.INTERROGANTE){
                int row = i / COLUMNS;
                int col = i % COLUMNS;
                Image image = new Image(getClass().getResource("/Resources/interrogante.png").toExternalForm());
                ImageView imageInterrogante = new ImageView(image);
                imageInterrogante.setFitWidth(40);
                imageInterrogante.setFitHeight(40);
                imageInterrogante.setPreserveRatio(true);
                tablero.add(imageInterrogante, col, row);
            } else if(casillas[i] == TipoCasilla.OSO){
                int row = i / COLUMNS;
                int col = i % COLUMNS;
                Image image = new Image(getClass().getResource("/Resources/oso.png").toExternalForm());
                ImageView imageOso = new ImageView(image);
                imageOso.setFitWidth(40);
                imageOso.setFitHeight(40);
                imageOso.setPreserveRatio(true);
                tablero.add(imageOso, col, row);
            } else if(casillas[i] == TipoCasilla.TRINEO){
                int row = i / COLUMNS;
                int col = i % COLUMNS;
                Image image = new Image(getClass().getResource("/Resources/trineo.png").toExternalForm());
                ImageView imageTrineo = new ImageView(image);
                imageTrineo.setFitWidth(40);
                imageTrineo.setFitHeight(40);
                imageTrineo.setPreserveRatio(true);
                tablero.add(imageTrineo, col, row);
            } else if(casillas[i] == TipoCasilla.META){
                int row = i / COLUMNS;
                int col = i % COLUMNS;
                Image image = new Image(getClass().getResource("/Resources/meta.png").toExternalForm());
                ImageView imageMeta = new ImageView(image);
                imageMeta.setFitWidth(40);
                imageMeta.setFitHeight(40);
                imageMeta.setPreserveRatio(true);
                tablero.add(imageMeta, col, row);
    		}
    	}
    }
}