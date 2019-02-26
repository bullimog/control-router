package router.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import router.connectors.HttpConnection;
import router.models.Greeting;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;





@Controller
public class HomeController {

    HttpConnection connection;

    public HomeController(){}

    public HomeController(HttpConnection connection){
        super();
        this.connection = connection;
    }

    public String getSomeData(String url){
        return connection.doGet(url);
    }



    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public ModelAndView index() {
        HashMap params = new HashMap<String, Object>();
        return new ModelAndView("index", params);
    }



    @RequestMapping(value = "/getDateAndTime")
    public ModelAndView getDateAndTime() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date_time = dtf.format(now);

        HashMap params = new HashMap<String, Object>();
        params.put("date_time", date_time);

        return new ModelAndView("showMessage", params);
    }

    @RequestMapping("/greeting")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting g = new Greeting(counter.incrementAndGet(),
                String.format(template, name));

        HashMap params = new HashMap<String, Object>();
        params.put("greeting", g);
        return new ModelAndView("greeting", params);
    }




}