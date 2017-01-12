package com.logigear.tempeanalyze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import org.restexpress.Request;
import org.restexpress.Response;

public class SampleController {

    public SampleController() {
        super();
    }

    public Object create(Request request, Response response) {
        //TODO: Your 'POST' logic here...
        return null;
    }

    public Object average(Request request, Response response) {
        String strDate = request.getHeader("date", "No resource ID supplied");
        String valid = "";
        String ret = "";
        String output = "";
        try {
            URL url = new URL("http://lgvn14123:9870/data/temperaturelist/date?date=" + strDate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                ret = conn.getResponseCode() + ": ";
                return ret;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            double result = 0;
            output = br.readLine();
            JSONArray array = new JSONArray(output);
            if(array.length() == 0)
                return null;
            for (int i = 0; i < array.length(); i++) {
                JSONObject jObj = array.getJSONObject(i);
                double tempe = jObj.getDouble("temperature");
                result += tempe;
            }
            conn.disconnect();
            return result / array.length();
        } catch (MalformedURLException e) {
            System.out.println(e.getStackTrace());
            return ret;
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return ret;
        }
    }

    public Object max(Request request, Response response) {
        String strDate = request.getHeader("date", "No resource ID supplied");
        System.out.println("Service A : GET function");
        String valid = "";
        String ret = "";
        String output = "";
        try {
            URL url = new URL("http://lgvn14123:9870/data/temperaturelist/date?date=" + strDate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                ret = conn.getResponseCode() + ": ";
                return ret;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            output = br.readLine();
            JSONArray array = new JSONArray(output);
            if(array.length() == 0)
                return null;
            JSONObject result = array.getJSONObject(0);
            for (int i = 1; i < array.length(); i++) {
                JSONObject jObj = array.getJSONObject(i);
                double tempe = jObj.getDouble("temperature");
                if(tempe >= result.getDouble("temperature"))
                    result = jObj;
            }
            conn.disconnect();
            return result;
        } catch (MalformedURLException e) {
            System.out.println(e.getStackTrace());
            return ret;
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return ret;
        }
    }

    public Object min(Request request, Response response) {
        String strDate = request.getHeader("date", "No resource ID supplied");
        System.out.println("Service A : GET function");
        String valid = "";
        String ret = "";
        String output = "";
        try {
            URL url = new URL("http://lgvn14123:9870/data/temperaturelist/date?date=" + strDate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                ret = conn.getResponseCode() + ": ";
                return ret;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            output = br.readLine();
            JSONArray array = new JSONArray(output);
            if(array.length() == 0)
                return null;
            JSONObject result = array.getJSONObject(0);
            for (int i = 1; i < array.length(); i++) {
                JSONObject jObj = array.getJSONObject(i);
                double tempe = jObj.getDouble("temperature");
                if(tempe <= result.getDouble("temperature"))
                    result = jObj;
            }
            conn.disconnect();
            return result;
        } catch (MalformedURLException e) {
            System.out.println(e.getStackTrace());
            return ret;
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return ret;
        }
    }

    public List<Object> readAll(Request request, Response response) {
        //TODO: Your 'GET collection' logic here...
        return Collections.emptyList();
    }

    public Object read(Request request, Response response) {
        //TODO: Your 'GET collection' logic here...
        return null;
    }

    public void update(Request request, Response response) {
        //TODO: Your 'PUT' logic here...
        response.setResponseNoContent();
    }

    public void delete(Request request, Response response) {
        //TODO: Your 'DELETE' logic here...
        response.setResponseNoContent();
    }
}
