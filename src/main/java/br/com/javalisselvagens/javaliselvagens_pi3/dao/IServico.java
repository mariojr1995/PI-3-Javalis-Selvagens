/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rogerio Sobrinho
 * @param <T>
 */
public interface IServico<T> {
    void excluir(long id, int status) throws SQLException, Exception;
    List<T> procurarNome(String nome) throws SQLException, Exception;
    T obter(long id) throws SQLException, Exception;
    void cadastrar (T obj) throws SQLException, Exception;
    void atualizar (T obj) throws SQLException, Exception;
}
