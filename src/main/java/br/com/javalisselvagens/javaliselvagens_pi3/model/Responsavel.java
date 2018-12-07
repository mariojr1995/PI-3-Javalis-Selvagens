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
public class Responsavel extends Pessoa {
    private String celular;
    
    public Responsavel(){
        
    }
    
    public Responsavel(String nome, Date dataNascimento, String sexo, String cpf,  String rg, String orgao_emissor, String estado_emissor, String telefone, String celular){
        super.setNome(nome);
        super.setDataNascimento(dataNascimento);
        super.setSexo(sexo);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setOrgao_emissor(orgao_emissor);
        super.setEstado_emissor(estado_emissor);
        super.setTelefone(telefone);
        this.celular = celular;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
}
