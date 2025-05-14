package Vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Controlador.bbdd;
import Controlador.saveCon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Node;

public class pantallaPrincipalController {

    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    @FXML private TextField userField;
    @FXML private PasswordField passField;

    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    private void initialize() {
        // This method is called automatically after the FXML is loaded
        // You can set initial values or add listeners here
        System.out.println("pantallaPrincipalController initialized");
    }

    @FXML
    private void handleNewGame() {
        System.out.println("New Game clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/pantallaJuego.fxml"));
            Parent pantallaJuegoRoot = loader.load();

            Scene pantallaJuegoScene = new Scene(pantallaJuegoRoot);
            Stage stage = (Stage) newGame.getParentPopup().getOwnerWindow();
            stage.setScene(pantallaJuegoScene);
            stage.setTitle("Nueva Partida");
        } catch (Exception e) {
            e.printStackTrace();
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
        // TODO
    }

    @FXML
    private void handleQuitGame(ActionEvent event) {
        Platform.exit();
    }
   
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = userField.getText();
        String password = passField.getText();

        saveCon.setUser(username);
        String usuario = saveCon.getUser();
        
        System.out.println("Login pressed: " + username + " / " + password);

        if (!username.isEmpty() && !password.isEmpty()) {
            try (Connection conn = bbdd.conectarBaseDatos()) {
                String sql = "SELECT * FROM JUGADOR WHERE NICKNAME = ? AND CONTRASEÑA = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                
                ResultSet rs = stmt.executeQuery();
                
                String sqlUpdatePJugadas = "UPDATE JUGADOR SET PARTIDAS_JUGADAS = PARTIDAS_JUGADAS + 1 WHERE nickname = '" + usuario + "'";
                bbdd.update(conn, sqlUpdatePJugadas);
                
                if (rs.next()) {
                    // Usuario válido, carga la siguiente pantalla
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/pantallaJuego.fxml"));
                    Parent pantallaJuegoRoot = loader.load();

                    Scene pantallaJuegoScene = new Scene(pantallaJuegoRoot);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(pantallaJuegoScene);
                    stage.setTitle("Pantalla de Juego");
                } else {
                    System.out.println("Nombre de usuario o contraseña incorrectos.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Por favor, introduce nombre de usuario y contraseña.");
        }
    
    }
    @FXML
    private void handleRegister() {
        String username = userField.getText();
        String password = passField.getText();

        System.out.println("Register pressed: " + username + " / " + password);

        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                // Crear conexión y guardarla en saveCon
                Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB2", // Cambia según tu base
                    "DW2425_PIN_GRUP06",                          // Cambia por tu usuario
                    "ABMM006"                        // Cambia por tu contraseña
                );
                saveCon.setConexion(conn);

                // Verificar si ya existe el nickname
                String checkSql = "SELECT COUNT(*) FROM JUGADOR WHERE NICKNAME = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, username);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();

                if (rs.getInt(1) > 0) {
                    System.out.println("El nombre de usuario ya existe.");
                    return;
                }

                // Insertar nuevo jugador con secuencia
                String insertSql = "INSERT INTO JUGADOR (ID_USUARIO, NICKNAME, PARTIDAS_JUGADAS, CONTRASEÑA) " +
                                   "VALUES (JUGADOR_SECUENCIA.NEXTVAL, ?, 0, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();

                System.out.println("Registro exitoso.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Por favor, introduce nombre de usuario y contraseña.");
        }
    }
}