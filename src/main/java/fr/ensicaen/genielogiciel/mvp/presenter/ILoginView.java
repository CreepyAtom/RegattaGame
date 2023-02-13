package fr.ensicaen.genielogiciel.mvp.presenter;

public interface ILoginView {
    void displayError( String string );

    void close();

    int getLanguage();

    void setLanguage(int language);
}
