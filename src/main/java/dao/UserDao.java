package dao;

import model.User;

import java.util.Date;
import java.util.List;

public interface UserDao {

    boolean addUser(User user);

    User findByUserUsername(String username);

    void addBillToUser(String username, double price);

    List<User> getUserList();

    void removeUser(User user1);

    /***this function is needed in authorization process***/
    User findByUserName(String username);

}
