package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.dao.ConnectionManager;
import com.example.dao.DAO;
import com.example.model.Marca;
import com.example.model.Produto;
import com.example.dao.EstadoDAO;
import com.example.dao.ProdutoDAO;

public class AppBd {
    public static void main (String[] args) {
        new AppBd();
    }
    
    public AppBd() {
        try(var conn = ConnectionManager.getConnection()) {
            // carregarDriverJDBC(); // não é necessário usar o JDBC diretamente
            var estadoDAO = new EstadoDAO(conn);

            estadoDAO.listar();
            estadoDAO.localizar("MG");
            var marca = new Marca();
            marca.setId(2L);
            
            var produto = new Produto();
            produto.setId(205L);
            produto.setMarca(marca);
            produto.setValor(75.50);
            produto.setNome("Produto teste 9");
            
            //inserirProduto(conn, produto);
            
            var produtoDAO = new ProdutoDAO(conn);
            produtoDAO.alterar(produto);
            produtoDAO.excluir(202L);

            var dao = new DAO(conn);
            dao.listar("produto");
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar-se ao banco de dados: " + e.getMessage());
        }
    }

    private void carregarDriverJDBC() {
        /*
         * Esta função não é necessária atualmente no JDBC. Isso é feito de forma automática.
         */
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível carregar a biblioteca para acesso ao banco de dados: " + e.getMessage());
        }
    }
}
