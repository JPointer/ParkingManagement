package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.tree.ExpandVetoException;
import java.util.List;

@Controller
public class CreateController {

    @RequestMapping("/create")
    public String showCreate(){
        return "create";
    }
}