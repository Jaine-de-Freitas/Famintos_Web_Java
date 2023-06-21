/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author jaine
 */
public class EstoqueDTO {
    private String produto,categoria,lote,vencimento,preco,filtro,txtPesq,data_cadastro,operacao,valor;
    private double id_produto,id_p_tbl;
    private int quantidade, qtd_tbl;

    /**
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the lote
     */
    public String getLote() {
        return lote;
    }

    /**
     * @param lote the lote to set
     */
    public void setLote(String lote) {
        this.lote = lote;
    }

    /**
     * @return the vencimento
     */
    public String getVencimento() {
        return vencimento;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    /**
     * @return the preco
     */
    public String getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(String preco) {
        this.preco = preco;
    }

    /**
     * @return the filtro
     */
    public String getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the txtPesq
     */
    public String getTxtPesq() {
        return txtPesq;
    }

    /**
     * @param txtPesq the txtPesq to set
     */
    public void setTxtPesq(String txtPesq) {
        this.txtPesq = txtPesq;
    }

    /**
     * @return the id_produto
     */
    public double getId_produto() {
        return id_produto;
    }

    /**
     * @param id_produto the id_produto to set
     */
    public void setId_produto(double id_produto) {
        this.id_produto = id_produto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the data_cadastro
     */
    public String getData_cadastro() {
        return data_cadastro;
    }

    /**
     * @param data_cadastro the data_cadastro to set
     */
    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    /**
     * @return the id_p_tbl
     */
    public double getId_p_tbl() {
        return id_p_tbl;
    }

    /**
     * @param id_p_tbl the id_p_tbl to set
     */
    public void setId_p_tbl(double id_p_tbl) {
        this.id_p_tbl = id_p_tbl;
    }

    /**
     * @return the operacao
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * @param operacao the operacao to set
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * @return the qtd_tbl
     */
    public int getQtd_tbl() {
        return qtd_tbl;
    }

    /**
     * @param qtd_tbl the qtd_tbl to set
     */
    public void setQtd_tbl(int qtd_tbl) {
        this.qtd_tbl = qtd_tbl;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    
}
