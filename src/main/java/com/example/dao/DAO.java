package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DAO {
    private Connection conn;

    public DAO(Connection conn) {
        this.conn = conn;
    }

    public void listar(String tabela) {
        var sql = "SELECT * FROM " + tabela; //não será alvo de sql injection se esse campo não vier como entrada do usuário
        try{
            var statement = conn.createStatement();
            var result = statement.executeQuery(sql);
            var metadata = result.getMetaData();
            int cols = metadata.getColumnCount();
            
            for (int i = 0; i < cols; i++) {
                System.out.printf("%-20s |", metadata.getColumnName(i + 1));
            }

            System.out.println();
            
            while(result.next()) {
                for (int i = 0; i < cols; i++) {
                    System.out.printf("%-20s |", result.getString(i + 1));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Erro na execução da consulta: " + e.getMessage());
        }
    }
}
