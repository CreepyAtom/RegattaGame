package fr.ensicaen.genielogiciel.mvp.model;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.abs;

public class RaceModel {
    WeatherModel _weatherModel;
    final List<BoatModel> _boats = new ArrayList<>();

    public int finishX1, finishY1, finishX2, finishY2;

    public RaceModel() {
        try {  //temporary try catch
            _weatherModel = new WeatherModel();
        } catch (IOException | JSONException e) {
            int spd = 2 * (new Random().nextInt((15-2)+1)+2);
            int angle = 10 * (new Random().nextInt((36+1)));
            _weatherModel = new WeatherModel(spd, angle);
        }

        _boats.add(new BaseBoatModel(500, 500));
        _boats.add(new BaseBoatModel(550, 550));
        _boats.add(new BaseBoatModel(600, 600));
        //_boats.add(new BaseBoatModel(100, 100));
        finishX1 = 0;
        finishY1 = 0;
        finishX2 = 40;
        finishY2 = 40;
    }

    public void updateRace() {
        moveAIBoats();
        for (BoatModel boat : _boats) {
            calculateSpeedBoat(boat);
            boat.move();
            if (finishX1 <= boat.getX() && boat.getX() <= finishX2 && finishY1 <= boat.getY() && boat.getY() <= finishY2) {
                boat.finishedRace(true);
                System.out.println("RACE IS OVER !");
            }
        }
    }

    public BoatModel getPlayerBoat() {
        return _boats.get(0);
    }

    public List<BoatModel> getAllBoats() {
        return _boats;
    }

    public void calculateSpeedBoat(BoatModel boatModel) {
        int diffAngle = 180;
        //int diffAngle =  (int)(Math.round(abs(_weatherModel.get_angle() - boatModel.getAngle()) / 10)*10) % 180;
        int rawDiffAngle =  (int)abs(boatModel.getAngle() - _weatherModel.get_angle());
        if (rawDiffAngle < 180) {
            diffAngle =  rawDiffAngle % 180;
        } else if (rawDiffAngle > 180) {
            diffAngle =  (360-rawDiffAngle) % 180;
        }
        int windSpeed = _weatherModel.get_speed();

        boatModel.updateSpeed(diffAngle, windSpeed);

    }

    private void moveAIBoats() {
        int i = 0;
        for (BoatModel boat: getAllBoats()){
            if (i==0) {
                i++;
                continue;
            }

            Random random = new Random();
            if (random.nextInt() % 3 == 0){
                boat.rotate(-2);
            } else if(random.nextInt() % 2 == 0){
                boat.rotate(+2);
            }
        }
    }
}
