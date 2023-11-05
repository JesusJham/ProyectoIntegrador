/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
        private Connection conexion;
    private String url = "jdbc:mysql://localhost:3307/nexusCompany?serverTimezone=UTC";
    private String usuario = "root";
    private String password = "";

    public Conexion() {
    }

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(url, usuario, password);
            return conexion;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (conexion != null) {
            return conexion.prepareStatement(sql);
        } else {
            throw new SQLException("No se ha establecido la conexión");
        }
    }

    public PreparedStatement prepareStatement(String query, int returnGeneratedKeys) throws SQLException {
    if (conexion != null) {
        return conexion.prepareStatement(query, returnGeneratedKeys);
    } else {
        throw new SQLException("No se ha establecido la conexión");
    }
}
}
