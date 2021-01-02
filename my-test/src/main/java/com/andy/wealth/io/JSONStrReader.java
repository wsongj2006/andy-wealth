package com.andy.wealth.io;

import java.io.*;

public class JSONStrReader {
    public static String readJsonStrFromFile(String path) {
        String jsonStr = "";
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
