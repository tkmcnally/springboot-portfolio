package portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.model.Person;

/**
 * Created by missionary on 2016-03-04.
 */
@Controller
public class PersonController {

    public static final String FIRSTNAME = "Thomas";
    public static final String LASTNAME = "McNally";
    public static final String ADDRESS = "234-1711 Rideau St. Ottawa, ON";

    @RequestMapping("/person")
    public Person getPerson() {
        return new Person(FIRSTNAME, LASTNAME, ADDRESS);
    }

    @RequestMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("name", "Thomas McNally");
        return "index";
    }

}
