package controller;

import model.User;
import model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DriverService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private DriverService driverService;

    @RequestMapping(value = {"/", "/home"})
    public String showIndex(Model model, Authentication authentication){
        String username= authentication.getName();
        User user = driverService.findByUserUsername(username);
        List<UserRole> userRoles = driverService.findUserRolesByUsername(username);
        model.addAttribute("user",user);

        String role = new String();
        for(UserRole ur: userRoles){
            String s = ur.getRole();
            if(s.equals("ROLE_OPERATOR"))
                role+="OPERATOR ";
            if(s.equals("ROLE_REGULAR"))
                role+="REGULAR ";
            if(s.equals("ROLE_VIP"))
                role+="VIP ";

        }
        model.addAttribute("role",role);
        return "home";
    }

}
