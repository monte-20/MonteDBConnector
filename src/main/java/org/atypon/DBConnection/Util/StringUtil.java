package org.atypon.DBConnection.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class StringUtil {

    public static String convertMapToString(Map map) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(map);
        return json;
    }

}
