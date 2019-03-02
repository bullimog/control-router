package router.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import router.config.LoginControllerConfig;
import router.connectors.HttpConnection;

import org.junit.Test;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class) //runs MockitoAnnotations.initMocks(this);
public class TestLoginController {

    @InjectMocks //Request to inject mocks into the LoginController instance
    private LoginController loginController = new LoginController();

    @Mock //Mockito will create a mock version and inject it into LoginController.
    private HttpConnection connection;

    @Mock //Mockito will create a mock version and inject it into LoginController.
    private LoginControllerConfig loginControllerConfig;

    @Test
    public void testLoginController() {
        when(loginControllerConfig.getLoginUrl()).thenReturn("/test");
        when(loginControllerConfig.getUsername()).thenReturn("Dave");
        when(loginControllerConfig.getPassword()).thenReturn("Davidson");

        HashMap<String, String> formData = new HashMap<>();
        formData.put("rn", "Dave");
        formData.put("hidepw", "Davidson");
        when(connection.doPost("/test", formData)).thenReturn(200);

        ModelAndView result = loginController.loginPage();
        assertEquals("showMessage", result.getViewName());
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("date_time", 200);
        assertEquals(expected, result.getModel());
    }

}
