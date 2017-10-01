
/**
 * This is the 12 hour version a typical digital clock. It operates on 
 * an internal 12 hour cycle, but can also accept input in the 24 hour, or 
 * military standard. 
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
     * parameters. We also check for 24 hour inputs above 12
     * and adjust accordingly
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        if (hour > 12)
            setTime((hour-12), minute);
        else
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward. 
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute. when updating the time to be displayed, check
     * if we need to reduce to 12 hour.
     */
    public void setTime(int hour, int minute)
    {
        if (hour >12)
        {
            hours.setValue((hour-12));
            minutes.setValue(minute);
            updateDisplay();
        }
        else 
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
     * display when 0 is entered for military standard, or when clock instance
     * is created without getting parameters from user.
     */
    private void updateDisplay()
    {
        
        if (hours.getValue() == 0)     //when its midnight
            displayString = "12" + ":" + minutes.getDisplayValue();
        
        else
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
    }
}
