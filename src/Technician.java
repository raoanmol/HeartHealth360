/*           Name: Anmol Rao
 *         ASU ID: 1226456714
 *          Class: CSE 360 - Professor Carter - Th @ 1330
 *           Date: 03/26/2024
 *    Description: This is the technician's view. This allows them to enter the
 * Patient's ID and then write their scores. This will then take those scores,
 *                 validate them and then write them to the database.
 *      File name: TechnicianView.java
 */

 package application;

 import java.io.IOException;
 import javafx.geometry.Insets;
 import javafx.geometry.Pos;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.layout.GridPane;
 import javafx.stage.Stage;
 
 public class Technician {
     public static Scene getTechnicianView(Stage stage, Scene backScene) {
         // Create the grid pane
         GridPane grid = new GridPane();
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(25, 25, 25, 25));
 
         // Add UI components
         Label patientIdLabel = new Label("Patient ID:");
         TextField patientIdTextField = new TextField();
         grid.add(patientIdLabel, 0, 0);
         grid.add(patientIdTextField, 1, 0);
 
         Label totalScoreLabel = new Label("The total Agatston CAC score");
         TextField totalScoreTextField = new TextField();
         grid.add(totalScoreLabel, 0, 1);
         grid.add(totalScoreTextField, 1, 1);
 
         Label vesselScoreLabel = new Label("Vessel level Agatston CAC score");
         grid.add(vesselScoreLabel, 0, 2);
 
         // Add TextFields for each vessel
         Label LMLabel = new Label("LM:");
         TextField LMField = new TextField();
         grid.add(LMLabel, 0, 3);
         grid.add(LMField, 1, 3);
 
         Label LADLabel = new Label("LAD:");
         TextField LADField = new TextField();
         grid.add(LADLabel, 0, 4);
         grid.add(LADField, 1, 4);
 
         Label LCXLabel = new Label("LCX:");
         TextField LCXField = new TextField();
         grid.add(LCXLabel, 0, 5);
         grid.add(LCXField, 1, 5);
 
         Label RCALabel = new Label("RCA:");
         TextField RCAField = new TextField();
         grid.add(RCALabel, 0, 6);
         grid.add(RCAField, 1, 6);
 
         Label PDALabel = new Label("PDA:");
         TextField PDAField = new TextField();
         grid.add(PDALabel, 0, 6);
         grid.add(PDAField, 1, 6);
 
         // Save button
         Button btnSave = new Button("Save");
         grid.add(btnSave, 1, 8);
 
         // Back button
         Button btnBack = new Button("Back");
         grid.add(btnBack, 0, 8);
 
         btnBack.setOnAction(e -> {
             stage.setScene(backScene);
         });
 
         btnSave.setOnAction(e -> {
             String patientID = patientIdTextField.getText();
             String agatstonScore = totalScoreTextField.getText();
             String LMScore = LMField.getText();
             String LADScore = LADField.getText();
             String LCXScore = LCXField.getText();
             String RCAScore = RCAField.getText();
             String PDAScore = PDAField.getText();
 
             int patientIDInt = Integer.parseInt(patientID);
 
             try {
                 createPatientFile(patientIDInt, agatstonScore, LMScore,
                     LADScore, LCXScore, RCAScore, PDAScore);
             } catch (IOException e2) {
                 System.out.println("Could not Write");
             }
         });
 
         // Set the scene
         Scene scene = new Scene(grid, 400, 300);
 
         return scene;
     }
 
     private static boolean createPatientFile(int patientID,
         String agatstonScore, String LMScore, String LADScore, String LCXScore,
         String RCAScore, String PDAScore) throws IOException {
         Database db = new Database();
 
         int agatstonScoreInt = Integer.parseInt(agatstonScore);
         int LADScoreInt = Integer.parseInt(LADScore);
         int RCAScoreInt = Integer.parseInt(RCAScore);
         double LMScoreDouble = Double.parseDouble(LMScore);
         double LCXScoreDouble = Double.parseDouble(LCXScore);
         double PDAScoreDouble = Double.parseDouble(PDAScore);
 
         return db.setAgatstonScore(agatstonScoreInt, patientID)
             && db.setLADScore(LADScoreInt, patientID)
             && db.setRCAScore(RCAScoreInt, patientID)
             && db.setLMScore(LMScoreDouble, patientID)
             && db.setLCXScore(LCXScoreDouble, patientID)
             && db.setPDAScore(PDAScoreDouble, patientID);
     }
 }
 