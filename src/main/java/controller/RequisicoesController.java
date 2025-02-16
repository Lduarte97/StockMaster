package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Requisicoes;

/**
 *
 * @author devmat
 */
public class RequisicoesController {

    private Connection conexao;

    public RequisicoesController(Connection conexao) {
        this.conexao = conexao;
    }

    // Método para criar uma nova requisição
    public boolean criarRequisicao(Requisicoes requisicao) {
        String sql = "INSERT INTO Requisicoes (IdUsuario, CodigoSolicitante, DataRequisicao, StatusReq) VALUES (?, ?, GETDATE(), 'Pendente')";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, requisicao.getIdUsuario());
            stmt.setString(2, requisicao.getCodigoSolicitante());

            stmt.executeUpdate();
            System.out.println("Requisição criada com sucesso!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar requisição: " + e.getMessage());
            return false;
        }
    }

    // Método para atualizar o status da requisição
    public boolean atualizarStatusRequisicao(int idRequisicao, String novoStatus) {
        String sql = "UPDATE Requisicoes SET StatusReq = ? WHERE IdRequisicao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, idRequisicao);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todas as requisições
    public List<Requisicoes> listarRequisicoes() {
        List<Requisicoes> requisicoes = new ArrayList<>();
        String sql = "SELECT * FROM Requisicoes";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Requisicoes req = new Requisicoes();
                req.setIdRequisicao(rs.getInt("IdRequisicao"));
                req.setIdUsuario(rs.getInt("IdUsuario"));
                req.setCodigoSolicitante(rs.getString("CodigoSolicitante"));
                req.setStatusReq(rs.getString("StatusReq"));
                req.setDataRequisicao(rs.getString("DataRequisicao"));

                requisicoes.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requisicoes;
    }

    // Método para gerar relatório de requisições
    public void gerarRelatorio() {
        List<Requisicoes> requisicoes = listarRequisicoes();

        System.out.println("===== Relatório de Requisições =====");
        for (Requisicoes req : requisicoes) {
            System.out.println(req);
        }
        System.out.println("====================================");
    }
}
