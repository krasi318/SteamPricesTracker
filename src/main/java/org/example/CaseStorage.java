package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CaseStorage {
    private static final String FILE_PATH = "cases.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();




    public static Map<String, Case> loadCases() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No cases.json found — creating new file.");
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, Case>>() {}.getType();
            Map<String, Case> cases = gson.fromJson(reader, type);
            if (cases == null) return new HashMap<>();
            return cases;
        } catch (Exception e) {
            System.err.println("Error reading cases.json: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void saveCases(Map<String, Case> cases) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(cases, writer);
            System.out.println("Saved " + cases.size() + " cases to cases.json");
        } catch (Exception e) {
            System.err.println("Error saving cases.json: " + e.getMessage());
        }
    }
}

