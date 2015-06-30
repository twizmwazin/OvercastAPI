package me.ryanw.overcast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class MappingParser {

    private final String mappingLocation = "https://raw.githubusercontent.com/ryanw-se/OvercastAPI/master/mappings/mappings.json";
    private final Document doc;

    public MappingParser(Document doc) {
        this.doc = doc;
    }

    private String fetchMappings(String url) {
        try {
            return new JsonParser().parse(new InputStreamReader(new URL(url).openStream())).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getEntry(String id) {
        JsonArray entries = (JsonArray) new JsonParser().parse(fetchMappings(mappingLocation));

        for (int i = 0; i < entries.size(); i++) {
            JsonObject entry = ((JsonObject) entries.get(i));

            if (Objects.equals(id, entry.get("id").getAsString())) {
                String result = doc.select(entry.get("selector").getAsString()).text();
                if (result == null) return "";

                if (!entry.get("filter").isJsonNull()) {
                    result = getFilteredResult(result, entry.get("filter").getAsString());
                }
                return result;
            }
        }
        return "";
    }

    private String getFilteredResult(String result, String filter) {
        String[] filterList = filter.split(" ");
        String newResult = "";

        for (String filterItem : filterList) {
            newResult += result.replaceAll(filterItem, "");
        }

        return newResult.trim();
    }

}
