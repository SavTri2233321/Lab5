package com.example.lab6;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;


public class SceneConnexion extends Scene {

    Scene sceneDeTransition;
    Scene sceneDeTransition2;


    List<String> s;
    public SceneConnexion(Stage stage) throws IOException {
        super(new Group());
        s = Files.readAllLines(Paths.get("utilisateur.csv"));
        Button bouttonSinscrire = new Button("S'inscrire");
        Button bouttonSeConnecter = new Button("Se connecter");

        Label labelNomDutilisateur = new Label("Nom d'utilisateur");
        TextField nomDutilisateur = new TextField();
        nomDutilisateur.setPromptText("Veuillez entrer votre nom d'utilisateur");

        Label labelMotDePasse = new Label("Mot de passe");
        PasswordField motDePasse = new PasswordField();
        motDePasse.setPromptText("Veuillez entrer votre nom mot de passe");

        Label erreur = new Label();
        erreur.setTextFill(Color.RED);

        bouttonSinscrire.setOnAction(actionEvent -> stage.setScene(sceneDeTransition));
        bouttonSeConnecter.setOnAction(actionEvent -> {
            try {
                s = Files.readAllLines(Paths.get("utilisateur.csv"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (dejaExistant(s,nomDutilisateur.getText(),motDePasse.getText()))
                stage.setScene(sceneDeTransition2);
            else {
                erreur.setText("La connexion a échoué");
            }
        });


        VBox allignement = new VBox();
        HBox bouttonHbox = new HBox(bouttonSeConnecter,bouttonSinscrire);
        bouttonHbox.setSpacing(70);
        allignement.getChildren().addAll(labelNomDutilisateur,nomDutilisateur,labelMotDePasse,motDePasse,bouttonHbox,erreur);
        allignement.setSpacing(6);
        allignement.setTranslateX(250);
        allignement.setTranslateY(200);
        allignement.setMaxWidth(400);
        allignement.setMinWidth(200);


        Group group = new Group(allignement);
        this.setRoot(group);

    }

    public boolean dejaExistant(List<String> l,String nomDutilisateur,String motDePasse ) {
        boolean u = false;
        for (String s : l) {
            if (Objects.equals(s.split(", ")[2], nomDutilisateur) && Objects.equals(s.split(", ")[3], motDePasse)) {
                u = true;
                break;
            }
        }
        return u;
    }

    public void setSceneDeTransition(Scene sceneDeTransition) {
        this.sceneDeTransition = sceneDeTransition;
    }

    public void setSceneDeTransition2(Scene sceneDeTransition2) {
        this.sceneDeTransition2 = sceneDeTransition2;
    }
}
