package fr.ensicaen.genielogiciel.mvp.model;

public abstract class BoatModel {
    protected double _x = 580;
    protected double _y = 480;
    private double _dx = 0;
    private double _dy = 0;
    private double _speed = 0;
    private int _anglePositive = 0;
    protected Glider _glider;
    private boolean _finishedRace = false;

    private final double coeff = 0.2;

    public BoatModel(int x, int y) {
        _x = x;
        _y = y;
    }

    public BoatModel() {

    }

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void rotate( int angle ) {
        _anglePositive = (360 + _anglePositive + angle) % 360;
    }

    public double getAngle() {
        return _anglePositive;
    }

    public double getDx() {
        return _dx* _speed * coeff;
    }

    public double getDy() {
        return _dy* _speed * coeff;
    }

    public void set_x(double _x) {
        this._x = _x;
    }

    public void set_y(double _y) {
        this._y = _y;
    }

    public void move( ) {
        _dx = Math.sin(_anglePositive * Math.PI / 180);
        _dy = -Math.cos(_anglePositive * Math.PI / 180);
        _x += _dx * _speed * coeff;
        _y += _dy * _speed * coeff;

        //System.out.println( "boat moved :" + _speed + "pos : " + _x + " " + _y);
    }

    public void setSpeed(double speed) {
        _speed = speed;

    }

    public void updateSpeed(int angleToWind, int windSpeed) {
        _speed = _glider.calculateSpeedBoat(angleToWind, windSpeed);

    }

    public boolean finishedRace() {
        return _finishedRace;
    }

    public void finishedRace(boolean b) {
        _finishedRace = b;
    }
}
