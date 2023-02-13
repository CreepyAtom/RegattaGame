package fr.ensicaen.genielogiciel.mvp.model;

import java.net.*;
import java.io.*;
import java.util.*;
import org.json.*;


public class WeatherModel {
    private final double _speed;
    private final double _angle;
    private final String _angleString;


    public WeatherModel() throws IOException, JSONException {
        Map<String, Integer> directions = new HashMap<>();
        directions.put("N", 0);
        directions.put("NE", 45);
        directions.put("E", 90);
        directions.put("SE", 135);
        directions.put("S", 180);
        directions.put("SO", 225);
        directions.put("O", 270);
        directions.put("NO", 315);
        String url_string = "https://www.prevision-meteo.ch/services/json/lat=49.283lng=-0.25";
        URL url = new URL(url_string);
        StringBuilder json;
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
        }

        String jsonString = json.toString();

        //System.out.println(jsonString);

        JSONObject j = new JSONObject(jsonString);

        _angleString = j.getJSONObject("current_condition").getString("wnd_dir");
        // 1.852 is the factor between km/h and knots
        double speed = j.getJSONObject("current_condition").getDouble("wnd_spd") / 1.852;
        double angle = directions.get(_angleString);
        if (speed <= 4) {
            _speed = 4;
        } else if (speed >= 30){
            _speed = 30;
        } else {
            _speed = Math.round(speed/2) * 2;
        }
        _angle = Math.round(angle / 10) * 10;
    }

    WeatherModel(double speed, double angle){
        if (speed <= 4) {
            _speed = 4;
        } else if (speed >= 30){
            _speed = 30;
        } else {
            _speed = Math.round(speed/2) * 2;
        }
        _angle = Math.round(angle / 10) * 10;
        if (angle < 22.5) {
            _angleString = "N";
        } else if (angle < 67.5) {
            _angleString = "NE";
        } else if (angle < 112.5) {
            _angleString = "E";
        } else if (angle < 157.5) {
            _angleString = "SE";
        } else if (angle < 202.5) {
            _angleString = "S";
        } else if (angle < 247.5) {
            _angleString = "SO";
        } else if (angle < 292.5) {
            _angleString = "O";
        } else if (angle < 337.5) {
            _angleString = "NO";
        } else {
            _angleString = "N";
        }
    }

    public int get_angle() {
        return (int)_angle;
    }

    public int get_speed() {
        return (int)_speed;
    }

    public String get_angleString() {
        return _angleString;
    }
}
