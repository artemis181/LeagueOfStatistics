package com.example.iyengara18.leagueofstatistics;

import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    final static String LOG_TAG = MainActivity.class.getSimpleName();

    /** Sample JSON response for a USGS query */
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    public static ArrayList<ChampMasteryInfo> extractMasteryInfo(String JSONResponse) {

        // Create an empty ArrayList that we can start adding matches to
        ArrayList<ChampMasteryInfo> champMasteries = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(JSONResponse);
            JSONArray eq = root.getJSONArray("features");
            double mag = 0.0;
            String loc = "";
            long sec = 0;
            String url = "";
            for(int i=0;i<eq.length();i++){
                JSONObject eObj = eq.getJSONObject(i);
                JSONObject earthObj = eObj.getJSONObject("properties");
                mag = earthObj.getDouble("mag");
                loc = earthObj.getString("place");
                sec = earthObj.getLong("time");
                url = earthObj.getString("url");
                ChampMasteryInfo mastery = new ChampMasteryInfo(loc, loc, 6, 9);
                champMasteries.add(mastery);
            }
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return champMasteries;
    }

    public static ArrayList extractPlayerData(String JSONResponse){
        ArrayList playerData = new ArrayList();
        return playerData;
    }

    public static ArrayList<MatchHistoryInfo> extractMatchData(String JSONresponse){
        ArrayList<MatchHistoryInfo> matchHistory = new ArrayList<>();
        return matchHistory;
    }

    public static List<ChampMasteryInfo> fetchMasteryData(String requestUrl){
        URL url = createUrl(requestUrl);
        String JSONResponse = null;
        try{
            JSONResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Error creating connection");
        }
        List<ChampMasteryInfo> champMasteries = extractMasteryInfo(JSONResponse);
        return champMasteries;
    }

    public static List fetchPlayerData(String requestUrl){
        URL url = createUrl(requestUrl);
        String JSONResponse = null;
        try{
            JSONResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Error creating connection");
        }
        List playerData = extractPlayerData(JSONResponse);
        return playerData;
    }

    public static List<MatchHistoryInfo> fetchMatchData(String requestUrl){
        URL url = createUrl(requestUrl);
        String JSONResponse = null;
        try{
            JSONResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Error creating connection");
        }
        List<MatchHistoryInfo> matchHistory = extractMatchData(JSONResponse);
        return matchHistory;
    }

    private static URL createUrl(String urlString){
        URL url = null;
        try{
            url = new URL(urlString);
        }catch(MalformedURLException e){
            Log.e(LOG_TAG, "Error creating URL", e);
            return null;
        }
        return url;
    }

    private static String makeHttpRequest(URL url)throws IOException{
        String JSONResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                JSONResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG, "Error response code:" + urlConnection.getResponseCode());
            }
        }catch(IOException e){
            Log.e(LOG_TAG, "Problem retrieving the JSON results");
        }finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return JSONResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}

