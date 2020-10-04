package com.example.bpdts.BPDTSTEST.components;

import com.example.bpdts.BPDTSTEST.models.User;
import com.example.bpdts.BPDTSTEST.repositories.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class CreateAPI implements ApplicationRunner {

    public void run(ApplicationArguments args) {
        getApi();
    }

    @Autowired
    UserRepository userRepository;

    public static HttpURLConnection connection;

    public void getApi() {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer ();
        try {
            URL url = new URL("https://bpdts-test-app-v3.herokuapp.com/city/London/users");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) !=null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) !=null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            parse(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(String responseBody) {
        JSONArray users = new JSONArray(responseBody);
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            int id = user.getInt("id");
            String firstName = user.getString("first_name");
            String lastName = user.getString("last_name");
            String email = user.getString("email");
            String ipAddress = user.getString("ip_address");
            double latitude = user.getDouble("latitude");
            double longitude = user.getDouble("longitude");

            User api = new User(firstName, lastName, email, ipAddress, latitude, longitude);

            userRepository.save(api);
        }

    }
}
