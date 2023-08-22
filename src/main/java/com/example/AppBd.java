package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppBd {
    private static final String PASSWORD = "";
    private static final String USERNAME = "gitpod";
    private static final String JDBC_URL = "jdbc:postgresql://localhost/postgres";

    public static void main (String[] args) {
        new AppBd();
    }
    
    public AppBd() {
        try(var conn = getConnection()) {
            // carregarDriverJDBC(); // não é necessário usar o JDBC diretamente
            listarEstados(conn);
            localizarEstado(conn, "MG");
            listarDadosTabela(conn, "cliente");
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar-se ao banco de dados: " + e.getMessage());
        }
    }

    private void listarDadosTabela(Connection conn, String tabela) {
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

    private void localizarEstado(Connection conn, String uf) {
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

    private void listarEstados(Connection conn) {
        try {
            System.out.println("Conexão com o banco de dados realizada com sucesso.");
            var statement = conn.createStatement();
            var result = statement.executeQuery("SELECT * FROM estado");
            while(result.next()) {
                System.out.printf("ID: %02d --- NOME: %30s --- UF: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível executar a consulta ao banco: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void carregarDriverJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível carregar a biblioteca para acesso ao banco de dados: " + e.getMessage());
        }
    }
}
