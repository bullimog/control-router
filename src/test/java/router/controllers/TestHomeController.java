package router.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.web.servlet.ModelAndView;
import router.connectors.HttpConnection;

import org.junit.Test;

import java.util.HashMap;

public class TestHomeController {
    @Test
    public void testHomeControllerIndex() {
        HttpConnection dataServiceMock = mock(HttpConnection.class);
        when(dataServiceMock.doGet("/")).thenReturn("Test Data");
        HomeController hc = new HomeController(dataServiceMock);
        ModelAndView result = hc.index();
        assertEquals("index", result.getViewName());
        assertEquals(new HashMap<String, Object>(), result.getModel());
    }

}
