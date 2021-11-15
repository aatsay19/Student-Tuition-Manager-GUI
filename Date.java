package student_tuition_manager_gui;

import java.util.Calendar;

/**
 * Class that defines the Date abstract data type with year, month, and day instance variables.
 * @author Aatif Sayed, Pranav Tailor
 */
public class Date implements Comparable<Date> {
    
    private int year;
    private int month;
    private int day;
    
    private static final int JAN = 1;
    private static final int FEB = 2;
//  private static final int MAR = 3;
    private static final int APR = 4;
//  private static final int MAY = 5;
    private static final int JUN = 6;
//  private static final int JUL = 7;
//  private static final int AUG = 8;
    private static final int SEP = 9;
//  private static final int OCT = 10;
    private static final int NOV = 11;
    private static final int DEC = 12;
    
    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUARTERCENTENNIAL = 400;
    private static final int CURRENT_YEAR = 2021;
    
    private static final int MINIMUM_DAY_NUMBER = 1;
    private static final int DAYS_IN_MONTH_1 = 30;  // For April, June, September, and November
    private static final int DAYS_IN_MONTH_2 = 31;  // For all other months besides February and the ones listed above
    private static final int DAYS_IN_NON_LEAP_YEAR = 365;
//  private static final int DAYS_IN_LEAP_YEAR = 366;
    private static final int NON_LEAP_YEAR_FEBRUARY_DAYS = 28;
    private static final int LEAP_YEAR_FEBRUARY_DAYS = 29;
    
    /**
     * This is a parameterized constructor that takes a String in the form of "mm/dd/yyyy" and creates an instance of Date.
     * @param date a date in the format: "mm/dd/yyyy".
     */
    public Date(String date) {
        String[] dateTokens = date.split("/");
        month = Integer.parseInt(dateTokens[0]);
        day = Integer.parseInt(dateTokens[1]);
        year = Integer.parseInt(dateTokens[2]);
    }
    
    /**
     * This method checks whether an instance of Date is a valid date or not.
     * A Date is considered valid if it corresponds to a valid calendar date, is not before 2021, and is not a future date.
     * @return true if Date is a valid date, false otherwise.
     */
    public boolean isValid() {
        if (month == SEP && day == DEC && year == CURRENT_YEAR)
            return true;
        Calendar calendar = Calendar.getInstance();
        if (month < JAN || month > DEC || year < CURRENT_YEAR || year > calendar.get(Calendar.YEAR) 
                || day < MINIMUM_DAY_NUMBER || day > DAYS_IN_MONTH_2)
            return false;
        if (month == APR || month == JUN || month == SEP || month == NOV) {
            if (day > DAYS_IN_MONTH_1)
                return false;
        }
        if (!(isValidFebruary()))
            return false;
        if (isFutureDate())
            return false;
        return true;
    }
    
    /**
     * This is a private helper method that checks if a Date instance with the month as February is valid or not.
     * @return true if Date is a valid February date, false otherwise.
     */
    private boolean isValidFebruary() {
        if (month == FEB) {
            if (isLeapYear(year)) {
                if (day > LEAP_YEAR_FEBRUARY_DAYS)
                    return false;
            }
            else {
                if (day > NON_LEAP_YEAR_FEBRUARY_DAYS)
                    return false;
            }
        }
        return true;
    }
    
    /**
     * This method checks whether a given year is a leap year or not.
     * @param year the year that is going to be checked.
     * @return true if specified year is a leap year, false otherwise.
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0)
                    return true;
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * This is a private helper method that checks if a Date instance is past today's date or not.
     * @return true if Date is in the future, false otherwise.
     */
    private boolean isFutureDate() {
        Calendar calendar = Calendar.getInstance();
        if (year == calendar.get(Calendar.YEAR)) {
            if (month > calendar.get(Calendar.MONTH))
                return true;
            if (month == calendar.get(Calendar.MONTH)) {
                if (day > calendar.get(Calendar.DATE))
                    return true;
            }
        }
        return false;
    }
    
    /**
     * This method overrides the compareTo() method and compares 2 Date instances.
     * @return integer representing the difference in the number of days between 2 dates, return 0 if date is null.
     */
    @Override
    public int compareTo(Date date) {
        if (date != null) {
            int yearDifference = 0, monthDifference = 0, dayDifference = 0;
            yearDifference = DAYS_IN_NON_LEAP_YEAR * (year - date.year);
            monthDifference = DAYS_IN_MONTH_1 * (month - date.month);
            dayDifference = day - date.day;
            return yearDifference + monthDifference + dayDifference;
        }
        return 0;
    }
    
    /**
     * This method converts a Date object to a textual representation (string) with "mm/dd/yyyy" format.
     * @return the Date object formatted as a string in "mm/dd/yyyy" format.
     */
    @Override
    public String toString() {
        String dateAsString = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        return dateAsString;
    }
}