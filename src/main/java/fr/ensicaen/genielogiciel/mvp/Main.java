package fr.ensicaen.genielogiciel.mvp;

import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public final class Main extends Application {

    public static void main( String[] args ) {
        launch(args);
    }

    public static ResourceBundle getMessageBundle(int language) {
        String French = "fr.ensicaen.genielogiciel.mvp.FRA.MessageBundle";
        String English = "fr.ensicaen.genielogiciel.mvp.ENG.MessageBundle_en_US";
        if (language == 1){
            return ResourceBundle.getBundle(English);
        }
        return ResourceBundle.getBundle(French);
    }

    @Override
    public void start( final Stage primaryStage ) throws Exception {
        primaryStage.setTitle(getMessageBundle(1).getString("project.title"));
        LoginView view = LoginView.LoginViewFactory.createView(primaryStage);
        LoginPresenter presenter = new LoginPresenter();
        presenter.setStage(primaryStage);
        presenter.setLoginView(view);
        view.setLoginPresenter(presenter);
        System.out.println("Langage : 1" );
        view.show();
    }

    @Override
    public void stop() {
        System.out.println(getMessageBundle(1).getString("project.bye"));
    }
}