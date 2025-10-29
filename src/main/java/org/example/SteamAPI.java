package org.example;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SteamAPI {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static Double fetchCasePrice(String caseName) {
        try {
            String encodedName = URLEncoder.encode(caseName, StandardCharsets.UTF_8);
            String url = String.format(
                    "https://steamcommunity.com/market/priceoverview/?currency=3&appid=730&market_hash_name=%s",
                    encodedName
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("HTTP Error: " + response.statusCode());
                return null;
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            if (!json.get("success").getAsBoolean()) {
                System.err.println("API returned unsuccessful for " + caseName);
                return null;
            }

            String priceStr = json.get("lowest_price").getAsString();
            priceStr = priceStr.replace("€", "").replace(",", ".").trim();

            return Double.parseDouble(priceStr);

        } catch (IOException | InterruptedException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing price for " + caseName + ": " + e.getMessage());
        }
        return null;
    }
}
