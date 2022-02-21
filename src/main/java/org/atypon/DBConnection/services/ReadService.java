package org.atypon.DBConnection.services;

import org.atypon.DBConnection.Util.Cache;
import org.atypon.DBConnection.dao.DataBaseConnector;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadService {
    private DataBaseConnector dataBaseConnector;

    public Map<String, Object> readFile(String filePath) throws IOException {
        Map<String, Object> responseData = Cache.getFromTemp(filePath);
        if (responseData == null) {
            dataBaseConnector = getDBConnector();
            responseData = dataBaseConnector.readFile(filePath);
        }
        return responseData;
    }

    public List<Map<String, Object>> readCollection(String collectionPath) throws IOException {
        List<Map<String, Object>> responseData = Cache.getFromTemp(collectionPath);
        if (responseData == null) {
            dataBaseConnector = getDBConnector();
            responseData = dataBaseConnector.readCollection(collectionPath);
        }
        return responseData;
    }

    private DataBaseConnector getDBConnector() {
        return new DataBaseConnector();
    }
}
