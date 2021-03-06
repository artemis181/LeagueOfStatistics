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

    public static int summonerId;
    public static int accountId;
    private static String REQUEST_CHAMP_URL;
    private static final String API_KEY = "RGAPI-47cbc5f0-7cbb-43ea-927b-f41a433ea0c6";

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
            JSONArray masteryArray = new JSONArray(JSONResponse);
            String name="fill";
            String epithet="fill";
            int masteryLevel;
            int pointsLeft;
            int champId;
            for(int i=0;i<10;i++){
                JSONObject masteryObj = masteryArray.getJSONObject(i);
                masteryLevel = masteryObj.getInt("championLevel");
                pointsLeft = masteryObj.getInt("championPointsUntilNextLevel");
                champId = masteryObj.getInt("championId");
                REQUEST_CHAMP_URL = "https://na1.api.riotgames.com/lol/static-data/v3/champions/"+champId+"?locale=en_US&api_key="+API_KEY;
                String champJSON = fetchChampName(REQUEST_CHAMP_URL);
                name = (String)extractChampName(champJSON).get(0);
                epithet = (String)extractChampName(champJSON).get(1);
                ChampMasteryInfo mastery = new ChampMasteryInfo(name, epithet, masteryLevel, pointsLeft);
                champMasteries.add(mastery);
            }
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the Mastery JSON results", e);
        }

        // Return the list of earthquakes
        return champMasteries;
    }

    public static ArrayList extractChampName(String JSONResponse){
        String champName="";
        String champEpithet="";
        ArrayList champInfo = new ArrayList();
        JSONObject champ = new JSONObject();
        try{
            champName = champ.getString("name");
            champEpithet = champ.getString("title");
        }catch(JSONException e){
            Log.e("QueryUtils", "Problem parsing the champ results");
        }
        champInfo.add(champName);
        champInfo.add(champEpithet);
        return champInfo;
    }

    public static ArrayList extractPlayerData(String JSONResponse){
        ArrayList playerData = new ArrayList();
        return playerData;
    }
    public static void extractSummonerId(String JSONResponse){
        try{
            JSONObject playerInfo = new JSONObject(JSONResponse);
            summonerId = playerInfo.getInt("id");
            accountId = playerInfo.getInt("accountId");
        }catch(JSONException e) {
            Log.e("QueryUtils", "Problem parsing summoner id results"+JSONResponse);
        }
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

    public static void fetchSummonerId(String requestUrl){
        URL url = createUrl(requestUrl);
        String JSONResponse = null;
        try{
            JSONResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Error creating connection");
        }
        extractSummonerId(JSONResponse);
    }

    public static String fetchChampName(String requestUrl){
        URL url = createUrl(requestUrl);
        String JSONResponse = null;
        try{
            JSONResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG, "Error creating connection");
        }
        return JSONResponse;
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

