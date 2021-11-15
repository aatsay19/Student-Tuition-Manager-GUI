package student_tuition_manager_gui;

/**
 * Class that defines an international student (a subgroup of non-resident which is a subgroup of student).
 * Keeps track of whether the student studies abroad or not.
 * @author Aatif Sayed, Pranav Tailor
 */
public class International extends NonResident {
    
    private boolean studyAbroad;
    
    private static final double ADDITIONAL_INTERNATIONAL_FEE = 2650;
    
    /**
     * Parameterized constructor that takes a 'Profile' object, calls the superclass constructors, and creates an instance of an 'International' student.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     */
    public International(Profile profile) {
        super(profile);
        studyAbroad = false;
    }
    
    /**
     * Second parameterized constructor that takes a 'Profile' instance, a number of credits, and a boolean (true/false) value to hold the study abroad status and uses them to construct an 'International' student instance by calling its superclass constructor.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     * @param credits the number of credits the student is taking this semester.
     * @param studyAbroad boolean value (true/false) to determine whether the student studies abroad or not.
     */
    public International(Profile profile, int credits, boolean studyAbroad) {
        super(profile, credits);
        this.studyAbroad = studyAbroad;
    }
    
    /**
     * Method that overrides tuitionDue() method in 'Student' and 'NonResident' classes; calculates and sets the tuition that an international student owes.
     */
    @Override
    public void tuitionDue() {
        if (getTotalTuitionPaid() > 0)
            return;
        double _tuitionOwed = 0;
        if (studyAbroad)
            _tuitionOwed = FULL_TIME_UNIVERSITY_FEE + ADDITIONAL_INTERNATIONAL_FEE;
        else if (getCredits() >= MINIMUM_FULL_TIME_CREDITS && getCredits() <= ADDITIONAL_TUITION_CREDIT_THRESHOLD)
            _tuitionOwed = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE + ADDITIONAL_INTERNATIONAL_FEE;
        else
            _tuitionOwed = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE + ADDITIONAL_INTERNATIONAL_FEE +
                    (NONRESIDENT_TUITION_RATE_PER_CREDIT * (getCredits() - ADDITIONAL_TUITION_CREDIT_THRESHOLD));
        setTuitionOwed(_tuitionOwed);
    }
    
    /**
     * Setter method to set an international student's study abroad status to true or false.
     * @param studyAbroad true or false value where true represents international student studying abroad and false represents international student not studying abroad.
     */
    public void setStudyAbroadStatus(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }
    
    /**
     * Method that calls upon superclass toString() methods and converts an 'International' student instance to a textual representation.
     * @return 'International' student instance formatted as a string.
     */
    @Override
    public String toString() {
        String studyAbroadAsString = "";
        if (studyAbroad)
            studyAbroadAsString = ":study abroad";
        return super.toString() + ":international" + studyAbroadAsString;
    }
}