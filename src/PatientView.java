/*           Name: Anmol Rao
 *         ASU ID: 1226456714
 *          Class: CSE 360 - Professor Carter - Th @ 1330
 *           Date: 03/26/2024
 *    Description: This is the patient's view. This first asks for their assigned patient ID and then displays their information to them in a systematic manner.
 *    			   Additionally, this has a back button which allows the user to go back to the home page.
 *      File name: PatientView.java
 */

 package application;

 import java.io.File;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 
 import javafx.geometry.Insets;
 import javafx.geometry.Pos;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.layout.BorderPane;
 import javafx.scene.layout.GridPane;
 import javafx.scene.layout.HBox;
 import javafx.scene.layout.VBox;
 import javafx.stage.Stage;
 
 public class PatientView {
     public static Scene getPatientView(Stage stage, Scene backScene) {
         VBox vbox = new VBox(10);
         vbox.setAlignment(Pos.CENTER);
         vbox.setPadding(new Insets(20));
 
         Label idLabel = new Label("Enter your Patient ID:");
         TextField idInputField = new TextField();
         Button submitButton = new Button("Submit");
 
         submitButton.setOnAction(e -> {
             String patientId = idInputField.getText();
             String patientInfoPath = "userData" + File.separator + "PatientInformation" + File.separator + patientId + "_PatientInfo.txt";
 
             // Check if the patient info file exists
             if(Files.exists(Paths.get(patientInfoPath))) {
                 try {
                     Scene patientViewScene = patientViewScene(stage, backScene, patientId);
                     stage.setScene(patientViewScene);
                 } catch (IOException ex) {
                     ex.printStackTrace();
                     showAlert("Error", "An error occurred while trying to load the patient information.", Alert.AlertType.ERROR);
                 }
             } else {
                 showAlert("Invalid ID", "No information found for the given Patient ID. Please try again.", Alert.AlertType.INFORMATION);
             }
         });
 
         vbox.getChildren().addAll(idLabel, idInputField, submitButton);
 
         return new Scene(vbox, 300, 200);
     }
     
     private static Scene patientViewScene(Stage stage, Scene backScene, String patientIDString) throws IOException {
         int patientID = Integer.parseInt(patientIDString);
         Database db = new Database();
         BorderPane borderPane = new BorderPane();
         borderPane.setPadding(new Insets(20));
 
         Label headingLabel = new Label("Patient View");
         headingLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
         borderPane.setTop(headingLabel);
         BorderPane.setAlignment(headingLabel, Pos.CENTER);
 
         GridPane gridPane = new GridPane();
         gridPane.setHgap(10);
         gridPane.setVgap(10);
         gridPane.setPadding(new Insets(20));
 
         Label helloLabel = new Label("Hello " + db.getFirstName(patientID));
         helloLabel.setStyle("-fx-font-size: 14px;");
         helloLabel.setMaxWidth(Double.MAX_VALUE);
         helloLabel.setAlignment(Pos.CENTER_RIGHT);
         gridPane.add(helloLabel, 1, 0);
 
         String[] labels = {"The total Agatston CAC score", "LM:", "LAD:", "LCX:", "RCA:", "PDA:"};
         for (int i = 0; i < labels.length; i++) {
             Label label = new Label(labels[i]);
             TextArea textArea = new TextArea();
             textArea.setEditable(false);
 
             if (i == 0) {
                 // Make the first TextArea larger
                 textArea.setPrefColumnCount(20);
                 textArea.setPrefRowCount(1);
                 textArea.setText(String.valueOf(db.getAgatstonScore(patientID)));
                 gridPane.add(label, 0, i, 2, 1);
                 gridPane.add(textArea, 0, i + 1, 2, 1);
             } else {
                 textArea.setPrefColumnCount(10);
                 textArea.setPrefRowCount(1);
                 switch(i) {
                 case 1:
                     textArea.setText(String.valueOf(db.getLMSCore(patientID)));
                     break;
                 case 2:
                     textArea.setText(String.valueOf(db.getLADScore(patientID)));
                     break;
                 case 3:
                     textArea.setText(String.valueOf(db.getLCXScore(patientID)));
                     break;
                 case 4:
                     textArea.setText(String.valueOf(db.getRCAScore(patientID)));
                     break;
                 case 5:
                     textArea.setText(String.valueOf(db.getPDAScore(patientID)));
                     break;
                 default:
                     headingLabel.setText("Error!");
                 }
                 gridPane.add(label, 0, i + 1);
                 gridPane.add(textArea, 1, i + 1);
             }
         }
 
         borderPane.setCenter(gridPane);
 
         // Create back button and set action to go back to the previous scene
         Button backButton = new Button("Back");
         backButton.setOnAction(e -> stage.setScene(backScene));
         
         // Align the back button to the right
         HBox buttonBox = new HBox(backButton);
         buttonBox.setAlignment(Pos.CENTER_RIGHT);
         buttonBox.setPadding(new Insets(10));
         borderPane.setRight(buttonBox);
 
         // Bottom for the footer
         Label footerLabel = new Label("Screen 3: Patient View");
         borderPane.setBottom(footerLabel);
         BorderPane.setAlignment(footerLabel, Pos.CENTER);
 
         return new Scene(borderPane, 600, 400);
     }
     
     private static void showAlert(String title, String content, Alert.AlertType alertType) {
         Alert alert = new Alert(alertType);
         alert.setTitle(title);
         alert.setHeaderText(null);
         alert.setContentText(content);
         alert.showAndWait();
     }
 }
 