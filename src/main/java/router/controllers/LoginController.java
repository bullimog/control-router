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


    @RequestMapping("/login")
    public ModelAndView loginPage() {
        HashMap<String, String> formData = new HashMap<>();
        formData.put("rn", config.getUsername());
        formData.put("hidepw",config.getPassword());
        int response = connection.doPost(config.getLoginUrl(), formData);

        HashMap<String, Object> params = new HashMap<>();
        params.put("date_time", response);
        return new ModelAndView("showMessage", params);
    }

}