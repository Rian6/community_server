/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.service;

import br.com.community.entity.Pedido;
import br.com.community.entity.PedidoItem;
import br.com.community.entity.Pessoa;
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
public class PedidoItemService {

    public void salvarPedidoItemPedido(List<PedidoItem> itens, Pedido pedido) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(pedido);

            itens.forEach(item -> {
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
