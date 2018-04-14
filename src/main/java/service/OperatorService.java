package service;

import dao.HistoryDao;
import dao.ParkingDao;
import dao.VisitDao;
import model.Parking;
import model.ParkingInfo;
import model.Visit;
import model.VisitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class OperatorService {

    @Autowired
    @Qualifier("parkingDaoImpl")
    private ParkingDao parkingDao;

    @Autowired
    @Qualifier("visitDaoImpl")
    private VisitDao visitDao;

    @Autowired
    @Qualifier("historyDaoImpl")
    private HistoryDao historyDao;


    /***this functions are needed in controller to show info***/
    public List<ParkingInfo> getParkingInfoList(String username) {
        ArrayList<ParkingInfo> parkingInfos = new ArrayList<ParkingInfo>();

        for(Parking p: parkingDao.getPakingList()) {
            Visit v = visitDao.getVisitById(username, p.getIdParking());
            if(v==null)
                parkingInfos.add(new ParkingInfo(p, false));
            else
                parkingInfos.add(new ParkingInfo(p, true));
        }
        return parkingInfos;
    }

    public List<VisitInfo> getVisitList() {
        ArrayList<VisitInfo> visitInfos = new ArrayList<VisitInfo>();
        for(Visit v: visitDao.getVisitList())
            visitInfos.add(new VisitInfo(v));
        return visitInfos;
    }

    /***this function is to check how much money was earned today***/
    public double howMuchEarnedGivenDay(Date date){
        return historyDao.getMoneyForDay(date);
    };

    /***this function are required in User stories but was not needed to be call in controller
     *  in controller we just show the whole list of started visits so there was no need to invoke this function***/
    public boolean checkIfStarted(String username, int idParking){
        return (visitDao.getVisitById(username, idParking)!=null);
    };
}
