package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.model.Produto;

public class ProdutoDAO extends DAO {
    public ProdutoDAO(Connection conn) {
        super(conn);
    }

    public void excluir(long id) {
        var sql = "DELETE FROM produto WHERE id = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            if(statement.executeUpdate() == 1) {
                System.out.println("Produto excluído com sucesso.");
            }else {
                System.out.println("Produto não foi localizado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir o produto: " + e.getMessage());
        }
    }

    public void inserir(Produto produto) {
        var sql = "INSERT INTO produto (nome, marca_id, valor) VALUES (?, ?, ?)";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getMarca().getId());
            statement.setDouble(3, produto.getValor());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro na execução da consulta: " + e.getMessage());
        }
    }

    public void alterar(Produto produto) {
        var sql = "UPDATE produto SET nome = ?, marca_id = ?, valor = ? WHERE id = ?";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getMarca().getId());
            statement.setDouble(3, produto.getValor());
            statement.setLong(4, produto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro na alteração do produto: " + e.getMessage());
        }
    }
    
}
