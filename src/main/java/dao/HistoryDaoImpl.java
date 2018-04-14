package dao;

import model.History;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository("historyDaoImpl")
public class HistoryDaoImpl implements HistoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBillToHistory(Date date, double price) {
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            History history = (History) session.get(History.class, date);

            if(history!=null){
                history.setDaily(history.getDaily()+price);
                session.update(history);
            }
            else
                session.save(new History(date,price));
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public double getMoneyForDay(Date date) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        double money = 0.0;

        tx.begin();
        History history = (History) session.get(History.class, date);
        if(history!=null)
            money = history.getDaily();
        tx.commit();
        session.close();

        return money;
    }

    @Override
    public void removeHistory(final Date date) {
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            History  history = (History) session.get(History.class, date);
            session.delete(history);
            tx.commit();

        }catch(Exception e){
            if(tx!=null)
                tx.rollback();
        }
        finally {
            session.close();
        }
    }

}
