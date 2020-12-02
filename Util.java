package xkcd;

import java.net.URL;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import org.json.JSONObject;
import java.net.URI;
import java.io.*;
import java.text.DateFormatSymbols;

public class Util {
    public static HttpClient client = HttpClient.newHttpClient();
    public static final String xkcdUri = "https://xkcd.com/";
    public static final String newComicUri = xkcdUri + "info.0.json";
    public static String comicUri = xkcdUri + "%d/info.0.json";

    /**
     * @return the comic's URL that was parsed from {@link #getComicInfo(int)}'s response.
     */

    public static String getComicUrl(int n) throws IOException, InterruptedException {
        return getComicInfo(n).getString("img");
    }

    /**
     * @param n the number of the comic to get, 0 is used for latest comic.
     * @return the response containing the comic's metadata in JSON form.
     * @see JSONObject
     */

    public static JSONObject getComicInfo(int n) throws IOException, InterruptedException {
        URI uri = n != 0 ? URI.create(String.format(comicUri, n)) : URI.create(newComicUri);
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return new JSONObject(response.body());
    }

    public static InputStream getComic(Integer n) throws IOException, InterruptedException {
        return new URL(getComicUrl(n).replaceAll("http://", "https://")).openStream();
    }

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
}
