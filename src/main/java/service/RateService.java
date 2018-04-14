package service;


import dao.RateDao;
import model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    @Autowired
    @Qualifier("rateDaoImpl")
    private RateDao rateDao;

    public List<Rate> getRates(){
        return rateDao.getRateList();
    }
}
