package com.class1926.copybigdata;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author junhi
 * @date 2019/6/20 10:04
 */
public class test02 {

    @Test
    public void test() throws IOException {
        Object[][] obj = {  {"三亚", "三明", "三门峡", "上海"},
                {21, 22, 23, 24},
                {31, 32, 33, 34},
                {41 , 42, 43, 44}
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(obj);


    }
}
