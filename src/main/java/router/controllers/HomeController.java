package router.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
import router.models.Greeting;

import java.util.concurrent.atomic.AtomicLong;





@Controller
public class HomeController {

//    DatabaseConnection db;
//
//    public GreetingController(){}
//
//    public GreetingController(DatabaseConnection db){
//        super();
//        this.db = db;
//    }

//    public String getSomeData(String query){
//        return db.getData(query);
//    }



    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public String index() {
        int[] nums = {1,2,3};
        sum(nums);
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/rest/{pathparam1}/{pathparam2}")
    public Greeting test(@PathVariable String pathparam1, @PathVariable String pathparam2, @RequestParam(value="name", defaultValue="The World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, pathparam1));
    }

    int sum(int[] numbers) {
        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        return sum;
    }

}