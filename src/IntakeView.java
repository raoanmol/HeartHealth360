/*           Name: Anmol Rao
 *         ASU ID: 1226456714
 *          Class: CSE 360 - Professor Carter - Th @ 1330
 *           Date: 03/26/2024
 *    Description: This is the intake view that will be seen by the receptionist. This is where they take all of the patient's information
 *    			   Then a random patientID is generated, it is made sure that this patient doesn't already exist. Then the patient's files are generated
 *    			   Once the patient file is generated, this writes to their file.
 *      File name: IntakeView.java
 */

 package application;

 import java.io.File;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.Random;
 
 import javafx.geometry.Insets;
 import javafx.geometry.Pos;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.layout.BorderPane;
 import javafx.scene.layout.GridPane;
 import javafx.scene.layout.HBox;
 import javafx.scene.layout.VBox;
 import javafx.stage.Stage;
 
 public class IntakeView {
     static boolean correctInfo = false;
     
     public static Scene getIntakeFormView(Stage stage, Scene backScene) {
         do {
             // =================== Heading Setup ==================
             Label mainHeading = new Label("Patient Intake Form");
             mainHeading.setStyle("-fx-font-family: 'Times New Roman', Times, serif; -fx-font-size: 20px; -fx-font-weight: bold;");
         
             HBox header = new HBox(mainHeading);
             header.setAlignment(Pos.CENTER);
             header.setPadding(new Insets(10, 0, 10, 0));
         
         
             // ===================== Form Pane ====================
             GridPane grid = new GridPane();
             grid.setAlignment(Pos.CENTER);
             grid.setHgap(10);
             grid.setVgap(10);
             grid.setPadding(new Insets(0, 10, 0, 10));
             BorderPane layout = new BorderPane();
             HBox buttonBox = new HBox(25);
             buttonBox.setAlignment(Pos.CENTER);
             buttonBox.setPadding(new Insets(15));
             VBox notesSection = new VBox(10);
             notesSection.setPadding(new Insets(10));
             notesSection.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-border-insets: 10px;");
         
         
             // ==================== Form Labels ===================
             Label firstNameLabel = new Label("First Name:");
             Label lastNameLabel = new Label("Last Name:");
             Label emailLabel = new Label("Email:");
             Label phoneNumberLabel = new Label("Phone Number:");
             Label healthHistoryLabel = new Label("Health History:");
             Label insuranceIDLabel = new Label("Insurance ID:");
         
             // Styling the labels
             firstNameLabel.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             lastNameLabel.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             emailLabel.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             phoneNumberLabel.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             healthHistoryLabel.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             insuranceIDLabel.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
         
             // Adding to grid
             grid.add(firstNameLabel, 0, 0);
             grid.add(lastNameLabel, 0, 1);
             grid.add(emailLabel, 0, 2);
             grid.add(phoneNumberLabel, 0, 3);
             grid.add(healthHistoryLabel, 0, 4);
             grid.add(insuranceIDLabel, 0, 5);
             
         
             // ================== Form Text Fields ================
             TextField firstNameField = new TextField();
             TextField lastNameField = new TextField();
             TextField emailField = new TextField();
             TextField phoneNumberField = new TextField();
             TextField healthHistoryField = new TextField();
             TextField insuranceIDField = new TextField();
         
             // Styling the fields
             firstNameField.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             lastNameField.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             emailField.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             phoneNumberField.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             healthHistoryField.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             insuranceIDField.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
         
             // Adding to grid
             grid.add(firstNameField, 1, 0);
             grid.add(lastNameField, 1, 1);
             grid.add(emailField, 1, 2);
             grid.add(phoneNumberField, 1, 3);
             grid.add(healthHistoryField, 1, 4);
             grid.add(insuranceIDField, 1, 5);
         
         
             // ================ Button Section Setup ===============
             Button saveButton = new Button("Save");
             Button backButton = new Button("Back");
         
             // Styling the buttons;
             saveButton.setPrefWidth(75);
             backButton.setPrefWidth(75);
             saveButton.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             backButton.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
         
             // Adding to grid
             buttonBox.getChildren().addAll(backButton, saveButton);
         
         
             // ================== Note Area Setup ==================
             Label notesHeading = new Label("Notes:");
             TextArea notesDisplayArea = new TextArea();
             notesDisplayArea.setEditable(false);
             notesDisplayArea.setWrapText(true);
             notesDisplayArea.setMaxWidth(200);
         
             // Styling notes section
             notesHeading.setStyle("-fx-font-family: 'Times New Roman', Times, serif; -fx-font-size: 15px; -fx-font-weight: bold;");
             notesDisplayArea.setStyle("-fx-font-family: 'Consolas', monospace;");
         
             // Adding to grid
             notesSection.getChildren().addAll(notesHeading, notesDisplayArea);
         
         
             // =================== Final Layout ===================
             layout.setTop(header);
             layout.setCenter(grid);
             layout.setRight(notesSection);
             layout.setBottom(buttonBox);
         
         
             // ================ Event Handlers Setup ==============
             saveButton.setOnAction(e -> {
                 String firstName = firstNameField.getText();
                 String lastName = lastNameField.getText();
                 String email = emailField.getText();
                 String phoneNumber = phoneNumberField.getText();
                 String healthHistory = healthHistoryField.getText();
                 String insuranceID = insuranceIDField.getText();
                 
                 int patientID;
                 
                 correctInfo = validateInformation(firstName, lastName, email, phoneNumber, healthHistory, insuranceID);
                 
                 if(correctInfo) {
                     patientID = generatePatientID();
                     
                     try {
                         createPatientFile(patientID, firstName, lastName, email, phoneNumber, healthHistory, insuranceID);
                         notesDisplayArea.setText("Patient Created Successfully!\nYour PatientID is " + patientID + "\nYou can exit now.");
                     } catch (IOException e1) {
                         notesDisplayArea.setText("An error occured while creating patient's file.");
                         correctInfo = false;
                     }
                 } else {
                     notesDisplayArea.setText("Information was not filled out correctly.\nPlease try again.");
                 }
             });
             
             backButton.setOnAction(e -> {
                 stage.setScene(backScene);
             });
     
         
             // ================= Scene and Stage ==================
             Scene scene = new Scene(layout, 550, 350);
             return scene;
         } while(correctInfo);
     }
     
     private static int generatePatientID() {
         Random rand = new Random();
         int patientID = -1;
 
         do {
             patientID = rand.nextInt(90000) + 10000;
         } while (Files.exists(Paths.get("userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt")));
 
         return patientID;
     }
 
     private static boolean createPatientFile(int patientID, String firstName, String lastName, String email, String phoneNumber, String healthHistory, String insuranceID) throws IOException {
         Database db = new Database();
         
         long phoneNumberLong = Long.parseLong(phoneNumber);
         long insuranceIDLong = Long.parseLong(insuranceID);
 
         return db.createPatientFiles(patientID) &&
                 db.setFirstName(firstName, patientID) &&
                 db.setLastName(lastName, patientID) &&
                 db.setEmail(email, patientID) &&
                 db.setPhoneNumber(phoneNumberLong, patientID) &&
                 db.setHealthHistory(healthHistory, patientID) &&
                 db.setInsuranceID(insuranceIDLong, patientID);
     }
 
     private static boolean validateInformation(String firstName, String lastName, String email, String phoneNumber, String healthHistory, String insuranceID) {
         boolean firstNameIsValid;
         boolean lastNameIsValid;
         boolean emailIsValid;
         boolean phoneNumberIsValid;
         boolean healthHistoryIsValid;
         boolean insuranceIDIsValid;
 
         // helper variable(s)
         String regex = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
 
         // Checking if user inputs are valid
         firstNameIsValid = !firstName.isEmpty();
         lastNameIsValid = !lastName.isEmpty();
         emailIsValid = email.matches(regex);
         phoneNumberIsValid = !phoneNumber.isEmpty() && phoneNumber.matches("[0-9]+");
         healthHistoryIsValid = !healthHistory.isEmpty();
         insuranceIDIsValid = !insuranceID.isEmpty() && insuranceID.matches("[0-9]+");
 
         return firstNameIsValid && lastNameIsValid && emailIsValid && phoneNumberIsValid && healthHistoryIsValid && insuranceIDIsValid;
     }
 }