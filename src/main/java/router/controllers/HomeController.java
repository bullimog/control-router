package router.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import router.connectors.FamilyConnection;
import router.repositories.PersonRepository;
import router.connectors.HttpConnection;
import router.models.Family;
import router.models.Greeting;
import org.springframework.web.servlet.ModelAndView;
import router.models.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;


@Controller
public class HomeController {

    @Autowired
    private HttpConnection connection;

    @Autowired
    private FamilyConnection fc; //Dependency resolved by a @Bean method that returns a FamilyConnection

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private PersonRepository repository;

    //https://spring.io/guides/gs/accessing-data-mongodb/
    //db.person.find().pretty();
    //spring.data.mongodb.uri is set in application.properties (https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-mongodb)
    @RequestMapping("/storePerson")
    public ModelAndView mongoStore(@RequestParam(value="first", defaultValue="first") String firstName,
                              @RequestParam(value="last", defaultValue="last") String lastName,
                                   @RequestParam(value="age", defaultValue="age") String age) {

        long id = counter.incrementAndGet();
        int ageIn= 1;
        try{
            ageIn = Integer.parseInt(age);
        }catch(NumberFormatException ex) {System.out.println("WARN:"+ex);}
        Person person = new Person(firstName, lastName, ageIn);
        repository.save(person);

        Greeting g = new Greeting(id,
                String.format(template,person.getFirst()));


        HashMap<String, Object> params = new HashMap<>();
        params.put("greeting", g);
        return new ModelAndView("greeting", params);
    }

    @RequestMapping("/findPerson")
    public ModelAndView mongoFind(@RequestParam(value="first", defaultValue="first") String firstName)  {

        Person person = repository.findByFirst(firstName);

        Greeting g = new Greeting(person.getId(),
                String.format(template,person.getLast()));

        HashMap<String, Object> params = new HashMap<>();
        params.put("greeting", g);
        return new ModelAndView("greeting", params);
    }

    @RequestMapping("/deletePerson")
    public ModelAndView mongodelete(@RequestParam(value="first", defaultValue="first") String firstName)  {

        repository.deleteByFirst(firstName);

        Person person = new Person("First", "Deleted", 0);
        Greeting g = new Greeting(person.getId(),
                String.format(template,person.getLast()));

        HashMap<String, Object> params = new HashMap<>();
        params.put("greeting", g);
        return new ModelAndView("greeting", params);
    }


    //unauthenticated method. See WebSecurityConfig
    @RequestMapping("/family")
    public ModelAndView family(@RequestParam(value="id", defaultValue="1") String id) {
        long idIn = 1;
        try{
            idIn = Long.parseLong(id);
        }catch(NumberFormatException ex) {System.out.println("WARN:"+ex);}
        Family family = fc.getFamily(idIn);
        HashMap<String, Object> params = new HashMap<>();
        params.put("family", family);
        return new ModelAndView("family", params);
    }

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


    //unauthenticated method. See WebSecurityConfig
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

        // https://spring.io/guides/gs/async-method/
        // https://dzone.com/articles/domain-model-design-pattern-video
        // https://dzone.com/articles/20-examples-of-using-javas-completablefuture

        HashMap<String, Object> params = new HashMap<>();
        CompletableFuture<String> cfContent = connection.doGet(url);
        CompletableFuture<Void> cf =cfContent.thenAcceptAsync(c -> params.put("date_time", c));
        cf.join();

        return new ModelAndView("showMessage", params);
    }


    //html file consumes from messages.properties file...
    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }

    @GetMapping("/hello")
    public String getHelloPage() {
        return "hello";
    }



}