package router.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import router.connectors.HttpConnection;
import router.models.Greeting;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;


@Controller
public class HomeController {

    @Autowired
    private HttpConnection connection;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public ModelAndView index() {
        HashMap<String, Object> params = new HashMap<>();
        return new ModelAndView("index", params);
    }

    @RequestMapping("/forward")
    public RedirectView forward(RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttr", "Nothing");
        attributes.addAttribute("attr", "Nothing");
        return new RedirectView("/greeting");
    }


    @RequestMapping(value = "/getDateAndTime")
    public ModelAndView getDateAndTime() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date_time = dtf.format(now);

        HashMap<String, Object> params = new HashMap<>();
        params.put("date_time", date_time);

        return new ModelAndView("showMessage", params);
    }


    @RequestMapping("/greeting")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {

        Greeting g = new Greeting(counter.incrementAndGet(),
                String.format(template, name));

        HashMap<String, Object> params = new HashMap<>();
        params.put("greeting", g);
        return new ModelAndView("greeting", params);
    }


    @RequestMapping("/page")
    public ModelAndView pullPage(@RequestParam(value="url", defaultValue="http://www.google.com") String url) {

        String content = connection.doGet(url);
        HashMap<String, Object> params = new HashMap<>();
        params.put("date_time", content);
        return new ModelAndView("showMessage", params);
    }




}