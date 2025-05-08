package Vista;

import Controlador.bbdd;
import Modelo.GestorBaseDeDatos;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.sql.Connection;
import java.sql.SQLException;

public class pantallaPrincipalController {
	
		// Componentes FXML
	 	@FXML private TextField userField;
	    @FXML private PasswordField passField;
	    @FXML private Button loginButton;
	    @FXML private Button registerButton;
	    @FXML private MenuItem newGame;
	    @FXML private MenuItem loadGame;
	    @FXML private MenuItem quitGame;
    
	    // Variables de estado según el modelo relacional
	    private int idUsuario;          // PK de JUGADOR
	    private int numeroPartida;      // PK de PARTIDA
	    private int idInventario;       // PK de INVENTARIO

    @FXML
    private void initialize() {
        // This method is called automatically after the FXML is loaded
        // You can set initial values or add listeners here
        System.out.println("pantallaPrincipalController initialized");
        
        // Inicializar estado
        idUsuario = -1;
        numeroPartida = -1;
        idInventario = -1;
        
        // Deshabilitar menú hasta login
        habilitarMenuJuego(false);
    }

    @FXML
    private void handleNewGame() {
        System.out.println("New Game clicked");
        try {
            // Crear partida y obtener su PK
            numeroPartida = GestorBaseDeDatos.crearPartida(idUsuario, 0, "inicio");
            
            // Crear inventario inicial y obtener su PK
            idInventario = GestorBaseDeDatos.crearInventario(
                numeroPartida, 
                idUsuario, 
                1,  // dados iniciales
                0,  // peces iniciales
                0   // bolas de nieve iniciales
            );
            
            // Actualizar estadísticas del jugador
            GestorBaseDeDatos.actualizarPartidasJugadas(idUsuario);
            
            cargarPantallaJuego(event);
            
        } catch (SQLException e) {
            manejarErrorBD(e, "crear partida");
            resetearEstadoPartida();
        }
    }

    @FXML
    private void handleSaveGame() {
        System.out.println("Save Game clicked");
        // TODO
    }

    @FXML
    private void handleLoadGame() {
        System.out.println("Load Game clicked");
        try {
            // Obtener la última partida del jugador (ejemplo simplificado)
            numeroPartida = GestorBaseDeDatos.obtenerUltimaPartida(idUsuario);
            
            if (numeroPartida == -1) {
                mostrarAlerta("Información", "No tienes partidas guardadas", Alert.AlertType.INFORMATION);
                return;
            }
            
            // Obtener PK del inventario asociado
            idInventario = GestorBaseDeDatos.obtenerIdInventario(numeroPartida, idUsuario);
            
            cargarPantallaJuego(event);
            
        } catch (SQLException e) {
            manejarErrorBD(e, "cargar partida");
        }
    }

    @FXML
    private void handleQuitGame() {
        System.out.println("Quit Game clicked");
        // TODO
        System.exit(0);
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
    	String nickname = userField.getText().trim();
        String contrasena = passField.getText().trim();

        if (!validarCredenciales(nickname, contrasena)) return;

        try {
            // Autenticar y obtener PK del jugador
            idUsuario = GestorBaseDeDatos.autenticarJugador(nickname, contrasena);
            
            if (idUsuario != -1) {
                habilitarControlesJuego(true);
                mostrarAlerta("Bienvenido", "Sesión iniciada correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "Credenciales incorrectas", Alert.AlertType.ERROR);
                resetearEstado();
            }
        } catch (SQLException e) {
            manejarErrorBD(e, "autenticar");
        }
    }


    @FXML
    private void handleRegister() {
        System.out.println("Register pressed");
        
        String nickname = userField.getText().trim();
        String contrasena = passField.getText().trim();

        if (!validarCredenciales(nickname, contrasena)) return;

        try {
            // Registrar y obtener PK del nuevo jugador
            idUsuario = GestorBaseDeDatos.registrarJugador(nickname, contrasena);
            
            if (idUsuario != -1) {
                mostrarAlerta("Éxito", "Registro completado. ID: " + idUsuario, Alert.AlertType.INFORMATION);
                habilitarControlesJuego(true);
            } else {
                mostrarAlerta("Error", "El nickname ya existe", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            manejarErrorBD(e, "registrar");
        }
    }
    
    // ----- Métodos auxiliares -----
    private void cargarPantallaJuego(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaJuego.fxml"));
            Parent root = loader.load();
            
            // Pasar las PKs al controlador del juego
            pantallaJuegoController juegoController = loader.getController();
            juegoController.inicializarPartida(idUsuario, numeroPartida, idInventario);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Joc del Pinguí - " + userField.getText());
            
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo cargar el juego: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private boolean validarCredenciales(String nickname, String contrasena) {
        if (nickname.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Nickname y contraseña son obligatorios", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void habilitarControlesJuego(boolean habilitar) {
        newGame.setDisable(!habilitar);
        loadGame.setDisable(!habilitar);
    }

    private void resetearEstado() {
        idUsuario = -1;
        resetearEstadoPartida();
    }

    private void resetearEstadoPartida() {
        numeroPartida = -1;
        idInventario = -1;
    }

    private void manejarErrorBD(SQLException e, String operacion) {
        System.err.println("Error al " + operacion + ": " + e.getMessage());
        mostrarAlerta("Error BD", "Falló la operación: " + operacion, Alert.AlertType.ERROR);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    
}