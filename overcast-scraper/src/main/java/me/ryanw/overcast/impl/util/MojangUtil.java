package me.ryanw.overcast.impl.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MojangUtil {

    /**
     * Fetches the username of a user by passing a UUID as a string. This function supports both full and trimmed UUIDs.
     * Original code was taken from here, https://github.com/twizmwazin/CardinalPGM/blob/master/src/main/java/in/twizmwaz/cardinal/util/MojangUtil.java#L14.
     * @param uuid The uuid to check with mojangs session servers
     * @return username that belongs to the UUID provided
     */
    public static String getUsername(String uuid) {
        try {
            String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.replace("-", "");
            JsonObject response = (JsonObject) new JsonParser().parse(new InputStreamReader(new URL(url).openStream()));
            return response.get("name").getAsString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Fetches the former username of a user by passing a UUID as a string. This function supports both full and trimmed UUIDs.
     * @param uuid The uuid to check with mojangs session servers
     * @return former username that belongs to the UUID provided
     */
    public static String getFormerUsername(String uuid) {
        try {
            String url = "https://api.mojang.com/user/profiles/" + uuid.replaceAll("-", "")+ "/names";
            JsonArray usernameArray = new JsonParser().parse(new InputStreamReader(new URL(url).openStream())).getAsJsonArray();

            if (usernameArray.size() >= 2) {
                JsonObject usernameElement = usernameArray.get(usernameArray.size() - 2).getAsJsonObject();
                return usernameElement.get("name").getAsString();
            }

            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Fetches the UUID of a player by using their current username to look the UUID up.
     * @param currentUsername The username to return the uuid of
     * @return the uuid owner of the username provided
     */
    public static String getUUID(String currentUsername) {
        try {
            String url = "https://api.mojang.com/users/profiles/minecraft/" + currentUsername;
            JsonObject response = (JsonObject) new JsonParser().parse(new InputStreamReader(new URL(url).openStream()));
            return response.get("id").getAsString();
        } catch (IOException e) {
            return null;
        }
    }
}
