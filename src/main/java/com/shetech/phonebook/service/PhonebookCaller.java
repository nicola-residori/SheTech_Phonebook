package com.shetech.phonebook.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shetech.phonebook.domain.Contact;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PhonebookCaller {

    public static final String BASE_ENDPOINT = "http://localhost:8081/contact";


    public List<Contact> retrieveContacts() {

        /* - perform call - */
        String jsonResponse = call("GET", null, null);

        /* - check response - */
        if (jsonResponse == null) {
            return null;
        }

        /* - parse jsonResponse to List of Contact - */
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Contact>>() {
        }.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public Contact retrieveContactByPhone(String phone) {

        /* - perform call - */
        String jsonResponse = call("GET", phone, null);

        /* - check response - */
        if (jsonResponse == null) {
            return null;
        }

        /* - parse jsonResponse to List of Contact - */
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Contact.class);
    }


    public boolean addContact(Contact contact) {

        /* - prepare request - */
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(contact);

        /* - perform call - */
        String jsonResponse = call("POST", null, jsonRequest);

        /* - check response - */
        return jsonResponse != null;
    }

    public boolean removeContact(String phone) {

        /* - perform call - */
        String jsonResponse = call("DELETE", phone, null);

        /* - check response - */
        return jsonResponse != null;
    }

    public boolean updateContact(String phone, Contact contact) {
        /* - prepare request - */
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(contact);

        /* - perform call - */
        String jsonResponse = call("PUT", phone, jsonRequest);

        /* - check response - */
        return jsonResponse != null;
    }

    /**
     * call rest service
     *
     * @param httpMethod
     * @param path
     * @param jsonRequest
     * @return json response
     */
    private String call(String httpMethod, String path, String jsonRequest) {

        /* - prepare response - */
        String jsonResponse = null;

        /* - prepare connection - */
        HttpURLConnection conn = null;
        try {

            /* - create connection - */
            URL url = new URL(BASE_ENDPOINT + (path != null ? "/" + path : ""));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod.toUpperCase());
            conn.setRequestProperty("Accept", "application/json");

            /* - add body request- */
            if (jsonRequest != null) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonRequest.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            /* - perform call - */
            if (conn.getResponseCode() != 200) {
                System.err.println("An error occurred during calling rest service, code=" + conn.getResponseCode() + ", response=\n" + conn.getResponseMessage());
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }

            /* - read response - */
            try (InputStreamReader in = new InputStreamReader(conn.getInputStream()); BufferedReader br = new BufferedReader(in);) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (jsonResponse == null) {
                        jsonResponse = "";
                    }
                    jsonResponse += line;
                }
            }

        } catch (Exception e) {
            System.err.println("An error occurred during calling rest service" + e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return jsonResponse;
    }
}
