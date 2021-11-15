package student_tuition_manager_gui;

/**
 * Class that defines a tri-state student (a subgroup of non-resident which is a subgroup of student).
 * Keeps track of the state that the tri-state student is from and the tuition discount that comes with it.
 * @author Aatif Sayed, Pranav Tailor
 */
public class TriState extends NonResident {
    
    private State state;
    private double tuitionDiscount;
    
    /**
     * Parameterized constructor that takes a 'Profile' object, calls the superclass constructors, and creates an instance of an 'TriState' student.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     */
    public TriState(Profile profile) {
        super(profile);
        state = null;
        tuitionDiscount = 0;
    }
    
    /**
     * Second parameterized constructor that takes a 'Profile' instance, a number of credits, and a 'State' enum (to hold the tri-state student's state) and uses them to construct an 'TriState' student instance by calling its superclass constructors.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     * @param credits the number of credits the student is taking this semester.
     * @param state 'State' enum to hold whether a student is from New York or Connecticut.
     */
    public TriState(Profile profile, int credits, State state) {
        super(profile, credits);
        if (State.convertStateToString(state).equals("NY"))
            tuitionDiscount = 4000;
        else
            tuitionDiscount = 5000;
        this.state = state;
    }
    
    /**
     * Method that overrides tuitionDue() method in 'Student' and 'NonResident' classes; calculates and sets the tuition that a tri-state student owes.
     */
    @Override
    public void tuitionDue() {
        if (getTotalTuitionPaid() > 0)
            return;
        double _tuitionOwed = 0;
        if (getCredits() < MINIMUM_FULL_TIME_CREDITS)
            _tuitionOwed = (getCredits() * NONRESIDENT_TUITION_RATE_PER_CREDIT) + PART_TIME_UNIVERSITY_FEE;
        else if (getCredits() >= MINIMUM_FULL_TIME_CREDITS && getCredits() <= ADDITIONAL_TUITION_CREDIT_THRESHOLD)
            _tuitionOwed = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE - tuitionDiscount;
        else
            _tuitionOwed = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIVERSITY_FEE + (NONRESIDENT_TUITION_RATE_PER_CREDIT * 
                    (getCredits() - ADDITIONAL_TUITION_CREDIT_THRESHOLD)) - tuitionDiscount;
        setTuitionOwed(_tuitionOwed);
    }
    
    /**
     * Method that calls upon superclass toString() methods and converts an 'TriState' student instance to a textual representation.
     * @return 'TriState' student instance formatted as a string.
     */
    @Override
    public String toString() {
        return super.toString() + "(tri-state):" + state;
    }
}