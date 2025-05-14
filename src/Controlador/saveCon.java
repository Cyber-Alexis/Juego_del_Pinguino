package Controlador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class saveCon {
    
    private static Connection Conexion;
    private static String user;
    private static int partida_id;

    public static int getPartida_id() {
		return partida_id;
	}

	public static void setPartida_id(int partida_id) {
		saveCon.partida_id = partida_id;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		saveCon.user = user;
	}

	public static Connection getConexion() {
        return Conexion;
    }

    public static void setConexion(Connection conexion) {
        Conexion = conexion;
    }
    
}