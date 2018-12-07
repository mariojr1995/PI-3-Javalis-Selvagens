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
public class Turma {
   private long idTurma;
    private String nomeTurma;
    private String periodo;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private int capacidade;
    private int status;
    private long idCurso;

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }
    
    public long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(long idTurma) {
        this.idTurma = idTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(java.sql.Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(java.sql.Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    public long excluir(long id){
        return id;
    }
    
    public long procurar(long id){
        return id;
    }
    
    private String procurarNome(String nome){
        return nome;
    }
    
    public long obter(long id){
        return id;
    }
    
    public Object cadastar(Object id){
        return id;
    }
    
    public Object atualizar(Object id){
        return id;
    }

}
