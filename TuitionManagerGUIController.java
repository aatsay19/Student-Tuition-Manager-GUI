/*
 * Sample Skeleton for 'TuitionManagerGUIView.fxml' Controller Class
 */

package student_tuition_manager_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class TuitionManagerGUIController {

    private Roster roster;

    ObservableList<Integer> possibleCreditsList = FXCollections.observableArrayList(3, 4, 5,
            6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="command"
    private ToggleGroup command; // Value injected by FXMLLoader

    @FXML // fx:id="creditsDropDownMenu"
    private ChoiceBox<Integer> creditsDropDownMenu; // Value injected by FXMLLoader

    @FXML // fx:id="dollarAmountField"
    private TextField dollarAmountField; // Value injected by FXMLLoader

    @FXML // fx:id="executeCommandButton"
    private Button executeCommandButton; // Value injected by FXMLLoader

    @FXML // fx:id="outputConsole"
    private TextArea outputConsole; // Value injected by FXMLLoader

    @FXML // fx:id="paymentDateField"
    private DatePicker paymentDateField; // Value injected by FXMLLoader

    @FXML // fx:id="studentMajor"
    private ToggleGroup studentMajor; // Value injected by FXMLLoader

    @FXML // fx:id="studentMajorBox"
    private HBox studentMajorBox; // Value injected by FXMLLoader

    @FXML // fx:id="studentNameField"
    private TextField studentNameField; // Value injected by FXMLLoader

    @FXML // fx:id="studentState"
    private ToggleGroup studentState; // Value injected by FXMLLoader

    @FXML // fx:id="studentStatus"
    private ToggleGroup studentStatus; // Value injected by FXMLLoader

    @FXML // fx:id="studentStatusBox"
    private HBox studentStatusBox; // Value injected by FXMLLoader

    @FXML // fx:id="studyAbroadStatus"
    private CheckBox studyAbroadStatus; // Value injected by FXMLLoader

    @FXML // fx:id="tristateStudentState"
    private HBox tristateStudentState; // Value injected by FXMLLoader

    private String commandAErrorHandler() {
        String outputToConsole = "";
        try {
            String studentTypeSelected = ((RadioButton) studentStatus.getSelectedToggle()).getText();
            if (studentTypeSelected.equals("Tri-State")) {
                try {
                    String triStateStudentState = ((RadioButton) studentState.getSelectedToggle()).getText();
                }
                catch (NullPointerException nullPointerException) {
                    outputToConsole = "Please select a state option for 'Status-Specific Details'.\n";
                    return outputToConsole;
                }
            }
        }
        catch (NullPointerException nullPointerException) {
            outputToConsole = "Please select an option for 'Student Type/Status'.\n";
            return outputToConsole;
        }
        try {
            String studentMajorSelected = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        }
        catch (NullPointerException nullPointerException) {
            outputToConsole = "Please select an option for 'Student Major'.\n";
            return outputToConsole;
        }
        try {
            int creditsSelected = creditsDropDownMenu.getValue();
        }
        catch (NullPointerException nullPointerException) {
            outputToConsole = "Please select a number of credit hours from the dropdown menu.\n";
            return outputToConsole;
        }
        String name = studentNameField.getText();
        if (name.equals("")) {
            outputToConsole = "'Student Name' field cannot be empty.\n";
            return outputToConsole;
        }
        return outputToConsole;
    }

    private String executeCommandA() {
        String outputToConsole = commandAErrorHandler();
        if (!(outputToConsole.equals("")))
            return outputToConsole;
        String status = ((RadioButton) studentStatus.getSelectedToggle()).getText();
        String name = studentNameField.getText();
        String major = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        int credits = creditsDropDownMenu.getValue();
        if (status.equals("International") && credits < Roster.MINIMUM_INTERNATIONAL_CREDITS) {
            outputToConsole = "International students must be enrolled in at least 12 credits.\n";
            return outputToConsole;
        }
        Profile newProfile = new Profile(name, Major.convertStringToMajor(major));
        Student newStudent;
        if (status.equals("Resident")) {
            newStudent = new Resident(newProfile, credits);
        } else if (status.equals("Non-Resident")) {
            newStudent = new NonResident(newProfile, credits);
        } else if (status.equals("Tri-State")) {
            String state = ((RadioButton) studentState.getSelectedToggle()).getText();
            newStudent = new TriState(newProfile, credits, State.convertStringToState(state));
        } else {
            boolean studyAbroad = studyAbroadStatus.isSelected();
            newStudent = new International(newProfile, credits, studyAbroad);
        }
        if (roster.add(newStudent))
            outputToConsole = "Student successfully added.\n";
        else
            outputToConsole = "Student could not be added; student is already within the roster.\n";
        return outputToConsole;
    }

    private String executeCommandCA() {
        roster.calculateTuition();
        return "Tuition successfully calculated for all students.";
    }

    private String executeCommandCO() {
        String outputToConsole = "";

        return outputToConsole;
    }

    private String executeCommandF() {
        String outputToConsole = "";
        try {
            String studentMajorSelected = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        }
        catch (NullPointerException nullPointerException) {
            outputToConsole = "Please select an option for 'Student Major'.\n";
            return outputToConsole;
        }
        try {
            double payTuitionAmount = Double.parseDouble(dollarAmountField.getText());
        }
        catch (NumberFormatException numberFormatException) {
            outputToConsole = "Please enter a valid numerical financial aid amount for 'Amount (in $)'.\n";
            return outputToConsole;
        }
        String name = studentNameField.getText();
        if (name.equals("")) {
            outputToConsole = "'Student Name' field cannot be empty.\n";
            return outputToConsole;
        }
        String amountAsString = dollarAmountField.getText();
        if (amountAsString.equals("")) {
            outputToConsole = "'Amount (in $)' field cannot be empty.\n";
            return outputToConsole;
        }
        double amountAsDouble = Double.parseDouble(amountAsString);
        if (amountAsDouble <= 0 || amountAsDouble > 10000) {
            outputToConsole = "Tuition payment cannot be less than $0.00 or greater than $10,000.00.\n";
            return outputToConsole;
        }
        String major = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        Profile newResidentProfile = new Profile(name, Major.convertStringToMajor(major));
        Resident newResidentStudent = new Resident(newResidentProfile);
        outputToConsole = roster.setFinancialAid(newResidentStudent, amountAsDouble);
        return outputToConsole;
    }

    private String executeCommandR() {
        String outputToConsole = "";
        try {
            String studentMajorSelected = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        }
        catch (NullPointerException nullPointerException) {
            outputToConsole = "Please select an option for 'Student Major'.\n";
            return outputToConsole;
        }
        String name = studentNameField.getText();
        if (name.equals("")) {
            outputToConsole = "'Student Name' field cannot be empty.\n";
            return outputToConsole;
        }
        String major = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        Profile newProfile = new Profile(name, Major.convertStringToMajor(major));
        Student newStudent = new Student(newProfile);
        if (roster.remove(newStudent))
            outputToConsole = "Student successfully removed from the roster.\n";
        else
            outputToConsole = "Student could not be removed; student was not found or does not exist within roster.\n";
        return outputToConsole;
    }

    private String executeCommandS() {
        String outputToConsole = "";
        try {
            String studentMajorSelected = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        }
        catch (NullPointerException nullPointerException) {
            outputToConsole = "Please select an option for 'Student Major'.\n";
            return outputToConsole;
        }
        String name = studentNameField.getText();
        if (name.equals("")) {
            outputToConsole = "'Student Name' field cannot be empty.\n";
            return outputToConsole;
        }
        String major = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        boolean studyAbroad = studyAbroadStatus.isSelected();
        Profile newInternationalProfile = new Profile(name, Major.convertStringToMajor(major));
        International newInternationalStudent = new International(newInternationalProfile);
        outputToConsole = roster.setStudyAbroadToTrue(newInternationalStudent, studyAbroad);
        return outputToConsole;
    }

    private String executeCommandT() {
        String outputToConsole = "";
        try {
            String studentMajorSelected = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        }
        catch (NullPointerException nullPointerException) {
            outputToConsole = "Please select an option for 'Student Major'.\n";
            return outputToConsole;
        }
        try {
            double payTuitionAmount = Double.parseDouble(dollarAmountField.getText());
        }
        catch (NumberFormatException numberFormatException) {
            outputToConsole = "Please enter a valid numerical value for 'Amount (in $)'.\n";
            return outputToConsole;
        }
        String name = studentNameField.getText();
        if (name.equals("")) {
            outputToConsole = "'Student Name' field cannot be empty.\n";
            return outputToConsole;
        }
        String amountAsString = dollarAmountField.getText();
        if (amountAsString.equals("")) {
            outputToConsole = "'Amount (in $)' field cannot be empty.\n";
            return outputToConsole;
        }
        double amountAsDouble = Double.parseDouble(amountAsString);
        if (amountAsDouble <= 0) {
            outputToConsole = "Tuition payment cannot be less than or equal to $0.00.\n";
            return outputToConsole;
        }
        String major = ((RadioButton) studentMajor.getSelectedToggle()).getText();
        Profile newProfile = new Profile(name, Major.convertStringToMajor(major));
        Student newStudent = new Student(newProfile);
        String date = paymentDateField.getValue().toString();
        outputToConsole = roster.processPayment(newStudent, amountAsDouble, new Date(date));
        return outputToConsole;
    }

    @FXML
    void onAddCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(false);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(true);
        studentMajorBox.setDisable(true);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onCalculateAllTuitionCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(true);
        studentMajorBox.setDisable(true);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onCalculateSingleTuitionCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onExecuteCommandButtonClick(ActionEvent event) {
        RadioButton selectedCommand = (RadioButton) command.getSelectedToggle();
        switch (selectedCommand.getText()) {
            case "A" : outputConsole.appendText(executeCommandA());
                break;
            case "R" : outputConsole.appendText(executeCommandR());
                break;
            case "CO" : outputConsole.appendText(executeCommandCO());
                break;
            case "CA" : outputConsole.appendText(executeCommandCA());
                break;
            case "T" : outputConsole.appendText(executeCommandT());
                break;
            case "S" : outputConsole.appendText(executeCommandS());
                break;
            case "F" : outputConsole.appendText(executeCommandF());
                break;
            case "P" : outputConsole.appendText(roster.print());
                break;
            case "PN" : outputConsole.appendText(roster.printByStudentName());
                break;
            case "PT" : outputConsole.appendText(roster.printByPaymentDate());
                break;
        }
    }

    @FXML
    void onInternationalClick(ActionEvent event) {
        studentStatusBox.setDisable(false);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(false);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(false);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
    }

    @FXML
    void onNonResidentClick(ActionEvent event) {
        studentStatusBox.setDisable(false);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(false);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
    }

    @FXML
    void onPayTuitionCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(false);
        paymentDateField.setDisable(false);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onPrintByNameCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(true);
        studentMajorBox.setDisable(true);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onPrintByPaymentDateCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(true);
        studentMajorBox.setDisable(true);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onPrintRosterCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(true);
        studentMajorBox.setDisable(true);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onRemoveCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onResidentClick(ActionEvent event) {
        studentStatusBox.setDisable(false);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(false);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
    }

    @FXML
    void onSetFinancialAidCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(false);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onSetStudyAbroadCommandClick(ActionEvent event) {
        studentStatusBox.setDisable(true);
        tristateStudentState.setDisable(true);
        studyAbroadStatus.setDisable(false);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(true);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
        executeCommandButton.setDisable(false);
    }

    @FXML
    void onTriStateClick(ActionEvent event) {
        studentStatusBox.setDisable(false);
        tristateStudentState.setDisable(false);
        studyAbroadStatus.setDisable(true);
        studentNameField.setDisable(false);
        studentMajorBox.setDisable(false);
        creditsDropDownMenu.setDisable(false);
        dollarAmountField.setDisable(true);
        paymentDateField.setDisable(true);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        roster = new Roster();
        creditsDropDownMenu.setItems(possibleCreditsList);

        assert command != null : "fx:id=\"command\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert creditsDropDownMenu != null : "fx:id=\"creditsDropDownMenu\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert dollarAmountField != null : "fx:id=\"dollarAmountField\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert executeCommandButton != null : "fx:id=\"executeCommandButton\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert outputConsole != null : "fx:id=\"outputConsole\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert paymentDateField != null : "fx:id=\"paymentDateField\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert studentMajor != null : "fx:id=\"studentMajor\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert studentMajorBox != null : "fx:id=\"studentMajorBox\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert studentNameField != null : "fx:id=\"studentNameField\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert studentState != null : "fx:id=\"studentState\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert studentStatus != null : "fx:id=\"studentStatus\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert studentStatusBox != null : "fx:id=\"studentStatusBox\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert studyAbroadStatus != null : "fx:id=\"studyAbroadStatus\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
        assert tristateStudentState != null : "fx:id=\"tristateStudentState\" was not injected: check your FXML file 'TuitionManagerGUIView.fxml'.";
    }

}
