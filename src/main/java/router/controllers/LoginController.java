package router.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import router.config.LoginControllerConfig;
import router.connectors.HttpConnection;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;


@Controller
public class LoginController {

    @Autowired
    private HttpConnection connection;

    @Autowired
    private LoginControllerConfig config;

    @RequestMapping("/loginXX")
    public ModelAndView loginPage() {
        HashMap<String, String> formData = new HashMap<>();
        formData.put("username", config.getUsername());
        formData.put("password",config.getPassword());
        int response = connection.doPost(config.getLoginUrl(), formData);

        HashMap<String, Object> params = new HashMap<>();
        params.put("date_time", response);
        return new ModelAndView("login", params);
    }

    @RequestMapping("/login")
    public String getLoginPage() {
        return "login";
    }

}