package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import system.service.DBService;

import java.util.ArrayList;

/**
 * Created by Alex on 08.05.2017.
 */
@Controller
public class IndexController {
    @Autowired
    private DBService dbService;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView indexPage = new ModelAndView("index");
        ArrayList<String> group = dbService.getForTabFourGroup();
        indexPage.addObject("group", group);

        return indexPage;
    }


}
