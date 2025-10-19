package com.termux.app;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;


public class ReadJson {
    public Map<String, String> readScriptsFromJson(File file) {
        Map<String, String> scriptsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }

            // Parse the JSON
            JSONObject jsonObject = new JSONObject(jsonString.toString());
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String label = keys.next();
                String scriptPath = jsonObject.getString(label);
                scriptsMap.put(label, scriptPath);  // Add label and script path to the map
            }
            return scriptsMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scriptsMap;
    }

}
