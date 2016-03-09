package portfolio.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by missionary on 2016-03-06.
 */
@Controller
public class HomepageController implements ErrorController {

    private static final String INDEX_PAGE = "index";
    private static final String ERROR_PAGE = "error_page";

    private static final String ERROR_PATH = "/error";

    private static final String PLEX_SERVERNAME = "plex";
    private static final String PLEX_REDIRECT = "redirect:http://plexserver.tkmcnally.com:32400/web/index.html";

    @RequestMapping("/")
    public String getHomepage(HttpServletRequest request, Model model) {
        final String serverName = request.getServerName().split("\\.")[0];

        if(StringUtils.equals(PLEX_SERVERNAME, serverName)) {
            return PLEX_REDIRECT;
        }
        return INDEX_PAGE;
    }

    @RequestMapping(value = ERROR_PATH)
    public String getErrorPage(Model model) {
        return ERROR_PAGE;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
