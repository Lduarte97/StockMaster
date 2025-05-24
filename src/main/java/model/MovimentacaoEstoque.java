/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDateTime;
/**
 *
 * @author lldua
 */
public class MovimentacaoEstoque {
     private int idMovimentacao;
    private int idProduto;
    private int tipoMovimentacao; // 1 = Entrada, 2 = Saída
    private String codigoSolicitante;
    private String codigoProduto;
    private int quantidade;
    private int idUsuario;
    private String NomeProduto;
    private LocalDateTime dataMov;

    // Construtor vazio
    public MovimentacaoEstoque() {}

    // Construtor com parâmetros
    public MovimentacaoEstoque(int idProduto, int tipoMovimentacao, String codigoSolicitante, String codigoProduto, int quantidade, int idUsuario,String NomeProduto, LocalDateTime dataMov) {
        this.idProduto = idProduto;
        this.tipoMovimentacao = tipoMovimentacao;
        this.codigoSolicitante = codigoSolicitante;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.NomeProduto = NomeProduto;
        this.dataMov = dataMov;
    }

    // Getters e Setters
    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(int tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getCodigoSolicitante() {
        return codigoSolicitante;
    }

    public void setCodigoSolicitante(String codigoSolicitante) {
        this.codigoSolicitante = codigoSolicitante;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeProduto() {
        return NomeProduto;
    }

    public void setNomeProduto(String NomeProduto) {
        this.NomeProduto = NomeProduto;
    }

    public LocalDateTime getDataMov() {
        return dataMov;
    }

    public void setDataMov(LocalDateTime DataMov) {
        this.dataMov = DataMov;
    }

}