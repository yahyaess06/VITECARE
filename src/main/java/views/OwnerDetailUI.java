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
import models.Owner;
import models.Pet;
import models.Visit;

import java.time.LocalDate;

public class OwnerDetailUI {

    public static void display(Owner owner) {
        Stage window = new Stage();
        window.setTitle("Détails du Propriétaire");

        Label ownerInfo = createLabel(
                "👤 " + owner.getFullName() + "\n" +
                        "🏠 " + owner.getAddress() + ", " + owner.getCity() + "\n" +
                        "📞 " + owner.getTelephone(),
                FontWeight.SEMI_BOLD,
                15,
                "#2c3e50"
        );

        VBox petsBox = new VBox(20);
        petsBox.setAlignment(Pos.TOP_CENTER);
        petsBox.setStyle("-fx-background-color: #f5f7fa; -fx-padding: 20; -fx-border-radius: 10; -fx-border-color: #dcdfe3;");

        for (Pet pet : owner.getPets()) {
            petsBox.getChildren().add(createPetCard(pet, owner, window));
        }

        Button addPetBtn = new Button("➕ Ajouter un Animal");
        addPetBtn.setFont(Font.font("System", FontWeight.BOLD, 14));
        addPetBtn.setStyle(buttonStyle("#2ecc71"));
        addPetBtn.setOnAction(e -> {
            TextInputDialog nameDialog = new TextInputDialog("Nouvel Animal");
            nameDialog.setTitle("Ajouter un Animal");
            nameDialog.setHeaderText("Nom de l'animal :");
            nameDialog.setContentText("Nom :");
            nameDialog.showAndWait().ifPresent(newName -> {
                TextInputDialog typeDialog = new TextInputDialog("Type");
                typeDialog.setTitle("Type de l'animal");
                typeDialog.setHeaderText("Type de l'animal :");
                typeDialog.setContentText("Type :");
                typeDialog.showAndWait().ifPresent(type -> {
                    owner.getPets().add(new Pet(newName, type));
                    window.close();
                    display(owner);
                });
            });
        });

        VBox layout = new VBox(25, ownerInfo, petsBox, addPetBtn);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #ecf0f1;");

        animateEntrance(layout);

        Scene scene = new Scene(layout, 720, 700);
        window.setScene(scene);
        window.show();
    }

    private static VBox createPetCard(Pet pet, Owner owner, Stage window) {
        VBox petCard = new VBox(10);
        petCard.setPadding(new Insets(15));
        petCard.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: #e0e6ed;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 4, 0.2, 0, 2);"
        );

        Label nameLabel = createLabel("🐾 Nom : " + pet.getName(), FontWeight.BOLD, 14, "#34495e");
        Label typeLabel = createLabel("📌 Type : " + pet.getType(), FontWeight.NORMAL, 13, "#555");

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_LEFT);

        Button modifyBtn = new Button("Modifier");
        modifyBtn.setStyle(buttonStyle("#f39c12"));
        modifyBtn.setOnAction(e -> {
            TextInputDialog nameDialog = new TextInputDialog(pet.getName());
            nameDialog.setTitle("Modifier Animal");
            nameDialog.setHeaderText("Nouveau nom :");
            nameDialog.setContentText("Nom :");
            nameDialog.showAndWait().ifPresent(pet::setName);

            TextInputDialog typeDialog = new TextInputDialog(pet.getType());
            typeDialog.setTitle("Modifier Animal");
            typeDialog.setHeaderText("Nouveau type :");
            typeDialog.setContentText("Type :");
            typeDialog.showAndWait().ifPresent(pet::setType);

            window.close();
            display(owner);
        });

        Button removeBtn = new Button("Supprimer");
        removeBtn.setStyle(buttonStyle("#e74c3c"));
        removeBtn.setOnAction(e -> {
            owner.getPets().remove(pet);
            window.close();
            display(owner);
        });

        Button addVisitBtn = new Button("Ajouter Visite");
        addVisitBtn.setStyle(buttonStyle("#3498db"));
        addVisitBtn.setOnAction(e -> {
            DatePicker datePicker = new DatePicker(LocalDate.now());
            TextInputDialog descDialog = new TextInputDialog("Contrôle de routine");
            descDialog.setTitle("Ajouter Visite");
            descDialog.setHeaderText("Description de la visite :");
            descDialog.setContentText("Description :");
            descDialog.showAndWait().ifPresent(description -> {
                Visit visit = new Visit(datePicker.getValue(), description);
                pet.addVisit(visit);
                window.close();
                display(owner);
            });
        });

        buttons.getChildren().addAll(modifyBtn, removeBtn, addVisitBtn);

        VBox visitsBox = new VBox(5);
        for (Visit v : pet.getVisits()) {
            HBox visitRow = new HBox(10);
            visitRow.setAlignment(Pos.CENTER_LEFT);
            Label visitLabel = new Label("🗓 " + v.getDate() + " - " + v.getDescription());
            visitLabel.setFont(Font.font("System", 13));

            Button removeVisitBtn = new Button("Supprimer");
            removeVisitBtn.setStyle(buttonStyle("#c0392b"));
            removeVisitBtn.setOnAction(e -> {
                pet.getVisits().remove(v);
                window.close();
                display(owner);
            });

            visitRow.getChildren().addAll(visitLabel, removeVisitBtn);
            visitsBox.getChildren().add(visitRow);
        }

        petCard.getChildren().addAll(nameLabel, typeLabel, buttons, visitsBox);
        return petCard;
    }

    private static Label createLabel(String text, FontWeight weight, int size, String colorHex) {
        Label label = new Label(text);
        label.setFont(Font.font("System", weight, size));
        label.setTextFill(Color.web(colorHex));
        return label;
    }

    private static String buttonStyle(String color) {
        return "-fx-background-color: " + color + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 6 14;" +
                "-fx-cursor: hand;" +
                "-fx-font-size: 13px;";
    }

    private static void animateEntrance(Pane pane) {
        pane.setOpacity(0);
        pane.setTranslateY(40);
        FadeTransition fade = new FadeTransition(Duration.millis(700), pane);
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slide = new TranslateTransition(Duration.millis(700), pane);
        slide.setFromY(40);
        slide.setToY(0);
        slide.setInterpolator(Interpolator.EASE_OUT);

        new ParallelTransition(fade, slide).play();
    }
}
