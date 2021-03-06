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

class ComicRequest {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String xkcdUri = "https://xkcd.com/";
    private static final String newComicUri = xkcdUri + "info.0.json";

    /**
     * @param comicNumber the number of the comic to get, 0 is used for latest comic.
     * @return the response containing the comic's metadata in JSON form.
     * @see JSONObject
     */

    public static JSONObject getComicInfo(int comicNumber) throws Exception {
        String comicUri = xkcdUri + comicNumber + "/info.0.json";
        URI uri = comicNumber != 0 ? URI.create(comicUri) : URI.create(newComicUri);
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        if(response.body().matches("<[^>]*>") && response.body().toLowerCase().contains("404 not found")) {
            throw new Exception("Invalid comic number.");
        }

        return new JSONObject(response.body());
    }

    /**
     * @return the comic's URL that was parsed from {@link #getComicInfo(int)}'s response.
     */

    public static InputStream getComic(int comicNumber) throws Exception {
        String imageUri = getComicInfo(comicNumber).getString("img");
        return new URL(imageUri.replaceAll("http://", "https://")).openStream();
    }
}