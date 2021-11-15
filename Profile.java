package student_tuition_manager_gui;

/**
 * Class that defines the 'Profile' abstract data type which represents the profile of a student (a profile consists of a student's name and major).
 * @author Aatif Sayed, Pranav Tailor
 */
public class Profile {
    
    private String name;
    private Major major;  // 5 majors and 2 characters each (case-insensitive): CS, IT, BA, EE, ME
    
    /**
     * Parameterized constructor that takes a name as a String and a major as a 'Major' enum and creates and instance of a 'Profile'.
     * @param name the name of a student to be used in the profile as a String.
     * @param major the major of a student to be used in the profile as a 'Major' enum.
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }
    
    /**
     * Getter method to return a name in a 'Profile' instance.
     * @return a name as a String.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Determines if two 'Profile' instances are equal to each other or not by comparing their names and majors.
     * @param obj 'Profile' object to be compared with.
     * @return true if the 'Profile' instances are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (name.equals(((Profile)obj).name) && major.equals(((Profile)obj).major))
            return true;
        return false;
    }
    
    /**
     * Method that overrides toString() method and converts a 'Profile' instance to a textual representation.
     * @return 'Profile' instance formatted as a string.
     */
    @Override
    public String toString() {
        String profileAsString = name + ":" + Major.convertMajorToString(major);
        return profileAsString;
    }
}