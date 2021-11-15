package student_tuition_manager_gui;

/**
 * Class that defines the abstract data type that models a Student in a roster of students.
 * @author Aatif Sayed, Pranav Tailor
 */
public class Student {
    
    private Profile profile;
    private int credits;
    private double tuitionOwed;
    private double totalTuitionPaid;
    private Date lastPaymentDate;
    
    /* Constants are public because other classes also need to access them */
    public static final double FULL_TIME_UNIVERSITY_FEE = 3268;
    public static final int ADDITIONAL_TUITION_CREDIT_THRESHOLD = 16;
    public static final int MINIMUM_FULL_TIME_CREDITS = 12;
    public static final double PART_TIME_UNIVERSITY_FEE_MULTIPLIER = 0.8;  // Represents 80%
    
    /* Represents 80% of full time university fee (i.e. 80% of $3,268 = $2614.40) */
    public static final double PART_TIME_UNIVERSITY_FEE = PART_TIME_UNIVERSITY_FEE_MULTIPLIER * FULL_TIME_UNIVERSITY_FEE;
    
    /**
     * Parameterized constructor that takes a 'Profile' object and creates an instance of a 'Student', setting other instance variables to default values.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     */
    public Student(Profile profile) {
        this.profile = profile;
        credits = 0;
        tuitionOwed = 0;
        totalTuitionPaid = 0;
        lastPaymentDate = null; 
    }
    
    /**
     * Second parameterized constructor that takes a 'Profile' instance and a number of credits and uses them to construct a 'Student' instance, setting other instance variables to default values.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     * @param credits the number of credits the student is taking this semester.
     */
    public Student(Profile profile, int credits) {
        this.profile = profile;
        this.credits = credits;
        tuitionOwed = 0;
        totalTuitionPaid = 0;
        lastPaymentDate = null;
    }
    
    /** Method to calculate and set the tuition due for any type of student; method is overridden by subclasses. */
    public void tuitionDue() {}
    
    /**
     * Getter method to return a student's profile ('Profile' instance).
     * @return the profile of a student as a 'Profile' object.
     */
    public Profile getProfile() {
        return profile;
    }
    
    /**
     * Getter method to return the number of credits a student's is enrolled for this semester.
     * @return the number of credits that a student is enrolled for.
     */
    public int getCredits() {
        return credits;
    }
    
    /**
     * Getter method to return the tuition that a student owes for this semester.
     * @return the tuition that a student owes for this semester.
     */
    public double getTuitionOwed() {
        return tuitionOwed;
    }
    
    /**
     * Getter method to return the total tuition that a student has paid for this semester.
     * @return the total tuition that a student has paid this semester.
     */
    public double getTotalTuitionPaid() {
        return totalTuitionPaid;
    }
    
    /**
     * Getter method to return a student's last (most recent) tuition payment date.
     * @return the last (most recent) tuition payment date as a 'Date' object.
     */
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }
    
    /**
     * Setter method to set a student's number of credits.
     * @param credits the number of credits that is to be set.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    /**
     * Setter method to set the tuition that a student owes.
     * @param tuitionOwed the amount that the tuition owed is to be set at.
     */
    public void setTuitionOwed(double tuitionOwed) {
        this.tuitionOwed = tuitionOwed;
    }
    
    /**
     * Setter method to set the total tuition that a student has paid.
     * @param totalTuitionPaid the amount that the total tuition paid is to be set at.
     */
    public void setTotalTuitionPaid(double totalTuitionPaid) {
        this.totalTuitionPaid = totalTuitionPaid;
    }
    
    /**
     * Setter method to set a student's last (most recent) tuition payment date.
     * @param lastPaymentDate the amount that the last (most recent) tuition payment date is to be set at.
     */
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }
    
    /**
     * Determines if two 'Student' instances are equal to each other or not by comparing their profiles (names and majors).
     * @param obj 'Student' object to be compared with.
     * @return true if the 'Student' instances are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (profile.equals(((Student)obj).profile))
            return true;
        return false;
    }
    
    /**
     * Method that overrides toString() method and converts a 'Student' instance to a textual representation.
     * @return 'Student' instance formatted as a string.
     */
    @Override
    public String toString() {
        String lastPaymentDateAsString = null;
        if (lastPaymentDate == null)
            lastPaymentDateAsString = "--/--/--";
        else
            lastPaymentDateAsString = lastPaymentDate.toString();
        String studentAsString = profile.toString() + ":" + credits + " credit hours:tuition due:" + 
                String.format("%,.2f", tuitionOwed) + ":total payment:" + String.format("%,.2f", totalTuitionPaid) + 
                ":last payment date: " + lastPaymentDateAsString + ":";
        return studentAsString;
    }
}