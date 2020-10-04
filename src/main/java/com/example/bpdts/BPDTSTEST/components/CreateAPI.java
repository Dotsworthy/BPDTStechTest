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
import java.util.ArrayList;

@Component
public class CreateAPI implements ApplicationRunner {

    public CreateAPI() throws MalformedURLException {
    }

    public void run(ApplicationArguments args) {
        getApi("London", urlLondon);
        getApi("ArrayList", urlAllUsers);
        getUsers(filteredUsers);
    }

    @Autowired
    UserRepository userRepository;
    ArrayList<Integer> filteredUsers = new ArrayList<Integer>();

    URL urlLondon = new URL("https://bpdts-test-app-v3.herokuapp.com/city/London/users");
    URL urlAllUsers = new URL("https://bpdts-test-app-v3.herokuapp.com/users");

    Double londonLatitude = getRadians(51.5074);
    Double londonLongitude = getRadians(0.1278);

    public static HttpURLConnection connection;

    public void getApi(String getType, URL url) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer ();
        try {
//            URL url = new URL("https://bpdts-test-app-v3.herokuapp.com/city/London/users");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) !=null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            if (getType == "London") {
                parseLondoners(responseContent.toString());
            } else if (getType == "ArrayList") {
                parseUsersByProximity(responseContent.toString());
            } else if (getType == "User") {
                parseUser(responseContent.toString());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUsers(ArrayList arrayList) {
        arrayList.forEach(e -> {
            int userId = (int) e;
            String output = String.format("https://bpdts-test-app-v3.herokuapp.com/user/%s", userId);
            URL userURL = null;
            try {
                userURL = new URL(output);
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            getApi("User", userURL);
        });
    }

    double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        return 6371 * Math.acos(
                Math.sin(latitude1) * Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude2 - longitude1)
        );
    }

    double getRadians(double degree) {
        return degree * (Math.PI / 180);
    }


    public void parseLondoners(String responseBody) {
        JSONArray users = new JSONArray(responseBody);
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            String firstName = user.getString("first_name");
            String lastName = user.getString("last_name");
            String email = user.getString("email");
            String ipAddress = user.getString("ip_address");
            double latitude = user.getDouble("latitude");
            double longitude = user.getDouble("longitude");
            String city = "London";

            User api = new User(firstName, lastName, email, ipAddress, latitude, longitude, city);

            userRepository.save(api);
        }
    }

    public void parseUsersByProximity(String responseBody) {
        JSONArray users = new JSONArray(responseBody);
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            int userId = user.getInt("id");
            double latitude = user.getDouble("latitude");
            double longitude = user.getDouble("longitude");

            double radianLatitude = getRadians(latitude);
            double radianLongitude = getRadians(longitude);

            double distance = getDistance(londonLatitude, londonLongitude, radianLatitude, radianLongitude);
            System.out.println(distance);

            if (distance >= 1685.1769633508) {
               filteredUsers.add(userId);
            }
        }
    }

    public void parseUser(String responseBody) {
            JSONObject user = new JSONObject(responseBody);
            String firstName = user.getString("first_name");
            String lastName = user.getString("last_name");
            String email = user.getString("email");
            String ipAddress = user.getString("ip_address");
            double latitude = user.getDouble("latitude");
            double longitude = user.getDouble("longitude");
            String city = user.getString("city");

            if (city != "London") {
                User api = new User(firstName, lastName, email, ipAddress, latitude, longitude, city);
                userRepository.save(api);
            }

    }
}
