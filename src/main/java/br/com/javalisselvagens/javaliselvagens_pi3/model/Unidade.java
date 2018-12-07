/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.model;

/**
 *
 * @author Zeca'$
 */
public class Unidade {

    private long idUnidade;
    private String razaoSocial;
    private String nomeFantasia;
    private String CNPJ;
    private int status;

    public long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long excluir(long id) {
        return id;
    }

    public long procurar(long id) {
        return id;
    }

    public String procurarNome(String nome) {
        return nome;
    }

    public long obter(long id) {
        return id;
    }
    
    public Object cadastrar(Object id){
        return id;
    }
    
     public Object atualizar(Object id){
        return id;
    }
    

}
