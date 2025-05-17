package views;

import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import m.DataStorage;
import models.Owner;

public class AddOwnerUI {

    public static void display(Owner existingOwner) {
        Stage window = new Stage();
        boolean isEdit = existingOwner != null;
        window.setTitle(isEdit ? "Modifier un Propriétaire" : "Ajouter un Propriétaire");

        Label title = new Label(isEdit ? "Modifier un Propriétaire" : "Ajouter un Propriétaire");
        title.setFont(Font.font("System", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#2c3e50"));

        TextField fName = createStyledField("Prénom");
        TextField lName = createStyledField("Nom");
        TextField address = createStyledField("Adresse");
        TextField city = createStyledField("Ville");
        TextField phone = createStyledField("Téléphone");

        if (isEdit) {
            fName.setText(existingOwner.getFirstName());
            lName.setText(existingOwner.getLastName());
            address.setText(existingOwner.getAddress());
            city.setText(existingOwner.getCity());
            phone.setText(existingOwner.getTelephone());
        }

        Button submit = new Button("Enregistrer");
        String baseBtn = "-fx-background-color: #2c7be5; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6;";
        String hoverBtn = "-fx-background-color: #1a5dc9; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6;";
        submit.setStyle(baseBtn);
        submit.setOnMouseEntered(e -> submit.setStyle(hoverBtn));
        submit.setOnMouseExited(e -> submit.setStyle(baseBtn));

        submit.setOnAction(e -> {
            Owner o = new Owner(
                    fName.getText(),
                    lName.getText(),
                    address.getText(),
                    city.getText(),
                    phone.getText()
            );
            if (isEdit) {
                DataStorage.removeOwner(existingOwner.getFullName());
            }
            DataStorage.addOwner(o);
            window.close();
        });

        VBox form = new VBox(16,
                createFieldGroup("Prénom", fName),
                createFieldGroup("Nom", lName),
                createFieldGroup("Adresse", address),
                createFieldGroup("Ville", city),
                createFieldGroup("Téléphone", phone),
                submit
        );
        form.setAlignment(Pos.CENTER_LEFT);

        VBox card = new VBox(25, title, form);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(30));
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #d1d9e0;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.07), 8, 0.4, 0, 2);"
        );

        // Animation
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
        Scene scene = new Scene(root, 480, 550);
        window.setScene(scene);
        window.show();
    }

    private static TextField createStyledField(String placeholder) {
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
