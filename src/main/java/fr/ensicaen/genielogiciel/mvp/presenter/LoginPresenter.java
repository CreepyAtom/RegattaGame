package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import fr.ensicaen.genielogiciel.mvp.view.SettingsView;
import javafx.stage.Stage;

import java.io.IOException;

public final class LoginPresenter {
    private ILoginView _loginView;
    private Stage _stage;

    public void setStage(Stage stage){
        _stage = stage;
    }

    public void setLoginView( LoginView loginView ) {
        _loginView = loginView;
    }

    public void launchGame( String nickName ) {
        if (nickName.isEmpty()) {
            _loginView.displayError(Main.getMessageBundle(_loginView.getLanguage()).getString("error.nickname"));
        } else {
            try {
                GameView view = GameView.GameViewFactory.createView();
                GamePresenter gamePresenter = new GamePresenter(nickName);
                view.setGamePresenter(gamePresenter);
                gamePresenter.setGameView(view);
                gamePresenter.setStage(_stage);
                view.setLanguage(_loginView.getLanguage());
                view.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            _loginView.close();
        }
    }

    public void launchSettings() {
        System.out.print("Started launchSettings");
        try {
            SettingsView view = SettingsView.SettingsViewFactory.createView();
            SettingsPresenter settingsPresenter = new SettingsPresenter();
            settingsPresenter.setSettingsView(view);
            settingsPresenter.setStage(_stage);
            view.setSettingsPresenter(settingsPresenter);
            view.setLanguage(_loginView.getLanguage());
            view.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _loginView.close();
    }
}
