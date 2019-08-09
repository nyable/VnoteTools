package com.hafuhafu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Json工具类
 *
 * @author Tang
 * @date 2019/7/4 9:52
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换为Json字符串
     *
     * @param object 对象
     * @return Json字符串
     */
    public static String objectToJson(Object object) {
        try {
            String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);

            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Json字符串转换为对应Java对象
     *
     * @param json  json字符串
     * @param clazz 对象类型
     * @param <T>   泛型
     * @return 对象
     */
    public static <T> T jsonToPojo(String json, Class<T> clazz) {
        try {
            T t = MAPPER.readValue(json, clazz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Json字符串转换为List类型的对象
     *
     * @param json  Json字符串
     * @param clazz List类型
     * @param <T>   泛型
     * @return List类型的对象
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            List<T> list = MAPPER.readValue(json, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据key值获取value
     *
     * @param json         json字符串
     * @param key          key值
     * @param defaultValue 没找到对应key时返回的默认值
     * @return key值对应的value
     */
    public static String getValueByKey(String json, String key, String defaultValue) {
        JsonNode jsonNode = null;
        try {
            jsonNode = MAPPER.readTree(json);
            return jsonNode.path(key).asText(defaultValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getValueByKeyDefaultNull(String json, String key) {
        return getValueByKey(json, key, null);
    }
}
