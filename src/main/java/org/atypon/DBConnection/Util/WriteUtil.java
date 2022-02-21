package org.atypon.DBConnection.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


public class WriteUtil<T> {
    public void writeFile(String path, T data) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (file.createNewFile()) {
            System.out.println("File created at: " + path);
        }
        writeData(file, data);
    }


    private void writeData(File file, Object data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, data);

    }

}
