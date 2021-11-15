package student_tuition_manager_gui;

/**
 * Class that defines a non-resident student (a subgroup of student).
 * @author Aatif Sayed, Pranav Tailor
 */
public class NonResident extends Student {
    
    public static final double FULL_TIME_NONRESIDENT_TUITION = 29737;
    public static final double NONRESIDENT_TUITION_RATE_PER_CREDIT = 966;
    
    /**
     * Parameterized constructor that takes a 'Profile' object, calls the superclass constructor, and creates an instance of an 'TriState' student.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     */
    public NonResident(Profile profile) {
        super(profile);
    }
    
    /**
     * Second parameterized constructor that takes a 'Profile' instance and a number of credits and uses them to construct an 'TriState' student instance by calling its superclass constructor.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     * @param credits the number of credits the student is taking this semester.
     */
    public NonResident(Profile profile, int credits) {
        super(profile, credits);
    }
    
    /**
     * Method that overrides tuitionDue() method in 'Student' class; calculates and sets the tuition that a non-resident student owes.
     */
    @Override
    public void tuitionDue() {
        if (getTotalTuitionPaid() > 0)
            return;
        double _tuitionOwed = 0;
        if (getCredits() < MINIMUM_FULL_TIME_CREDITS)
            _tuitionOwed = (getCredits() * NONRESIDENT_TUITION_RATE_PER_CREDIT) + PART_TIME_UNIVERSITY_FEE;
        else if (getCredits() >= MINIMUM_FULL_TIME_CREDITS && getCredits() <= ADDITIONAL_TUITION_CREDIT_THRESHOLD)
            _tuitionOwed = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE;
        else
            _tuitionOwed = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE + (NONRESIDENT_TUITION_RATE_PER_CREDIT * 
                    (getCredits() - ADDITIONAL_TUITION_CREDIT_THRESHOLD));
        setTuitionOwed(_tuitionOwed);
    }
    
    /**
     * Method that calls upon superclass toString() method and converts a 'NonResident' student instance to a textual representation.
     * @return 'NonResident' student instance formatted as a string.
     */
    @Override
    public String toString() {
        return super.toString() + "non-resident";
    }
}