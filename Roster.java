package student_tuition_manager_gui;

/**
 * Class that defines the 'Roster' abstract data type; an instance of Roster can hold a list of Student objects.
 * @author Aatif Sayed, Pranav Tailor
 */
public class Roster {
    
    private Student[] roster;
    private int size;  // keep track of the number of students in the roster
    
    private static final int INITIAL_CAPACITY = 4;
    private static final int CAPACITY_INCREASE_INCREMENT = 4;
    private static final int NOT_FOUND = -1;
    private static final int MAXIMUM_FINANCIAL_AID = 10000;

    public static final int MINIMUM_INTERNATIONAL_CREDITS = 12;
    
    /**
     * Default constructor to instantiate an empty roster with a capacity of INITIAL_CAPACITY (or 4).
     */
    public Roster() {
        roster = new Student[INITIAL_CAPACITY];
        size = 0;  // Roster is initially empty 
    }
    
    /**
     * A private helper method to find a student in the roster.
     * @param student the student that the application wants to search for.
     * @return the index at which the student was found in the roster; if not found, return NOT_FOUND (or -1).
     */
    private int find(Student student) {
        if (size == 0)
            return NOT_FOUND;
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null && roster[i].equals(student))
                return i;
        }
        return NOT_FOUND;
    }
    
    /**
     * A private helper method to automatically increase the capacity of the roster by CAPACITY_INCREASE_INCREMENT (or 4).
     */
    private void grow() {
        Student[] newRoster = new Student[size + CAPACITY_INCREASE_INCREMENT];
        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }
    
    /**
     * This method adds a student to the roster if the student is not already in it.
     * Checks if there is space to add the student; if not, invokes the 'grow()' method.
     * @param student the student to be added to the roster.
     * @return true if the student was added successfully, false otherwise.
     */
    public boolean add(Student student) {
        if (find(student) != NOT_FOUND)
            return false;
        if (size == roster.length)
           grow(); 
        roster[size] = student;
        size++;
        return true;
    }
    
    /**
     * This method removes a student from the roster if the roster is not empty and if the student is found within it.
     * @param student the student to be removed from the roster.
     * @return true if the student was removed successfully, false otherwise.
     */
    public boolean remove(Student student) {
        if (size == 0)
            return false;
        int indexOfStudent = find(student);
        if (indexOfStudent != NOT_FOUND) {
            roster[indexOfStudent] = null;
            for (int i = indexOfStudent + 1; i < roster.length; i++)
                roster[i - 1] = roster[i];
            roster[roster.length - 1] = null;
            size--;
            return true;
        }
        return false;
    }
    
    /**
     * This method calculates the tuition for every student in the roster by invoking the 'tuitionDue()' method which is implemented differently in different classes.
     */
    public void calculateTuition() {
        for (int i = 0; i < size; i++) {
            if (roster[i] != null)
                roster[i].tuitionDue();
        }
    }
    
    /**
     * This method processes student tuition payments and recalculates the tuition that the student owes.
     * @param studentToUpdate the student that the application wants to update the information for.
     * @param paymentAmount the amount of money that the student is paying towards tuition.
     * @param dateOfPayment the date that the student is making a payment on.
     * @return true if the payment was successfully processed, false otherwise.
     */
    public String processPayment(Student studentToUpdate, double paymentAmount, Date dateOfPayment) {
        String outputToConsole = "";
        for (Student student : roster) {
            if (student != null && student.equals(studentToUpdate)) {
                if (paymentAmount > student.getTuitionOwed()) {
                    outputToConsole = "Amount is greater than amount due.\n";
                    return outputToConsole;
                }
                student.setTotalTuitionPaid(student.getTotalTuitionPaid() + paymentAmount);
                student.setTuitionOwed(student.getTuitionOwed() - paymentAmount);
                student.setLastPaymentDate(dateOfPayment);
                outputToConsole = "Payment applied.\n";
                return outputToConsole;
            }
        }
        return outputToConsole;
    }
    
    /**
     * This method sets the study abroad status of an international student to true.
     * @param internationalStudent the international student whose study abroad status we want to set to true.
     * @param studyAbroadStatus boolean value (true/false) that dictates whether or not an international student is studying abroad.
     * @return true if international student's study abroad status was changed successfully, false otherwise.
     */
    public String setStudyAbroadToTrue(International internationalStudent, boolean studyAbroadStatus) {
        String outputToConsole = "";
        int internationalStudentIndex = find(internationalStudent);
        if (internationalStudentIndex == NOT_FOUND) {
            outputToConsole = "Could not find international student within roster.\n";
            return outputToConsole;
        }
        International studentConvertedToInternational = (International)roster[internationalStudentIndex];
        if (studyAbroadStatus) {
            if (studentConvertedToInternational.getCredits() > MINIMUM_INTERNATIONAL_CREDITS) {
                studentConvertedToInternational.setCredits(MINIMUM_INTERNATIONAL_CREDITS);
            }
            studentConvertedToInternational.setTotalTuitionPaid(0);
            studentConvertedToInternational.setLastPaymentDate(null);
            studentConvertedToInternational.setStudyAbroadStatus(studyAbroadStatus);
            studentConvertedToInternational.tuitionDue();
            outputToConsole = "Study abroad status set to true; tuition and other data updated accordingly.\n";
        }
        else {
            studentConvertedToInternational.tuitionDue();
            outputToConsole = "Study abroad status set to false; tuition amount due recalculated.\n";
        }
        return outputToConsole;
    }
    
    /**
     * This method sets the financial aid amount for a resident student.
     * @param residentStudent an instance of a 'Resident' student whose financial aid amount we are updating.
     * @param financialAidAmount the amount of financial aid to be set.
     * @return true if resident student's financial aid amount was successfully changed, false otherwise.
     */
    public String setFinancialAid(Resident residentStudent, double financialAidAmount) {
        String outputToConsole = "";
        int residentStudentIndex = find(residentStudent);
        if (residentStudentIndex == NOT_FOUND) {
            outputToConsole = "Student not in the roster.\n"; return outputToConsole;
        }
        if (!(roster[residentStudentIndex] instanceof Resident)) {
            outputToConsole = "Not a resident student."; return outputToConsole;
        }
        Resident studentConvertedToResident = (Resident)roster[residentStudentIndex];
        if (financialAidAmount < 0 || financialAidAmount > MAXIMUM_FINANCIAL_AID) {
            outputToConsole = "Invalid amount."; return outputToConsole;
        }
        if (studentConvertedToResident.getFinancialAid() != 0) {
            outputToConsole = "Awarded once already."; return outputToConsole;
        }
        if (studentConvertedToResident.getCredits() < Student.MINIMUM_FULL_TIME_CREDITS) {
            outputToConsole = "Part-time student doesn't qualify for the award."; return outputToConsole;
        }
        studentConvertedToResident.setFinancialAid(financialAidAmount);
        studentConvertedToResident.tuitionDue();
        outputToConsole = "Tuition successfully updated.\n";
        return outputToConsole;
    }
    
    /**
     * Print out the list of students in the roster.
     * If the collection is empty, print a message to the console letting the user know.
     */
    public String print() {
        String outputToConsole;
        if (size == 0) {
            outputToConsole = "Student roster is empty!\n";
            return outputToConsole;
        }
        outputToConsole = "* list of students in the roster **\n";
        for (Student student : roster) {
            if (student != null)
                outputToConsole += student.toString() + "\n";
        }
        outputToConsole += "* end of roster **\n";
        return outputToConsole;
    }
    
    /**
     * Print out the list of students in the roster sorted by payment date from oldest to newest.
     * Students with the same payment date may be printed in any order and may not match expected output order.
     */
    public String printByPaymentDate() {
        String outputToConsole;
        if (size == 0) {
            outputToConsole = "Student roster is empty!\n";
            return outputToConsole;
        }
        outputToConsole = "* list of students made payments ordered by payment date **\n";
        sortByPaymentDate(roster);
        for (int i = 0; i < size; i++) {
            if (roster[i] != null && roster[i].getLastPaymentDate() != null)
                outputToConsole += roster[i].toString() + "\n";
        }
        outputToConsole += "* end of roster **\n";
        return outputToConsole;
    }
    
    /**
     * Print out the list of students in the roster sorted by student name in alphabetical order.
     * Students with the same name may be printed in any order and may not match expected output order.
     */
    public String printByStudentName() {
        String outputToConsole;
        if (size == 0) {
            outputToConsole = "Student roster is empty!\n";
            return outputToConsole;
        }
        outputToConsole = "* list of students ordered by name **\n";
        sortByStudentName(roster);
        for (Student student : roster) {
            if (student != null)
                outputToConsole += student.toString() + "\n";
        }
        outputToConsole += "* end of roster **\n";
        return outputToConsole;
    }
    
    /**
     * Private helper method to sort the list of students according to payment date (from oldest to newest).
     * @param listOfStudents the list of students to be sorted represented as an array of Student objects.
     */
    private void sortByPaymentDate(Student[] listOfStudents) {
        for (int i = 0; i < listOfStudents.length - 1; i++) {
            for (int j = i + 1; j < listOfStudents.length; j++) {
                if (listOfStudents[i] != null && listOfStudents[j] != null && listOfStudents[i].getLastPaymentDate() != null && 
                        listOfStudents[j].getLastPaymentDate() != null && 
                        listOfStudents[i].getLastPaymentDate().compareTo(listOfStudents[j].getLastPaymentDate()) > 0) {
                    Student temp = listOfStudents[i];
                    listOfStudents[i] = listOfStudents[j];
                    listOfStudents[j] = temp;
                }
            }
        }
    }
    
    /**
     * Private helper method to sort the list of students alphabetically according to name.
     * @param listOfStudents the list of students to be sorted represented as an array of Student objects.
     */
    private void sortByStudentName(Student[] listOfStudents) {
        for (int i = 0; i < listOfStudents.length - 1; i++) {
            for (int j = i + 1; j < listOfStudents.length; j++) {
                if (listOfStudents[i] != null && listOfStudents[j] != null && 
                        listOfStudents[i].getProfile().getName().compareTo(listOfStudents[j].getProfile().getName()) > 0) {
                    Student temp = listOfStudents[i];
                    listOfStudents[i] = listOfStudents[j];
                    listOfStudents[j] = temp;
                }
            }
        }
    }
}