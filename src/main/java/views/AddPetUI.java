package views;

import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Owner;
import models.Pet;

public class AddPetUI {

    public static void display(Owner owner) {
        Stage window = new Stage();
        window.setTitle("Ajouter un Animal");

        Label title = new Label("Ajouter un Animal");
        title.setFont(Font.font("System", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#2c3e50"));

        TextField name = createTextField("Nom de l'animal");
        TextField type = createTextField("Type d'animal");

        Button submit = new Button("Ajouter");
        String baseBtn = "-fx-background-color: #2c7be5; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6;";
        String hoverBtn = "-fx-background-color: #1a5dc9; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6;";
        submit.setStyle(baseBtn);
        submit.setOnMouseEntered(e -> submit.setStyle(hoverBtn));
        submit.setOnMouseExited(e -> submit.setStyle(baseBtn));

        submit.setOnAction(e -> {
            Pet p = new Pet(name.getText(), type.getText());
            owner.addPet(p);
            window.close();
        });

        VBox formBox = new VBox(16,
                createFieldGroup("Nom", name),
                createFieldGroup("Type", type),
                submit
        );
        formBox.setAlignment(Pos.CENTER_LEFT);
        formBox.setPadding(new Insets(30));

        VBox card = new VBox(25, title, formBox);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(30));
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #d1d9e0;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.07), 8, 0.4, 0, 2);"
        );

        // Animation: Fade + Slide Up
        card.setOpacity(0);
        card.setTranslateY(30);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(600), card);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition slideUp = new TranslateTransition(Duration.millis(600), card);
        slideUp.setFromY(30);
        slideUp.setToY(0);
        slideUp.setInterpolator(Interpolator.EASE_OUT);

        new ParallelTransition(fadeIn, slideUp).play();

        StackPane root = new StackPane(card);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #eaeff5;");

        Scene scene = new Scene(root, 450, 400);
        window.setScene(scene);
        window.show();
    }

    private static TextField createTextField(String placeholder) {
        TextField tf = new TextField();
        tf.setPromptText(placeholder);
        tf.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-border-color: #ced4da;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 10 14;" +
                        "-fx-font-size: 14px;"
        );
        tf.setPrefWidth(300);
        return tf;
    }

    private static VBox createFieldGroup(String labelText, TextField field) {
        Label label = new Label(labelText);
        label.setFont(Font.font("System", FontWeight.SEMI_BOLD, 13));
        label.setTextFill(Color.web("#343a40"));
        return new VBox(6, label, field);
    }
}
