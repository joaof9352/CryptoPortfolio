import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {

    private URL url;
    private HttpURLConnection connection;

    public Connection(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String response(){
        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();
        try {
            connection = (HttpURLConnection) this.url.openConnection();
            //setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null)
                    response.append(line);
                reader.close();
            } else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null)
                    response.append(line);
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          connection.disconnect();
        }
        return response.toString();
    }

}
