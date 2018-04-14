package mockObjects;

import dao.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class MockDriverService {

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

    public User findByUserUsername(String username){

        return userDao.findByUserUsername(username);
    }

    public List<UserRole> findUserRolesByUsername(String username) {
        return userRoleDao.findUserRolesByUsername(username);
    }

    public void startMeter(String username, int idParking){
        User user = userDao.findByUserUsername(username);
        Parking parking = parkingDao.getParkingById(idParking);

        Visit visit = new Visit(user, parking, new Timestamp(System.currentTimeMillis()));
        visitDao.addVisit(visit);

    }

    public void stopMeter(String username, int idParking){
        Visit visit = visitDao.getVisitById(username, idParking);
        Timestamp begin = visit.getBeginTime();
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        long sub = now.getTime()-begin.getTime();
        int hours = (int)sub/3600000;
        addBill(hours, username, idParking, now);
        visitDao.removeVisit(visit);
    }

    /***this function add bill to user***/
    private void addBill(int time, String username, int idParking, Date date) {
        User user = userDao.findByUserUsername(username);
        Parking parking = parkingDao.getParkingById(idParking);

        Rate rate = rateDao.getRateById(parking.getRate().getIdRate());

        UserRole userRole = userRoleDao.findUserRolesByUsername(username).get(0);

        String r = userRole.getRole();

        double  firstHour = 0.0;
        double secondHour = 0.0;
        double mul = 0.0;//this is for third and each next hour

        if(r.equals("ROLE_VIP")){
            firstHour = rate.getFirstHourVip();
            secondHour = rate.getSecondHourVip();
            mul = rate.getMulNextHourVip();
        }else if(r.equals("ROLE_REGULAR")){
            firstHour = rate.getFirstHourRegular();
            secondHour = rate.getSecondHourRegular();
            mul = rate.getMulNextHourRegular();
        }
        double price = 0.0;
        if(time>=1)
            price+= firstHour;
        if(time>=2)
            price+= secondHour;
        if(time>=3)
        {
            time-=2;
            double beforeHourPrice = secondHour;
            while(time>0){
                price+= beforeHourPrice * mul;
                beforeHourPrice*= mul;
                time--;
            }//to while
        }//to if
        userDao.addBillToUser(username, price);
        historyDao.addBillToHistory(date, price);
    }//to add Bill

    /***  this function was created because of User Story 4
     *    but it was not used because I get information about user account from User class
     ***/
    public double howMuchToPay(String username){
        User user = userDao.findByUserUsername(username);
        return user.getBill();
    };
}
