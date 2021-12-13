/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.service;

import br.com.community.entity.Pessoa;
import br.com.community.entity.Produto;
import br.com.community.hibernate.HibernateUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author rian
 */
@Service
public class PessoaService {

    public List<Pessoa> getAllPessoas() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p FROM Pessoa p");
        Query query = session.createQuery(sql.toString());

        return query.getResultList();
    }

    public void salvarPessoa(Pessoa pessoa) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(pessoa);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarPessoa(Pessoa pessoa) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.remove(pessoa);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Pessoa> filtrarPessoaPorNome(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p FROM Pessoa p            \n");
        sql.append("WHERE p.nome LIKE '%" + nome + "%'    \n");

        Query query = session.createQuery(sql.toString());

        return query.getResultList();
    }
}
