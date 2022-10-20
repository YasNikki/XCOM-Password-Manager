
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login_DAO {
    
    static String url = "jdbc:mysql://localhost/xcom";
    static String username = "root";
    static String password = "";
    
    static String usuario;
    static String senha;
    
    public static int aprovacao = 0;
    public static int contador = 1;
    
    public static void logar() throws SQLException{
        
        String SQL = "select * from usuarios";
        
        usuario = view.login_GUI.userTXT.getText().toLowerCase();
        senha = String.valueOf(view.login_GUI.passTXT.getPassword()).toLowerCase();
        
        Connection con = (Connection) DriverManager.getConnection(url, username, password);
        
        try{
            
            PreparedStatement pst = con.prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                if (usuario.equals(rs.getString("login").toLowerCase())){
                    
                    if (senha.equals(rs.getString("senha").toLowerCase())){
                        
                        authentication.id_user = rs.getInt("id");
                        authentication.user = rs.getString("login");
                        new view.home_GUI().setVisible(true);
                        aprovacao = 1;
                    
                    }
                    
                }
            }
            
        }catch (Exception e){
        }
        
    }
    
}
