/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import livrariajpa.Editora;
import livrariajpa.Livros;

/**
 *
 * @author Denis
 */
public class LivrariaDao {
    
    private EntityManager entityManager;
    
    public void inserir(String nome, String email) {
        
        entityManager = Persistence.createEntityManagerFactory("LivrariaJPAPU").createEntityManager();
        entityManager.getTransaction().begin();
        
        Editora user = new Editora();
        user.setEmail(email);
        user.setNome(nome);
        entityManager.persist(user);
        
        entityManager.getTransaction().commit();
        entityManager.close();
        
    }
    
    public void inserirLivroDao(String titulo, double preco, int ed_id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LivrariaJPAPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT e FROM Editora e Where e.id = '" + ed_id + "'", Editora.class);
        
        Editora editoras = (Editora) query.getSingleResult();
        
        Livros lv = new Livros();
        lv.setTitulo(titulo);
        lv.setPreco(preco);
        lv.setEditora(editoras);
        manager.persist(lv);
        
        manager.getTransaction().commit();
        manager.close();
        
    }
    
    public void remover(Editora e) {
        entityManager = Persistence.createEntityManagerFactory("LivrariaJPAPU").createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(e.getClass(), e.getId()));
        entityManager.getTransaction().commit();
        entityManager.close();
        
    }
    
    public void atualizarDao(Editora e, String nome, String email) {
        entityManager = Persistence.createEntityManagerFactory("LivrariaJPAPU").createEntityManager();
        entityManager.getTransaction().begin();
        Editora editora = entityManager.find(e.getClass(), e.getId());
        editora.setNome(nome);
        editora.setEmail(email);
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public void atualizarLivroDao(Livros l, String titulo, double preco, int ed) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LivrariaJPAPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT l FROM Livros l Where l.id = '" + l.getId() + "'", Livros.class);
        Livros lv = (Livros) query.getSingleResult();
        query = manager.createQuery("SELECT e FROM Editora e Where e.id = '" + ed + "'", Editora.class);       
        Editora editora = (Editora) query.getSingleResult();
        
        lv.setEditora(editora);
        lv.setPreco(preco);
        lv.setTitulo(titulo);
        lv.setTitulo(titulo);
        lv.setPreco(preco);
        
        manager.persist(lv);
        
        manager.getTransaction().commit();
        manager.close();
        
    }
    
    public Editora buscarEditora(int id_ed) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LivrariaJPAPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT e FROM Editora e Where e.id = '" + id_ed + "'", Editora.class);
        Editora editoras = (Editora) query.getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return editoras;
        
    }
    
    public Livros buscarLivro(int id_ed) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LivrariaJPAPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT l FROM Livros l Where l.id = '" + id_ed + "'", Livros.class);
        Livros lv = (Livros) query.getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return lv;
        
    }
    
    public List<Editora> listaEditoraDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LivrariaJPAPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery("SELECT e FROM Editora e");
        List<Editora> editoras = query.getResultList();
        manager.close();
        factory.close();
        return editoras;
    }
    
    public List<Livros> listaLivroDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LivrariaJPAPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery("SELECT l FROM Livros l");
        List<Livros> livros = query.getResultList();
        manager.close();
        factory.close();
        return livros;
    }
}
