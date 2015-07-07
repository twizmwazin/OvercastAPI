package me.ryanw.overcast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Pattern;

public class MappingParser {

    private final String location = "https://raw.githubusercontent.com/ryanw-se/OvercastAPI/master/mappings/mappings.json";
    private final Document doc;

    public MappingParser(Document doc) {
        this.doc = doc;
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     * @param id The id of the json object to fetch and return as a byte
     * @return Filtered byte result of a {@link #getEntry(String)}
     */
    public Byte getByte(String id) {
        return Byte.parseByte(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     * @param id The id of the json object to fetch and return as a short
     * @return Short result of a {@link #getEntry(String)}
     */
    public Short getShort(String id) {
        return Short.parseShort(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     * @param id The id of the json object to fetch and return as a long
     * @return Long result of a {@link #getEntry(String)}
     */
    public Long getLong(String id) {
        return Long.parseLong(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     * @param id The id of the json object to fetch and return as a float
     * @return Float result of a {@link #getEntry(String)}
     */
    public Float getFloat(String id) {
        return Float.parseFloat(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     * @param id The id of the json object to fetch and return as a double
     * @return Double result of a {@link #getEntry(String)}
     */
    public Double getDouble(String id) {
        return Double.parseDouble(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     * @param id The id of the json object to fetch and return as a boolean
     * @return Boolean result of a {@link #getEntry(String)}
     */
    public Boolean getBoolean(String id) {
        return Boolean.parseBoolean(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     * @param id The id of the json object to fetch and return as a integer
     * @return Integer result of a {@link #getEntry(String)}
     */
    public Integer getInteger(String id) {
        return Integer.parseInt(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result, alternative call.
     * @param id The id of the json object to fetch and return as a string
     * @return String result of a {@link #getEntry(String)}
     */
    public String getString(String id) {
        return getEntry(id);
    }

    /**
     * Searches through a JsonArray created from the mappings.json file for one that has the same ID as one passed
     * through as a method arg. If it can't find one, the method will return null.
     * @param id The id of the json object to look for
     * @return String that can be converted into the correct primitive type
     */
    public String getEntry(String id) {
        String mappings;
        try {
            mappings = new JsonParser().parse(new InputStreamReader(new URL(location).openStream())).toString();
            if (mappings == null) throw new NullPointerException("Mappings location cannot be null!");
            JsonArray jsonArray = (JsonArray) new JsonParser().parse(mappings);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);
                if (jsonObject.get("id").getAsString().equals(id)) {
                    if (jsonObject.get("filter") != null) {
                        String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                        return doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, "");
                    }
                    return doc.select(jsonObject.get("selector").getAsString()).text();
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}
