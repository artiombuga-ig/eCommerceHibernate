package org.buga.data;

import org.buga.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    private Class<T> type;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T t) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.persist(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public T read(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        T t = null;
        try {
            t = currentSession.get(type, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return t;
    }

    @Override
    public void update(T t) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.merge(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        try {
            T t = currentSession.get(type, id);
            if (t != null) {
                currentSession.remove(t);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
