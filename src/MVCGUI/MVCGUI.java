/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MVCGUI;
import java.sql.Connection;
import Model.Conexion;
import Controller.LoginController;

/**
 *
 * @author PC
 */
public class MVCGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion conn = new Conexion();
        Connection con = conn.getConnection();
        
        if(con!= null){
            Login login = new Login();
            LoginController loginController = new LoginController(login);
            login.setVisible(true);
            System.out.println("Conexion Exitosa");
            
        }else{
            System.out.println("No se Conecto a la Base de Datos");
        }
    }
    
}
