package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorBaseDeDatos {
	
	// Configuración de la base de datos
    private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521/XEPDB2";
    private static final String USER = "DW2425_PIN_GRUP06";
    private static final String PWD = "ABMM006";
	
  
    // Sentencias SQL para la tabla JUGADOR
    private static final String INSERTAR_JUGADOR = 
        "INSERT INTO JUGADOR (nickname, contraseña) VALUES (?, ?)";
    
    private static final String BUSCAR_JUGADOR_POR_CREDENCIALES = 
        "SELECT id_usuario FROM JUGADOR WHERE nickname = ? AND contraseña = ?";
    
    private static final String ACTUALIZAR_PARTIDAS_JUGADAS = 
        "UPDATE JUGADOR SET partidas_jugadas = ? WHERE id_usuario = ?";
    
    // Sentencias SQL para la tabla PARTIDA
    private static final String INSERTAR_PARTIDA = 
        "INSERT INTO PARTIDA (fecha_hora, estado_partida, id_casilla, tipo_casilla) VALUES (?, ?, ?, ?)";
    
    private static final String OBTENER_PARTIDAS_POR_JUGADOR = 
        "SELECT p.* FROM PARTIDA p JOIN INVENTARIO i ON p.numero_partida = i.numero_partida " +
        "WHERE i.id_usuario = ? ORDER BY p.fecha_hora DESC";
    
    private static final String ACTUALIZAR_ESTADO_PARTIDA = 
        "UPDATE PARTIDA SET estado_partida = ?, id_casilla = ?, tipo_casilla = ? WHERE numero_partida = ?";
    
    // Sentencias SQL para la tabla INVENTARIO
    private static final String INSERTAR_INVENTARIO = 
        "INSERT INTO INVENTARIO (dados, peces, bolas_nieve, numero_partida, id_usuario) " +
        "VALUES (?, ?, ?, ?, ?)";
    
    private static final String ACTUALIZAR_INVENTARIO = 
        "UPDATE INVENTARIO SET dados = ?, peces = ?, bolas_nieve = ? " +
        "WHERE id_partida_jugador = ?";
    
    private static final String OBTENER_INVENTARIO_POR_PARTIDA = 
        "SELECT * FROM INVENTARIO WHERE numero_partida = ? AND id_usuario = ?";
    
    // Obtiene una conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }
    
    // Registra un nuevo jugador en la base de datos
    public static boolean registrarJugador(String nickname, String contraseña) {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(INSERTAR_JUGADOR)) {
            
            ps.setString(1, nickname);
            ps.setString(2, contraseña);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar jugador: " + e.getMessage());
            return false;
        }
    }
    
    // Autentica a un jugador con su nickname y contraseña
    public static int autenticarJugador(String nickname, String contraseña) {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(BUSCAR_JUGADOR_POR_CREDENCIALES)) {
            
            ps.setString(1, nickname);
            ps.setString(2, contraseña);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario");
                }
            }
            return -1;
            
        } catch (SQLException e) {
            System.err.println("Error al autenticar jugador: " + e.getMessage());
            return -1;
        }
    }
    
    // Crea una nueva partida en la base de datos
    public static int crearPartida(int idJugador, int idCasilla, String tipoCasilla) {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(INSERTAR_PARTIDA, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, "en_progreso");
            ps.setInt(3, idCasilla);
            ps.setString(4, tipoCasilla);
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idPartida = rs.getInt(1);
                    
                    // Crear inventario inicial para la partida
                    crearInventarioInicial(idPartida, idJugador);
                    
                    // Actualizar contador de partidas jugadas
                    actualizarPartidasJugadas(idJugador);
                    
                    return idPartida;
                }
            }
            return -1;
            
        } catch (SQLException e) {
            System.err.println("Error al crear partida: " + e.getMessage());
            return -1;
        }
    }
    
    // Crea el inventario inicial para una partida
    private static boolean crearInventarioInicial(int idPartida, int idJugador) throws SQLException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(INSERTAR_INVENTARIO)) {
            
            // Valores iniciales según las reglas del juego
            ps.setInt(1, 1);  // 1 dado inicial
            ps.setInt(2, 0);   // 0 peces iniciales
            ps.setInt(3, 0);   // 0 bolas de nieve iniciales
            ps.setInt(4, idPartida);
            ps.setInt(5, idJugador);
            
            return ps.executeUpdate() > 0;
        }
    }

    // Actualiza el contador de partidas jugadas de un jugador
    private static boolean actualizarPartidasJugadas(int idJugador) {
        try (Connection conexion = obtenerConexion();
             PreparedStatement psSelect = conexion.prepareStatement("SELECT partidas_jugadas FROM JUGADOR WHERE id_usuario = ?");
             PreparedStatement psUpdate = conexion.prepareStatement(ACTUALIZAR_PARTIDAS_JUGADAS)) {
            
            // Obtener partidas jugadas actuales
            psSelect.setInt(1, idJugador);
            int partidasActuales = 0;
            
            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    partidasActuales = rs.getInt("partidas_jugadas");
                }
            }
            
            // Actualizar contador
            psUpdate.setInt(1, partidasActuales + 1);
            psUpdate.setInt(2, idJugador);
            return psUpdate.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar partidas jugadas: " + e.getMessage());
            return false;
        }
    }

    // Actualiza el estado de una partida
    public static boolean actualizarPartida(int idPartida, String estado, int idCasilla, String tipoCasilla) {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ACTUALIZAR_ESTADO_PARTIDA)) {
            
            ps.setString(1, estado);
            ps.setInt(2, idCasilla);
            ps.setString(3, tipoCasilla);
            ps.setInt(4, idPartida);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar partida: " + e.getMessage());
            return false;
        }
    }

    // Actualiza el inventario de un jugador en una partida
    public static boolean actualizarInventario(int idInventario, int dados, int peces, int bolasNieve) {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ACTUALIZAR_INVENTARIO)) {
            
            ps.setInt(1, dados);
            ps.setInt(2, peces);
            ps.setInt(3, bolasNieve);
            ps.setInt(4, idInventario);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar inventario: " + e.getMessage());
            return false;
        }
    }

    // Obtiene el inventario de un jugador en una partida específica
    public static Inventario obtenerInventario(int idPartida, int idJugador) {
        Inventario inventario = new Inventario();
        
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_INVENTARIO_POR_PARTIDA)) {
            
            ps.setInt(1, idPartida);
            ps.setInt(2, idJugador);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    inventario.setDados(rs.getInt("dados"));
                    inventario.setPeces(rs.getInt("peces"));
                    inventario.setBolasDeNieve(rs.getInt("bolas_nieve"));
                    inventario.setIdInventario(rs.getInt("id_inventario"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener inventario: " + e.getMessage());
        }
        
        return inventario;
    }

    // Obtiene el historial de partidas de un jugador
    public static List<Partida> obtenerHistorialPartidas(int idJugador) {
        List<Partida> partidas = new ArrayList<>();
        
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_PARTIDAS_POR_JUGADOR)) {
            
            ps.setInt(1, idJugador);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Partida partida = new Partida();
                    partida.setNumeroPartida(rs.getInt("numero_partida"));
                    partida.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                    partida.setEstado(rs.getString("estado_partida"));
                    partida.setIdCasilla(rs.getInt("id_casilla"));
                    partida.setTipoCasilla(rs.getString("tipo_casilla"));
                    partidas.add(partida);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener historial de partidas: " + e.getMessage());
        }
        
        return partidas;
    }
}
