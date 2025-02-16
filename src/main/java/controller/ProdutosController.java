/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ConexaoBancoDeDados;

/**
 *
 * @author devmat
 */
public class ProdutosController {

    
    private Iterable<Produtos> listaProdutos;
   public boolean cadastrandoProdutos(Produtos produto) {
        String query = "INSERT INTO Produtos (" +
                       "TipoProduto, MarcaProduto, Codigo, CodigoFornecedor, " +
                       "UnidadeVenda, DataCadastro, PrecoCusto, PrecoAtacado, " +
                       "PrecoVarejo, PrecoDistribuidora, EstoqueMaximo, " +
                       "EstoqueMinimo, EstoqueAtual, FotoProduto, Categoria) " + // Adicionando Categoria
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparadoStatement = connection.prepareStatement(query)) {
            
            // Definindo os valores para o PreparedStatement
            preparadoStatement.setString(1, produto.getTipoProduto());
            preparadoStatement.setString(2, produto.getMarcaProduto());
            preparadoStatement.setString(3, produto.getCodigo());
            preparadoStatement.setString(4, produto.getCodigoFornecedor());
            preparadoStatement.setString(5, produto.getUnidadeVenda());
            preparadoStatement.setString(6, produto.getDataCadastro());
            preparadoStatement.setDouble(7, produto.getPrecoCusto());
            preparadoStatement.setDouble(8, produto.getPrecoAtacado());
            preparadoStatement.setDouble(9, produto.getPrecoVarejo());
            preparadoStatement.setDouble(10, produto.getPrecoDistribuidora());
            preparadoStatement.setInt(11, produto.getEstoqueMaximo());
            preparadoStatement.setInt(12, produto.getEstoqueMinimo());
            preparadoStatement.setInt(13, produto.getEstoqueAtual());
            preparadoStatement.setString(14, produto.getFotoProduto());
            preparadoStatement.setString(15, produto.getCategoria()); // Adicionando Categoria
            
            // Verifica se o INSERT foi executado
            int rowsAffected = preparadoStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Imprimindo erro ao inserir
            System.err.println("Erro ao inserir os dados dos produtos: " + e);
            return false;
        }
    }
    public Produtos buscarProdutosPorNomeOuCodigo(String termo) {
    String query = "SELECT * FROM Produtos WHERE Codigo = ? OR TipoProduto = ?";
    Produtos produto = null;

    try (Connection connection = ConexaoBancoDeDados.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
         
        // Definindo os parâmetros para o código e o tipo de produto
        preparedStatement.setString(1, termo);
        preparedStatement.setString(2, termo);
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // Se encontrar um produto, cria o objeto e popula com os dados do banco
                produto = new Produtos();
                produto.setProdutoID(resultSet.getInt("produtoID"));
                produto.setTipoProduto(resultSet.getString("tipoProduto"));
                produto.setMarcaProduto(resultSet.getString("marcaProduto"));
                produto.setCodigo(resultSet.getString("codigo"));
                produto.setCodigoFornecedor(resultSet.getString("codigoFornecedor"));
                produto.setUnidadeVenda(resultSet.getString("unidadeVenda"));
                produto.setDataCadastro(resultSet.getString("dataCadastro"));
                produto.setPrecoCusto(resultSet.getDouble("precoCusto"));
                produto.setPrecoAtacado(resultSet.getDouble("precoAtacado"));
                produto.setPrecoVarejo(resultSet.getDouble("precoVarejo"));
                produto.setPrecoDistribuidora(resultSet.getDouble("precoDistribuidora"));
                produto.setEstoqueMaximo(resultSet.getInt("estoqueMaximo"));
                produto.setEstoqueMinimo(resultSet.getInt("estoqueMinimo"));
                produto.setEstoqueAtual(resultSet.getInt("estoqueAtual"));
                produto.setFotoProduto(resultSet.getString("fotoProduto"));
                produto.setCategoria(resultSet.getString("categoria"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar produto: " + e.getMessage());
    }

    return produto; // Retorna o produto encontrado ou null se não encontrar
}

    
   /** private String gerarCodigoUsuario() throws SQLException {
        String ultimoCodigo = null;
        String sql = "SELECT TOP 1 Codigo FROM Produtos ORDER BY ProdutoID DESC";
        try (Connection conexao = ConexaoBancoDeDados.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                ultimoCodigo = rs.getString("Codigo");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao gerar código: " + e);
        }
        
        
        
        // Se for o primeiro produto, começa com "0001"
        if (ultimoCodigo == null) {
            return "0001";
        } else {
            int novoCodigo = Integer.parseInt(ultimoCodigo) + 1;
            return String.format("%04d", novoCodigo); // Garante 4 dígitos
        }
    }**/
        
        public List<Produtos> listarProdutos() {
    // Consulta SQL para selecionar todos os campos da tabela Produtos
    String query = "SELECT * FROM Produtos;";
    List<Produtos> lista = new ArrayList<>();

    try (Connection connection = ConexaoBancoDeDados.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        // Iterando sobre o resultado da consulta e adicionando os produtos na lista
        while (resultSet.next()) {
            Produtos produto = new Produtos();
            produto.setProdutoID(resultSet.getInt("ProdutoID"));
            produto.setTipoProduto(resultSet.getString("TipoProduto"));
            produto.setMarcaProduto(resultSet.getString("MarcaProduto"));
            produto.setCodigo(resultSet.getString("Codigo"));
            produto.setCodigoFornecedor(resultSet.getString("CodigoFornecedor"));
            produto.setUnidadeVenda(resultSet.getString("UnidadeVenda"));
            produto.setDataCadastro(resultSet.getString("DataCadastro"));
            produto.setPrecoCusto(resultSet.getDouble("PrecoCusto"));
            produto.setPrecoAtacado(resultSet.getDouble("PrecoAtacado"));
            produto.setPrecoVarejo(resultSet.getDouble("PrecoVarejo"));
            produto.setPrecoDistribuidora(resultSet.getDouble("PrecoDistribuidora"));
            produto.setEstoqueMaximo(resultSet.getInt("EstoqueMaximo"));
            produto.setEstoqueMinimo(resultSet.getInt("EstoqueMinimo"));
            produto.setEstoqueAtual(resultSet.getInt("EstoqueAtual"));
            produto.setFotoProduto(resultSet.getString("FotoProduto"));
            produto.setCategoria(resultSet.getString("Categoria"));

            // Adicionando o produto à lista
            lista.add(produto);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao listar produtos: " + e.getMessage());
    }

    return lista;
}
     public List<Produtos> listarProdutosNome(String termo) {
    // Consulta ajustada para procurar pelo tipo, marca ou código do produto
    String query = "SELECT * FROM Produtos WHERE TipoProduto LIKE ? OR MarcaProduto LIKE ? OR Codigo LIKE ?;";

    List<Produtos> lista = new ArrayList<>();

    try (Connection connection = ConexaoBancoDeDados.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        // Passando o parâmetro de busca para os três campos (TipoProduto, MarcaProduto e Codigo)
        preparedStatement.setString(1, "%" + termo + "%");
        preparedStatement.setString(2, "%" + termo + "%");
        preparedStatement.setString(3, "%" + termo + "%");

        // Executando a consulta
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterando sobre o resultado da consulta e adicionando os produtos na lista
        while (resultSet.next()) {
            Produtos produto = new Produtos();
            produto.setProdutoID(resultSet.getInt("ProdutoID"));
            produto.setTipoProduto(resultSet.getString("TipoProduto"));
            produto.setMarcaProduto(resultSet.getString("MarcaProduto"));
            produto.setCodigo(resultSet.getString("Codigo"));
            produto.setCodigoFornecedor(resultSet.getString("CodigoFornecedor"));
            produto.setUnidadeVenda(resultSet.getString("UnidadeVenda"));
            produto.setDataCadastro(resultSet.getString("DataCadastro"));
            produto.setPrecoCusto(resultSet.getDouble("PrecoCusto"));
            produto.setPrecoAtacado(resultSet.getDouble("PrecoAtacado"));
            produto.setPrecoVarejo(resultSet.getDouble("PrecoVarejo"));
            produto.setPrecoDistribuidora(resultSet.getDouble("PrecoDistribuidora"));
            produto.setEstoqueMaximo(resultSet.getInt("EstoqueMaximo"));
            produto.setEstoqueMinimo(resultSet.getInt("EstoqueMinimo"));
            produto.setEstoqueAtual(resultSet.getInt("EstoqueAtual"));
            produto.setFotoProduto(resultSet.getString("FotoProduto"));
            produto.setCategoria(resultSet.getString("Categoria"));

            // Adicionando o produto à lista
            lista.add(produto);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao listar produtos: " + e.getMessage());
    }

    return lista;
}
}     