package service;

import config.ApplicationConfig;
import dao.HistoryDao;
import dao.ParkingDao;
import dao.UserDao;
import dao.VisitDao;
import mockObjects.MockDriverService;
import mockObjects.MockOperatorService;
import model.Parking;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class OperatorServiceTest {

    @Autowired
    @Qualifier("mockParkingDao")
    private ParkingDao parkingDao;

    @Autowired
    @Qualifier("mockVisitDao")
    private VisitDao visitDao;

    @Autowired
    @Qualifier("mockHistoryDao")
    private HistoryDao historyDao;

    @Autowired
    @Qualifier("mockUserDao")
    private UserDao userDao;

    @Autowired
    private MockOperatorService operatorService;

    @Autowired
    private MockDriverService driverService;

    @Test
    public void howMuchEarnedGivenDayTest(){
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        historyDao.addBillToHistory(now,200.0);
        assertEquals(200.0,operatorService.howMuchEarnedGivenDay(now),0.01);
        historyDao.removeHistory(now);
    }

    @Test
    public void checkIfStarteTest() {
        User user = new User("username", "password", true, "firstName", "seconName",100.0);
        Parking parking = new Parking(1,"Warszawa","Nowowiejska",10);

        userDao.addUser(user);
        parkingDao.addParking(parking);

        driverService.startMeter(user.getUsername(),parking.getIdParking());

        operatorService.checkIfStarted(user.getUsername(), parking.getIdParking());
        assertTrue(operatorService.checkIfStarted(user.getUsername(), parking.getIdParking()));

        userDao.removeUser(user);
        parkingDao.removeParking(parking);
        visitDao.removeVisit(visitDao.getVisitList().get(0));
    }

}
