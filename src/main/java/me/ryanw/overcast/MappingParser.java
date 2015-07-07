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
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a byte object type.
     * @param id The id of the json object to fetch and return as a byte
     * @return Filtered byte result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public Byte getByte(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return Byte.parseByte(doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, ""));
            }
            return Byte.parseByte(doc.select(jsonObject.get("selector").getAsString()).text());
        }
        return null;
    }

    /**
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a short object type.
     * @param id The id of the json object to fetch and return as a short
     * @return Filtered short result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public Short getShort(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return Short.parseShort(doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, ""));
            }
            return Short.parseShort(doc.select(jsonObject.get("selector").getAsString()).text());
        }
        return null;
    }

    /**
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a long object type.
     * @param id The id of the json object to fetch and return as a long
     * @return Filtered long result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public Long getLong(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return Long.parseLong(doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, ""));
            }
            return Long.parseLong(doc.select(jsonObject.get("selector").getAsString()).text());
        }
        return null;
    }

    /**
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a float object type.
     * @param id The id of the json object to fetch and return as a float
     * @return Filtered float result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public Float getFloat(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return Float.parseFloat(doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, ""));
            }
            return Float.parseFloat(doc.select(jsonObject.get("selector").getAsString()).text());
        }
        return null;
    }

    /**
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a double object type.
     * @param id The id of the json object to fetch and return as a double
     * @return Filtered double result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public Double getDouble(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return Double.parseDouble(doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, ""));
            }
            return Double.parseDouble(doc.select(jsonObject.get("selector").getAsString()).text());
        }
        return null;
    }

    /**
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a boolean object type.
     * @param id The id of the json object to fetch and return as a boolean
     * @return Filtered boolean result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public Boolean getBoolean(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return Boolean.parseBoolean(doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, ""));
            }
            return Boolean.parseBoolean(doc.select(jsonObject.get("selector").getAsString()).text());
        }
        return null;
    }

    /**
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a integer object type.
     * @param id The id of the json object to fetch and return as a integer
     * @return Filtered integer result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public Integer getInteger(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return Integer.parseInt(doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, ""));
            }
            return Integer.parseInt(doc.select(jsonObject.get("selector").getAsString()).text());
        }
        return null;
    }

    /**
     * Sends a call to {@link #getEntry(String)}, it will select the content at the cssQuery provided and then
     * parse the result if a filter is provided and return the result as a string object type.
     * @param id The id of the json object to fetch and return as a string
     * @return Filtered string result of a {@link #getEntry(String)}
     * @throws IOException Passed on from {@link #getEntry(String)} to be handled by the user.
     */
    public String getString(String id) throws IOException {
        JsonObject jsonObject = getEntry(id);
        if (jsonObject != null) {
            if (jsonObject.get("filter") != null) {
                String regex = Pattern.compile(doc.select(jsonObject.get("filter").getAsString()).text()).toString();
                return doc.select(jsonObject.get("selector").getAsString()).text().replaceAll(regex, "");
            }
            return doc.select(jsonObject.get("selector").getAsString()).text();
        }
        return null;
    }

    /**
     * Searches through a JsonArray created from the mappings.json file for one that has the same ID as one passed
     * through as a method arg. If it can't find one, the method will return null.
     * @param id The id of the json object to look for
     * @return JsonObject that can be filtered and converted to the correct object type
     * @throws IOException Thrown if there is a error parsing the mappings from the location provided
     */
    public JsonObject getEntry(String id) throws IOException {
        String mappings = new JsonParser().parse(new InputStreamReader(new URL(location).openStream())).toString();
        if (mappings == null) throw new NullPointerException("Mappings location cannot be null!");
        JsonArray jsonArray = (JsonArray) new JsonParser().parse(mappings);

        for (int i =0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if (jsonObject.get("id").getAsString().equals(id)) return jsonObject;
        }
        return null;
    }
}
