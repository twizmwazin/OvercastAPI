package me.ryanw.overcast.mappings;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Pattern;

public class MappingsParser {

    private final String url = "https://raw.githubusercontent.com/ryanw-se/OvercastAPI/master/mappings/mappings.json";
    private final JsonReader jsonReader;
    private final Document document;

    public MappingsParser(Document document) throws IOException {
        this.document = document;
        this.jsonReader = new JsonReader(new InputStreamReader(new URL(url).openStream()));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a byte
     * @return Filtered byte result of a {@link #getEntry(String)}
     */
    public Byte getByte(String id) {
        return Byte.parseByte(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a short
     * @return Short result of a {@link #getEntry(String)}
     */
    public Short getShort(String id) {
        return Short.parseShort(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a long
     * @return Long result of a {@link #getEntry(String)}
     */
    public Long getLong(String id) {
        return Long.parseLong(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a float
     * @return Float result of a {@link #getEntry(String)}
     */
    public Float getFloat(String id) {
        return Float.parseFloat(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a double
     * @return Double result of a {@link #getEntry(String)}
     */
    public Double getDouble(String id) {
        return Double.parseDouble(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a boolean
     * @return Boolean result of a {@link #getEntry(String)}
     */
    public Boolean getBoolean(String id) {
        return Boolean.parseBoolean(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a integer
     * @return Integer result of a {@link #getEntry(String)}
     */
    public Integer getInteger(String id) {
        return Integer.parseInt(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result, alternative call.
     *
     * @param id The id of the json object to fetch and return as a string
     * @return String result of a {@link #getEntry(String)}
     */
    public String getString(String id) {
        return getEntry(id);
    }

    /**
     * Gets a entry from the mappings file, passes the selector through Jsoup and formats the result.
     * @param id The id to look for in the mappings file
     * @return formatted result selected in the html file by Jsoup using the selector
     */
    public String getEntry(String id) {
        JsonArray mappingArray = new JsonParser().parse(jsonReader).getAsJsonArray();

        for (JsonElement mapping : mappingArray) {
            MappingsEntry mappingsEntry = new Gson().fromJson(mapping, MappingsEntry.class);

            if (mappingsEntry.id.equals(id)) {
                if (mappingsEntry.selector == null) return null;
                String result = document.select(mappingsEntry.selector).text();
                return result.replaceAll(String.valueOf(Pattern.compile(mappingsEntry.filter)), "");
            }
        }

        return null;
    }

    /**
     * Represents one given entry in an array of mapping entries
     */
    private class MappingsEntry {
        private String id;
        private String selector;
        private String filter;
    }
}