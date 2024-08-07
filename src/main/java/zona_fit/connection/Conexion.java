package zona_fit.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection() {
        Connection connection = null;
        var dataBase = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + dataBase;
        var user = "root";
        var password = "root071428";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectarse a la base de datos" + e);
        }
        return connection;
    }

   /* public static void main(String[] args) {
        var connection = Conexion.getConnection();
        if (connection != null){
            System.out.println("Conexión establecida " + connection);
        }else {
            System.out.println("Fallo la conexión");
        }
    }*/
}