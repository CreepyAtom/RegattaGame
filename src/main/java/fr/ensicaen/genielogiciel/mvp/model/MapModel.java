package fr.ensicaen.genielogiciel.mvp.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MapModel {
    private final String _mapName;
    private final int _width;
    private final int _height;
    private final int [][] _theMap;
    private final Position [] _start;
    private final Position [] _end;

    public MapModel(String mapName){
        _width  = 800;
        _height = 600;
        _mapName = mapName;
        _theMap = new int [_height][_width];
        _start = new Position[3];
        _end = new Position[2];
    }

        public void loadMap() throws IOException {
            System.out.println(new File(_mapName).getAbsolutePath());
            String file = new File(_mapName).getAbsolutePath();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String c = null;
            String []array = null;
            int height =0, width=0;

            readStart(br);
            readEnd(br);

            while ((c = br.readLine()) != null && height <600 && width <800) {
                 array =  c.split("");
                 for(String el: array) {
                     _theMap[height][width] = Integer.parseInt(el);
                     width++;
                 }
                 width = 0;
                 height++;
            }

    }

    private void readStart( BufferedReader br ) throws IOException {
        String []array = null;
        String []start = null;
        String c = null;
        int nbPlayer = 0;

        if((c = br.readLine()) != null){
            array =  c.split(":");
            for(String startPosition: array){
                start = startPosition.split(" ");
                _start[nbPlayer] = new Position();
                System.out.println(nbPlayer);
                _start[nbPlayer]._x = Integer.parseInt(start[0]);
                _start[nbPlayer]._y = Integer.parseInt(start[1]);
                nbPlayer++;
            }
        }
    }

    private void readEnd( BufferedReader br) throws IOException {
        String []array = null;
        String []end = null;
        String c = null;

        if((c = br.readLine()) != null){
            array =  c.split(":");
            end = array[0].split(" ");
            _end[0] = new Position();
            _end[0]._x = Integer.parseInt(end[0]);
            _end[0]._y = Integer.parseInt(end[1]);
            end = array[1].split(" ");
            _end[1] = new Position();
            _end[1]._x = Integer.parseInt(end[0]);
            _end[1]._y = Integer.parseInt(end[1]);

        }
    }

    public String get_mapName() {
        return _mapName;
    }

    public int get_width() {
        return _width;
    }

    public int get_height() {
        return _height;
    }

    public int[][] get_theMap() {
        return _theMap;
    }

    public Position[] get_start() {
        return _start;
    }

    public Position[] get_end() {
        return _end;
    }

    public void display() {
        for(Position pos: _start){
                pos.displayPosition();
            }
            for(Position fin: _end){
                fin.displayPosition();
            }
        }
}
