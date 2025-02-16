/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.MovimentacaoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ConexaoBancoDeDados;

public class MovimentacaoController {
    
    // Método para registrar a movimentação de estoque
    public boolean registrarMovimentacao(MovimentacaoEstoque movimentacao) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoBancoDeDados.getConnection();
            if (conn == null) {
                throw new SQLException("Não foi possível estabelecer a conexão com o banco de dados.");
            }// fim do if

            // Insere a movimentação na tabela MovimentacoesEstoque
            String sql = "INSERT INTO MovimentacoesEstoque (IdProduto, TipoMovimentacao, CodigoSolicitante, CodigoProduto, Quantidade, IdUsuario) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, movimentacao.getIdProduto());
            stmt.setInt(2, movimentacao.getTipoMovimentacao());
            stmt.setString(3, movimentacao.getCodigoSolicitante());
            stmt.setString(4, movimentacao.getCodigoProduto());
            stmt.setInt(5, movimentacao.getQuantidade());
            stmt.setInt(6, movimentacao.getIdUsuario());

            int resultado = stmt.executeUpdate();

            if (resultado > 0) {
                // Atualiza o estoque do produto na tabela Produtos
                atualizarEstoque(movimentacao, conn);
                return true;
            } else {
                return false;
            }// fim do if else
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }// fim do try/catch
        finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt);
        }// fim do finaly e fechando a conexão com o banco de dados
    }// fim do registrarMovimentacao

    // Método para atualizar o estoque do produto na tabela Produtos
    private void atualizarEstoque(MovimentacaoEstoque movimentacao, Connection conn) {
        PreparedStatement stmt = null;

        try {
            String sql;
            if (movimentacao.getTipoMovimentacao() == 1) { // Entrada
                sql = "UPDATE Produtos SET EstoqueAtual = EstoqueAtual + ? WHERE ProdutoID = ?";
            } else { // Saída
                sql = "UPDATE Produtos SET EstoqueAtual = EstoqueAtual - ? WHERE ProdutoID = ?";
            }// fim do if else

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, movimentacao.getQuantidade());
            stmt.setInt(2, movimentacao.getIdProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } // fim do try/catch
        finally {
            ConexaoBancoDeDados.fecharConexao(null, stmt);
        }// fim do finaly e fechando a conexão com o banco de dados
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
            }// fim do if

            String sql = "SELECT EstoqueAtual FROM Produtos WHERE ProdutoID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("EstoqueAtual");
            } else {
                return -1; // Retorna -1 caso o produto não seja encontrado
            }// fim do if else
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } // fim do try catch
        finally {
            ConexaoBancoDeDados.fecharConexao(conn, stmt, rs);
        }// fim do finaly e fechando a conexão com o banco de dados
        
    }// fim do buscarEstoqueAtual
    
}// fim da classe MovimentacaoController

