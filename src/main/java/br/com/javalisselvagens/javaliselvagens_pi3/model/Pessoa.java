/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.model;

import java.util.Date;

/**
 *
 * @author Zeca'$
 */
public class Pessoa {
    private long id;
    private String nome;
    private String cpf; 
    private String rg; 
    private String orgao_emissor;
    private String estado_emissor;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String sexo;
    private int ativo;

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getOrgao_emissor() {
        return orgao_emissor;
    }

    public void setOrgao_emissor(String orgao_emissor) {
        this.orgao_emissor = orgao_emissor;
    }

    public String getEstado_emissor() {
        return estado_emissor;
    }

    public void setEstado_emissor(String estado_emissor) {
        this.estado_emissor = estado_emissor;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public long excluir(long id){
            return id;
    }

    public long procurar(long id){
        return id;
    }

    public String procurarNome(String nome){
        return nome;
    }

    public long obter(long id){
        return id;
    }

    public Object cadastrar(Object id){
        return id;
    }

    public Object atualizar(Object id){
        return id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date nascimento) {
        this.dataNascimento = nascimento;
    }
    
    
}
