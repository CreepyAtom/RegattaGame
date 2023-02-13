package fr.ensicaen.genielogiciel.mvp.presenter;

import javafx.scene.paint.Color;

public interface IGameView {
    void addBoat( double x, double y, Color color );

    void update( int i, double dx, double dy, double angle );

    int getLanguage();
    void setLanguage(int language);

    void close();

    void createFloater(int i, int j);

    void drawFinishLine(int x, int y, int w, int h);
}
