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

@RunWith(MockitoJUnitRunner.class)
public class TestHomeController {


    @InjectMocks //We'd like to inject mocks into this Object
    private HomeController hc = new HomeController();

    @Mock //Mockito will create a mock version and inject it into HomeController.
    private HttpConnection connection;

    @Test
    public void testHomeControllerIndex() {
        ModelAndView result = hc.index();
        assertEquals("index", result.getViewName());
        assertEquals(new HashMap<String, Object>(), result.getModel());

        when(connection.doGet("/")).thenReturn("Test Data");
        result = hc.pullPage("/");
        assertEquals("showMessage", result.getViewName());
        assertEquals("Test Data", result.getModel().get("date_time"));
    }

}
