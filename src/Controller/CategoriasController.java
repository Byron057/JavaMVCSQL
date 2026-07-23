/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.CategoriasDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import Model.Categorias;
import MVCGUI.Dashboard_Categorias;

/**
 *
 * @author PC
 */
public class CategoriasController {

    private final Dashboard_Categorias vistaCategorias;
    private final CategoriasDAO dao;

    public CategoriasController(Dashboard_Categorias vistaCategorias) {
        this.vistaCategorias = vistaCategorias;
        dao = new CategoriasDAO();
    }
    public void insertCategoria(){
        Categorias c = new Categorias();
        c.setNombre(vistaCategorias.flNombreCategoria.getText());
        c.setDescripcion(vistaCategorias.flDescripcionCategoria.getText());
        c.setEstado(vistaCategorias.cbxEstadoCategoria.getSelectedItem().toString());
         if (dao.insertCategoria(c)){
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
        }else{
            JOptionPane.showMessageDialog(null, "No Se Pudo regitsrar el Usuario");
        }
        
    }
    public void listarCategorias(){
        DefaultTableModel modelo = (DefaultTableModel) vistaCategorias.tblCategorias.getModel();
        modelo.setRowCount(0);
        List<Categorias> lista = dao.listarCategorias();
        for(Categorias c: lista){
            Object[] fila ={
                c.getId_categoria(),
                c.getNombre(),
                c.getDescripcion(),
                c.getEstado()
            };
            modelo.addRow(fila);
        }
    }
    public void eliminarCategoria(){
        int fila = vistaCategorias.tblCategorias.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Registro");
            return;
        }else{
            int opc = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar este Registro?", "Confirmar Accion", JOptionPane.YES_NO_OPTION);
            if(opc!=JOptionPane.YES_OPTION){
                return;
            }else{
                int id = Integer.parseInt(vistaCategorias.tblCategorias.getValueAt(fila, 0).toString());
                if(dao.eliminarCategoria(id)){
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                    listarCategorias();
                }
                
            }
        }
    }
    public void editarCategoria(){
        int fila = vistaCategorias.tblCategorias.getSelectedRow();
        if(fila ==-1){
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Registro");
            return;
        }
        Categorias c = new Categorias();
        c.setId_categoria(Integer.parseInt(vistaCategorias.tblCategorias.getValueAt(fila, 0).toString()));
        c.setNombre(vistaCategorias.flNombreCategoria.getText());
        c.setDescripcion(vistaCategorias.flDescripcionCategoria.getText());
        c.setEstado(vistaCategorias.cbxEstadoCategoria.getSelectedItem().toString());
        if(dao.actualizarCategoria(c)){
            JOptionPane.showMessageDialog(null, "Categoria Actualizada con Exito");
            listarCategorias();
        }else{
            JOptionPane.showMessageDialog(null, "No Se pudo Actualizar");
        }
    }
    
}
