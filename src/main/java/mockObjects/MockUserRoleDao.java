package mockObjects;

import dao.UserRoleDao;
import model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("mockUserRoleDao")
public class MockUserRoleDao implements UserRoleDao {

    private Set<UserRole> userRoles = new HashSet<UserRole>();

    @Override
    public List<UserRole> findUserRolesByUsername(String username) {
        return new ArrayList<UserRole>(userRoles);
    }

    @Override
    public void addRole(UserRole userRole) {
        userRoles.add(userRole);
    }

    @Override
    public void removeRole(UserRole userRole) {
        userRoles.remove(userRole);
    }

    /*** this function was added just for test ***/
}
