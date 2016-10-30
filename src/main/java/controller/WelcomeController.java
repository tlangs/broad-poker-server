package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by trevyn on 10/30/16.
 */
@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String redirectToIndex(HttpServletResponse httpServletResponse) {
        return "redirect:index.html";
    }
}
