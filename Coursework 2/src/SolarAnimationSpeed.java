
/**
 * Represents the different speeds that can be used in
 * the Solar System animation.
 * 
 * @author Jack Smith - 24265241
 *
 */
public enum SolarAnimationSpeed {
	
	REAL_TIME,
	ONE_YEAR_PER_DAY,
	ONE_YEAR_PER_HOUR,
	ONE_YEAR_PER_30_MINUTES,
	ONE_YEAR_PER_10_MINUTES,
	ONE_YEAR_PER_5_MINUTES,
	ONE_YEAR_PER_MINUTE,
	ONE_YEAR_PER_30_SECONDS,
	ONE_YEAR_PER_10_SECONDS,
	ONE_YEAR_PER_SECOND;
	
	/**
	 * Gets the double value to be used to divide the baseline speed
	 * of 1yr/min to get the described speed.
	 * 
	 * All values were calculated based off 1 representing 1 minute.
	 * e.g. for 1 second - 1 / 60 - as there are 60 seconds in a minute;
	 * 		for 1 day - 1 * (60 * 24) - as there are 60 minutes in an hour and 24 hours in a day;
	 * 
	 * @return value to divide the speed value for 1yr/min by
	 */
	public double getValue() {
		switch(this) {
		case REAL_TIME:
			return 52596;
		case ONE_YEAR_PER_DAY:
			return 144;
		case ONE_YEAR_PER_HOUR:
			return 60;
		case ONE_YEAR_PER_30_MINUTES:
			return 30;
		case ONE_YEAR_PER_10_MINUTES:
			return 10;
		case ONE_YEAR_PER_5_MINUTES:
			return 5;
		case ONE_YEAR_PER_MINUTE:
			return 1;
		case ONE_YEAR_PER_30_SECONDS:
			return 0.5;
		case ONE_YEAR_PER_10_SECONDS:
			return 0.167;
		case ONE_YEAR_PER_SECOND:
			return 0.0167;
		default:
			return 1;
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case REAL_TIME:
			return "Real Time";
		case ONE_YEAR_PER_DAY:
			return "One year every 24 hrs";
		case ONE_YEAR_PER_HOUR:
			return "One year every 1 hr";
		case ONE_YEAR_PER_30_MINUTES:
			return "One year every 30 mins";
		case ONE_YEAR_PER_10_MINUTES:
			return "One year every 10 mins";
		case ONE_YEAR_PER_5_MINUTES:
			return "One year every 5 mins";
		case ONE_YEAR_PER_MINUTE:
			return "One year every 1 min";
		case ONE_YEAR_PER_30_SECONDS:
			return "One year every 30 secs";
		case ONE_YEAR_PER_10_SECONDS:
			return "One year every 10 secs";
		case ONE_YEAR_PER_SECOND:
			return "One year every 1 sec";
		default:
			return "Unknown";
		}
	}
	
	/*
	 * The methods below to find the next value in an enum were
	 * researched at the following resource:
	 * 
	 * Garrison, J., 2013. What's the best way to implement `next` and `previous` on an enum type? [online].
	 * Available from: https://stackoverflow.com/questions/17006239/whats-the-best-way-to-implement-next-and-previous-on-an-enum-type
	 * [Accessed 28 April 2022].
	 * 
	 * The code was not copied verbatim, there was unnecessary variables created and has the functionality of
	 * retrieving the first value as the 'next' value of the last, which is not suitable to this scenario so
	 * the code was changed for this purpose.
	 */
	
	/**
	 * Increases the speed to the next speed step to a maximum
	 * value of 1yr/sec
	 * 
	 * @param currentSpeed the speed to return the increased value of
	 * @return the increased speed, unless at maximum speed, then the maximum speed is returned
	 */
	public static SolarAnimationSpeed increaseSpeed(SolarAnimationSpeed currentSpeed) {
		if(currentSpeed == ONE_YEAR_PER_SECOND) {
			// don't increase on the maximum speed
			return currentSpeed;
		} else {
			return values()[currentSpeed.ordinal() + 1]; // get the next speed in the enum
		}
	}
	
	/**
	 * Decreases the speed to the next speed step to a minimum
	 * value matching real-time (1yr/1yr)
	 * 
	 * @param currentSpeed the speed to return the decreased value of
	 * @return the increased speed, unless at minimum speed, then the minimum speed is returned
	 */
	public static SolarAnimationSpeed decreaseSpeed(SolarAnimationSpeed currentSpeed) {
		if(currentSpeed == REAL_TIME) {
			// don't decrease on the minimum speed
			return currentSpeed;
		} else {
			return values()[currentSpeed.ordinal() - 1]; // get the previous value in the enum
		}
	}
	
}
