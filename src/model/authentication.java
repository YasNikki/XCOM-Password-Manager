
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class authentication {
    
    public static int id_user = 0;
    public static String user = "";
    
    static String url = "jdbc:mysql://localhost/xcom";
    static String username = "root";
    static String password = "";
    
    static int update_id;
    
    public static void carregaSenhas() throws SQLException{
        
        String SQL = "select * from credenciais where creator_id='"+id_user+"'";
        
        DefaultTableModel model = (DefaultTableModel)  view.home_GUI.registros.getModel();
        model.setRowCount(0);
        
        Connection con = (Connection) DriverManager.getConnection(url, username, password);
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                String titulo = rs.getString("titulo");
                String link = rs.getString("link");
                String login = rs.getString("login");
                String pass = rs.getString("senha");
                
                String tBD[] = {titulo, link, login, pass};
                model.addRow(tBD);
                
            }
        }catch (Exception e){
        }
        
    }
    
    public static void registraSenhas(){
        
        String titulo = view.new_GUI.titleCBX.getText();
        String link = view.new_GUI.linkCBX.getText();
        String login = view.new_GUI.loginCBX.getText();
        String senha = view.new_GUI.passCBX.getText();
        
        controller.Conexao_DB.carregaDriver();

        Connection con = null;
        
        try{
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String SQL = "insert into credenciais(creator_id,titulo,link,login,senha) values(?,?,?,?,?)";
        
        try{
            PreparedStatement insert = (PreparedStatement) con.prepareStatement(SQL);
            insert.setInt(1, id_user);
            insert.setString(2, titulo);
            insert.setString(3, link);
            insert.setString(4, login);
            insert.setString(5, senha);
            
            insert.execute();
            
        }catch (Exception ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            carregaSenhas();
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void atualizador() throws SQLException{
        
        DefaultTableModel model = (DefaultTableModel) view.home_GUI.registros.getModel();
        int row = view.home_GUI.registros.getSelectedRow();
        String tituloS = view.home_GUI.registros.getModel().getValueAt(row, 0).toString();
        String linkS = view.home_GUI.registros.getModel().getValueAt(row, 1).toString();
        String loginS = view.home_GUI.registros.getModel().getValueAt(row, 2).toString();
        String senhaS = view.home_GUI.registros.getModel().getValueAt(row, 3).toString();
        
        String SQL = "select * from credenciais where senha='"+senhaS+"' and login='"+loginS+"' and link='"+linkS+"' and titulo='"+tituloS+"' and creator_id='"+id_user+"'";
        
        Connection con = (Connection) DriverManager.getConnection(url, username, password);
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                update_id = rs.getInt("reg_id");
                String titulo = rs.getString("titulo");
                String link = rs.getString("link");
                String login = rs.getString("login");
                String pass = rs.getString("senha");
                
                view.update_GUI.titleCBX.setText(titulo);
                view.update_GUI.linkCBX.setText(link);
                view.update_GUI.loginCBX.setText(login);
                view.update_GUI.passCBX.setText(pass);
                
                
            }
        }catch (Exception e){
        }
        
    }
    
    public static void atualizar(){
        
        controller.Conexao_DB.carregaDriver();

        Connection con = null;
        
        try{
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String SQL = "update credenciais set titulo=?,link=?,login=?,senha=? where reg_id=?";
        
        
        try{
            PreparedStatement insert = (PreparedStatement) con.prepareStatement(SQL);
            insert.setString(1, view.update_GUI.titleCBX.getText());
            insert.setString(2, view.update_GUI.linkCBX.getText());
            insert.setString(3, view.update_GUI.loginCBX.getText());
            insert.setString(4, view.update_GUI.passCBX.getText());
            insert.setInt(5, update_id);
            insert.executeUpdate();
            
        }catch (Exception ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            carregaSenhas();
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void deletar(){
        
        controller.Conexao_DB.carregaDriver();

        Connection con = null;
        
        try{
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultTableModel model = (DefaultTableModel) view.home_GUI.registros.getModel();
        int row = view.home_GUI.registros.getSelectedRow();
        String tituloS = view.home_GUI.registros.getModel().getValueAt(row, 0).toString();
        String linkS = view.home_GUI.registros.getModel().getValueAt(row, 1).toString();
        String loginS = view.home_GUI.registros.getModel().getValueAt(row, 2).toString();
        String senhaS = view.home_GUI.registros.getModel().getValueAt(row, 3).toString();
        
        String SQL = "delete from credenciais where senha='"+senhaS+"' and login='"+loginS+"' and link='"+linkS+"' and titulo='"+tituloS+"' and creator_id='"+id_user+"'";
        
        
        try{
            PreparedStatement insert = (PreparedStatement) con.prepareStatement(SQL);
            insert.execute();
            
        }catch (Exception ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            carregaSenhas();
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
