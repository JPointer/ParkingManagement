package service;

import config.ApplicationConfig;
import dao.*;
import mockObjects.MockDriverService;
import model.*;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class DriverServiceTest {

    @Autowired
    @Qualifier("mockUserDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("mockUserRoleDao")
    private UserRoleDao userRoleDao;

    @Autowired
    @Qualifier("mockVisitDao")
    private VisitDao visitDao;

    @Autowired
    @Qualifier("mockParkingDao")
    private ParkingDao parkingDao;

    @Autowired
    @Qualifier("mockRateDao")
    private RateDao rateDao;

    @Autowired
    @Qualifier("mockHistoryDao")
    private HistoryDao historyDao;

    @Autowired
    private MockDriverService driverService;

    @Test
    public void startMeterVisitListNotEmpty() {
        User user = new User("username", "password", true, "firstName", "seconName",100.0);
        Parking parking = new Parking(1,"Warszawa","Nowowiejska",10);

        userDao.addUser(user);
        parkingDao.addParking(parking);

        driverService.startMeter(user.getUsername(),parking.getIdParking());

        assertEquals(visitDao.getVisitList().size(),1);

        userDao.removeUser(user);
        parkingDao.removeParking(parking);
        visitDao.removeVisit(visitDao.getVisitList().get(0));
    }

    @Test
    public void stopMeterTestVisitListEmpty(){
        User user = new User("username", "password", true, "firstName", "seconName",100.0);
        UserRole userRole = new UserRole(user, "ROLE_REGULAR");
        Set<UserRole> a = new HashSet<UserRole>();
        a.add(userRole);
        user.setUserRole(a);

        Rate rate = new Rate(1, 1.0, 2.0, 0.0, 2.0, 2.0, 1.5,"PLN");
        Parking parking = new Parking(1,"Warszawa","Nowowiejska",10);
        parking.setRate(rate);
        Set<Parking> l = new HashSet<Parking>();
        l.add(parking);
        rate.setParkings(l);

        userDao.addUser(user);
        parkingDao.addParking(parking);
        rateDao.addRate(rate);
        userRoleDao.addRole(userRole);

        driverService.startMeter(user.getUsername(),parking.getIdParking());
        driverService.stopMeter(user.getUsername(), parking.getIdParking());

        assertEquals(visitDao.getVisitList().size(),0);

        userDao.removeUser(user);
        parkingDao.removeParking(parking);
        rateDao.removeRate(rate);
        userRoleDao.removeRole(userRole);
        historyDao.removeHistory(new Date(Calendar.getInstance().getTimeInMillis()));

    }

    @Test
    public void howMuchToPayTest(){
        User user = new User("username", "password", true, "firstName", "seconName",0.0);
        UserRole userRole = new UserRole(user, "ROLE_REGULAR");
        Set<UserRole> a = new HashSet<UserRole>();
        a.add(userRole);
        user.setUserRole(a);

        Rate rate = new Rate(1, 1.0, 2.0, 0.0, 2.0, 2.0, 1.5,"PLN");
        Parking parking = new Parking(1,"Warszawa","Nowowiejska",10);
        parking.setRate(rate);
        Set<Parking> l = new HashSet<Parking>();
        l.add(parking);
        rate.setParkings(l);

        // 4 hours are 14 400 000 milliseconds
        Visit visit = new Visit(user, parking, new Timestamp(System.currentTimeMillis()-14400000));

        userDao.addUser(user);
        parkingDao.addParking(parking);
        rateDao.addRate(rate);
        userRoleDao.addRole(userRole);
        visitDao.addVisit(visit);

        driverService.stopMeter(user.getUsername(), parking.getIdParking());

        assertEquals(driverService.howMuchToPay(user.getUsername()),15.0, 0.01);

        userDao.removeUser(user);
        parkingDao.removeParking(parking);
        rateDao.removeRate(rate);
        userRoleDao.removeRole(userRole);
        historyDao.removeHistory(new Date(Calendar.getInstance().getTimeInMillis()));
    }

    @Test
    public void findUserRolesByUsername(){
        User user1 = new User("myUsername1", "password", true, "firstName", "seconName",100.0);
        UserRole userRole = new UserRole(user1, "ROLE_REGULAR");
        Set<UserRole> a = new HashSet<UserRole>();
        a.add(userRole);
        user1.setUserRole(a);

        userRoleDao.addRole(userRole);
        userDao.addUser(user1);

        ArrayList<UserRole> userRoles = (ArrayList<UserRole>) driverService.findUserRolesByUsername("myUsername1");

        assertEquals(userRole.getRole(), userRoles.get(0).getRole());
        userRoleDao.removeRole(userRole);
        userDao.removeUser(user1);

    }

    @Test
    public void findByUserUsenameTest(){
        User user = new User("myUsername", "password", true, "firstName", "seconName",100.0);
        userDao.addUser(user);

        User user1 =  driverService.findByUserUsername("myUsername");
        assertNotNull(user1);

        userDao.removeUser(user1);
    }

    @Test
    public void findByUserUsenameTestNull(){
        User user = new User("myUsername", "password", true, "firstName", "seconName",100.0);
        userDao.addUser(user);

        User user1 =  driverService.findByUserUsername("myUsername2");
        assertNull(user1);

        userDao.removeUser(user1);
    }
}
