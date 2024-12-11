*package com.contactmanager.view;

import com.contactmanager.controller.ContactController;
import com.contactmanager.model.Contact;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContactManagerGUI extends Application {

    private TableView<Contact> tableView;
    private ObservableList<Contact> contactList;
    private ContactController contactController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestionnaire de Contacts");

        contactController = new ContactController();
        contactList = contactController.getContactList();
        tableView = new TableView<>(contactList);

        TableColumn<Contact, String> firstNameColumn = new TableColumn<>("Prénom");
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));

        TableColumn<Contact, String> lastNameColumn = new TableColumn<>("Nom");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));

        TableColumn<Contact, String> phoneColumn = new TableColumn<>("Numéro de téléphone");
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));

        TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        TableColumn<Contact, String> categoryColumn = new TableColumn<>("Categorie");
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, phoneColumn, emailColumn, categoryColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button addButton = new Button("Ajouter Contact");
        addButton.setOnAction(e -> showAddContactDialog());

        Button updateButton = new Button("Modifier Contact");
        updateButton.setOnAction(e -> showUpdateContactDialog());

        Button deleteButton = new Button("Supprimer Contact");
        deleteButton.setOnAction(e -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                contactController.removeContact(selectedContact);
            }
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher par le nom ou le numéro");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchContact(newValue));

        HBox buttonBox = new HBox(addButton, updateButton, deleteButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(searchField, tableView, buttonBox);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddContactDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Contact");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Prénom");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Nom");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Numéro de téléphone");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Famille", "Amis", "Travail");
        categoryComboBox.setPromptText("Catégorie");

        Label messageLabel = new Label();

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            Contact newContact = new Contact(
                firstNameField.getText(),
                lastNameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                categoryComboBox.getValue()
            );
            if (contactController.addContact(newContact)) {
                messageLabel.setText("Contact ajouté avec succès !");
                dialog.close();
            } else {
                messageLabel.setText("Ce contact existe déjà !");
            }
        });

        VBox fieldsBox = new VBox(firstNameField, lastNameField, phoneField, emailField, categoryComboBox, saveButton, messageLabel);
        fieldsBox.setSpacing(10);

        vbox.getChildren().addAll(fieldsBox);
        Scene scene = new Scene(vbox, 300, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void showUpdateContactDialog() {
        Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            return;
        }

        Stage dialog = new Stage();
        dialog.setTitle("Modifier Contact");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        TextField firstNameField = new TextField(selectedContact.getFirstName());
        TextField lastNameField = new TextField(selectedContact.getLastName());
        TextField phoneField = new TextField(selectedContact.getPhoneNumber());
        TextField emailField = new TextField(selectedContact.getEmail());
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Famille", "Amis", "Travail");
        categoryComboBox.setValue(selectedContact.getCategory());

        Label messageLabel = new Label();

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            Contact updatedContact = new Contact(
                firstNameField.getText(),
                lastNameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                categoryComboBox.getValue()
            );
            if (contactController.updateContact(selectedContact, updatedContact)) {
                messageLabel.setText("Contact modifié avec succès !");
                dialog.close();
            } else {
                messageLabel.setText("Modification échoué !");
            }
        });

        VBox fieldsBox = new VBox(firstNameField, lastNameField, phoneField, emailField, categoryComboBox, saveButton, messageLabel);
        fieldsBox.setSpacing(10);

        vbox.getChildren().addAll(fieldsBox);
        Scene scene = new Scene(vbox, 300, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void searchContact(String query) {
        if (query.isEmpty()) {
            tableView.setItems(contactList);
        } else {
            ObservableList<Contact> filteredList = contactList.filtered(contact ->
                contact.getFirstName().toLowerCase().contains(query.toLowerCase()) ||
                contact.getLastName().toLowerCase().contains(query.toLowerCase()) ||
                contact.getPhoneNumber().contains(query)
            );
            tableView.setItems(filteredList);
        }
    }
}
