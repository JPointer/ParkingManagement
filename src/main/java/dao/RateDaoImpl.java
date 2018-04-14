package dao;

import model.Rate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rateDaoImpl")
public class RateDaoImpl extends GenDao<Rate> implements RateDao {

    @Override
    public boolean addRate(Rate rate) {
        return addObject(rate);
    }

    @Override
    public Rate getRateById(int id) {
        return getObjectById(id,"from Rate where idRate=?");
    }

    @Override
    public List<Rate> getRateList() {
        return getObjectsList("from Rate");
    }

    @Override
    public void removeRate(Rate rate) {
        removeObject(rate);
    }
}