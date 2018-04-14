package mockObjects;

import dao.RateDao;
import model.Rate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("mockRateDao")
public class MockRateDao implements RateDao {

    private Set<Rate> rates = new HashSet<Rate>();

    @Override
    public boolean addRate(Rate rate) {
        rates.add(rate);
        return true;
    }

    @Override
    public Rate getRateById(int id) {
        for(Rate r: rates)
            if(r.getIdRate()==id)
                return r;
        return null;
    }

    @Override
    public List<Rate> getRateList() {
        return new ArrayList<Rate>(rates);
    }

    @Override
    public void removeRate(Rate rate) {
        rates.remove(rate);
    }
}
