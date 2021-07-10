package pace.cs639.healthyshopper;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    // Base URL for Books API.
    private static final String FDA_BASE_URL =  "https://api.nal.usda.gov/fdc/v1/foods/search?";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "query";
    // Parameter that limits search results.
    private static final String PAGE_SIZE = "pageSize";
    // Parameter to filter by print type.
    private static final String API_KEY = "api_key";
    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();
    static String getNutrientsInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String nutrientsJSONString = null;
        try {
            Uri builtURI = Uri.parse(FDA_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(PAGE_SIZE, "1")
                    .appendQueryParameter(API_KEY, "sxfwBx1cvqXRTshE7zNOeWYZRrFhegphfczpo7hG")
                    .build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();

// Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

// Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            nutrientsJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, nutrientsJSONString);
        return nutrientsJSONString;
    }
}
