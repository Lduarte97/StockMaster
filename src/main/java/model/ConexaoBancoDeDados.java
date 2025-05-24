/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lldua
 */
public class ConexaoBancoDeDados {
    private static final String URL=
            "jdbc:sqlserver://localhost:1433;databaseName=StockMaster;"
            + "integratedSecurity=true;"
            + "trustServerCertificate=true";
    public static Connection getConnection(){
        // criando um objeto de tipo Connection
        Connection conection = null;
        // try/ catch é como se fosse um if else, só que para erros no sistema
        
        try{// se não der erro nenhum
            // estabeleça a conexão com o banco de dados
            conection = DriverManager.getConnection(URL);
            System.out.println("Conexão bem sucedida!");
        }catch (SQLException e){/* caso dê algum erro. SQLException captura o erro e 
            joga ele na variável "e", que é chamada no print para mostrar ao 
            usuário qual é o erro */
            System.out.println("Erro de conexão!"+e);
        }
        return conection;
        
    }// final do getConnecti
public static void fecharConexao(Connection conn) {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    
    public static void fecharConexao(Connection conn, PreparedStatement stmt) {
        fecharConexao(conn);
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //throw new UnsupportedOperationException("Not supported yet...."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }   

    public static void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(conn, stmt);
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    
    
}
