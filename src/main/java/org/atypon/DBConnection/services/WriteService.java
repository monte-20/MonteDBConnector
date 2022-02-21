package org.atypon.DBConnection.services;


import org.atypon.DBConnection.Util.Cache;
import org.atypon.DBConnection.dao.DataBaseConnector;

import java.io.IOException;
import java.util.Map;

public class WriteService {

    public boolean write(String filePath, Map data) throws IOException {
        if (getDBConnector().write(filePath, data)) {
            return Cache.addToTemp(filePath, data);
        }
        System.out.println("Write Failed");
        return false;
    }

    private DataBaseConnector getDBConnector() {
        return new DataBaseConnector();
    }
}
