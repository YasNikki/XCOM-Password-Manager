
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class cadastro_DAO {
    
    static String url = "jdbc:mysql://localhost/xcom";
    static String username = "root";
    static String password = "";
    
    static String usuario;
    static String senha;
    
    public static int aprovado = 0;
    
    public static void cadastraUsuario(){
        
        boolean hasLetter = false;
        boolean hasDigit = false;
        
        usuario = view.sign_GUI.usuarioTXT.getText();
        senha = String.valueOf(view.sign_GUI.passTXT.getPassword());
        
        if (senha.length() >= 8) {
            for (int i = 0; i < senha.length(); i++) {
                char x = senha.charAt(i);
                if (Character.isLetter(x)) {
                    hasLetter = true;
                }
                else if (Character.isDigit(x)) {
                    hasDigit = true;
                }

                if(hasLetter && hasDigit){
                    
                    
                    
                }
            }
            if (hasLetter && hasDigit) {
                
                controller.Conexao_DB.carregaDriver();

                Connection con = null;

                try{
                    con = (Connection) DriverManager.getConnection(url, username, password);
                } catch (SQLException ex) {
                    Logger.getLogger(cadastro_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }

                String SQL = "insert into usuarios(login, senha) values('"+usuario+"','"+senha+"')";

                try{
                    PreparedStatement insert = (PreparedStatement) con.prepareStatement(SQL);
                    insert.execute();

                    aprovado = 1;

                }catch (Exception ex) {
                    Logger.getLogger(cadastro_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Senha não é forte o suficiente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senha não é forte o suficiente.");
        } 
    }
    
}
