package org.atypon.DBConnection.Util;

import java.io.IOException;
import java.util.Map;

public class Cache {
    private static final int CACHE_SIZE = 1000;

    public static boolean addToTemp(String filePath, Object data) throws IOException {
        Map<String, Object> tempData = ReadUtil.readFile(APIPath.temp());
        if (tempData.size() > CACHE_SIZE) {
            clearTemp(tempData);
        }
        tempData.put(filePath, data);
        updateCachedData(tempData);
        return tempData.containsKey(filePath) && tempData.containsValue(data);
    }

    public static <T> T getFromTemp(String path) throws IOException {
        Map<String, Object> temp = ReadUtil.readFile(APIPath.temp());
        if (temp.containsKey(path)) {
            System.out.println("we get into cache");
            return (T) temp.get(path);
        }
        return null;
    }

    private static void updateCachedData(Object data) throws IOException {
        WriteUtil<Object> writer = new WriteUtil<>();
        writer.writeFile(APIPath.temp(), data);
    }

    private static void clearTemp(Map<String, Object> map) throws IOException {
        map.clear();
    }
}
