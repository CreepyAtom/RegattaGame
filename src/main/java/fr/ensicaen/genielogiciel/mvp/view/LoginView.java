package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.ILoginView;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView implements ILoginView {
    private LoginPresenter _loginPresenter;
    private SettingsView _settingsView;
    private Stage _stage;
    @FXML
    private TextField _nickName;
    @FXML
    private Label _errorMessage;

    private static int _language; // 0 French, 1 English

    public void setLanguage(int language){
        _language = language;
    }

    public int getLanguage() {
        return _language;
    }
    public void setLoginPresenter( LoginPresenter presenter ) {
        _loginPresenter = presenter;
    }

    public void show() {
        _stage.show();
    }

    @Override
    public void close() {
        _stage.close();
    }

    @Override
    public void displayError( String message ) {
        _errorMessage.setText(message);
    }

    @FXML
    private void onClickOnStartGame() {
        System.out.print("ONCLICKSTARTGAME");

        _loginPresenter.launchGame(_nickName.getText());
    }

    @FXML
    private void onClickOnSettings() throws IOException {
        System.out.print("CLICKED SETTINGS");
        _loginPresenter.launchSettings();
    }

    public static class LoginViewFactory {
        private LoginViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static LoginView createView( Stage primaryStage ) throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("LoginDialog.fxml"), Main.getMessageBundle(_language));
            Parent root = loader.load();
            LoginView view = loader.getController();
            Scene scene = new Scene(root);
            view._stage = primaryStage;
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            System.out.println("Langage : " + _language );
            return view;
        }
    }
}