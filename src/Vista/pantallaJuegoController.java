package Vista;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    private void initialize() {
        eventos.setText("¡El juego ha comenzado!");
        inicializarCasillas();
    }

    private void inicializarCasillas() {
        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = TipoCasilla.NORMAL;
        }

        casillas[5] = TipoCasilla.AGUJERO;
        casillas[12] = TipoCasilla.AGUJERO;
        casillas[15] = TipoCasilla.OSO;
        casillas[20] = TipoCasilla.AGUJERO;
        casillas[35] = TipoCasilla.OSO;
        casillas[49] = TipoCasilla.META;
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
                eventos.setText("¡Caíste en un agujero! Retrocedes 1 casilla.");
                p1Position = Math.max(0, p1Position - 1);
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
        eventos.setText("Usaste el botón rápido.");
    }

    @FXML
    private void handleLento() {
        eventos.setText("Usaste el botón lento.");
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
}
