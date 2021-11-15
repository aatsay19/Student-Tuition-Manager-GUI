package student_tuition_manager_gui;

/**
 * Class that defines a resident student (a subgroup of student).
 * @author Aatif Sayed, Pranav Tailor
 */
public class Resident extends Student {
    
    private double financialAid;
    
    private static final double FULL_TIME_RESIDENT_TUITION = 12536;
    private static final double RESIDENT_TUITION_RATE_PER_CREDIT = 404;
    
    /**
     * Parameterized constructor that takes a 'Profile' object, calls the superclass constructor, and creates an instance of a 'Resident' student.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     */
    public Resident(Profile profile) {
        super(profile);
    }
    
    /**
     * Second parameterized constructor that takes a 'Profile' instance and a number of credits and uses them to construct a 'Resident' student instance by calling its superclass constructor.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     * @param credits the number of credits the student is taking this semester.
     */
    public Resident(Profile profile, int credits) {
        super(profile, credits);
        financialAid = 0;
    }
    
    /**
     * Method that overrides tuitionDue() method in 'Student' class; calculates and sets the tuition that a resident student owes.
     */
    @Override
    public void tuitionDue() {
        if (getTotalTuitionPaid() > 0)
            return;
        double _tuitionOwed = 0;
        if (getCredits() < MINIMUM_FULL_TIME_CREDITS)
            _tuitionOwed = (getCredits() * RESIDENT_TUITION_RATE_PER_CREDIT) + PART_TIME_UNIVERSITY_FEE;
        else if (getCredits() >= MINIMUM_FULL_TIME_CREDITS && getCredits() <= ADDITIONAL_TUITION_CREDIT_THRESHOLD)
            _tuitionOwed = FULL_TIME_RESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE - financialAid;
        else
            _tuitionOwed = FULL_TIME_RESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE + (RESIDENT_TUITION_RATE_PER_CREDIT * 
                    (getCredits() - ADDITIONAL_TUITION_CREDIT_THRESHOLD)) - financialAid;
        setTuitionOwed(_tuitionOwed);
    }
    
    /**
     * Getter method to return a resident student's financial aid amount.
     * @return the genre of the album as a Genre object.
     */
    public double getFinancialAid() {
        return financialAid;
    }
    
    /**
     * Setter method to set a resident student's financial aid.
     * @param financialAid the amount that the financial aid is to be set.
     */
    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
    }
    
    /**
     * Method that calls upon superclass toString() method and converts a 'Resident' student instance to a textual representation.
     * @return 'Resident' student instance formatted as a string.
     */
    @Override
    public String toString() {
        String financialAidAsString = "";
        if (financialAid != 0)
            financialAidAsString = ":financial aid $" + String.format("%,.2f", financialAid);
        return super.toString() + "resident" + financialAidAsString;
    }
}