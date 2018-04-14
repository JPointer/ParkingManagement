package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.RateService;

@Controller
public class RatesController {

    @Autowired
    private RateService rateService;

    @RequestMapping("/rates")
    public ModelAndView showRates(){
        ModelAndView modelAndView = new ModelAndView("rates");
        modelAndView.addObject("rates", rateService.getRates());
        return modelAndView;
    }
}
