/*           Name: Anmol Rao
 *         ASU ID: 1226456714
 *          Class: CSE 360 - Professor Carter - Th @ 1330
 *           Date: 03/26/2024
 *    Description: This is a modularized backend for the application. This has all the set and get methods that read from the files.
 *    			   This has methods that can be called to crreate the patient's files, instantiate the database.
 *      File name: Database.java
 */

package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
 
public class Database {
    public void createDatabase() {
        String userDataPath = "userData";
        String patientInfoPath = userDataPath + File.separator + "PatientInformation";
        String patientCTPath = userDataPath + File.separator + "CTScans";
 
        File userDataCreator = new File(userDataPath);
        File patientInfoCreator = new File(patientInfoPath);
        File patientCTCreator = new File(patientCTPath);
 
        if (!userDataCreator.exists())
            userDataCreator.mkdirs();
 
        if (!patientInfoCreator.exists())
            patientInfoCreator.mkdirs();
 
        if (!patientCTCreator.exists())
            patientCTCreator.mkdirs();
    }
 
    public boolean createPatientFiles(int patientID) {
        String patientInfoFile = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String patientCTFile = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
 
        boolean fileCreated = false;
 
        try {
            File makeInfoFile = new File(patientInfoFile);
            File makeCTFile = new File(patientCTFile);
 
            if (makeInfoFile.createNewFile() && makeCTFile.createNewFile()) {
                fileCreated = true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
 
        try {
            FileWriter infoWriter = new FileWriter(patientInfoFile);
            FileWriter CTWriter = new FileWriter(patientCTFile);
 
            infoWriter.write(initInfoFile());
            CTWriter.write(initCTFile());
 
            infoWriter.close();
            CTWriter.close();
        } catch (IOException e) {
            return false;
        }
 
        return true;
    }
 
    // patientID_PatientInfo.txt
    public String getFirstName(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"FirstName\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if (startIndex == targetKey.length() - 1)
            return "First name not found";
 
        if (endIndex == -1)
            return "First name not found";
 
        return fileContent.substring(startIndex, endIndex);
    }
 
    public String getLastName(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LastName\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if (startIndex == targetKey.length() - 1)
            return "Last name not found";
 
        if (endIndex == -1)
            return "Last name not found";
 
        return fileContent.substring(startIndex, endIndex);
    }
 
    public String getEmail(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"Email\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if (startIndex == targetKey.length() - 1)
            return "Email ID not found";
 
        if (endIndex == -1)
            return "Email ID not found";
 
        return fileContent.substring(startIndex, endIndex);
    }
 
    public String getHealthHistory(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"HealthHistory\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if (startIndex == targetKey.length() - 1)
            return "No health history found";
 
        if (endIndex == -1)
            return "No health history found";
 
        return fileContent.substring(startIndex, endIndex);
    }
 
    public long getPhoneNumber(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"PhoneNumber\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if (startIndex == targetKey.length() - 1)
            return -1;
 
        if (endIndex == -1)
            return -1;
 
        return Long.parseLong(fileContent.substring(startIndex, endIndex));
    }
 
    public long getInsuranceID(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"InsuranceID\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if (startIndex == targetKey.length() - 1)
            return -1;
 
        if (endIndex == -1)
            return -1;
 
        return Long.parseLong(fileContent.substring(startIndex, endIndex));
    }
 
    public boolean setFirstName(String firstName, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"FirstName\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;
 
        String updatedContent = fileContent.substring(0, startIndex) + firstName + fileContent.substring(endIndex);
 
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }
 
        return true;
    }
 
    public boolean setLastName(String lastName, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LastName\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;
 
        String updatedContent = fileContent.substring(0, startIndex) + lastName + fileContent.substring(endIndex);
 
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }
 
        return true;
    }
 
    public boolean setEmail(String email, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"Email\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;
 
        String updatedContent = fileContent.substring(0, startIndex) + email + fileContent.substring(endIndex);
 
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }
 
        return true;
    }
 
    public boolean setHealthHistory(String healthHistory, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"HealthHistory\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;
 
        String updatedContent = fileContent.substring(0, startIndex) + healthHistory + fileContent.substring(endIndex);
 
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }
 
        return true;
    }
 
    public boolean setPhoneNumber(long phoneNumber, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"PhoneNumber\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;

        String updatedContent = fileContent.substring(0, startIndex) + phoneNumber + fileContent.substring(endIndex);
 
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }
 
        return true;
    }
 
    public boolean setInsuranceID(long insuranceID, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "PatientInformation" + File.separator + patientID + "_PatientInfo.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"InsuranceID\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;
 
        String updatedContent = fileContent.substring(0, startIndex) + insuranceID + fileContent.substring(endIndex);
 
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }
 
        return true;
    }
 
    // patientIDCTResults.txt
    public int getAgatstonScore(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"AgatstonScore\":\"";
 
        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);
 
        if (startIndex == targetKey.length() - 1)
            return -1;
 
        if (endIndex == -1)
            return -1;
 
        return Integer.parseInt(fileContent.substring(startIndex, endIndex));
    }
 
    public int getLADScore(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LADScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if (startIndex == targetKey.length() - 1)
            return -1;

        if (endIndex == -1)
            return -1;

        return Integer.parseInt(fileContent.substring(startIndex, endIndex));
    }

    public int getRCAScore(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"RCAScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if (startIndex == targetKey.length() - 1)
            return -1;

        if (endIndex == -1)
            return -1;

        return Integer.parseInt(fileContent.substring(startIndex, endIndex));
    }

    public double getLMSCore(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LMScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if (startIndex == targetKey.length() - 1)
            return -1;

        if (endIndex == -1)
            return -1;

        return Double.parseDouble(fileContent.substring(startIndex, endIndex));
    }

    public double getLCXScore(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LCXScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if (startIndex == targetKey.length() - 1)
            return -1;

        if (endIndex == -1)
            return -1;

        return Double.parseDouble(fileContent.substring(startIndex, endIndex));
    }

    public double getPDAScore(int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"PDAScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if (startIndex == targetKey.length() - 1)
            return -1;

        if (endIndex == -1)
            return -1;

        return Double.parseDouble(fileContent.substring(startIndex, endIndex));
    }

    public boolean setAgatstonScore(int agatstonScore, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"AgatstonScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;

        String updatedContent = fileContent.substring(0, startIndex) + agatstonScore + fileContent.substring(endIndex);

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean setLADScore(int LADScore, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LADScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;

        String updatedContent = fileContent.substring(0, startIndex) + LADScore + fileContent.substring(endIndex);

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean setRCAScore(int RCAScore, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"RCAScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;

        String updatedContent = fileContent.substring(0, startIndex) + RCAScore + fileContent.substring(endIndex);

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean setLMScore(double LMScore, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LMScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;

        String updatedContent = fileContent.substring(0, startIndex) + String.format("%.2f", LMScore) + fileContent.substring(endIndex);

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean setLCXScore(double LCXScore, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"LCXScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;

        String updatedContent = fileContent.substring(0, startIndex) + String.format("%.2f", LCXScore) + fileContent.substring(endIndex);

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean setPDAScore(double PDAScore, int patientID) throws IOException {
        String filePath = "userData" + File.separator + "CTScans" + File.separator + patientID + "CTResults.txt";
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String targetKey = "\"PDAScore\":\"";

        int startIndex = fileContent.indexOf(targetKey) + targetKey.length();
        int endIndex = fileContent.indexOf("\"", startIndex);

        if ((startIndex == targetKey.length() - 1) || (endIndex == -1))
            return false;

        String updatedContent = fileContent.substring(0, startIndex) + String.format("%.2f", PDAScore) + fileContent.substring(endIndex);

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(updatedContent);
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private static String initInfoFile() {
        return """
                {
                "FirstName":"",
                "LastName":"",
                "Email":"",
                "HealthHistory":"",
                "PhoneNumber":"-1",
                "InsuranceID":"-1"
                }""";
    }

    private static String initCTFile() {
        return """
                {
                "AgatstonScore":"-1",
                "LMScore":"-1",
                "LADScore":"-1",
                "LCXScore":"-1",
                "RCAScore":"-1",
                "PDAScore":"-1"
                }""";
    }
}
