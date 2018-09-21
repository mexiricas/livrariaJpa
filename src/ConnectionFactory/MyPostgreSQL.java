/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionFactory;

/**
 *
 * @author Denis
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyPostgreSQL {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public MyPostgreSQL() {
        connection = null;
    }

    public boolean conecta() {
        connection = null;
         // verifica se drive JDBC está acessível
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC PostgreSQL não foi encontrado.");
            System.out.println(e.getMessage());
            return false;
        }
         // string de conexão com banco de dados “livraria”
        String conexao = "jdbc:postgresql://localhost:5432/livraria";
        try {
            // conecta com BD usando usuário “postgresql” e senha “root”
            connection = DriverManager.getConnection(conexao, "postgres", "1234");
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar com Banco de Dados!");
            System.out.println(e.getMessage());
            connection = null;
            return false;
        }
        return true;
    }

    public void encerra() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("Erro no encerramento da conexão com BD.");
                System.out.println(e.getMessage());
            }
        }
    }
}

