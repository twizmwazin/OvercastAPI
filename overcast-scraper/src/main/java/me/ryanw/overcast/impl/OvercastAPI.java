package me.ryanw.overcast.impl;

import me.ryanw.overcast.impl.object.ParsedPlayer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class OvercastAPI {

    public static ParsedPlayer getPlayer(String username) throws IOException {
        Connection.Response response = Jsoup.connect("https://oc.tc/users/" + username).method(Connection.Method.GET).execute();
        if (response.statusCode() != 200) return null;
        return new ParsedPlayer(response.parse());
    }
}
