package router.connectors;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class HttpConnectionImpl implements HttpConnection {



    @Override
    public String doGet(String url) {
        String rtn = "No data";

        try {

            URL urlIn = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) urlIn.openConnection();
            con.setRequestMethod("GET");



            int responseCode = con.getResponseCode();
            System.out.println("Response Code: "+responseCode);
            if(responseCode >=200 && responseCode < 300) {
                try {
                    String cookiesHeader = con.getHeaderField("Set-Cookie");
                    List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
                    System.out.println("Cookies... ");
                    for (HttpCookie cookie : cookies) {
                        System.out.println("path: " + cookie.getPath());
                        System.out.println("name: " + cookie.getName());
                        System.out.println("value: " + cookie.getValue());
                        System.out.println("domain: " + cookie.getDomain());
                        System.out.println("maxAge: " + cookie.getMaxAge());
                        System.out.println("");
                    }
                }catch(NullPointerException ex){
                    System.out.println("NPE: "+ex);
                }
            }


//            CookieManager cookieManager = new CookieManager();
//            cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));
//            Optional<HttpCookie> usernameCookie = cookies.stream()
//                    .findAny().filter(cookie -> cookie.getName().equals("username"));
//            if (usernameCookie == null) {
//                cookieManager.getCookieStore().add(null, new HttpCookie("username", "john"));
//            }
//            con.disconnect();
//            con = (HttpURLConnection) urlIn.openConnection();
//
//            con.setRequestProperty("Cookie",
//                    StringUtils.join(cookieManager.getCookieStore().getCookies(), ";"));
//
            StringBuffer content = new StringBuffer();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            rtn = content.toString();


        }catch(IOException ex){
            System.out.println("IOException Caught: "+ex);
        }

        return rtn;
    }
}
