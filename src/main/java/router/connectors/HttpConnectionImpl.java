package router.connectors;


import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionImpl implements HttpConnection {



    @Override
    public String doGet(String url) {
        StringBuffer content = new StringBuffer();

        try {

            URL urlIn = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlIn.openConnection();
            con.setRequestMethod("GET");
//            int Status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();



        }catch(IOException ex){
            System.out.println("IOException Caught: "+ex);
        }

        return content.toString();
    }
}
