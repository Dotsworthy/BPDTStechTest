package com.example.bpdts.BPDTSTEST;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APITestFetch {

    public static HttpURLConnection connection;

    public static void main(String[] args) {
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
//            System.out.println(responseContent.toString());
            parse(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String parse(String responseBody) {
        JSONArray users = new JSONArray(responseBody);
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            int id = user.getInt("id");
//            int userId = user.getInt("userId");
            String firstName = user.getString("first_name");
            System.out.println(id + " " + firstName);
        }
        return null;
    }

}
