package Vista;

import java.util.Random;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class pantallaJuegoController {
	
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
    private IntegerProperty cantidadPeces = new SimpleIntegerProperty(0);
    private IntegerProperty cantidadNieve = new SimpleIntegerProperty(0);

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");
        inicializarCasillas();
    }

    private void inicializarCasillas() {
        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = TipoCasilla.NORMAL;
        }

        // Asignación aleatoria dentro de los rangos especificados
        colocarCasillas(TipoCasilla.INTERROGANTE, r.nextInt(6) + 1); // 1 a 6
        colocarCasillas(TipoCasilla.OSO, r.nextInt(3) + 1);          // 1 a 3
        colocarCasillas(TipoCasilla.TRINEO, r.nextInt(3) + 2);       // 2 a 4
        colocarCasillas(TipoCasilla.AGUJERO, r.nextInt(4) + 2);      // 2 a 5
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
    private boolean aplicarEfecto = false;
    
    private void moveP1(int steps) {
        p1Position += steps;
        if (p1Position >= 50) p1Position = 49;
        
        int row = p1Position / COLUMNS;
        int col = p1Position % COLUMNS;
        GridPane.setRowIndex(P1, row);
        GridPane.setColumnIndex(P1, col);

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
                    lento_t.setText("Dado lento: " + numDadosLentos.get()); // Actualizar texto
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir un dado lento, pero ya tienes el máximo.");
                }

            } else if (prob < 60) { // 35-59: bola de nieve (25%)
                if (cantidadNieve.get() < 6) {
                    cantidadNieve.set(cantidadNieve.get() + 1);
                    eventos.setText("¡Interrogante! Encontraste una bola de nieve. ❄️ Total: " + cantidadNieve.get());
                    nieve_t.setText("Bolas de Nieve: " + cantidadNieve.get());
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir una bola de nieve, pero ya tienes el máximo.");
                }

            } else if (prob < 85) { // 60-84: pez (25%)
                if (cantidadPeces.get() < 2) {
                	cantidadPeces.set(cantidadPeces.get() + 1);
                    tienePez = true;
                    eventos.setText("¡Interrogante! Obtuviste un pez. 🐟 Total: " + cantidadPeces.get());
                    peces_t.setText("Peces: " + cantidadPeces.get());
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir un pez, pero ya tienes el máximo.");
                }

            } else { // 85-99: dado rápido (15%)
                if (numDadosRapidos.get() < 3) {
                    numDadosRapidos.set(numDadosRapidos.get() + 1);
                    eventos.setText("¡Interrogante! Obtuviste un dado rápido. 🎲💨 Total: " + numDadosRapidos);
                    rapido_t.setText("Dado Rapido: " + numDadosRapidos.get());
                } else {
                    eventos.setText("¡Interrogante! Ibas a recibir un dado rápido, pero ya tienes el máximo.");
                }
            }
            break;

            case OSO:
                if (tienePez) {
                    eventos.setText("¡Te salvaste del oso con un pez! 🐟");
                    cantidadPeces.set(cantidadPeces.get() - 1);
                    peces.setText("Peces: " + cantidadPeces.get());
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
                Alert alerta = new Alert(AlertType.INFORMATION);
            	alerta.setTitle(null);
            	alerta.setHeaderText("Final del Juego");
            	alerta.showAndWait();
                break;

            default:
                eventos.setText("Avanzaste a una casilla normal.");
                break;
        }

    }

    @FXML
    private void handlePeces() {
        tienePez = true;
        eventos.setText("¡Conseguiste un pez! 🐟");
    }

    @FXML
    private void handleRapido() {
        Random rand = new Random();
        if (numDadosRapidos.get() > 0) {
            int diceResult = rand.nextInt(6) + 5; // 5 a 10 inclusive
            dadoResultText.setText("Ha salido: " + diceResult);
            aplicarEfecto = true;
            moveP1(diceResult);
            numDadosRapidos.set(numDadosRapidos.get() - 1);
            rapido_t.setText("Dado Rápido: " + numDadosRapidos.get());
        } else {
            eventos.setText("¡No tienes dados rápidos disponibles! 🎲💨");
        }
    }

    @FXML
    private void handleLento() {
        Random rand = new Random();
        if (numDadosLentos.get() > 0) {
            int diceResult = rand.nextInt(3) + 1; // 1 a 3
            dadoResultText.setText("Ha salido: " + diceResult);
            aplicarEfecto = true;
            moveP1(diceResult);
            numDadosLentos.set(numDadosLentos.get() - 1);
            lento_t.setText("Dado lento: " + numDadosLentos.get());
        } else {
            eventos.setText("¡No tienes dados lentos disponibles! 🐢");
        }
    }
    
    @FXML
    private void handleNieve() {
        eventos.setText("Usaste el botón nieve.");
    }

    @FXML
    private void handleNewGame() {
        eventos.setText("Nuevo juego iniciado.");
        
        // Reiniciar estado del jugador
        p1Position = 0;
        tienePez = false;

        // Reiniciar inventario
        numDadosRapidos.set(0);
        numDadosLentos.set(0);
        cantidadPeces.set(0);
        cantidadNieve.set(0);

        // Actualizar textos del inventario
        rapido_t.setText("Dado Rápido: 0");
        lento_t.setText("Dado Lento: 0");
        peces_t.setText("Peces: 0");
        nieve_t.setText("Bolas de Nieve: 0");

        // Regenerar el tablero
        inicializarCasillas(); 

        // Reposicionar jugador
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
    private void handleQuitGame(ActionEvent event) {
        Platform.exit();
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
    	
    	
        tablero.getChildren().removeIf(node -> node instanceof ImageView);
    	
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