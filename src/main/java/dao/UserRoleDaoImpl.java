package dao;

import model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userRoleDaoImpl")
public class UserRoleDaoImpl extends GenDao<UserRole> implements  UserRoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserRole> findUserRolesByUsername(String username) {
        List<UserRole> list = new ArrayList<UserRole>();
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.getTransaction();
            tx.begin();
            list = (List<UserRole>) session
                    .createQuery("from UserRole where username=?")
                    .setParameter(0,username)
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

    @Override
    public void addRole(UserRole userRole) {
        addObject(userRole);
    }

    @Override
    public void removeRole(UserRole userRole) {
        removeObject(userRole);
    }
}
