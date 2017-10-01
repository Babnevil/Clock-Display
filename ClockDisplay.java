
/**
 * This class is meant to represent a typical digital clock. It operates on 
 * an internal 24 hour cycle, and expects input in the 24 hour, or military
 * standard. 
 * A few changes were made to the code to display in 12 hour format
 * No changes were made to the NumberDisplay class.
 * 
 * @author Matthew Schilling   
 * @version 10.01.2017
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean nightCheck;     //used to determine if output is am or pm
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00, but displayed as 12:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters. We also update the day/night status, looking for
     * entries in the 12pm hour, as these slip by later logic checks
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        if (hour == 12)         //check for noon entry
            nightCheck = true;
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward. update nightCheck 
     * if the tick pushes to the 12pm hour.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        if(hours.getValue() == 12)      //pushed to noon, set for pm
            nightCheck = true;
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute. when updating the time to be displayed, check
     * if we need to display in pm and reset the hour display if needed
     */
    public void setTime(int hour, int minute)
    {
        if (hour >12)
        {
            nightCheck = true;      //catch and reset numbers > 12
            hour -= 12;
        }
        else nightCheck = false;
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display. force 12 to
     * display when in the 0 hour(midnight). also catch all night checks
     * and append a pm to the string.
     */
    private void updateDisplay()
    {
        if (nightCheck == true)     //when its noon or later
           displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + "pm";
        else if (hours.getValue() == 0)     //when its midnight
            displayString = "12" + ":" + minutes.getDisplayValue();
        
        else
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
    }
}
