package controller;

import model.ParkingInfo;
import model.UserRole;
import model.VisitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DriverService;
import service.OperatorService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StartStopController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value= "/start", method = RequestMethod.POST)
    public String makeStart(Model model, @ModelAttribute("parkingStart") int startParkingId , Authentication authentication){
        String username= authentication.getName();
        driverService.startMeter(username, startParkingId);
        List<UserRole> userRoles = driverService.findUserRolesByUsername(username);
        ArrayList<String> roles = new ArrayList<String>();

        for(UserRole r: userRoles)
            roles.add(r.getRole());

        if(roles.contains("ROLE_REGULAR") || roles.contains("ROLE_VIP")){
            List<ParkingInfo> parkings = operatorService.getParkingInfoList(username);
            model.addAttribute("parkings",parkings);
        }
        if(roles.contains("ROLE_OPERATOR")){
            List<VisitInfo> usersVisits = operatorService.getVisitList();
            model.addAttribute("visits", usersVisits);
        }

        return "list";
    }

    @RequestMapping(value= "/stop", method = RequestMethod.POST)
    public String makeStop(Model model, @ModelAttribute("parkingStop") int stopParkingId, Authentication authentication){
        String username= authentication.getName();
        driverService.stopMeter(username, stopParkingId);
        List<UserRole> userRoles = driverService.findUserRolesByUsername(username);
        ArrayList<String> roles = new ArrayList<String>();

        for(UserRole r: userRoles)
            roles.add(r.getRole());

        if(roles.contains("ROLE_REGULAR") || roles.contains("ROLE_VIP")){
            List<ParkingInfo> parkings = operatorService.getParkingInfoList(username);
            model.addAttribute("parkings",parkings);
        }
        if(roles.contains("ROLE_OPERATOR")){
            List<VisitInfo> usersVisits = operatorService.getVisitList();
            model.addAttribute("visits", usersVisits);
        }
        return "list";
    }
}
