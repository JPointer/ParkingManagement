package mockObjects;

import dao.HistoryDao;
import model.History;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Repository("mockHistoryDao")
public class MockHistoryDao implements HistoryDao {

    private Set<History> histories = new HashSet<History>();

    @Override
    public void addBillToHistory(Date date, double price) {
        for(History his: histories){
            if(DateUtils.isSameDay(his.getDate(), date)) {
                his.setDaily(his.getDaily()+price);
                return;
            }
        }
        histories.add(new History(date, price));
    }

    @Override
    public double getMoneyForDay(Date date) {
        for(History his: histories)
            if(DateUtils.isSameDay(his.getDate(), date))
                return his.getDaily();

        return 0;
    }

    @Override
    public void removeHistory(Date date) {
        History his = null;
        for(History h: histories)
            if(DateUtils.isSameDay(h.getDate(), date))
            {
                his=h;
                break;
            }
        if(his!=null)
            histories.remove(his);

    }


}
