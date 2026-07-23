/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.UsuariosDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.Usuarios;
import MVCGUI.Dashboard_Usuarios;
import java.util.List;

public class UsuariosController {
    private Dashboard_Usuarios vistaUsuarios;
    private UsuariosDAO dao;
    public UsuariosController(Dashboard_Usuarios vistaUsuarios){
        this.vistaUsuarios = vistaUsuarios;
        dao = new UsuariosDAO();
    }
    public void insert(){
        Usuarios u = new Usuarios();
        u.setCedula(vistaUsuarios.flCedulaUsuario.getText());
        u.setNombre(vistaUsuarios.flNombreUsuario.getText());
        u.setApellido(vistaUsuarios.flApellidoUsuario.getText());
        u.setGenero(vistaUsuarios.cdxGeneroUsuario.getSelectedItem().toString());
        u.setCorreo(vistaUsuarios.flEmailUsuario.getText());
        u.setDireccion(vistaUsuarios.flDireccion.getText());
        u.setEstado(vistaUsuarios.cdxEstadoUsuario.getSelectedItem().toString());
        if (dao.insertUsuarios(u)){
            JOptionPane.showMessageDialog(null, "Usuario Registrado");
        }else{
            JOptionPane.showMessageDialog(null, "No Se Pudo regitsrar el Usuario");
        }
    }
    public void listarUsuarios(){
        DefaultTableModel modelo = (DefaultTableModel) vistaUsuarios.tblUsuarios.getModel();
        modelo.setRowCount(0);
        List<Usuarios> lista = dao.listarUsuarios();
        for(Usuarios u: lista){
            Object[] fila={
                u.getId_usuario(),
                u.getCedula(),
                u.getNombre(),
                u.getApellido(),
                u.getGenero(),
                u.getCorreo(),
                u.getDireccion(),
                u.getRol(),
                u.getEstado()
            };
            modelo.addRow(fila);
        }
    }
    public void eliminarUsuario(){
        int fila = vistaUsuarios.tblUsuarios.getSelectedRow();
        if(fila ==-1){
            JOptionPane.showMessageDialog(null, "Debe Seleccionar una Fila");
            return;
        }else{
            int id = Integer.parseInt(vistaUsuarios.tblUsuarios.getValueAt(fila, 0).toString());
            if(dao.eliminarUsuario(id)){
                int opc = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar este Registro?", "Confirmar Accion", JOptionPane.YES_NO_OPTION);
                if(opc!=JOptionPane.YES_OPTION){
                return;
                }else{
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                    listarUsuarios();
                }
            }
        }
        
    }
}
