package router.connectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


public class HttpConnectionImpl implements HttpConnection {

    @Override
    public int doPost(String url, HashMap<String, String> formData) {
        int rtn = HttpURLConnection.HTTP_INTERNAL_ERROR;
        System.out.println("##### formData: " + formData);
        System.out.println("##### Url: " + url);

        try{
            URL urlIn = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlIn.openConnection();
            rtn = tryPost(con, formData);
        }
        catch(IOException ex){System.out.println("IOException caught: "+ex);}

        return rtn;
    }

    public int tryPost(HttpURLConnection con, HashMap<String, String> formData) throws IOException{
        int responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
        System.out.println("####### tryPost");
        //URL urlIn = new URL(url);
        //HttpURLConnection con = (HttpURLConnection) urlIn.openConnection();
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setRequestProperty("Cookie", "xAuth_SESSION_ID=someValue; name2=value2");

        StringBuffer requestParams = new StringBuffer();
        if (formData != null && formData.size() > 0) {
            con.setDoOutput(true); // true indicates POST request

//            Iterator<String> paramIterator = formData.keySet().iterator();
            formData.forEach((key,value) -> {
//                String key = paramIterator.next();
//                String value = formData.get(key);
                try{
                    requestParams.append(URLEncoder.encode(key, "UTF-8"));
                    requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
                    requestParams.append("&");
                }catch(UnsupportedEncodingException ex) {
                    System.out.println("UnsupportedEncodingException: "+ ex);
                }
            });

            OutputStreamWriter writer = new OutputStreamWriter(
                    con.getOutputStream());
            System.out.println("##### requestParams: " + requestParams);
            writer.write(requestParams.toString());
            responseCode = con.getResponseCode();
            System.out.println("##### responseCode: " + responseCode);
            writer.flush();
        }

        return responseCode;
    }


    @Override
    @Async
    public CompletableFuture<String> doGet(String url) {
        String rtn = "No data";

        try {
            URL urlIn = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlIn.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("INFO: Response Code from "+ url + " is " + responseCode);
            if(responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
                try {
                    String cookiesHeader = con.getHeaderField("Set-Cookie");
                    List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);

                    CookieManager cookieManager = new CookieManager();
                    cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));
                    Optional<HttpCookie> sessionCookie = cookies.stream()
                        .findAny().filter(cookie -> cookie.getName().equals("xAuth_SESSION_ID"));


                    System.out.println("Cookies... ");
                    for (HttpCookie cookie : cookies) {
                        System.out.println("path: " + cookie.getPath());
                        System.out.println("name: " + cookie.getName());
                        System.out.println("value: " + cookie.getValue());
                        System.out.println("domain: " + cookie.getDomain());
                        System.out.println("maxAge: " + cookie.getMaxAge());
                        System.out.println();

                        if(cookie.getName().equals("xAuth_SESSION_ID")) {
                            System.out.println("The session cookie: " + cookie.getValue());
                        }
                    }

                }catch(NullPointerException ex){
                    System.out.println("WARN: No Cookies in response");
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
        return CompletableFuture.completedFuture(rtn);
    }
}
