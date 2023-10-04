package com.example.lab6;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneDeChargement extends Scene {

    public SceneDeChargement() {
        super(new Group());

        ProgressIndicator progressIndicator = new ProgressIndicator();
        Label connexion = new Label("Chargement du contenu");

        Group group = new Group();
        VBox vBox = new VBox();
        vBox.getChildren().setAll(progressIndicator,connexion);
        vBox.setTranslateX(250);
        vBox.setTranslateY(100);
        vBox.setAlignment(Pos.CENTER);
        group.getChildren().add(vBox);

        this.setRoot(group);
    }
}
