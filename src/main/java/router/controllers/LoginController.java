package router.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        int response = 500;
        HashMap formData = new HashMap<String, String>();
        formData.put("username", config.username);
        formData.put("password",config.password);
        try {
            response = connection.doPost(config.loginUrl, formData);
        }catch (IOException ex){
            System.out.println("IO Exception caught in LoginController: " + ex);
        }

        HashMap params = new HashMap<String, Object>();
        params.put("date_time", response);
        return new ModelAndView("showMessage", params);
    }




}