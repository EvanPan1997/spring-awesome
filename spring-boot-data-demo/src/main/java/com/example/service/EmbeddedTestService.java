package com.example.service;

import com.example.entity.EmbeddedTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbeddedTestService {
    @PersistenceContext
    private EntityManager em;

    public void batchInsert(List<EmbeddedTest> entities) {
        Session session = em.unwrap(Session.class);
        int batchSize = 5000;
        SessionFactory sessionFactory = session.getSessionFactory();
        Transaction tx = null;
        try (StatelessSession statelessSession = sessionFactory.openStatelessSession()) {
            tx = session.beginTransaction();
            for (int i = 0; i < entities.size(); i++) {
                statelessSession.insert(entities.get(i));

                if (i % batchSize == 0 && i > 0) {
                    tx.commit();
                    tx = statelessSession.beginTransaction();
                }
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }
    }
}
