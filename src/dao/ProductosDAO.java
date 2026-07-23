/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Conexion;
import Model.Productos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Categorias;

/**
 *
 * @author PC
 */
public class ProductosDAO {
    Conexion cn = new Conexion(); 
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public Boolean insertProductos(Productos p){
        String sql = "INSERT INTO productos VALUES(null,?,?,?,?,?,?,?)";
        try{
            conn = cn.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getStock());
            ps.setDouble(4, p.getPrecio());
            ps.setString(5, p.getEstado());
            ps.setInt(6, p.getIdCategoria());
            ps.setInt(7, p.getIdUsuario());
           
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public List<Categorias> listarCategoriasActivas(){
        String sql ="SELECT * FROM categoria WHERE estado_cat = 'activo'";
        List<Categorias> lista = new ArrayList<>();
        try{
            conn = cn.getConnection();
            ps= conn.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                Categorias c = new Categorias();
                c.setId_categoria(rs.getInt("id_cat"));
                c.setNombre(rs.getString("nombre_cat"));
                lista.add(c);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return lista;
    }
}
