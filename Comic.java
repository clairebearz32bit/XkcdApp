package xkcd;

import java.io.*;
import java.net.URI;
import java.net.URL;
import org.json.JSONObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class Comic {
    public static HttpClient client = HttpClient.newHttpClient();
    public static final String xkcdURI = "https://xkcd.com/";
    public static final String comicURI = xkcdURI + "%d/info.0.json";
    public static final String newestComicURI = xkcdURI +"info.0.json";

    public static String getComicUrl(Integer n) throws IOException, InterruptedException {
        return getComicInfo(n).getString("img");
    }

    public static JSONObject getComicInfo(Integer n) throws IOException, InterruptedException {
        URI uri = n != 0 ? URI.create(String.format(comicURI, n)) : URI.create(newestComicURI);
        HttpRequest request = HttpRequest.newBuilder(uri).header("accept", "application/json").build();
        HttpResponse<String> response;
        response = client.send(
                request,
                BodyHandlers.ofString()
        );

        return new JSONObject(response.body());
    }

    public static InputStream getComic(Integer n) throws IOException, InterruptedException {
        return new URL(getComicUrl(n).replaceAll("http://", "https://")).openStream();
    }
}
