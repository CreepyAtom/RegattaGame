package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.MapModel;
import fr.ensicaen.genielogiciel.mvp.model.Position;


// Remarque : l'animation n'est pas considérée comme étant du graphisme à proprement parlé.
//            On peut la considérer comme une bibliothèque tiers de gestion de threading.
//            On peut donc l'utiliser dans le presenter.
import fr.ensicaen.genielogiciel.mvp.model.BoatModel;
import fr.ensicaen.genielogiciel.mvp.model.PlayerModel;
import fr.ensicaen.genielogiciel.mvp.model.RaceModel;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class GamePresenter {
    private Stage _stage;
    private final PlayerModel _playerModel;
    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    private final RaceModel _raceModel;
    private MapModel map;

    private final double frameDuration = 50; //ms

    private int [][] _theMap;


    public void setStage(Stage stage){
        _stage = stage;
    }

    public GamePresenter( String nickName ) {
        _playerModel = new PlayerModel();
        _playerModel.setNickname(nickName);
        _raceModel = new RaceModel();

        initGame();
    }

    public void setGameView( IGameView gameView ) {
        _gameView = gameView;
        Color color = Color.GREY;
        for (BoatModel boat : _raceModel.getAllBoats()) {
            _gameView.addBoat(boat.getX(), boat.getY(), color);
            color = Color.BLACK;
        }

        for(int i = 0; i<600; i++){
            for(int j = 0; j<800; j++){
                if(_theMap[i][j] == 1){
                    _gameView.createFloater(j,i);
                }
            }
        }

    Position[] end = map.get_end();
    _gameView.drawFinishLine(end[0]._x, end[0]._y, end[1]._x - end[0]._x, end[1]._y - end[0]._y);

    }

    public void handleUserAction( UserAction code ) {
        if (code == UserAction.START) {
            startGame();
        } else {
            if (code == UserAction.ESCAPE) {
                launchLogin();
            }
            changeDirection(code);
        }
    }

    private void startGame() {
        if (!_started) {
            _started = true;
            runGameLoop();
        }
    }

    private void changeDirection( UserAction action ) {
        if (action == UserAction.LEFT) {
            _raceModel.getPlayerBoat().rotate(-2);
        } else if (action == UserAction.RIGHT) {
            _raceModel.getPlayerBoat().rotate(+2);
        }
        //System.out.println("set angle " + _raceModel.getPlayerBoat().getAngle());

    }

    public void launchLogin() {
        System.out.print("Started launchSettings");
        try {
            LoginView view = LoginView.LoginViewFactory.createView(_stage);
            System.out.println("SETTINGS VIEW Langage : " + _gameView.getLanguage() );
            view.setLanguage(_gameView.getLanguage());
            view = LoginView.LoginViewFactory.createView(_stage);
            LoginPresenter LoginPresenter = new LoginPresenter();
            LoginPresenter.setLoginView(view);
            LoginPresenter.setStage(_stage);
            view.setLoginPresenter(LoginPresenter);
            view.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _gameView.close();
    }

    private void initGame() {
        map = new MapModel("./src/main/resources/fr/ensicaen/genielogiciel/mvp/map/map.txt");
        try { // TODO : move this in model
            map.loadMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _theMap = map.get_theMap();
        Position[]start = map.get_start();

        int i=0;
        for (BoatModel boat: _raceModel.getAllBoats()) {
            boat.set_x(start[i]._x);
            boat.set_y(start[i]._y);
            i++;

        }

        Position[] end = map.get_end();
        _raceModel.finishX1 = end[0]._x;
        _raceModel.finishY1 = end[0]._y;
        _raceModel.finishX2 = end[1]._x;
        _raceModel.finishY2 = end[1]._y;

    }

    private void runGameLoop() {
        _timeline = new Timeline(new KeyFrame(Duration.millis(frameDuration), onFinished -> {
            update();
            render();
        }));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private void endGameLoop() {
        _timeline.stop();
    }

    private void update() {
        _raceModel.updateRace();
        if (_raceModel.getPlayerBoat().finishedRace()) {
            endGameLoop();
            launchLogin();
        }
        //System.out.println(_raceModel.getPlayerBoat().getX()+"\t"+_raceModel.getPlayerBoat().getY());
    }

    private void render() {
        double coeff = 1;//msPerKnot * pixelPerMeter * frameDuration / 1000.0; // convertion from knots to pixels per frame
        int i = 0;
        for (BoatModel boat : _raceModel.getAllBoats()) {
            //System.out.println("get angle " + boat.getAngle());
            _gameView.update(i, boat.getX() * coeff, boat.getY() * coeff, boat.getAngle());
            i++;
        }
    }
}
