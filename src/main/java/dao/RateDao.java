package dao;


import model.Rate;

import java.util.List;

public interface RateDao {

    boolean addRate(Rate rate);

    Rate getRateById(int id);

    List<Rate> getRateList();

    void removeRate(Rate rate);
}
