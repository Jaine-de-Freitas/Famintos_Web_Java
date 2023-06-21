/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author user3
 */
public class UsuarioDTO {

    private String nome, senha, email, funcao, cpf, admissao, data_cadastro, filtro, txtPesq, sqls;
    private double id_usuario;
    private String guardar_user;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAdmissao() {
        return admissao;
    }

    public void setAdmissao(String admissao) {
        this.admissao = admissao;
    }

    public Object getSelectedItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getFuncao(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public double getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(double id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getSqls() {
        return sqls;
    }

    public void setSqls(String sqls) {
        this.sqls = sqls;
    }

    public String getGuardar_user() {
        return guardar_user;
    }

    public void setGuardar_user(String guardar_user) {
        this.guardar_user = guardar_user;
    }

}
