package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;

import com.example.model.Estado;

public class EstadoDAO extends DAO {
    public EstadoDAO(Connection conn) {
        super(conn);
    }

    public List<Estado> listar() throws SQLException {
        var lista = new LinkedList<Estado>();
        var statement = conn.createStatement();
        var result = statement.executeQuery("SELECT * FROM estado");
        while(result.next()) {
            var estado = new Estado();
            estado.setId(result.getLong("id"));
            estado.setNome(result.getString("nome"));
            estado.setUf(result.getString("uf"));
            lista.add(estado);
        }
        return lista;
    }
    

    public void localizar(String uf) {
        try{
            //var sql = "SELECT * FROM estado WHERE uf = '" + uf + "'"; //suscetível a SQL injection
            var sql = "SELECT * FROM estado WHERE uf = ?";
            var statement = conn.prepareStatement(sql);
            statement.setString(1, uf); // parâmetros começam de 1
            var result = statement.executeQuery();
            if(result.next()) {
                System.out.printf("Id: %d Nome: %s UF: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
        }
    }
}
