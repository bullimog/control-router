package router.config;

public class LoginControllerConfig {

    public String loginUrl;
    public String username;
    public String password;


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
