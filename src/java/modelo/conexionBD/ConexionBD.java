/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luispavezleon
 */
public class ConexionBD {

    private static ConexionBD instance;

    private Connection cnn;

    private ConexionBD() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo_carga_datos?zeroDateTimeBehavior=convertToNull", "root", "heroes");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Retorna una conexion activa al servidor de BD
     */
    public synchronized static ConexionBD estadoConexion() {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    public Connection getCnn() {
        return cnn;
    }

    public void cerrarConexion() {
        instance = null;

    }
}
