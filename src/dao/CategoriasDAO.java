/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Conexion;
import Model.Categorias;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCS
 */
public class CategoriasDAO {
    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public Boolean insertCategoria(Categorias c){
        String sql = "INSERT INTO categoria VALUES(null,?,?,?)";
        try{
            conn = cn.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setString(3, c.getEstado());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public List<Categorias> listarCategorias(){
        List<Categorias> lista = new ArrayList<>();
        String sql= "SELECT * FROM categoria";
        try{   
            conn= cn.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Categorias c = new Categorias();
                c.setId_categoria(rs.getInt("id_cat"));
                c.setNombre(rs.getString("nombre_cat"));
                c.setDescripcion(rs.getString("descripcion_cat"));
                c.setEstado(rs.getString("estado_cat"));
                lista.add(c);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return lista;
    }
    public boolean EliminarCategoria(int id){
        String sql = "DELETE FROM Categoria Where id_cat = ?";
        try{
            conn  = cn.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
        
    
}
