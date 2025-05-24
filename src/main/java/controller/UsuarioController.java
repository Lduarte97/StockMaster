package Controller;


import Model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.ConexaoBancoDeDados;



/**
 *
 * @author devmat
 */
public class UsuarioController {
    
    // Método para verificar se o nome de usuário já existe no banco de dados
    public boolean verificarNomeUsuario(String nomeUsuario) {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE NomeDeUsuario = ?";
        
        try (Connection conexao = ConexaoBancoDeDados.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o nome de usuário já existir
            }// fim do if
        } catch (SQLException e) {
            e.printStackTrace();
        }// fim do try/catch

        return false; // Retorna false caso o nome de usuário não exista
    }// fim do verificarNomeUsuario

   // Método para cadastrar um novo usuário
public Usuarios cadastrarUsuario(Usuarios usuario) {
    if (usuario == null) {
        System.out.println("Erro: Usuário recebido é nulo.");
        return null;
    }// fim do if

    int isAdminValue = usuario.getIsAdmin();
    String codigoUsuarioGerado = null;

    try {
        codigoUsuarioGerado = gerarCodigoUsuario();
    } catch (SQLException e) {
        System.out.println("Erro ao gerar código de usuário: " + e.getMessage());
       //return null;
    }// fim do try/catch
    // insere na coluna CodigodoUsuario um código que foi gerado automaticamente
    usuario.setCodigoUsuario(codigoUsuarioGerado);
    String sql = "INSERT INTO Usuarios (Nome, Email, NomeDeUsuario, Senha, IsAdmin, CodigoUsuario) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conexao = ConexaoBancoDeDados.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getNomeDeUsuario());
        stmt.setString(4, usuario.getSenha());
        stmt.setInt(5, isAdminValue);
        stmt.setString(6, codigoUsuarioGerado);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setIdUsuario(generatedKeys.getInt(1));
                    System.out.println("Usuário cadastrado com sucesso: " + usuario.getNomeDeUsuario());
                    return usuario;
                    
                }// fim do 2° if
                
            }// fim do try
            
        }// fim do 1º if
    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
    }// fim do try/catch

    System.out.println("Erro: Usuário não foi cadastrado corretamente.");
    return null;
}// fim do cadastrarUsuario

    // Método para gerar um código automaticamente para o usuário no momento do cadastro
    private String gerarCodigoUsuario() throws SQLException {
    String ultimoCodigo = null;
    String sql = "SELECT TOP 1 CodigoUsuario FROM Usuarios ORDER BY IdUsuario DESC";

    try (Connection conexao = ConexaoBancoDeDados.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        if (rs.next()) {
            ultimoCodigo = rs.getString("CodigoUsuario");
        }// fim do if
    } catch (Exception e) {
        System.out.println("Erro ao gerar código: " + e);
    }// fim do try/catch

    // Se for o primeiro usuário, começa com "1000"
    if (ultimoCodigo == null) {
        return "1000";
    } else {
        int novoCodigo = Integer.parseInt(ultimoCodigo) + 1;
        return String.format("%04d", novoCodigo); // Garante 4 dígitos
    }// fim do if/else
    
}// fim do gerarCodigoUsuario()

    // Método para alterar dados de um usuário
    public void alterarUsuario(Usuarios usuario) {
        if (usuario.getIdUsuario() == 0) {
        System.out.println("Erro: O ID do usuário não foi definido corretamente.");
        return;
    }// fim do if

    String sql = "UPDATE Usuarios SET Nome = ?, Email = ?, NomeDeUsuario = ?, Senha = ?, IsAdmin = ? WHERE IdUsuario = ?";

    try (Connection conexao = ConexaoBancoDeDados.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getNomeDeUsuario());
        stmt.setString(4, usuario.getSenha());
        stmt.setInt(5, usuario.getIsAdmin());
        stmt.setInt(6, usuario.getIdUsuario());// ID deve ser corretamente passado aqui

        int rowsUpdated = stmt.executeUpdate();
        
        if (rowsUpdated > 0) {
            System.out.println("Usuário atualizado com sucesso!");
        } else {
            System.out.println("Nenhum usuário encontrado com o ID especificado.");
        }// fim do if else
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        e.printStackTrace();
    }// fim do try/catch
 }// fim do alterarUsuario

    // Método para excluir um usuário
    public boolean excluirUsuario(int idUsuario) throws SQLException {
    String query = "DELETE FROM Usuarios WHERE IdUsuario=? ";
    try (Connection conection = ConexaoBancoDeDados.getConnection();
         PreparedStatement preparedStatement = conection.prepareStatement(query)) {

        preparedStatement.setInt(1, idUsuario);
        int excluiu = preparedStatement.executeUpdate();
        return excluiu > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }// fim do try/catch
}// fim do excluirUsuario
    
    // método para listar os usuários cadastrados
     public List<Usuarios> listarUsuarios() {
        String query = "SELECT * FROM Usuarios"; // Corrigido para a tabela de Usuarios
        List<Usuarios> lista = new ArrayList<>();

        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Passando os dados do ResultSet para objetos Usuario
            while (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setNomeDeUsuario(resultSet.getString("NomeDeUsuario"));
                usuario.setSenha(resultSet.getString("Senha"));
                usuario.setIsAdmin(resultSet.getInt("IsAdmin"));

                lista.add(usuario);
            }// fim do while
            return lista;
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            return null;
        }// fim do try/catch
    }// fim do listarUsuarios()
     
            // método para editar os dados dos usuários já cadastrados
            public boolean editarUsuarios(Usuarios usuario){
           String query = "UPDATE Usuarios SET Nome=?, Email=?, NomeDeUsuario=?, Senha=? WHERE IdUsuario=?";
           try (Connection connection = ConexaoBancoDeDados.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

               // Definindo os valores para a query
               preparedStatement.setString(1, usuario.getNome());
               preparedStatement.setString(2, usuario.getEmail());  // Alterado para String
               preparedStatement.setString(3, usuario.getNomeDeUsuario());  // Alterado para String
               preparedStatement.setString(4, usuario.getSenha());  // Alterado para String
               preparedStatement.setInt(5, usuario.getIdUsuario());  // Utilizando o ID para identificar o usuário

               // Executando o update e retornando true se o usuário for atualizado
               int linhasAfetadas = preparedStatement.executeUpdate();
               return linhasAfetadas > 0; // Retorna true se a atualização foi bem-sucedida
           } catch (SQLException e) {
               System.err.println("Erro ao atualizar o usuário: " + e.getMessage());
               return false;
         }// fim do try/catch
     }// fim do editarUsuarios
            
        // método para listar os usuários cadastrados 
        public List<Usuarios> listarUsuarioNome(String nome){
    String query = "SELECT * FROM Usuarios WHERE Nome LIKE ?";
    List<Usuarios> lista = new ArrayList<>();
    
        try (Connection conexao = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

            // Passando o valor para o SELECT (com a cláusula LIKE)
            preparedStatement.setString(1, "%" + nome + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setNomeDeUsuario(resultSet.getString("NomeDeUsuario"));
                usuario.setSenha(resultSet.getString("Senha"));
                usuario.setIsAdmin(resultSet.getInt("IsAdmin"));

                lista.add(usuario);
            }// fim do while

            return lista;
        } catch (SQLException e) {
            System.err.println("Não foi possível encontrar usuários: " + e.getMessage());
            return null;
        }// fim do try/catch
    }// fim do listarUsuarioNome
        
    // Método para buscar um usuário pelo nome de usuário
    // Adicionei este método que será útil para o filtro
        public Usuarios buscarUsuarioPorNomeDeUsuario(String nomeDeUsuario) {
        String query = "SELECT IdUsuario, Nome, Email, NomeDeUsuario, Senha, IsAdmin, CodigoUsuario FROM Usuarios WHERE NomeDeUsuario = ?";
        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nomeDeUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setNomeDeUsuario(resultSet.getString("NomeDeUsuario"));
                usuario.setSenha(resultSet.getString("Senha"));
                usuario.setIsAdmin(resultSet.getInt("IsAdmin"));
                usuario.setCodigoUsuario(resultSet.getString("CodigoUsuario"));
                return usuario;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por nome de usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
public Usuarios buscarUsuarioPorId(int idUsuario) {
    String query = "SELECT IdUsuario, NomeDeUsuario FROM Usuarios WHERE IdUsuario = ?";
    try (Connection connection = ConexaoBancoDeDados.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, idUsuario);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Usuarios usuario = new Usuarios();
            usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
            usuario.setNomeDeUsuario(resultSet.getString("NomeDeUsuario"));
            return usuario;
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
        e.printStackTrace();
    }
    return null;
}

        
}// fim da classe public class UsuarioController
