package xkcd.src;

import java.awt.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import org.json.JSONObject;
import java.net.URI;
import java.io.*;
import java.text.DateFormatSymbols;


public class Util extends ComicRequest {
    public static String getElement(JSONObject comicInfo, String key) {
        return comicInfo.getString(key);
    }

    public static int strToInt(String s) {
        return Integer.parseInt(s);
    }

    public static String getDate(JSONObject comicInfo) {
        String day = getElement(comicInfo, "day");
        String month = new DateFormatSymbols().getMonths()[strToInt(getElement(comicInfo, "month")) - 1];
        String year = getElement(comicInfo, "year");
        return String.format("%s %s, %s", month, day, year);
    }

    public static void openUrl(String url) {
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException exception) {
                exception.printStackTrace();
            }
        }
    }
}
