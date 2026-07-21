/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Conexion;
import Model.Usuarios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class UsuariosDAO {
    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public Usuarios Login(String Usuario, String Password){
        Usuarios u = null;
        String sql= "SELECT * FROM USUARIOS WHERE cedula_usu=? and password_usu=? and estado_usu= 'activo' and rol_usu='administrador'or rol_usu='empleado'";
        try{
            conn =cn.getConnection();
            ps =conn.prepareStatement(sql);
            ps.setString(1,Usuario);
            ps.setString(2, Password);
            rs= ps.executeQuery();
            if(rs.next()){
                u = new Usuarios();
                u.setId_usuario(rs.getInt("id_usu"));
                u.setNombre(rs.getString("nombre_usu"));
                u.setApellido(rs.getString("apellido_usu"));
                u.setRol(rs.getString("rol_usu"));
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
     return u; 
    }
    public Boolean insertUsuarios(Usuarios u){
       String sql ="INSERT INTO USUARIOS VALUES(null,?,?,?,?,?,?,?,?,?)";
        try{
            conn = cn.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getCedula() );
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido() );
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getCedula() );
            ps.setString(6, u.getGenero() );
            ps.setString(7, "Cliente");
            ps.setString(8,u.getDireccion() );
            ps.setString(9,u.getEstado() );
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
    public List<Usuarios> listarUsuarios(){
        List<Usuarios> lista = new ArrayList<>();
        String sql = "Select * From usuarios";
        try{
            conn = cn.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuarios u = new Usuarios();
                u.setCedula(rs.getString("cedula_usu"));
                u.setId_usuario(rs.getInt("id_usu"));
                u.setNombre(rs.getString("nombre_usu"));
                u.setApellido(rs.getString("apellido_usu"));
                u.setGenero(rs.getString("genero_usu"));
                u.setRol(rs.getString("rol_usu"));
                u.setCorreo(rs.getString("email_usu"));
                u.setEstado(rs.getString("estado_usu"));
                lista.add(u);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return lista;
    }
    
}
