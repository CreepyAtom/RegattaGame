package fr.ensicaen.genielogiciel.mvp.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Glider {

    private final double[][] _glider;

    public Glider(String path) throws FileNotFoundException {
        _glider = new double[20][15];
        readGliderTable(path);

    }

    private void readGliderTable(String path) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(path)).useLocale(Locale.US);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 15; j++) {
                if (i == 0 & j == 0){
                    _glider[i][j] = 0;
                } else {
                    _glider[i][j] = scan.nextDouble();
                }
            }
        }
        scan.close();
    }

    public double calculateSpeedBoat(int angle, int windSpeed) {
        int i, j;
        for (i = 1; i < 20; i++) {
            if (angle <= _glider[i][0]) {
                //System.out.println("break " + i);
                break;
            }
        }
        for (j = 1; j < 15; j++) {
            if (windSpeed == _glider[0][j]) {
                break;
            }
        }
        //System.out.println(angle + " | " + _glider[i][0]);

        return lerp(_glider[i-1][0], angle, _glider[i-1][j], _glider[(i)%20][j]);
    }

    private double lerp(double angleInf, double angleReal, double speedInf, double speedSup) {
        int angleStep = 10;
        return (angleReal-angleInf)/(angleStep) * (speedSup - speedInf) + speedInf;
    }
}
