/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author devmat
 */
public class Produtos {
    public int produtoID;
    private String tipoProduto;
    private String marcaProduto;
    private String codigo;
    private String codigoFornecedor;
    private String unidadeVenda;
    private String dataCadastro;
    private double precoCusto;
    private double precoAtacado;
    private double precoVarejo;
    private double precoDistribuidora;
    private int estoqueMaximo;
    private int estoqueMinimo;
    private int estoqueAtual;
    private String fotoProduto;
    private String categoria;

    public Produtos(int produtoID, String tipoProduto, String marcaProduto, String codigo, String codigoFornecedor, String unidadeVenda, String dataCadastro, double precoCusto, double precoAtacado, double precoVarejo, double precoDistribuidora, int estoqueMaximo, int estoqueMinimo, int estoqueAtual, String fotoProduto, String categoria) {
        this.produtoID = produtoID;
        this.tipoProduto = tipoProduto;
        this.marcaProduto = marcaProduto;
        this.codigo = codigo;
        this.codigoFornecedor = codigoFornecedor;
        this.unidadeVenda = unidadeVenda;
        this.dataCadastro = dataCadastro;
        this.precoCusto = precoCusto;
        this.precoAtacado = precoAtacado;
        this.precoVarejo = precoVarejo;
        this.precoDistribuidora = precoDistribuidora;
        this.estoqueMaximo = estoqueMaximo;
        this.estoqueMinimo = estoqueMinimo;
        this.estoqueAtual = estoqueAtual;
        this.fotoProduto = fotoProduto;
        this.categoria = categoria;
    }

    public Produtos(String tipoProduto, String marcaProduto, String codigo, String codigoFornecedor, String unidadeVenda, String dataCadastro, double precoCusto, double precoAtacado, double precoVarejo, double precoDistribuidora, int estoqueMaximo, int estoqueMinimo, int estoqueAtual, String fotoProduto, String categoria) {
        this.tipoProduto = tipoProduto;
        this.marcaProduto = marcaProduto;
        this.codigo = codigo;
        this.codigoFornecedor = codigoFornecedor;
        this.unidadeVenda = unidadeVenda;
        this.dataCadastro = dataCadastro;
        this.precoCusto = precoCusto;
        this.precoAtacado = precoAtacado;
        this.precoVarejo = precoVarejo;
        this.precoDistribuidora = precoDistribuidora;
        this.estoqueMaximo = estoqueMaximo;
        this.estoqueMinimo = estoqueMinimo;
        this.estoqueAtual = estoqueAtual;
        this.fotoProduto = fotoProduto;
        this.categoria = categoria;
    }

    public Produtos() {
    }

    public int getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(int produtoID) {
        this.produtoID = produtoID;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(String marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(String codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public String getUnidadeVenda() {
        return unidadeVenda;
    }

    public void setUnidadeVenda(String unidadeVenda) {
        this.unidadeVenda = unidadeVenda;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoAtacado() {
        return precoAtacado;
    }

    public void setPrecoAtacado(double precoAtacado) {
        this.precoAtacado = precoAtacado;
    }

    public double getPrecoVarejo() {
        return precoVarejo;
    }

    public void setPrecoVarejo(double precoVarejo) {
        this.precoVarejo = precoVarejo;
    }

    public double getPrecoDistribuidora() {
        return precoDistribuidora;
    }

    public void setPrecoDistribuidora(double precoDistribuidora) {
        this.precoDistribuidora = precoDistribuidora;
    }

    public int getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(int estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public String getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(String fotoProduto) {
        this.fotoProduto = fotoProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    
}
