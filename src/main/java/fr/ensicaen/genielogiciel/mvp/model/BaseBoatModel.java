package fr.ensicaen.genielogiciel.mvp.model;

import java.io.FileNotFoundException;

public class BaseBoatModel extends BoatModel{

    public BaseBoatModel(int x, int y) {
        try {
            _glider = new Glider("./src/main/resources/fr/ensicaen/genielogiciel/mvp/gliders/polaire-figaro.pol");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        _x = x;
        _y = y;
    }

    public BaseBoatModel() {
        try {
            _glider = new Glider("./src/main/resources/fr/ensicaen/genielogiciel/mvp/gliders/polaire-figaro.pol");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }    }
}
