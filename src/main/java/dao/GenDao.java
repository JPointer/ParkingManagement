package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected final boolean addObject(T object){
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            session.save(object);
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    protected final boolean removeObject(T object){
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            session.delete(object);
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    protected final T getObjectById(Object id, String query){
        List<T> list = new ArrayList<T>();
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            list = (List<T>) session
                    .createQuery(query)
                    .setParameter(0,id)
                    .list();
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
            return null;
        }
        finally {
            session.close();
        }
        if(list.size()==1)
            return list.get(0);
        else
            return null;
    }

    protected final List<T> getObjectsList(String query){
        List<T> list = new ArrayList<T>();
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            list = (List<T>) session
                    .createQuery(query)
                    .list();
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
            return null;
        }
        finally {
            session.close();
        }
        return list;
    }

    protected final boolean updateObject(T object){
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(object);
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }
}