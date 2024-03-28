/*           Name: Anmol Rao
 *         ASU ID: 1226456714
 *          Class: CSE 360 - Professor Carter - Th @ 1330
 *           Date: 03/26/2024
 *    Description: This is the starting point of the program. This presents the user with 3 buttons where they can decide which view they want to go to.
 *      File name: Main.java
 */

 package application;

 // Dependencies	
 import javafx.application.Application;
 import javafx.geometry.Insets;
 import javafx.geometry.Pos;
 import javafx.stage.Stage;
 import javafx.stage.StageStyle;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.layout.BorderPane;
 import javafx.scene.layout.VBox;
 
 public class Main extends Application {
     public static void main(String[] args) {
         // Launching the application
         launch(args);
     }
     
     @Override
     public void start(Stage userInterface) {
         try {
             Database db = new Database();
             db.createDatabase();
             
             // ================== Stage Settings ==================
             userInterface.setTitle("Heart Health Imaging and Recording System");
             userInterface.initStyle(StageStyle.DECORATED);
             userInterface.setResizable(false);
             
             
             // =================== Heading Setup ==================
             Label mainHeading = new Label("Welcome to Heart Health Imaging and Receiving System");
             mainHeading.setStyle("-fx-font-family: 'Times New Roman', Times, serif; -fx-font-size: 20px; -fx-font-weight: bold;");
             
             
             // ================ Button Section Setup ===============
             VBox buttonSection = new VBox();
             buttonSection.setSpacing(15);
             buttonSection.setPadding(new Insets(15));
             buttonSection.setAlignment(Pos.CENTER);
 
             
             Button intakeButton = new Button("Patient Intake");
             Button technicianButton = new Button("Technician View");
             Button patientButton = new Button("Patient View");
             
             // Styling button section
             intakeButton.setPrefSize(400, 50);
             technicianButton.setPrefSize(400, 50);
             patientButton.setPrefSize(400, 50);
             intakeButton.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             technicianButton.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             patientButton.setStyle("-fx-font-family: 'Times New Roman', Times, serif;");
             
             // Assemble
             buttonSection.getChildren().addAll(intakeButton, technicianButton, patientButton);
             
             
             // =================== Layout Setup ====================
             BorderPane layout = new BorderPane();
             layout.setTop(mainHeading);
             layout.setCenter(buttonSection);
             layout.setPadding(new Insets(30));
             BorderPane.setAlignment(buttonSection, Pos.CENTER);
             
             
             // ================ Event Handlers Setup ===============
             Scene scene = new Scene(layout, 560, 300);
             intakeButton.setOnAction(e -> userInterface.setScene(IntakeView.getIntakeFormView(userInterface, scene)));
             technicianButton.setOnAction(e -> userInterface.setScene(Technician.getTechnicianView(userInterface, scene)));
             patientButton.setOnAction(e -> userInterface.setScene(PatientView.getPatientView(userInterface, scene)));
 
 
             // ================= Scene and Stage ===================
             userInterface.setScene(scene);
             userInterface.show();
             
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 }
 