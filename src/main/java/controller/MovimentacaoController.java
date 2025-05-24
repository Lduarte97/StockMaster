/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.Usuarios;
import model.MovimentacaoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ConexaoBancoDeDados;
import java.time.LocalDateTime;
import java.sql.Timestamp; // Para usar com o PreparedStatement
import java.util.ArrayList;
import java.util.List;
import Controller.UsuarioController;

public class MovimentacaoController {

     // Instância do UsuarioController para buscar informações de usuários
    private UsuarioController usuarioController;

    public MovimentacaoController() {
        // Inicializa o UsuarioController. Certifique-se de que UsuarioController
        // está no pacote correto ou importado.
        this.usuarioController = new UsuarioController();
    }

    // Método para registrar a movimentação de estoque
    public void registrarMovimentacao(MovimentacaoEstoque movimentacao) {
        Connection conn = null;
        PreparedStatement stmtMovimentacao = null;
        // PreparedStatement stmtAtualizacao = null; // Removido, pois foi substituído por stmtUpdate
        ResultSet rsEstoqueAtual = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();
            conn.setAutoCommit(false); // Inicia a transação
            // 1. Insere a movimentação na tabela MovimentacoesEstoque
            // Corrigido para incluir IdMovimentacao como IDENTITY (se for o caso) ou auto-incremento
            // e para usar os nomes de coluna corretos
            String sqlMovimentacao = "INSERT INTO MovimentacoesEstoque (IdProduto, TipoMovimentacao, CodigoSolicitante, CodigoProduto, Quantidade, IdUsuario, NomeProduto, DataMov) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmtMovimentacao = conn.prepareStatement(sqlMovimentacao);
            stmtMovimentacao.setInt(1, movimentacao.getIdProduto());
            stmtMovimentacao.setInt(2, movimentacao.getTipoMovimentacao());
            stmtMovimentacao.setString(3, movimentacao.getCodigoSolicitante());
            stmtMovimentacao.setString(4, movimentacao.getCodigoProduto());
            stmtMovimentacao.setInt(5, movimentacao.getQuantidade());
            stmtMovimentacao.setInt(6, movimentacao.getIdUsuario());
            stmtMovimentacao.setString(7, movimentacao.getNomeProduto());

            // Define a DataMov corretamente no PreparedStatement usando LocalDateTime
            if (movimentacao.getDataMov() != null) {
                Timestamp timestamp = Timestamp.valueOf(movimentacao.getDataMov());
                stmtMovimentacao.setTimestamp(8, timestamp);
            } else {
                stmtMovimentacao.setNull(8, java.sql.Types.TIMESTAMP);
                System.err.println("Atenção: DataMov está nula. Inserindo NULL.");
            }

            stmtMovimentacao.executeUpdate();

            System.out.println("Movimentação registrada com sucesso.");

            // 2. Buscar o estoque atual do produto (agora usando a mesma conexão)
            String sqlEstoqueAtual = "SELECT EstoqueAtual FROM Produtos WHERE ProdutoID = ?";
            PreparedStatement stmtBuscarEstoque = conn.prepareStatement(sqlEstoqueAtual); // Nova instância
            stmtBuscarEstoque.setInt(1, movimentacao.getIdProduto());
            rsEstoqueAtual = stmtBuscarEstoque.executeQuery();

            if (rsEstoqueAtual.next()) {
                int estoqueAtual = rsEstoqueAtual.getInt("EstoqueAtual");
                int novaQuantidadeEstoque;

                if (movimentacao.getTipoMovimentacao() == 1) { // Entrada
                    novaQuantidadeEstoque = estoqueAtual + movimentacao.getQuantidade();
                } else { // Saída
                    if (estoqueAtual < movimentacao.getQuantidade()) {
                        throw new SQLException("Estoque insuficiente para realizar a movimentação.");
                    }
                    novaQuantidadeEstoque = estoqueAtual - movimentacao.getQuantidade();
                }

                // 3. Atualizar o estoque do produto na tabela Produtos (usando a mesma conexão)
                String sqlAtualizacao = "UPDATE Produtos SET EstoqueAtual = ? WHERE ProdutoID = ?";
                PreparedStatement stmtUpdate = conn.prepareStatement(sqlAtualizacao); // Nova instância
                stmtUpdate.setInt(1, novaQuantidadeEstoque);
                stmtUpdate.setInt(2, movimentacao.getIdProduto());
                stmtUpdate.executeUpdate();
                stmtUpdate.close(); // Fecha o stmtUpdate

                System.out.println("Estoque atualizado com sucesso.");
                conn.commit(); // Confirma a transação
                JOptionPane.showMessageDialog(null, "Movimentação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } else {
                conn.rollback(); // Desfaz a transação se o produto não for encontrado
                JOptionPane.showMessageDialog(null, "Erro ao buscar produto. Movimentação cancelada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Desfaz a transação em caso de erro
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Log do erro de rollback
                }
            }
            JOptionPane.showMessageDialog(null, "Erro ao realizar a movimentação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        } finally {
            // Fechar recursos
            try {
                if (rsEstoqueAtual != null) rsEstoqueAtual.close();
                // O stmtAtualizacao foi dividido em stmtBuscarEstoque e stmtUpdate.
                // stmtUpdate já foi fechado. Fechar stmtBuscarEstoque aqui.
                if (stmtMovimentacao != null) stmtMovimentacao.close();
                // Fechar stmtBuscarEstoque aqui (ou onde ele for criado se for dentro do try)
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexaoBancoDeDados.fecharConexao(conn); // Fecha a conexão
        }
    }//fim do registrarMovimentacao

    // Método para listar todas as movimentações
    public List<MovimentacaoEstoque> listarMovimentacoes() {
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT IdMovimentacao, IdProduto, NomeProduto, CodigoSolicitante, TipoMovimentacao, DataMov, IdUsuario, Quantidade, CodigoProduto FROM MovimentacoesEstoque ORDER BY DataMov DESC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                MovimentacaoEstoque mov = new MovimentacaoEstoque();
                mov.setIdMovimentacao(rs.getInt("IdMovimentacao"));
                mov.setIdProduto(rs.getInt("IdProduto")); // Adicionado
                mov.setNomeProduto(rs.getString("NomeProduto"));
                mov.setCodigoSolicitante(rs.getString("CodigoSolicitante"));
                mov.setTipoMovimentacao(rs.getInt("TipoMovimentacao"));
                mov.setIdUsuario(rs.getInt("IdUsuario"));
                mov.setQuantidade(rs.getInt("Quantidade"));
                mov.setCodigoProduto(rs.getString("CodigoProduto")); // Adicionado

                // Converter Timestamp do banco para LocalDateTime
                java.sql.Timestamp timestamp = rs.getTimestamp("DataMov");
                if (timestamp != null) {
                    mov.setDataMov(timestamp.toLocalDateTime());
                }
                movimentacoes.add(mov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar movimentações: " + e.getMessage());
        } finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt, rs); // Melhor forma de fechar todos os recursos
        }
        return movimentacoes;
    }//listarMovimentacoes()

    //BLOCO DE METODOS DE LISTAGENS DOS FILTROS DA TELAREQUISICOES
    public List<MovimentacaoEstoque> filtrarPorNomeOuCodigoProduto(String termoPesquisa) {
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT IdMovimentacao, IdProduto, NomeProduto, CodigoSolicitante, TipoMovimentacao, DataMov, IdUsuario, Quantidade, CodigoProduto " +
                         "FROM MovimentacoesEstoque " +
                         "WHERE NomeProduto LIKE ? OR CodigoProduto LIKE ? ORDER BY DataMov DESC"; // Use CodigoProduto aqui

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + termoPesquisa + "%");
            stmt.setString(2, "%" + termoPesquisa + "%"); // Busca também por CodigoProduto

            rs = stmt.executeQuery();

            while (rs.next()) {
                MovimentacaoEstoque mov = new MovimentacaoEstoque();
                mov.setIdMovimentacao(rs.getInt("IdMovimentacao"));
                mov.setIdProduto(rs.getInt("IdProduto"));
                mov.setNomeProduto(rs.getString("NomeProduto"));
                mov.setCodigoSolicitante(rs.getString("CodigoSolicitante"));
                mov.setTipoMovimentacao(rs.getInt("TipoMovimentacao"));
                mov.setIdUsuario(rs.getInt("IdUsuario"));
                mov.setQuantidade(rs.getInt("Quantidade"));
                mov.setCodigoProduto(rs.getString("CodigoProduto"));

                java.sql.Timestamp timestamp = rs.getTimestamp("DataMov");
                if (timestamp != null) {
                    mov.setDataMov(timestamp.toLocalDateTime());
                }
                movimentacoes.add(mov);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao filtrar movimentações por nome/código do produto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt, rs);
        }
        return movimentacoes;
    }//fim do filtrarPorNomeOuCodigoProduto

 
    public List<MovimentacaoEstoque> filtrarPorUsuario(int idUsuario) {
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT IdMovimentacao, IdProduto, NomeProduto, CodigoSolicitante, TipoMovimentacao, DataMov, IdUsuario, Quantidade, CodigoProduto " +
                         "FROM MovimentacoesEstoque WHERE IdUsuario = ? ORDER BY DataMov DESC";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            rs = stmt.executeQuery();

            while (rs.next()) {
                MovimentacaoEstoque mov = new MovimentacaoEstoque();
                mov.setIdMovimentacao(rs.getInt("IdMovimentacao"));
                mov.setIdProduto(rs.getInt("IdProduto"));
                mov.setNomeProduto(rs.getString("NomeProduto"));
                mov.setCodigoSolicitante(rs.getString("CodigoSolicitante"));
                mov.setTipoMovimentacao(rs.getInt("TipoMovimentacao"));
                mov.setIdUsuario(rs.getInt("IdUsuario"));
                mov.setQuantidade(rs.getInt("Quantidade"));
                mov.setCodigoProduto(rs.getString("CodigoProduto"));

                java.sql.Timestamp timestamp = rs.getTimestamp("DataMov");
                if (timestamp != null) {
                    mov.setDataMov(timestamp.toLocalDateTime());
                }
                movimentacoes.add(mov);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao filtrar movimentações por usuário: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt, rs);
        }
        return movimentacoes;
    }//fim do filtrarPorUsuario

    public List<MovimentacaoEstoque> filtrarPorTipoMovimentacao(int tipoMovimentacao) {
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT IdMovimentacao, IdProduto, NomeProduto, CodigoSolicitante, TipoMovimentacao, DataMov, IdUsuario, Quantidade, CodigoProduto " +
                         "FROM MovimentacoesEstoque WHERE TipoMovimentacao = ? ORDER BY DataMov DESC";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tipoMovimentacao);

            rs = stmt.executeQuery();

            while (rs.next()) {
                MovimentacaoEstoque mov = new MovimentacaoEstoque();
                mov.setIdMovimentacao(rs.getInt("IdMovimentacao"));
                mov.setIdProduto(rs.getInt("IdProduto"));
                mov.setNomeProduto(rs.getString("NomeProduto"));
                mov.setCodigoSolicitante(rs.getString("CodigoSolicitante"));
                mov.setTipoMovimentacao(rs.getInt("TipoMovimentacao"));
                mov.setIdUsuario(rs.getInt("IdUsuario"));
                mov.setQuantidade(rs.getInt("Quantidade"));
                mov.setCodigoProduto(rs.getString("CodigoProduto"));

                java.sql.Timestamp timestamp = rs.getTimestamp("DataMov");
                if (timestamp != null) {
                    mov.setDataMov(timestamp.toLocalDateTime());
                }
                movimentacoes.add(mov);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao filtrar movimentações por tipo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt, rs);
        }
        return movimentacoes;
    }// fim do filtrarPorTipoMovimentacao

    public List<MovimentacaoEstoque> filtrarPorMesEAno(int mes, int ano) {
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();
            // Assumindo SQL Server para as funções de data. Se for outro SGBD, altere aqui.
            // Para MySQL/PostgreSQL, usar: "WHERE EXTRACT(MONTH FROM DataMov) = ? AND EXTRACT(YEAR FROM DataMov) = ?"
            String sql = "SELECT IdMovimentacao, IdProduto, NomeProduto, CodigoSolicitante, TipoMovimentacao, DataMov, IdUsuario, Quantidade, CodigoProduto " +
                         "FROM MovimentacoesEstoque " +
                         "WHERE MONTH(DataMov) = ? AND YEAR(DataMov) = ? ORDER BY DataMov DESC";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);

            rs = stmt.executeQuery();

            while (rs.next()) {
                MovimentacaoEstoque mov = new MovimentacaoEstoque();
                mov.setIdMovimentacao(rs.getInt("IdMovimentacao"));
                mov.setIdProduto(rs.getInt("IdProduto"));
                mov.setNomeProduto(rs.getString("NomeProduto"));
                mov.setCodigoSolicitante(rs.getString("CodigoSolicitante"));
                mov.setTipoMovimentacao(rs.getInt("TipoMovimentacao"));
                mov.setIdUsuario(rs.getInt("IdUsuario"));
                mov.setQuantidade(rs.getInt("Quantidade"));
                mov.setCodigoProduto(rs.getString("CodigoProduto"));

                java.sql.Timestamp timestamp = rs.getTimestamp("DataMov");
                if (timestamp != null) {
                    mov.setDataMov(timestamp.toLocalDateTime());
                }
                movimentacoes.add(mov);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao filtrar movimentações por mês e ano: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt, rs);
        }
        return movimentacoes;
    }// fim do filtrarPorMesEAno

    //FIM DO BLOCO DE METODOS DE LISTAGENS DOS FILTROS DA TELAREQUISICOES
    
    // Método para buscar o nome do usuário pelo IdUsuario (na sua Controller
    public String buscarNomeUsuario(int idUsuario) {
        // Delega a busca para o UsuarioController, que agora tem um método para isso
        Usuarios usuario = usuarioController.buscarUsuarioPorId(idUsuario);
        return (usuario != null) ? usuario.getNomeDeUsuario() : "Desconhecido";
    }// fim do buscarNomeUsuario

    // mas foi mantido aqui caso ainda seja chamado externamente por outro lugar.
    private void atualizarEstoque(MovimentacaoEstoque movimentacao) throws SQLException {
        // Nota: Esta lógica de atualização de estoque já está contida em `registrarMovimentacao`
        // dentro de uma transação. Considerar remover este método ou reavaliar seu uso.
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();
            int estoqueAtual = buscarEstoqueAtual(movimentacao.getIdProduto());
            if (movimentacao.getTipoMovimentacao() == 2 && estoqueAtual < movimentacao.getQuantidade()) {
                throw new SQLException("Estoque insuficiente para realizar a movimentação.");
            }
            String sql;
            if (movimentacao.getTipoMovimentacao() == 1) { // Entrada
                sql = "UPDATE Produtos SET EstoqueAtual = EstoqueAtual + ? WHERE ProdutoID = ?";
            } else { // Saída
                sql = "UPDATE Produtos SET EstoqueAtual = EstoqueAtual - ? WHERE ProdutoID = ?";
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, movimentacao.getQuantidade());
            stmt.setInt(2, movimentacao.getIdProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lança a exceção para que o chamador saiba que algo deu errado
        } finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt);
        }
    }// fim do atualizarEstoque

    // Método para buscar o estoque atual do produto
    public int buscarEstoqueAtual(int idProduto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBancoDeDados.getConnection();
            if (conn == null) {
                throw new SQLException("Não foi possível estabelecer a conexão com o banco de dados.");
            }

            String sql = "SELECT EstoqueAtual FROM Produtos WHERE ProdutoID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("EstoqueAtual");
            } else {
                return -1; // Retorna -1 caso o produto não seja encontrado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt, rs);
        }
    }// fim do buscarEstoqueAtual

}// fim da classe MovimentacoesController