package model;

import java.util.Date;

public class Requisicoes {
    private int idRequisicao;
    private int idUsuario;
    private String codigoSolicitante; // Novo atributo para armazenar o código do usuário logado
    private String dataRequisicao;
    private String statusReq;
    
    // Construtor completo
    public Requisicoes(int idRequisicao, int idUsuario, String codigoSolicitante, String dataRequisicao, String statusReq) {
        this.idRequisicao = idRequisicao;
        this.idUsuario = idUsuario;
        this.codigoSolicitante = codigoSolicitante;
        this.dataRequisicao = dataRequisicao;
        this.statusReq = statusReq;
    }

    // Construtor para novas requisições (sem ID)
    public Requisicoes(int idUsuario, String codigoSolicitante, String dataRequisicao, String statusReq) {
        this.idUsuario = idUsuario;
        this.codigoSolicitante = codigoSolicitante;
        this.dataRequisicao = dataRequisicao;
        this.statusReq = statusReq;
    }

    // Construtor vazio
    public Requisicoes() {}

    // Getters e Setters
    public int getIdRequisicao() {
        return idRequisicao;
    }

    public void setIdRequisicao(int idRequisicao) {
        this.idRequisicao = idRequisicao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoSolicitante() {
        return codigoSolicitante;
    }

    public void setCodigoSolicitante(String codigoSolicitante) {
        this.codigoSolicitante = codigoSolicitante;
    }

    public String getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(String dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public String getStatusReq() {
        return statusReq;
    }

    public void setStatusReq(String statusReq) {
        this.statusReq = statusReq;
    }
}
