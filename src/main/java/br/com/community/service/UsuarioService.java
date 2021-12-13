/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.service;

import br.com.community.entity.Usuario;
import br.com.community.hibernate.HibernateUtil;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author rian
 */
@Service
public class UsuarioService {

    public Usuario autenticar(String login, String senha) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT usr FROM Usuario usr \n");
        sql.append("WHERE usr.login = :login    \n");
        sql.append("AND   usr.senha = :senha    \n");

        Query query = session.createQuery(sql.toString());

        query.setParameter("login", login);
        query.setParameter("senha", senha);

        Object result;
        try {
            result = query.getSingleResult();
        } catch (Exception e) {
            result = null;
        }
        return result != null ? (Usuario) result : null;
    }

    public Usuario verificarUsuarioExiste(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT usr FROM Usuario usr \n");
        sql.append("WHERE usr.login = :login    \n");

        Query query = session.createQuery(sql.toString());

        query.setParameter("login", login);

        Object result;
        try {
            result = query.getResultList().get(0);
        } catch (Exception e) {
            result = null;
        }

        return result != null ? (Usuario) result : null;
    }

    public Usuario salvar(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(usuario);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return usuario;
    }
}
