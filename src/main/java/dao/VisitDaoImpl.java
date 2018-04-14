package dao;

import model.Visit;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("visitDaoImpl")
public class VisitDaoImpl extends GenDao<Visit> implements VisitDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addVisit(Visit visit) {
        return addObject(visit);
    }

    @Override
    public Visit getVisitById(String username, int idParking) {
        List<Visit> list = new ArrayList<Visit>();
        Session session = getSessionFactory().openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            list = (List<Visit>) session
                    .createQuery("from Visit where username=? and idParking=?")
                    .setParameter(0,username)
                    .setParameter(1,idParking)
                    .list();
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
            e.printStackTrace();
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

    @Override
    public boolean removeVisit(Visit visit) {
        return removeObject(visit);
    }

    @Override
    public List<Visit> getVisitList() {
        List<Visit> list = new ArrayList<Visit>();
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            list = (List<Visit>) session
                    .createQuery("from Visit")
                    .list();
            for(Visit v:list) {
                Hibernate.initialize(v.getUser());
                Hibernate.initialize(v.getParking());
            }
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
        return list;
    }
}
