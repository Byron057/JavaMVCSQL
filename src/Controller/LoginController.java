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
import Model.Sesion;
/**
 *
 * @author PC
 */
public class LoginController {
    private final Login login;
    private final UsuariosDAO dao;
    
    public LoginController(Login vistaLogin){
        this.login= vistaLogin;
        dao = new UsuariosDAO();
    }
    public void inicairSesion(){
        String usuario = login.flUsuario.getText();
        String password = login.flPassword.getText();
        Usuarios u = dao.Login(usuario, password);
        if(usuario.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese los Campos");
        }else{
            if(u!= null){
                Dashboard dashboard = new Dashboard();
                DashboardController dashboardController = new DashboardController(dashboard);
                Sesion.usuario_actual = u;
                dashboard.setVisible(true);
                login.dispose();
                

            }else{
                JOptionPane.showMessageDialog(null, "Login Incorrecto");
            }  
        }
    }
    
}
