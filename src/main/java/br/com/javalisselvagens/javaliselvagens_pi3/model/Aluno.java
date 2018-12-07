/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.model;

import java.sql.Date;

/**
 *
 * @author Rogerio Sobrinho
 */
public class Aluno extends Pessoa {
    private String celular;
    private Endereco endereco;
    private Responsavel responsavel;
    
    public Aluno(){
        
    }
    
    public Aluno(String nome, Date dataNascimento, String sexo, String cpf,  String rg, String orgao_emissor, String estado_emissor, String telefone, String celular, String email, int ativo){
        super.setNome(nome);
        super.setDataNascimento(dataNascimento);
        super.setSexo(sexo);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setOrgao_emissor(orgao_emissor);
        super.setEstado_emissor(estado_emissor);
        super.setTelefone(telefone);
        this.celular = celular;
        super.setEmail(email);
        super.setAtivo(ativo);
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}