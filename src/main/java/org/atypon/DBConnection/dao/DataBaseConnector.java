package org.atypon.DBConnection.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atypon.DBConnection.Util.StringUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class DataBaseConnector {
    private static HttpURLConnection connection;


    public Map<String, Object> readFile(String filePath) throws IOException {
        String urlPath = "http://localhost:8080/database/readFile?path=" + filePath;
        URL url = new URL(urlPath);
        InputStream responseStream = setUpReadRequest(urlPath);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> obj = (Map<String, Object>) mapper.readValue(responseStream, Object.class);
        System.out.println(obj.toString());
        connection.disconnect();
        return obj;
    }

    public List<Map<String, Object>> readCollection(String collectionPath) throws IOException {
        String urlPath = "http://localhost:8080/database/readCollection?path=" + collectionPath;
        URL url = new URL(urlPath);
        InputStream responseStream = setUpReadRequest(urlPath);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> obj = (List<Map<String, Object>>) mapper.readValue(responseStream, Object.class);
        connection.disconnect();
        return obj;
    }

    private InputStream setUpReadRequest(String requestPath) throws MalformedURLException {
        URL url = new URL(requestPath);
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(50000);
            connection.setReadTimeout(50000);

            return connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return connection.getErrorStream();
        }
    }

    public boolean write(String filePath, Map<String, Object> data) throws IOException {
        String requestPath = "http://localhost:8080/database/write?path=" + filePath;

        connection = setUpWriteRequest(requestPath);
        String json = StringUtil.convertMapToString(data);
        System.out.println(json);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        ObjectMapper mapper = new ObjectMapper();
        boolean response = mapper.readValue(connection.getInputStream(), Boolean.class);
        connection.disconnect();

        return response;
    }

    private HttpURLConnection setUpWriteRequest(String requestPath) throws MalformedURLException {
        URL url = new URL(requestPath);
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setConnectTimeout(50000);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            return con;
        } catch (IOException e) {
            e.printStackTrace();
            throw new MalformedURLException();
        }
    }
}
