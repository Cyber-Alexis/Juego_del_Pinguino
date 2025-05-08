package Vista;

import Modelo.GestorBaseDeDatos;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class pantallaPrincipalController {

    // Componentes FXML
    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoContraseña;
    @FXML private Button botonLogin;
    @FXML private Button botonRegistro;
    @FXML private MenuItem menuNuevaPartida;
    @FXML private MenuItem menuCargarPartida;
    @FXML private MenuItem menuSalir;

    // Variables de instancia
    private int idUsuarioActual = -1;

    @FXML
    private void initialize() {
        // Configuración inicial si es necesaria
        menuNuevaPartida.setDisable(true);
        menuCargarPartida.setDisable(true);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = campoUsuario.getText().trim();
        String contraseña = campoContraseña.getText().trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese usuario y contraseña", Alert.AlertType.ERROR);
            return;
        }

        idUsuarioActual = GestorBaseDeDatos.autenticarJugador(usuario, contraseña);

        if (idUsuarioActual != -1) {
            mostrarAlerta("Éxito", "Inicio de sesión correcto", Alert.AlertType.INFORMATION);
            habilitarOpcionesMenu(true);
            cargarPantallaJuego(event, idUsuarioActual);
        } else {
            mostrarAlerta("Error", "Credenciales incorrectas", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleRegistro(ActionEvent event) {
        String usuario = campoUsuario.getText().trim();
        String contraseña = campoContraseña.getText().trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese usuario y contraseña", Alert.AlertType.ERROR);
            return;
        }

        if (GestorBaseDeDatos.registrarJugador(usuario, contraseña)) {
            mostrarAlerta("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
            idUsuarioActual = GestorBaseDeDatos.autenticarJugador(usuario, contraseña);
            habilitarOpcionesMenu(true);
        } else {
            mostrarAlerta("Error", "El usuario ya existe o hubo un error", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleNuevaPartida(ActionEvent event) {
        if (idUsuarioActual == -1) {
            mostrarAlerta("Error", "Debe iniciar sesión primero", Alert.AlertType.ERROR);
            return;
        }

        // Crear nueva partida con posición inicial (casilla 0)
        int idPartida = GestorBaseDatos.crearPartida(idUsuarioActual, 0, "inicio");
        
        if (idPartida != -1) {
            cargarPantallaJuego(event, idUsuarioActual, idPartida);
        } else {
            mostrarAlerta("Error", "No se pudo crear la partida", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCargarPartida(ActionEvent event) {
        // Implementar lógica para mostrar partidas guardadas y cargar una seleccionada
        // Esto requeriría una nueva pantalla FXML para seleccionar partidas
        mostrarAlerta("En desarrollo", "Funcionalidad en desarrollo", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleSalir() {
        System.exit(0);
    }

    // Métodos auxiliares
    private void cargarPantallaJuego(ActionEvent event, int idUsuario) {
        cargarPantallaJuego(event, idUsuario, -1); // -1 indica nueva partida
    }

    private void cargarPantallaJuego(ActionEvent event, int idUsuario, int idPartida) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/pantallaJuego.fxml"));
            Parent root = loader.load();

            // Pasar datos al controlador del juego
            PantallaJuegoController juegoController = loader.getController();
            juegoController.inicializarDatos(idUsuario, idPartida);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("El Joc d'en Pingu - " + campoUsuario.getText());

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la pantalla de juego", Alert.AlertType.ERROR);
        }
    }

    private void habilitarOpcionesMenu(boolean habilitar) {
        menuNuevaPartida.setDisable(!habilitar);
        menuCargarPartida.setDisable(!habilitar);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}