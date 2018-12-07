/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.model;

import java.util.Objects;

public class Usuario extends Pessoa {
 private Integer codigo;
    private String nome;
    private String login;
    private String senha;
    private boolean administrador;
    private int unidade;
  
    public Usuario() {
        this(null, null, null, null, false);
    }
  
    public Usuario(Integer codigo, String nome, String login, String senha, boolean administrador) {
        this.codigo = codigo;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.administrador = administrador;
    }
    
    public Integer getUnidade() {
        return unidade;
    }
  
    public void setUnidade(Integer unidade) {
        this.unidade = unidade;
    }
  
    public Integer getCodigo() {
        return codigo;
    }
  
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
  
    public String getNome() {
        return nome;
    }
  
    public void setNome(String nome) {
        this.nome = nome;
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
  
    public boolean isAdministrador() {
        return administrador;
    }
  
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
  
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
}
