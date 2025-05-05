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
    
}
