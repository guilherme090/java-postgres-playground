package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class EstadoDAO {
    private Connection conn;

    public EstadoDAO(Connection conn) {
        this.conn = conn;
    }

    public void listar() {
        try {
            var statement = conn.createStatement();
            var result = statement.executeQuery("SELECT * FROM estado");
            while(result.next()) {
                System.out.printf("ID: %02d --- NOME: %30s --- UF: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível executar a consulta ao banco: " + e.getMessage());
        }
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
