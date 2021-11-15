package student_tuition_manager_gui;

/**
 * This class is an enum class which defines the different possible states of students that are considered tri-state students.
 * @author Aatif Sayed, Pranav Tailor
 */
public enum State {
    NY,
    CT;

    /**
     * Convert a given String (ignore alphabetical case) to one of the declared states.
     * If given String is not equivalent to a constant in the enum, convert it to the 'Unknown' enum. 
     * @param stateAsString the state that a student belongs to, represented as a 2-character String.
     * @return an instance of one of the two declared states within the States enum, null otherwise.
     */
    public static State convertStringToState(String stateAsString) {
        State stringAsState;
        if (stateAsString.equalsIgnoreCase("NY"))
            stringAsState = NY;
        else if (stateAsString.equalsIgnoreCase("CT"))
            stringAsState = CT;
        else
            stringAsState = null;
        return stringAsState;
    }
    
    /**
     * Convert an instance of the State enum to a String.
     * @param stringAsState the state as a State object.
     * @return the state as a String.
     */
    public static String convertStateToString(State stringAsState) {
        String stateAsString = stringAsState.name();
        return stateAsString;
    }
}