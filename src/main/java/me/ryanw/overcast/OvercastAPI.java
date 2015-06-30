package me.ryanw.overcast;

import me.ryanw.overcast.objects.OvercastPlayer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class OvercastAPI {

    public static OvercastPlayer getPlayer(String username) throws IOException {
        Connection.Response response = Jsoup.connect("https://oc.tc/users/" + username).method(Connection.Method.GET).execute();
        if (response.statusCode() != 200) return null;
        return new OvercastPlayer(response.parse());
    }
}
