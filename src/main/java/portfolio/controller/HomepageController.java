package portfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;
import portfolio.util.WoL;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by missionary on 2016-03-06.
 */
@Controller
public class HomepageController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(HomepageController.class);


    private static final String INDEX_PAGE = "index";
    private static final String ERROR_PAGE = "errorpage";

    private static final String ERROR_PATH = "/error";

    private static final String PLEX_SERVERNAME = "plex";
    private static final String PLEX_REDIRECT = "redirect:http://plexserver.tkmcnally.com:32400/web/index.html";

    @Value("${plex.server.ip}")
    private String PLEX_SERVER_IP;

    @Value("${plex.server.mac}")
    private String PLEX_SERVER_MAC;

    @RequestMapping("/")
    public String getHomepage(HttpServletRequest request, Model model) {
        final String serverName = request.getServerName().split("\\.")[0];
        if(StringUtils.equals(PLEX_SERVERNAME, serverName)) {
            WoL.sendPacket(PLEX_SERVER_IP, PLEX_SERVER_MAC);
            logger.info("ServerNAME: " + serverName);
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
