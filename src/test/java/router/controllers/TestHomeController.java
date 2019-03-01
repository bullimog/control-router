package router.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import router.connectors.HttpConnection;

import org.junit.Test;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class) //runs MockitoAnnotations.initMocks(this);
public class TestHomeController {

    @InjectMocks //Request to inject mocks into the HomeController instance
    private HomeController homeController = new HomeController();

    @Mock //Mockito will create a mock version and inject it into HomeController.
    private HttpConnection connection;

    @Test
    public void testHomeControllerIndex() {
        ModelAndView result = homeController.index();
        assertEquals("index", result.getViewName());
        assertEquals(new HashMap<String, Object>(), result.getModel());

        when(connection.doGet("/")).thenReturn("Test Data");
        result = homeController.pullPage("/");
        assertEquals("showMessage", result.getViewName());
        assertEquals("Test Data", result.getModel().get("date_time"));
    }

}
