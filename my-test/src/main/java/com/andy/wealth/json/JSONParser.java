package com.andy.wealth.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.andy.wealth.io.JSONStrReader;
import com.andy.wealth.xls.WriteToExcel;

import java.util.List;

public class JSONParser {

    public static void main(String[] args) throws Exception {
        String path = "/Users/andy/Documents/kx-devices.json";
        String jsonStr = JSONStrReader.readJsonStrFromFile(path);
        JSONObject json = JSON.parseObject(jsonStr);
        List<JSONObject> jsonObjectList = json.getJSONObject("data").getJSONArray("devices").toJavaList(JSONObject.class);
        WriteToExcel.writeToExcel(jsonObjectList);
        System.out.println("Success");
    }
}
