package m;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import views.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Welcome label
        Label welcome = new Label("🐾 Bienvenue dans VetCare 360 🐾");
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        welcome.setTextFill(Color.web("#2E6DA4"));
        welcome.setTextAlignment(TextAlignment.CENTER);
        welcome.setWrapText(true);
        welcome.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 2);");

        // Subtitle with emojis
        Label subtitle = new Label("🐶 🐱 🐰 🐦 🐹 🐢");
        subtitle.setFont(Font.font("Arial", 20));
        subtitle.setTextAlignment(TextAlignment.CENTER);
        subtitle.setStyle("-fx-padding: 0 0 15 0;");

        // Buttons
        Button btnOwners = new Button("🔍 Rechercher un Propriétaire");
        Button btnVets = new Button("👨‍⚕️ Liste des Vétérinaires");

        // Button styles
        String baseBtnStyle = "-fx-background-color: linear-gradient(to bottom, #4CAF50, #3E8E41); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 12 25; " +
                "-fx-background-radius: 20; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 4, 0, 0, 2); " +
                "-fx-cursor: hand;";

        String hoverBtnStyle = "-fx-background-color: linear-gradient(to bottom, #5DBF60, #4DA450); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 12 25; " +
                "-fx-background-radius: 20; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 8, 0, 0, 3); " +
                "-fx-cursor: hand;";

        // btnOwners blue theme
        btnOwners.setStyle(baseBtnStyle.replace("#4CAF50", "#2E86C1").replace("#3E8E41", "#1A5276"));
        btnOwners.setOnMouseEntered(e -> btnOwners.setStyle(hoverBtnStyle.replace("#5DBF60", "#3498DB").replace("#4DA450", "#2874A6")));
        btnOwners.setOnMouseExited(e -> btnOwners.setStyle(baseBtnStyle.replace("#4CAF50", "#2E86C1").replace("#3E8E41", "#1A5276")));
        btnOwners.setPrefWidth(260);

        // btnVets purple theme
        btnVets.setStyle(baseBtnStyle.replace("#4CAF50", "#8E44AD").replace("#3E8E41", "#6C3483"));
        btnVets.setOnMouseEntered(e -> btnVets.setStyle(hoverBtnStyle.replace("#5DBF60", "#9B59B6").replace("#4DA450", "#7D3C98")));
        btnVets.setOnMouseExited(e -> btnVets.setStyle(baseBtnStyle.replace("#4CAF50", "#8E44AD").replace("#3E8E41", "#6C3483")));
        btnVets.setPrefWidth(260);

        // Actions
        btnOwners.setOnAction(e -> OwnerUI.display(primaryStage));
        btnVets.setOnAction(e -> VeterinarianUI.display(primaryStage));

        // VBox card with white semi-transparent background
        VBox card = new VBox(15, welcome, subtitle, btnOwners, btnVets);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(35, 30, 35, 30));
        card.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); " +
                "-fx-border-color: #E8E8E8; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 15, 0, 0, 5);");

        // Footer label
        Label footerLabel = new Label("🐾 VetCare 360 - Prenez soin de vos animaux 🐾");
        footerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        footerLabel.setTextFill(Color.web("#dddddd"));

        VBox footer = new VBox(footerLabel);
        footer.setAlignment(Pos.BOTTOM_CENTER);
        footer.setPadding(new Insets(0, 0, 15, 0));

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(card);
        mainLayout.setBottom(footer);

        // Root with gradient background
        StackPane root = new StackPane(mainLayout);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #6DD5FA, #2980B9);");

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("VetCare 360 - Accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DataStorage.initVeterinarians();
        launch(args);
    }
}