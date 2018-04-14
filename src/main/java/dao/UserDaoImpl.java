package dao;

import org.springframework.stereotype.Repository;
import model.User;
import java.util.ArrayList;
import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl extends GenDao<User> implements UserDao {

    @Override
    public boolean addUser(User user) {
        return addObject(user);
    }

    public User findByUserName(String username) {
        List<User> users = new ArrayList<User>();

        users = (List<User>)getSessionFactory().getCurrentSession()
                .createQuery("from User where username = :username")
                .setParameter("username",username)
                .list();


        if(users.size()>0)
            return users.get(0);
        else
            return null;
    }

    @Override
    public User findByUserUsername(String username) {
        return getObjectById(username, "from User where username=?");
    }

    @Override
    public void addBillToUser(String username, double price) {
        User user = findByUserUsername(username);
        user.setBill(user.getBill()+price);
        updateObject(user);
    }

    @Override
    public List<User> getUserList() {
        return getObjectsList("from User");
    }

    @Override
    public void removeUser(User user) {
        removeObject(user);
    }
}
