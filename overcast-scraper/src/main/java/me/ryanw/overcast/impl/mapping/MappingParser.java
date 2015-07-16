package me.ryanw.overcast.impl.mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MappingParser {

    private final String url = "https://raw.githubusercontent.com/ryanw-se/OvercastAPI/master/mappings/mappings.json";
    private final Document document;

    public MappingParser(Document document) throws IOException {
        this.document = document;
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a byte
     * @return Filtered byte result of a {@link #getEntry(String)}
     */
    public byte getByte(String id) {
        return Byte.parseByte(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a short
     * @return Short result of a {@link #getEntry(String)}
     */
    public short getShort(String id) {
        return Short.parseShort(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a long
     * @return Long result of a {@link #getEntry(String)}
     */
    public long getLong(String id) {
        return Long.parseLong(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a float
     * @return Float result of a {@link #getEntry(String)}
     */
    public float getFloat(String id) {
        return Float.parseFloat(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a double
     * @return Double result of a {@link #getEntry(String)}
     */
    public double getDouble(String id) {
        return Double.parseDouble(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a boolean
     * @return Boolean result of a {@link #getEntry(String)}
     */
    public boolean getBoolean(String id) {
        return Boolean.parseBoolean(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a integer
     * @return Integer result of a {@link #getEntry(String)}
     */
    public int getInteger(String id) {
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

        List<String> matches = new ArrayList<String>();
        JsonArray mappingArray;

        try {
            mappingArray = new JsonParser().parse(new InputStreamReader(new URL(url).openStream())).getAsJsonArray();
        } catch (IOException e) {
            throw new NullPointerException("Cannot fetch the latest mapping file from Github: " + e.getMessage());
        }

        for (JsonElement mapping : mappingArray) {
            MappingsEntry mappingsEntry = new Gson().fromJson(mapping, MappingsEntry.class);

            if (mappingsEntry.id.equals(id)) {

                if (mappingsEntry.selector == null) {
                    return null;
                }

                String payload = document.select(mappingsEntry.selector).text();
                String result = payload;

                if (mappingsEntry.filter != null) {
                    Pattern regex = Pattern.compile(mappingsEntry.filter);
                    Matcher matcher = regex.matcher(payload);

                    while (matcher.find()) {
                        matches.add(matcher.group().trim());
                    }

                    StringBuilder builder = new StringBuilder(matches.size());
                    for (String string : matches) builder.append(string);
                    result = builder.toString().trim();
                }

                return result;
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