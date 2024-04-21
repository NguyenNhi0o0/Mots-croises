package com.crossword.puzzle;

import java.util.ArrayList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataUtil {
    
    public static String timeFomat(int counter) {
        int min = counter/60;
        int second = counter%60;
        return String.format("%02d:%02d", min, second);
    }

    public static ArrayList<QuestionModel> getDataLevel(int level) {
        ArrayList<QuestionModel> listData = new ArrayList<>();

        try {  
            String content = new String(Files.readAllBytes(Paths.get("data/data_level.json")), StandardCharsets.UTF_8);

            JSONArray jsonDate = new JSONArray(content);

            JSONArray jsonLevel = jsonDate.getJSONArray(level - 1);

            for (int i = 0; i < jsonLevel.length(); i++) {
                JSONObject data = jsonLevel.getJSONObject(i);
                String title = data.getString("title");
                String answer = data.getString("answer");
                int sloved = data.getInt("sloved");
                int start = data.getInt("start");
                listData.add(new QuestionModel(title, answer, sloved, start));
            }

        } catch (IOException e) {

        }

        return listData;
    }
    
    public static ArrayList<Integer> getListLevel() {
        ArrayList<Integer> listData = new ArrayList<>();

        try {

            String content = new String(Files.readAllBytes(Paths.get("data/level.json")), StandardCharsets.UTF_8);

            JSONArray jsonDate = new JSONArray(content);

            for (int i = 0; i < jsonDate.length(); i++) {
                JSONObject data = jsonDate.getJSONObject(i);
                int type = data.getInt("type");
                listData.add(type);
            }

        } catch (IOException e) {

        }

        return listData;
    }
}
