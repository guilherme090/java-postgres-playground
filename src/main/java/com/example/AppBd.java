package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppBd {
    public static void main (String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível carregar a biblioteca para acesso ao banco de dados: " + e.getMessage());
        }

        Statement statement = null;
        try(var conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "gitpod", "")) {
            System.out.println("Conexão com o banco de dados realizada com sucesso.");

            statement = conn.createStatement();
            var result = statement.executeQuery("SELECT * FROM estado");
            while(result.next()) {
                System.out.printf("ID: %02d --- NOME: %30s --- UF: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
        } catch (SQLException e) {
            if(statement == null) {
                System.out.println("Não foi possível conectar-se ao banco de dados: " + e.getMessage());
            } else {
                System.out.println("Não foi possível executar a consulta ao banco: " + e.getMessage());
            }
        }
    }
}
