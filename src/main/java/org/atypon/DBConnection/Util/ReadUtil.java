package org.atypon.DBConnection.Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


class ReadUtil {


    private static <T> T read(String path, TypeReference<T> typeReference) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream(file);
        return objectMapper.readValue(inputStream, typeReference);
    }


    public static <T> T readFile(String path) throws IOException {
        TypeReference<T> typeReference = new TypeReference<T>() {
        };
        return read(path, typeReference);
    }


}
