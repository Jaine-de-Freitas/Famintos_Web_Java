/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user3
 */
public class Conexao {
    public Connection Conectar(){
        Connection conecte= null;
        try{
           String url="jdbc:mysql://localhost:3306/FamintosBD?user=root&password=";
           conecte=DriverManager.getConnection(url);
           
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return conecte;
    }
}
