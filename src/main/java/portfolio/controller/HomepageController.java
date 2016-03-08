package portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by missionary on 2016-03-06.
 */
@Controller
public class HomepageController {

    private static final String INDEX_PAGE = "index";

    @RequestMapping("/")
    public String getHomepage(Model model) {
        return INDEX_PAGE;
    }

}
