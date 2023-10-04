package com.example.lab6;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneConnexion sceneConnexion = new SceneConnexion(stage);
        SceneInscription sceneInscription = new SceneInscription(stage);
        SceneDeChargement sceneDeChargement = new SceneDeChargement();
        sceneConnexion.setSceneDeTransition(sceneInscription);
        sceneInscription.setSceneDeTransition(sceneConnexion);
        sceneConnexion.setSceneDeTransition2(sceneDeChargement);


        stage.setHeight(700);
        stage.setWidth(700);
        stage.setTitle("Lab6");
        stage.setScene(sceneConnexion);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}