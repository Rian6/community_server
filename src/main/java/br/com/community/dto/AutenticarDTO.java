/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.dto;

/**
 *
 * @author rian
 */
public class AutenticarDTO {
    private Integer idUsuario;
    private Boolean isAutenticado;
    private String token;
    private String nome;
    private String login;
    private String senha;
    private String erro;

    public Boolean getIsAutenticado() {
        return isAutenticado;
    }

    public void setIsAutenticado(Boolean isAutenticado) {
        this.isAutenticado = isAutenticado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
