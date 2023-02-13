package fr.ensicaen.genielogiciel.mvp.presenter;

public interface ISettingsView {
    void displayError( String string );

    void close();

    int getLanguage();

    void setLanguage(int language);
}
