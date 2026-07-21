/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.UsuariosDAO;
import javax.swing.JOptionPane;
import Model.Usuarios;
import MVCGUI.Login;
import MVCGUI.Dashboard;
import Controller.DashboardController;
/**
 *
 * @author PC
 */
public class LoginController {
    private Login login;
    private UsuariosDAO dao;
    
    public LoginController(Login vistaLogin){
        this.login= vistaLogin;
        dao = new UsuariosDAO();
    }
    public void inicairSesion(){
        String usuario = login.flUsuario.getText();
        String password = login.flPassword.getText();
        Usuarios u = dao.Login(usuario, password);
        if(u!= null){
            Dashboard dashboard = new Dashboard();
            DashboardController dashboardController = new DashboardController(dashboard);
            dashboard.setVisible(true);
            login.dispose();
   
        }else{
            JOptionPane.showMessageDialog(null, "Login Incorrecto");
        }
    }
}
