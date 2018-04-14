package controller;

import model.ParkingInfo;
import model.VisitInfo;
import model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DriverService;
import service.OperatorService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OperatorService operatorService;

    @RequestMapping("/list")
    public String showList(Model model, Authentication authentication) {
        String username= authentication.getName();
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
