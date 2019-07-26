package portfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.URL;

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
    private static final String PLEX_WAKEUP_PATH = "/wakeplex";
    private static final String PLEX_WAKEUP_PORT = ":3000";

    @Value("${plex.server.ip}")
    private String PLEX_SERVER_IP;

    @RequestMapping("/")
    public String getHomepage(HttpServletRequest request, Model model) {
        final String serverName = request.getServerName().split("\\.")[0];
        if(StringUtils.equals(PLEX_SERVERNAME, serverName)) {
            try {
                URL url = new URL("http://" + PLEX_SERVER_IP + PLEX_WAKEUP_PORT + PLEX_WAKEUP_PATH);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.getResponseCode();
                logger.info("Sending GET request to: " + url.toURI());
            } catch (Exception e) {
                logger.error("Error sending GET request to " + PLEX_SERVER_IP, e);
            }


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
