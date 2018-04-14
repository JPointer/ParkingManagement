package service;

import config.ApplicationConfig;
import dao.RateDao;
import mockObjects.MockRateService;
import model.Rate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class RateServiceTest {

    @Autowired
    @Qualifier("mockRateDao")
    private RateDao rateDao;

    @Autowired
    private MockRateService rateService;

    @Test
    public void getRateSize(){
        Rate rate1 = new Rate(1, 1.0, 2.0, 0.0, 2.0, 2.0, 1.5,"PLN");
        Rate rate2 = new Rate(2, 2.0, 2.0, 2.0, 2.0, 1.0, 1.0,"PLN");

        rateDao.addRate(rate1);
        rateDao.addRate(rate2);
        ArrayList<Rate> array = (ArrayList<Rate>) rateService.getRates();
        assertEquals(2,array.size());

        rateDao.removeRate(rate1);
        rateDao.removeRate(rate2);
    }
}
