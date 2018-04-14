package mockObjects;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("mockUserDao")
public class MockUserDao implements UserDao {

    private Set<User> users = new HashSet<User>();

    @Override
    public boolean addUser(User user) {
        users.add(user);
        return true;
    }

    @Override
    public User findByUserUsername(String username) {
        for(User u: users)
            if(u.getUsername().equals(username))
                return u;
        return null;
    }

    @Override
    public void addBillToUser(String username, double price) {
        for(User u: users)
            if(u.getUsername().equals(username))
                u.setBill(u.getBill()+price);
    }

    @Override
    public List<User> getUserList() {
        return new ArrayList<User>(users);
    }

    @Override
    public User findByUserName(String username) {
        return findByUserUsername(username);
    }


    @Override
    public void removeUser(User user){
        users.remove(user);
    }
}
