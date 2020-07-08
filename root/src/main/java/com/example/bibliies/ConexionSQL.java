package com.example.bibliies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="";
    private static final String url="jdbc:mysql://localhost:3306/basehijueputa";//toca colocarle el nombre de la tabla en su mysql
    Connection conectar=null;
     
public Connection conexion(){
         try {
            Class.forName(driver);
            conectar = (Connection) DriverManager.getConnection(url,user,pass);
            /*if(conectar != null){
                System.out.println("Conexión exitosa..");
            }*/
        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null,"Error de conexion " +e.getMessage());
               // System.out.println("Conexión fallida.."+e);
            
        }
        return conectar ;
        
    }

}