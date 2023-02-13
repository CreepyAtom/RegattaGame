package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.ISettingsView;
import fr.ensicaen.genielogiciel.mvp.presenter.SettingsPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsView implements ISettingsView {
    private static Stage _stage;
    private SettingsPresenter _settingsPresenter;
    @FXML
    private Label _errorMessage;
    private static int _language; // 0 French, 1 English


    public void setLanguage(int language){
        _language = language;
        System.out.println("Langage CHANGED : " + _language );
    }
    public void setLanguageEnglish(){
        _language = 1;
        System.out.println("Langage CHANGED : " + _language );
    }
    public void setLanguageFrench(){
        _language = 0;
        System.out.println("Langage CHANGED : " + _language );
     }

    public int getLanguage() {
        return _language;
    }
    public void setSettingsPresenter( SettingsPresenter presenter ) {
        _settingsPresenter = presenter;
    }

    public void show() {
        _stage.show();
    }

    @Override
    public void close() {
        _stage.close();
    }

    @Override
    public void displayError(String message) {
        _errorMessage.setText("woooooooooooow");
    }

    @FXML   private void onClickOnReturn() throws IOException {
        System.out.print("CLICKED SETTINGS");
        _settingsPresenter.launchLogin();
    }

    public static class SettingsViewFactory {
        private SettingsViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static SettingsView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(SettingsView.class.getResource("Settings.fxml"), Main.getMessageBundle(_language));
            Parent root = loader.load();
            SettingsView view = loader.getController();
            Scene scene = new Scene(root, 632.0, 495.0);
            Stage stage = new Stage();
            stage.setTitle(Main.getMessageBundle(_language).getString("project.title"));
            stage.setScene(scene);
            stage.setResizable(false);
            _stage = stage;
            System.out.println("Langage : " + _language );
            return view;
        }
    }
}