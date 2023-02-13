package fr.ensicaen.genielogiciel.mvp.model;

public class Position {
    public int _x;
    public int _y ;

    public void displayPosition(){
        System.out.println("( " + _x +" : " + _y + " )");
    }

    public Position() {
        _x =0;
        _y =0;
    }

    public Position(int _x, int _y) {
        this._x = _x;
        this._y = _y;
    }
}
