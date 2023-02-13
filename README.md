[<img src="https://www.ensicaen.fr/wp-content/uploads/2017/02/LogoEnsicaen.gif" width="256" >](https://www.ensicaen.fr)

Projet de régate
================

## Description du projet

Ce projet contient un exemple d'une application graphique écrite en Java avec
la bibliothèque graphique JavaFX. Elle est basée sur le patron d'architecture
Modèle-Vue-Présentation.

Le projet est géré par le moteur de production 'gradle'.

## Organisation du projet
 
Le projet a la structure suivante :

    .
    │
    ├── build.gradle, settings.gradle, gradle.properties
    │
    ├── .gitlab-ci.yml
    │
    └── src
        ├── main
        │   ├── java
        │   │      ├── fr.ensicaen.genielogiciel.mvp/*.java
        │   │      ├── fr.ensicaen.genielogiciel.mvp.model/*.java
        │   │      ├── fr.ensicaen.genielogiciel.mvp.presenter/*.java
        │   │      └── fr.ensicaen.genielogiciel.mvp.view/*.java
        │   │
        │   └── resources
        │          ├── fr.ensicaen.genielogiciel.mvp/view/*.fxml
        │          ├── fr.ensicaen.genielogiciel.mvp/view/*.css
        │          └── fr.ensicaen.genielogiciel.mvp/MessageBundle.properties.properties
        ├── test
            ├── java
            │      └── fr.ensicaen.genielogiciel.mvp/*.java
            └── resources

