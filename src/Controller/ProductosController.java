/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.ProductosDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import Model.Productos;
import Model.Categorias;
import MVCGUI.Dashboard_Productos;
import javax.swing.JComboBox;
import Model.Sesion;
/**
 *
 * @author PC
 */
public class ProductosController {
    private final Dashboard_Productos vistaProductos;
    private final ProductosDAO dao;

    public ProductosController(Dashboard_Productos vistaProductos) {
        this.vistaProductos = vistaProductos;
        dao = new ProductosDAO();
    }
    public void cargarCategoriasActivas(JComboBox cbxCategoriasProductos){
            cbxCategoriasProductos.removeAllItems();
            for(Categorias c: dao.listarCategoriasActivas()){
                cbxCategoriasProductos.addItem(c);
            }
            
    }
    public void insertProductos(){
        Productos p = new Productos();
        p.setNombre(vistaProductos.flNombreProducto.getText());
        p.setDescripcion(vistaProductos.flDescripcionProducto.getText());
        p.setStock(Integer.parseInt(vistaProductos.flStockProducto.getText()));
        p.setPrecio(Double.parseDouble(vistaProductos.flPrecioProducto.getText()));
        p.setEstado(vistaProductos.cbxEstadoProducto.getSelectedItem().toString());
        Categorias cat = (Categorias) vistaProductos.cbxCategoriaProducto.getSelectedItem();
        p.setIdCategoria(cat.getId_categoria());
        p.setIdUsuario(Sesion.usuario_actual.getId_usuario());
        
         if (dao.insertProductos(p)){
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
        }else{
            JOptionPane.showMessageDialog(null, "No Se Pudo regitsrar el Usuario");
        }
    }  
}
