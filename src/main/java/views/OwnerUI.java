package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OwnerUI {

    public static class Visit {
        String date;
        String description;

        public Visit(String date, String description) {
            this.date = date;
            this.description = description;
        }

        @Override
        public String toString() {
            return date + " - " + description;
        }
    }

    public static class Pet {
        String name;
        String type;
        String breed;
        int age;
        ObservableList<Visit> visits = FXCollections.observableArrayList();

        public Pet(String name, String type, String breed, int age) {
            this.name = name;
            this.type = type;
            this.breed = breed;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " (" + type + ", " + age + " ans)";
        }
    }

    public static class Owner {
        String name;
        String phone;
        String address;
        ObservableList<Pet> pets = FXCollections.observableArrayList();

        public Owner(String name, String phone, String address) {
            this.name = name;
            this.phone = phone;
            this.address = address;
        }

        @Override
        public String toString() {
            return name + " - " + phone;
        }
    }

    private static final ObservableList<Owner> owners = FXCollections.observableArrayList();

    public static void display(Stage primaryStage) {
        Stage window = new Stage();
        window.setTitle("Gestion Vétérinaire - Propriétaires et Animaux");

        // Barre de recherche
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher un propriétaire...");
        Button searchButton = new Button("Rechercher");

        // Bouton ajouter propriétaire
        Button addOwnerButton = new Button("Ajouter Propriétaire");
        addOwnerButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        addOwnerButton.setOnAction(e -> showAddOwnerDialog(window));

        HBox topBar = new HBox(10, searchField, searchButton, addOwnerButton);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT);

        VBox ownersContainer = new VBox(15);
        ownersContainer.setPadding(new Insets(15));

        // Fonctionnalité de recherche
        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText().toLowerCase();
            ownersContainer.getChildren().clear();

            if (searchTerm.isEmpty()) {
                // Afficher tous les propriétaires si la recherche est vide
                for (Owner owner : owners) {
                    ownersContainer.getChildren().add(createOwnerPane(window, owner));
                }
            } else {
                // Filtrer les propriétaires
                for (Owner owner : owners) {
                    if (owner.name.toLowerCase().contains(searchTerm) ||
                            owner.phone.contains(searchTerm)) {
                        ownersContainer.getChildren().add(createOwnerPane(window, owner));
                    }
                }
            }
        });

        // Exemple de données initiales
        if (owners.isEmpty()) {
            Owner o1 = new Owner("Jean Dupont", "0612345678", "12 Rue des Lilas");
            Pet p1 = new Pet("Fido", "Chien", "Labrador", 5);
            p1.visits.add(new Visit("2025-05-01", "Vaccination annuelle"));
            p1.visits.add(new Visit("2025-03-15", "Contrôle routine"));
            o1.pets.add(p1);
            owners.add(o1);

            Owner o2 = new Owner("Marie Curie", "0698765432", "45 Avenue des Roses");
            Pet p2 = new Pet("Minou", "Chat", "Persan", 3);
            p2.visits.add(new Visit("2025-04-10", "Stérilisation"));
            o2.pets.add(p2);
            owners.add(o2);
        }

        // Afficher tous les propriétaires initialement
        for (Owner owner : owners) {
            ownersContainer.getChildren().add(createOwnerPane(window, owner));
        }

        ScrollPane scrollPane = new ScrollPane(ownersContainer);
        scrollPane.setFitToWidth(true);

        VBox mainLayout = new VBox(10, topBar, scrollPane);

        Scene scene = new Scene(mainLayout, 800, 600);
        window.setScene(scene);
        window.initOwner(primaryStage);
        window.show();
    }

    private static TitledPane createOwnerPane(Stage window, Owner owner) {
        VBox ownerBox = new VBox(10);
        ownerBox.setPadding(new Insets(10));
        ownerBox.setStyle("-fx-border-color: #2980B9; -fx-border-width: 2; -fx-border-radius: 5;");

        // Header avec boutons d'action
        HBox headerBox = new HBox(10);
        Label ownerLabel = new Label("Propriétaire: " + owner.name + " - Tel: " + owner.phone);
        ownerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        // Boutons pour modifier/supprimer le propriétaire
        Button editOwnerBtn = new Button("Modifier");
        editOwnerBtn.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        editOwnerBtn.setOnAction(e -> showEditOwnerDialog(window, owner));

        Button deleteOwnerBtn = new Button("Supprimer");
        deleteOwnerBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        deleteOwnerBtn.setOnAction(e -> {
            owners.remove(owner);
            window.close();
            display(window);
        });

        headerBox.getChildren().addAll(ownerLabel, editOwnerBtn, deleteOwnerBtn);

        // Information détaillée
        Label addressLabel = new Label("Adresse: " + owner.address);

        // Bouton ajouter animal
        Button btnAddPet = new Button("Ajouter Animal");
        btnAddPet.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");
        btnAddPet.setOnAction(e -> showAddPetDialog(window, owner));

        // Container animaux
        VBox petsBox = new VBox(10);

        for (Pet pet : owner.pets) {
            VBox petBox = new VBox(5);
            petBox.setPadding(new Insets(5));
            petBox.setStyle("-fx-border-color: #27AE60; -fx-border-width: 1; -fx-border-radius: 4;");

            Label petLabel = new Label("Animal: " + pet.name + " - " + pet.type + " (" + pet.breed + ", " + pet.age + " ans)");
            petLabel.setStyle("-fx-font-weight: bold;");

            // Boutons pour l'animal
            HBox petButtons = new HBox(10);
            Button btnAddVisit = new Button("Ajouter Visite");
            btnAddVisit.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
            btnAddVisit.setOnAction(e -> showAddVisitDialog(window, pet));

            Button editPetBtn = new Button("Modifier");
            editPetBtn.setOnAction(e -> showEditPetDialog(window, pet));

            Button deletePetBtn = new Button("Supprimer");
            deletePetBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deletePetBtn.setOnAction(e -> {
                owner.pets.remove(pet);
                window.close();
                display(window);
            });

            petButtons.getChildren().addAll(btnAddVisit, editPetBtn, deletePetBtn);

            // Liste visites
            ListView<Visit> visitsListView = new ListView<>(pet.visits);
            visitsListView.setPrefHeight(80);

            // Bouton pour supprimer une visite
            Button deleteVisitBtn = new Button("Supprimer visite sélectionnée");
            deleteVisitBtn.setOnAction(e -> {
                Visit selected = visitsListView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    pet.visits.remove(selected);
                    window.close();
                    display(window);
                }
            });

            petBox.getChildren().addAll(petLabel, petButtons, visitsListView, deleteVisitBtn);
            petsBox.getChildren().add(petBox);
        }

        ownerBox.getChildren().addAll(headerBox, addressLabel, btnAddPet, petsBox);

        TitledPane titledPane = new TitledPane(owner.name, ownerBox);
        titledPane.setExpanded(false);
        return titledPane;
    }

    private static void showAddOwnerDialog(Stage ownerStage) {
        Stage dialog = new Stage();
        dialog.setTitle("Ajouter Propriétaire");
        dialog.initOwner(ownerStage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Champs de formulaire
        TextField nameField = new TextField();
        nameField.setPromptText("Nom complet");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Téléphone");
        TextField addressField = new TextField();
        addressField.setPromptText("Adresse");

        grid.add(new Label("Nom complet:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Téléphone:"), 0, 1);
        grid.add(phoneField, 1, 1);
        grid.add(new Label("Adresse:"), 0, 2);
        grid.add(addressField, 1, 2);

        Button btnAdd = new Button("Ajouter");
        btnAdd.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        Label message = new Label();

        btnAdd.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                message.setText("Le nom et le téléphone sont obligatoires.");
                message.setStyle("-fx-text-fill: red;");
            } else {
                owners.add(new Owner(name, phone, address));
                dialog.close();
                // Actualiser la fenêtre
                ownerStage.close();
                display(ownerStage);
            }
        });

        VBox layout = new VBox(10, grid, btnAdd, message);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private static void showEditOwnerDialog(Stage ownerStage, Owner owner) {
        Stage dialog = new Stage();
        dialog.setTitle("Modifier Propriétaire");
        dialog.initOwner(ownerStage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Champs de formulaire pré-remplis
        TextField nameField = new TextField(owner.name);
        TextField phoneField = new TextField(owner.phone);
        TextField addressField = new TextField(owner.address);

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Téléphone:"), 0, 1);
        grid.add(phoneField, 1, 1);
        grid.add(new Label("Adresse:"), 0, 2);
        grid.add(addressField, 1, 2);

        Button btnSave = new Button("Enregistrer");
        btnSave.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        Label message = new Label();

        btnSave.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                message.setText("Le nom et le téléphone sont obligatoires.");
                message.setStyle("-fx-text-fill: red;");
            } else {
                owner.name = name;
                owner.phone = phone;
                owner.address = address;
                dialog.close();
                // Actualiser la fenêtre
                ownerStage.close();
                display(ownerStage);
            }
        });

        VBox layout = new VBox(10, grid, btnSave, message);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private static void showAddPetDialog(Stage ownerStage, Owner owner) {
        Stage dialog = new Stage();
        dialog.setTitle("Ajouter Animal");
        dialog.initOwner(ownerStage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Champs de formulaire
        TextField nameField = new TextField();
        nameField.setPromptText("Nom");
        TextField typeField = new TextField();
        typeField.setPromptText("Type (Chien, Chat, etc.)");
        TextField breedField = new TextField();
        breedField.setPromptText("Race");
        TextField ageField = new TextField();
        ageField.setPromptText("Âge");

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Type:"), 0, 1);
        grid.add(typeField, 1, 1);
        grid.add(new Label("Race:"), 0, 2);
        grid.add(breedField, 1, 2);
        grid.add(new Label("Âge:"), 0, 3);
        grid.add(ageField, 1, 3);

        Button btnAdd = new Button("Ajouter");
        btnAdd.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        Label message = new Label();

        btnAdd.setOnAction(e -> {
            try {
                String name = nameField.getText().trim();
                String type = typeField.getText().trim();
                String breed = breedField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());

                if (name.isEmpty() || type.isEmpty()) {
                    message.setText("Le nom et le type sont obligatoires.");
                    message.setStyle("-fx-text-fill: red;");
                } else {
                    owner.pets.add(new Pet(name, type, breed, age));
                    dialog.close();
                    // Actualiser la fenêtre
                    ownerStage.close();
                    display(ownerStage);
                }
            } catch (NumberFormatException ex) {
                message.setText("L'âge doit être un nombre valide.");
                message.setStyle("-fx-text-fill: red;");
            }
        });

        VBox layout = new VBox(10, grid, btnAdd, message);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private static void showEditPetDialog(Stage ownerStage, Pet pet) {
        Stage dialog = new Stage();
        dialog.setTitle("Modifier Animal");
        dialog.initOwner(ownerStage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Champs pré-remplis
        TextField nameField = new TextField(pet.name);
        TextField typeField = new TextField(pet.type);
        TextField breedField = new TextField(pet.breed);
        TextField ageField = new TextField(String.valueOf(pet.age));

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Type:"), 0, 1);
        grid.add(typeField, 1, 1);
        grid.add(new Label("Race:"), 0, 2);
        grid.add(breedField, 1, 2);
        grid.add(new Label("Âge:"), 0, 3);
        grid.add(ageField, 1, 3);

        Button btnSave = new Button("Enregistrer");
        btnSave.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        Label message = new Label();

        btnSave.setOnAction(e -> {
            try {
                String name = nameField.getText().trim();
                String type = typeField.getText().trim();
                String breed = breedField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());

                if (name.isEmpty() || type.isEmpty()) {
                    message.setText("Le nom et le type sont obligatoires.");
                    message.setStyle("-fx-text-fill: red;");
                } else {
                    pet.name = name;
                    pet.type = type;
                    pet.breed = breed;
                    pet.age = age;
                    dialog.close();
                    // Actualiser la fenêtre
                    ownerStage.close();
                    display(ownerStage);
                }
            } catch (NumberFormatException ex) {
                message.setText("L'âge doit être un nombre valide.");
                message.setStyle("-fx-text-fill: red;");
            }
        });

        VBox layout = new VBox(10, grid, btnSave, message);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private static void showAddVisitDialog(Stage ownerStage, Pet pet) {
        Stage dialog = new Stage();
        dialog.setTitle("Ajouter Visite pour " + pet.name);
        dialog.initOwner(ownerStage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        TextField dateField = new TextField();
        dateField.setPromptText("AAAA-MM-JJ");
        TextArea descArea = new TextArea();
        descArea.setPromptText("Description de la visite");
        descArea.setPrefRowCount(3);

        grid.add(new Label("Date:"), 0, 0);
        grid.add(dateField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descArea, 1, 1);

        Button btnAdd = new Button("Ajouter");
        btnAdd.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        Label message = new Label();

        btnAdd.setOnAction(e -> {
            String date = dateField.getText().trim();
            String desc = descArea.getText().trim();
            if (date.isEmpty() || desc.isEmpty()) {
                message.setText("Tous les champs sont obligatoires.");
                message.setStyle("-fx-text-fill: red;");
            } else {
                pet.visits.add(new Visit(date, desc));
                dialog.close();
                // Actualiser la fenêtre
                ownerStage.close();
                display(ownerStage);
            }
        });

        VBox layout = new VBox(10, grid, btnAdd, message);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}