package student_tuition_manager_gui;

/**
 * This class is an enum class which defines the different possible majors of students within the student roster.
 * @author Aatif Sayed, Pranav Tailor
 */
public enum Major {
    CS,
    IT,
    BA,
    EE,
    ME;

    /**
     * Convert a given String (ignore alphabetical case) to one of the declared majors.
     * If given String is not equivalent to a constant in the enum, convert it to the 'Unknown' enum. 
     * @param majorAsString the major of a student, represented as a String.
     * @return an instance of one of the five declared majors within the Major enum, null otherwise.
     */
    public static Major convertStringToMajor(String majorAsString) {
        Major stringAsMajor;
        if (majorAsString.equalsIgnoreCase("CS"))
            stringAsMajor = CS;
        else if (majorAsString.equalsIgnoreCase("IT"))
            stringAsMajor = IT;
        else if (majorAsString.equalsIgnoreCase("BA"))
            stringAsMajor = BA;
        else if (majorAsString.equalsIgnoreCase("EE"))
            stringAsMajor = EE;
        else if (majorAsString.equalsIgnoreCase("ME"))
            stringAsMajor = ME;
        else
            stringAsMajor = null;
        return stringAsMajor;
    }
    
    /**
     * Convert an instance of the Major enum to a String.
     * @param stringAsMajor the major as a Major object.
     * @return the major as a String.
     */
    public static String convertMajorToString(Major stringAsMajor) {
        String majorAsString = stringAsMajor.name();
        return majorAsString;
    }
}