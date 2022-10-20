
package main;

import controller.Conexao_DB;
import view.splash_GUI;

public class Main {

    public static void main(String[] args) {
        
        Conexao_DB.carregaDriver();
        new splash_GUI().setVisible(true);
        
    }
    
}
