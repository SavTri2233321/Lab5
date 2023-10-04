package com.example.lab6;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneInscription extends Scene {

    boolean confirmation;
    Scene sceneDeTransition;

    List<String> allLines;
    public SceneInscription(Stage stage) throws IOException {
        super(new Group());
        VBox vBox = new VBox();
        HBox hBoxGenre = new HBox();
        HBox hBoxBoutton = new HBox();

        Label prenom = new Label("Prénom");
        TextField prenom1 = new TextField();
        prenom1.setPromptText("Prénom");

        Label nomDeFamille = new Label("Nom de famille");
        TextField nomDeFamille1 = new TextField();
        nomDeFamille1.setPromptText("Nom de famille");

        Label nomDutilisateur = new Label("Nom d'utilisateur");
        TextField nomDutilisateur1 = new TextField();
        nomDutilisateur1.setPromptText("Nom d'utilisateur");

        Label motDePasse = new Label("Mot de passe");
        PasswordField motDePasse1 = new PasswordField();
        motDePasse1.setPromptText("Nom de famille");

        Label confirmerLeMotDepasse = new Label("Confirmer le mot de passe");
        PasswordField confirmerLeMotDepasse1 = new PasswordField();
        confirmerLeMotDepasse1.setPromptText("Mot de passe");

        Label genre = new Label("Genre");
        RadioButton homme = new RadioButton("Homme");
        RadioButton femme = new RadioButton("Femme");
        RadioButton autre = new RadioButton("Autre");
        ToggleGroup toggleGroup = new ToggleGroup();
        homme.setToggleGroup(toggleGroup);
        femme.setToggleGroup(toggleGroup);
        autre.setToggleGroup(toggleGroup);
        hBoxGenre.getChildren().addAll(homme,femme,autre);

        Label age = new Label("Age");
        Spinner spinner = new Spinner(0,100,0);

        CheckBox verification = new CheckBox("J'accepte les conditions d'utilisation");
        verification.selectedProperty().addListener((observable,oldValue,newValue) -> {
            confirmation = newValue;
        });


        Label erreur = new Label();
        erreur.setTextFill(Color.RED);
        allLines = Files.readAllLines(Paths.get("utilisateur.csv"));


        Button bouttonSinscrire = new Button("S'inscrire");
        bouttonSinscrire.setOnAction(actionEvent -> {
            if (Objects.equals(prenom1.getText(), ""))
                erreur.setText("Prénom manquant");
            else if (Objects.equals(nomDeFamille1.getText(), ""))
                erreur.setText("Nom de famille manquant");
            else if (Objects.equals(nomDutilisateur1.getText(), ""))
                erreur.setText("Nom d'utilisateur manquant");
            else if (dejaExistant(allLines,nomDutilisateur1.getText()))
                erreur.setText("Nom d'utilisateur déja existant");
            else if (Objects.equals(motDePasse1.getText(), ""))
                erreur.setText("Mot de passse manquant");
            else if (Objects.equals(confirmerLeMotDepasse1.getText(), ""))
                erreur.setText("Vérification du mot de passe manquant");
            else if (confirmerLeMotDepasse1 == motDePasse1)
                erreur.setText("Pas le même mot de passe");
            else if (toggleGroup.getSelectedToggle() == null)
                erreur.setText("Genre manquant");
            else if ((int) spinner.getValue() < 18)
                erreur.setText("Âge legal de 18 ans requis");
            else if (!verification.isSelected())
                erreur.setText("Vous devez accepter les conditions d'utilisation");
            else {

                String genre2;
                if (toggleGroup.getSelectedToggle() == homme)
                    genre2 = "M";
                else if (toggleGroup.getSelectedToggle() == femme)
                    genre2 = "F";
                else genre2 = "A";


                String z = prenom1.getText() + ", " + nomDeFamille1.getText() + ", " + nomDutilisateur1.getText() + ", " + motDePasse1.getText() + ", " + genre2 + ", " + spinner.getValue();
                allLines.add(z);
                try {
                    Files.write(Paths.get("utilisateur.csv"), allLines);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                erreur.setText("");
            }
        });



        Button bouttonEffacer= new Button("Effacer");
        bouttonEffacer.setOnAction(actionEvent -> {
            prenom1.setText("");
            nomDeFamille1.setText("");
            nomDutilisateur1.setText("");
            motDePasse1.setText("");
            confirmerLeMotDepasse1.setText("");
            toggleGroup.selectToggle(null);
            spinner.getValueFactory().setValue(0);
            verification.setSelected(false);
        });
        Button bouttonRetour = new Button("Retour");
        bouttonRetour.setOnAction(actionEvent -> {
            stage.setScene(sceneDeTransition);
        });

        hBoxBoutton.getChildren().addAll(bouttonSinscrire,bouttonEffacer,bouttonRetour);
        vBox.getChildren().addAll(prenom,prenom1,nomDeFamille,nomDeFamille1,nomDutilisateur,nomDutilisateur1,motDePasse,motDePasse1,confirmerLeMotDepasse,confirmerLeMotDepasse1);
        vBox.getChildren().addAll(genre,hBoxGenre,age,spinner,verification,hBoxBoutton,erreur);
        vBox.setTranslateX(250);
        vBox.setTranslateY(100);
        Group group = new Group(vBox);
        this.setRoot(group);


    }

    public void setSceneDeTransition(Scene sceneDeTransition) {
        this.sceneDeTransition = sceneDeTransition;
    }

    public boolean dejaExistant(List<String> l,String t ) {
        boolean u = false;
        for (String s : l) {
            if (Objects.equals(s.split(", ")[2], t)) {
                u = true;
                break;
            }
        }
        return u;
    }

}
