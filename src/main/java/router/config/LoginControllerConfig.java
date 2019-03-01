package router.config;

public class LoginControllerConfig {

    private String loginUrl;
    private String username;
    private String password;


    public LoginControllerConfig(String loginUrl, String username, String password){
        this.loginUrl = loginUrl;
        this.username = username;
        this.password = password;
    }

    public String getLoginUrl(){
        return loginUrl;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
