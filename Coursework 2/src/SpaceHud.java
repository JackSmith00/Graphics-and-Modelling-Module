import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A HUD to use in a Solar System graphic, with a centre reticle,
 * a time-stamp and a speed indicator.
 * 
 * @author Jack Smith - 24265241
 *
 */
public class SpaceHud {

	/*
	 * The class needed to format the Date object used was researched at the following resource:
	 * 
	 * Oracle, 2020. Class SimpleDateFormat [online].
	 * Available from: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
	 * [Accessed 28 April 2022].
	 * 
	 * The code was not copied verbatim, it was used to find which characters to use in
	 * the input String to achieve the desired formatting
	 */
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd / MM / YYYY"); // format to display dates
	private Color hudColor = Color.GREEN; // default green HUD colour
	
	/**
	 * Draws the standard Space HUD as an overlay in the 3D graphic
	 * 
	 * @param date the date to display in the time-stamp
	 * @param speed the speed to display in the speed label
	 */
	public void drawOverlay(Date date, SolarAnimationSpeed speed) {
		
		StdDraw3D.setPenColor(hudColor); // ensure HUD colour is selected
		
		// create centre reticle
		StdDraw3D.overlayCircle(0, 0, 50);
		StdDraw3D.overlayLine(0, 0, 25, -25);
		StdDraw3D.overlayLine(0, 0, -25, -25);
		
		// add time-stamp and speed label
		StdDraw3D.overlayTextRight(750, -750, dateFormatter.format(date));
		StdDraw3D.overlayTextLeft(-750, -750, "SPEED : " + speed.toString().toUpperCase());
	}
	
	/**
	 * Called whenever the date is updated and displays the new
	 * value in the HUD
	 * 
	 * @param newDate the current, updated date of the animation
	 * @param speed the current speed of the animation
	 */
	public void updateDate(Date newDate, SolarAnimationSpeed speed) {
		StdDraw3D.clearOverlay(); // remove old HUD display as now outdated
		drawOverlay(newDate, speed); // redraw HUD with updated values
	}
	
	/**
	 * Shows an altered HUD when the animation is paused.
	 * The speed label and centre reticle are hidden and instead
	 * a PAUSED label is drawn in the centre of the scene.
	 * 
	 * The user may still look around when paused, but cannot move.
	 * 
	 * @param date the current date at the time of pausing
	 */
	public void showPaused(Date date) {
		StdDraw3D.clearOverlay(); // remove the old HUD so elements can be replaced
		StdDraw3D.setPenColor(hudColor); // ensure HUD colour is selected
		
		StdDraw3D.overlayText(0, 0, "PAUSED"); // show paused message in the centre of the scene
		StdDraw3D.overlayTextRight(750, -750, dateFormatter.format(date)); // show the time-stamp
	}
	
	/**
	 * Allows the HUD colour to be altered
	 * @param color the new colour for the HUD
	 */
	public void setHudColor(Color color) {
		this.hudColor = color;
	}
	
	/**
	 * Allows the format the date/time is displayed in
	 * to be altered
	 * 
	 * @param dateFormatter the new formatter containing the desired date format
	 */
	public void setDateFormatter(SimpleDateFormat dateFormatter) {
		this.dateFormatter = dateFormatter;
	}
}
