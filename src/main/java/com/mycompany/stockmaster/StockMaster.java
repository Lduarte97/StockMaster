/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stockmaster;

import View.TelaDeLogin;
import java.sql.Connection;
import static model.ConexaoBancoDeDados.getConnection;

/**
 *
 * @author lldua
 */
public class StockMaster {

    public static void main(String[] args) {
        
        TelaDeLogin telalogin = new TelaDeLogin();
        // deixando a tela visivel
        
        telalogin.setVisible(true);
        
        Connection conexao =getConnection();
        if(conexao!=null){
            System.out.println("Conexão deu certo!");
        }else{
            System.out.println("Conexão deu Errado!");
        }
    }
}
