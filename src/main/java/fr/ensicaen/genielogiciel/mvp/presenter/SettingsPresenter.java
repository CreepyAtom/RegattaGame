package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import fr.ensicaen.genielogiciel.mvp.view.SettingsView;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsPresenter {
    public ISettingsView _settingsView;
    private Stage _stage;
    public void setSettingsView(SettingsView settingsView) {
        _settingsView = settingsView;
    }

    public void setStage(Stage stage){
        _stage = stage;
    }

    public void launchLogin() {
        System.out.print("Started launchSettings");
        try {
            LoginView view = LoginView.LoginViewFactory.createView(_stage);
            System.out.println("SETTINGS VIEW Langage : " + _settingsView.getLanguage() );
            view.setLanguage(_settingsView.getLanguage());
            view = LoginView.LoginViewFactory.createView(_stage);
            LoginPresenter LoginPresenter = new LoginPresenter();
            LoginPresenter.setLoginView(view);
            LoginPresenter.setStage(_stage);
            view.setLoginPresenter(LoginPresenter);
            view.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _settingsView.close();
    }
}



