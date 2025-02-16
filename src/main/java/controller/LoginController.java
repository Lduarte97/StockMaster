/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Usuarios;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ConexaoBancoDeDados;


/**
 *
 * @author devmat
 */
public class LoginController {
    // Método para autenticar o usuário
    public Usuarios authenticate(String email, String senha) { 
        String query = "SELECT * FROM Usuarios WHERE NomeDeUsuario = ? AND Senha = ?";
        
        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Substitui as interrogações (?) pelos valores informados
            preparedStatement.setString(1, email); 
            preparedStatement.setString(2, senha); 
            
            // Executa a consulta
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Recupera os dados do usuário do banco
                    int idUsuario = resultSet.getInt("IdUsuario");
                    String nome = resultSet.getString("Nome");
                    String emailUsuario = resultSet.getString("Email");
                    String nomeUsuario = resultSet.getString("NomeDeUsuario");
                    String senhaUsuario = resultSet.getString("Senha");
                    int isAdmin = resultSet.getInt("IsAdmin");
                    String codigoUsuario = resultSet.getString("CodigoUsuario");
                 

                   
                    // Retorna o objeto usuário com os dados preenchidos
                    return new Usuarios(idUsuario, nome, emailUsuario, nomeUsuario, senhaUsuario, isAdmin, codigoUsuario);
                }
            }
        } catch (SQLException e) { 
            System.err.println("Erro ao autenticar o usuário: " + e.getMessage());
        }
        return null; // Retorna null caso a autenticação falhe
    }
    
}
