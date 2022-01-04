/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.service;

/**
 *
 * @author rian
 */
import br.com.community.entity.Produto;
import br.com.community.hibernate.HibernateUtil;
import java.util.List;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public void salvarProduto(Produto produto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(produto.getProdutoImagem());
            session.save(produto);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarProduto(Produto produto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.remove(produto);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Produto> getAllProdutos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p FROM Produto p \n");
        sql.append("LEFT JOIN FETCH p.produtoImagem pi \n");
        Query query = session.createQuery(sql.toString());

        return query.getResultList();
    }

    public List<Produto> filtrarProdutos(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p FROM Produto p          \n");
        sql.append("LEFT JOIN FETCH p.produtoImagem pi\n");
        sql.append("WHERE p.nome LIKE '%" + nome + "%'   \n");

        Query query = session.createQuery(sql.toString());

        return query.getResultList();
    }

    public Produto buscarPorCodigo(String codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p FROM Produto p                     \n");
        sql.append("LEFT JOIN FETCH p.produtoImagem pi          \n");
        sql.append("WHERE p.codigoBarra = '" + codigo + "'   \n");

        Query query = session.createQuery(sql.toString());

        Produto produto = query.getResultList() == null || query.getResultList().isEmpty()
                ? null
                : (Produto) query.getResultList().get(0);

        return produto;
    }
}
