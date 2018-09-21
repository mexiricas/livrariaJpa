/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import ConnectionFactory.MyPostgreSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import livrariajpa.Editora;
import livrariajpa.Livros;
import persistencia.LivrariaDao;

/**
 *
 * @author Denis
 */
public class EditoraCtrl {

    private static MyPostgreSQL pgBD = new MyPostgreSQL();
    private Scanner tc = new Scanner(System.in);
    private LivrariaDao lsDao = new LivrariaDao();
    private String nome = "";
    private String email = "";
    private String titulo = "";
    private double preco = 0.0;
    private int ed_id = 0;

    public void inserir() {
        System.out.println("-----------------");
        System.out.println("Digite nome da editora: ");
        nome = tc.nextLine();
        System.out.println("Digite email da editora:");
        email = tc.nextLine();
        lsDao.inserir(nome, email);
        System.out.println("blz gravou");

    }

    public void inserirLivro() {
        System.out.println("======================");
        System.out.println("Digite id da editora:");
        ed_id = tc.nextInt();
        tc.nextLine();

        System.out.println("Digite titulo do livro: ");
        titulo = tc.nextLine();

        System.out.println("Digite preço:");
        preco = Double.parseDouble(tc.nextLine());

        lsDao.inserirLivroDao(titulo, preco, ed_id);
        System.out.println("blz gravou");

    }

    public void remova() {
        String id = "";
        System.out.println("Digite um id para deletar");
        id = tc.nextLine();
        if (id.matches("^[0-9]*$")) {
            for (Editora e : listarEditoraHibernate()) {
                if (Integer.parseInt(id) == e.getId()) {
                    lsDao.remover(e);
                }
            }
            System.out.println("Objeto deletado");
        } else {
            System.out.println("Nada foi deletado!!!");
        }

    }

    public List<Editora> listarEditoraHibernate() {
        List<Editora> ls = lsDao.listaEditoraDao();
        System.out.println("=================================");
        System.out.println("=========Editoras================");
        if (ls.isEmpty()) {
            System.out.println("Lista Vazia!!!!!!!");
        } else {
            for (Editora dados : ls) {

                System.out.println(" " + dados.getId() + " | " + dados.getNome() + " | " + dados.getEmail());

            }
        }
        return ls;
    }

    public List<Livros> listarLivrosHibernate() {
        List<Livros> ls = lsDao.listaLivroDao();
        System.out.println("=================================");
        System.out.println("===========Livros================");
        if (ls.isEmpty()) {
            System.out.println("Lista Vazia!!!!!!!");
        } else {
            for (Livros dados : ls) {
                System.out.println("=================================");
                System.out.println(" " + dados.getId() + " | " + dados.getTitulo() + " | " + dados.getPreco() + " | " + dados.getEditora().getNome());

            }
        }
        return ls;
    }

    public void atualizar() {
        int id = 0;
        System.out.println("Digite o nome para atualização");
        nome = tc.nextLine();
        System.out.println("Digite email para atualização");
        email = tc.nextLine();
        System.out.println("Digite um id da Editora que você quer alterar");
        id = tc.nextInt();
        for (Editora e : listarEditoraHibernate()) {
            if (id == e.getId()) {
                lsDao.atualizarDao(e, nome, email);
            }
        }

        System.out.println("blz gravou");

    }

    public void atualizarLivros() {
        int id = 0;
        System.out.println("Digite um id do Livro que você quer alterar");
        id = Integer.parseInt(tc.next());
         System.out.println("Digite o id da Editora");
        id = Integer.parseInt(tc.next());tc.nextLine();
        System.out.println("Digite preço:");
        this.preco = Double.parseDouble(tc.nextLine());
        
        System.out.println("Digite titulo do livro: ");
        this.titulo = tc.nextLine();
        
        
        Livros lv = lsDao.buscarLivro(id);

       
        lsDao.atualizarLivroDao(lv, titulo, preco, id);

        System.out.println("blz gravou");

    }

    public List<Editora> listaEditoras() {
        List<Editora> ls = new ArrayList<>();
        if (pgBD.conecta() == false) {
            System.out.println("Falha na conexão com o banco de dados");
            return null;
        }
        try {
            String sql_str = "SELECT * FROM editoras order by id";
            Connection conexao = pgBD.getConnection();
            PreparedStatement comando = conexao.prepareStatement(sql_str);
            if (pgBD == null) {
                System.out.println("BD sem conexão!");
                return null;
            } else {
                System.out.println();
                System.out.println("Editoras cadastradas:");

                ResultSet rs = comando.executeQuery();

                int id;
                String nome, email;
                System.out.println(" Id   |     Nome          |     Email");
                while (rs.next()) {
                    Editora e = new Editora();
                    e.setId(rs.getLong("id"));
                    e.setNome(rs.getString("nome"));
                    e.setEmail(rs.getString("email"));

                    ls.add(e);
                    System.out.println(" " + e.getId() + " | " + e.getNome() + " | " + e.getEmail());
                }

                comando.close();
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta SQL");
            System.out.println(e.getMessage());
        }
        return ls;
    }

}
