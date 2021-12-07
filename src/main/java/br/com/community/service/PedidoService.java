/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.service;

import br.com.community.entity.Pedido;
import br.com.community.entity.Pessoa;
import br.com.community.entity.PedidoItem;
import br.com.community.hibernate.HibernateUtil;
import java.sql.Connection;
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
public class PedidoService {

    public List<Pedido> getAllPedidos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p FROM Pedido p      \n");
        sql.append("LEFT JOIN FETCH p.vendedor \n");
        sql.append("LEFT JOIN FETCH p.cliente  \n");
        Query query = session.createQuery(sql.toString());

        return query.getResultList();
    }

    public void salvarPedido(Pedido pedido, List<PedidoItem> pedidoItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(pedido);
            
            pedidoItem.forEach(item -> {
                item.setPedido(pedido);
                session.save(item);
            });

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
