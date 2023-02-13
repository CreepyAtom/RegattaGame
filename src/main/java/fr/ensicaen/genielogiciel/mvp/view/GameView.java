package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.IGameView;
import fr.ensicaen.genielogiciel.mvp.presenter.UserAction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameView implements IGameView {
    private static Stage _stage;
    private GamePresenter _gamePresenter;
    private final List<Ellipse> _boat = new ArrayList<>();
    @FXML
    private AnchorPane _base;
    private static int _language; // 0 French, 1 English

    public void setGamePresenter( GamePresenter gamePresenter ) {
        _gamePresenter = gamePresenter;
    }
    public void setLanguage(int language){
        _language = language;
    }
    public int getLanguage(){
        return _language;
    }

    public void rotate( Ellipse boat, double val ) {
        boat.setRotate(val);
    }

    public Ellipse drawBoat( double x, double y, double rx, double ry, Color color) {
        Ellipse boat = new Ellipse(0, 0, rx, ry);
        boat.setLayoutX(x);
        boat.setLayoutY(y);
        boat.setFill(color);
        _base.getChildren().add(boat);
        return boat;
    }

    public Rectangle drawFloater(int x, int y){
        Rectangle floater = new Rectangle(x, y,5,5);
        floater.setFill(Color.YELLOW);
        _base.getChildren().add(floater);
        return floater;
    }

    public void drawFinishLine(int x, int y, int w, int h){
        Rectangle line = new Rectangle(x, y, w, h);
        line.setFill(Color.RED);
        _base.getChildren().add(line);
    }

    public void move( Ellipse boat, double x, double y ) {
        boat.setLayoutX(x);
        boat.setLayoutY(y);
    }

    public void update( int i, double x, double y, double angle ) {
        rotate(_boat.get(i), angle);
        move(_boat.get(i), x, y);
    }

    public void show() {
        _stage.show();
    }

    @Override
    public void close() {
        _stage.close();
    }

    public void addBoat( double x, double y, Color color ) {
        _boat.add(drawBoat(x, y, 6, 16, color));
    }

    public void createFloater(int x, int y){
        drawFloater(x, y);
    }

    private void handleKeyPressed( KeyCode code ) {
        if (code == KeyCode.ESCAPE) {
            System.out.println("ESCAPE heard");
            _gamePresenter.handleUserAction(UserAction.ESCAPE);
        } else if (code == KeyCode.SPACE) {
                _gamePresenter.handleUserAction(UserAction.START);
            } else if (code == KeyCode.LEFT) {
                _gamePresenter.handleUserAction(UserAction.LEFT);
            } else if (code == KeyCode.RIGHT) {
                _gamePresenter.handleUserAction(UserAction.RIGHT);
            }
        }

    public static class GameViewFactory {
        private GameViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static GameView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("SpotMap.fxml"), Main.getMessageBundle(_language));
            Parent root = loader.load();
            GameView view = loader.getController();
            Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.setTitle(Main.getMessageBundle(_language).getString("project.title"));
            stage.setScene(scene);
            _stage = stage;
            scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                KeyCode code = event.getCode();
                view.handleKeyPressed(code);
            });
            return view;
        }
    }
}