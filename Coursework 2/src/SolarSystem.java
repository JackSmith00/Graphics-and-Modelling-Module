import java.awt.event.KeyEvent;
import java.util.Date;

/**
 * A 3D graphic of the Solar System, containing all 8 planets and 6 predominant moons
 * 
 * @author Jack Smith - 24265241
 *
 */
public class SolarSystem {
	
	// Elements for the HUD
	private Date date; // tracks the date currently being simulated
	private SpaceHud hud; // the hud itself, allows it to be updated
	private boolean paused;
	private SolarAnimationSpeed speed = SolarAnimationSpeed.ONE_YEAR_PER_MINUTE;
	
	// Astronomical bodies
	private AstronomicalBody sun; // the sun
	private AstronomicalBody mercury, venus, earth, mars, jupiter, saturn, uranus, neptune; // planets
	private AstronomicalBody moon, io, europa, ganymede, callisto, titan; // moons
	private AstronomicalBody[] allBodies; // array to access all bodies to save repeated code (doesn't include the sun)
	
	/*
	 * Below is a constant value to mimic one Astronomical Unit (AU), which is a measurement
	 * used for a planets orbit distance from the sun. 1 AU is equivalent to the distance between
	 * the Earth and the Sun (~150,000,000km), scaled down x500,000 here for display purposes. This
	 * information has been researched at the following resource:
	 * 
	 * NASA, 2021. Our Solar System [online]. 
	 * Available from: https://solarsystem.nasa.gov/solar-system/our-solar-system/overview/
	 * [Accessed 27 April 2022].
	 */
	private static final float AU = 300;
	
	/*
	 * Below is the number of milliseconds in 1 day, used to increment the date display
	 * for the HUD every frame. This figure was obtained at the following resource:
	 * 
	 * Unit Juggler, 2021. Convert days to milliseconds - time converter [online].
	 * Available from: https://www.unitjuggler.com/convert-time-from-day-to-ms.html
	 * [Accessed 27 April 2022].
	 */
	private static final long MILLISECONDS_IN_A_DAY = 86400000;

	public static void main(String[] args) {
		
		StdDraw3D.fullscreen(); // make the scene full-screen
		SolarSystem solarSystem = new SolarSystem(); // create the solar system
		solarSystem.setHud(new SpaceHud()); // assign a HUD
		
		solarSystem.animate(); // animate the graphic
	}
	
	/**
	 * Creates all components of the Solar System, as well as setting up the
	 * scene and camera position/mode
	 */
	@SuppressWarnings("deprecation")
	public SolarSystem() {
		// set up scene
		StdDraw3D.setScale(-800, 800);
		StdDraw3D.setBackground("Assets/stars_milky_way.jpg"); // star background
		StdDraw3D.setInfoDisplay(false); // hide default HUD with coordinate information
		
		// set up camera
		StdDraw3D.setCameraMode(StdDraw3D.AIRPLANE_MODE);
		StdDraw3D.setCameraPosition(1*AU *2 + 100, 6.4, 0); // camera positioned on Earth's North Pole
		StdDraw3D.setCameraOrientation(180, 300, 180); // Camera initially facing the sun
		
		// set up lighting
		StdDraw3D.clearLight(); // remove the default lighting
		StdDraw3D.pointLight(0, 0, 0, StdDraw3D.WHITE, 100);
		
		// set starting date
		date = new Date(2000 - 1900, 4, 4); // animation starts the day I was born
		
		/* create sun
		 * 
		 * The sun rotates and different speeds on its surface, the average speed has been
		 * used for this simulation. The figure has been taken from the following resource:
		 * 
		 * NASA, 2017. Solar Rotation Varies by Latitude [online].
		 * Available from: https://www.nasa.gov/mission_pages/sunearth/science/solar-rotation.html
		 * [Accessed 27 April 2022].
		 * 
		 * The suns orbit will not be included in this as it is not necessary.
		 * The sun will also not be to scale compared to the planets as it would be too big to
		 * view in this graphic, although it will still be larger than all planets.
		 */
		sun = new AstronomicalBody(StdDraw3D.sphere(0, 0, 0, 200, "Assets/sun.jpg"), 27, 0, null);
		
		
		/* create planets - to scale relative to each other
		 * 
		 * The following resources were used to research the orbital radius,
		 * orbital period, size, rotation period and initial position of all of
		 * the planets.
		 * 
		 * For the size, rotation period and orbital information:
		 * 
		 * NASA, 2022. Planet Compare [online].
		 * Available from: https://solarsystem.nasa.gov/planet-compare/
		 * [Accessed 27 April 2022].
		 * 
		 * For the initial positions:
		 * 
		 * The Planets Today, 2022. The Planets Today : Solar System Events Calendar [online].
		 * Available from: https://www.theplanetstoday.com/solar_system_events_calendar.html
		 * [Accessed 27 April 2022].
		 * 
		 */
		// inner solar system - orbit distance enlarged for visibility
		mercury = new AstronomicalBody(StdDraw3D.sphere(0.4*AU *2 + 100, 0, 0, 2.4, "Assets/mercury.jpg"), 58.6, 0.2409308692676249, sun, 18);
		venus = new AstronomicalBody(StdDraw3D.sphere(0.7*AU *2 + 100, 0, 0, 6.1, "Assets/venus.jpg"), 243, 0.6160164271047228, sun, 18);
		earth = new AstronomicalBody(StdDraw3D.sphere(1*AU *2 + 100, 0, 0, 6.4, "Assets/earth.jpg"), 1, 1, sun, 225);
		mars = new AstronomicalBody(StdDraw3D.sphere(1.5*AU *2 + 100, 0, 0, 3.4, "Assets/mars.jpg"), 1.03, 1.88, sun, 72);
		
		// standard orbit distance
		jupiter = new AstronomicalBody(StdDraw3D.sphere(5.2*AU, 0, 0, 69.9, "Assets/jupiter.jpg"), 0.41, 11.86, sun, 48);
		saturn = new AstronomicalBody(StdDraw3D.sphere(9.5*AU, 0, 0, 58.2, "Assets/saturn.jpg"), 0.45, 29.45, sun, 51);
		
		// orbit distance reduced for visibility
		uranus = new AstronomicalBody(StdDraw3D.sphere(19.8*AU / 1.5, 0, 0, 25.4, "Assets/uranus.jpg"), 0.72, 84, sun, 318);
		neptune = new AstronomicalBody(StdDraw3D.sphere(30.1*AU / 1.5, 0, 0, 24.6, "Assets/neptune.jpg"), 0.67, 164.81, sun, 305);
		
		// add ring to Saturn
		saturn.addRing(120, 1);
		
		/*
		 * Uranus and Venus rotate differently to the other planets so
		 * this needs to be addressed. Uranus rotates parallel to is 
		 * orbital plane and Venus rotates clockwise. This information
		 * was found at the following resource:
		 * 
		 * Mangum, J., 2013. Why do the Planets in our Solar System Orbit the Sun Counter-Clockwise? [online]
		 * Available from: https://public.nrao.edu/ask/why-do-the-planets-in-our-solar-system-orbit-the-sun-counter-clockwise/#:~:text=Answer%3A%20Most%20of%20the%20objects,it%20also%20began%20to%20rotate
		 * [Accessed 27 April 2022].
		 * 
		 */
		// change rotation axis for Uranus
		uranus.getPlanetShape().rotate(0, 0, 90);
		uranus.setRotationAxis('x');
		
		// change rotation direction for Venus
		venus.setRotationClockwise(true);
		
		/* create moons
		 * 
		 * The following resources were used to research the orbital radius,
		 * orbital period, size, rotation period and initial position of all of
		 * the moons. The orbital radii have been adjusted to make them more
		 * visible.
		 * 
		 * NASA, 2022. By the Numbers | Earth's Moon – NASA Solar System Exploration [online].
		 * Available from: https://solarsystem.nasa.gov/moons/earths-moon/by-the-numbers/
		 * [Accessed 28 April 2022].
		 * 
		 * Stewart, S., 2022. Moons in the Solar System [online].
		 * Available from: https://theplanets.org/moons/
		 * [Accessed 28 April 2022].
		 * 
		 */
		moon = new AstronomicalBody(StdDraw3D.sphere(0.00257*AU *2 + 20, 0, 0, 1.7, "Assets/moon.jpg"), 27, 0.0739219712525667, earth, 38);
		io = new AstronomicalBody(StdDraw3D.sphere(0.002812*AU + 90, 0, 0, 1.8, "Assets/io.jpg"), 1.77, 0.00479, jupiter);
		europa = new AstronomicalBody(StdDraw3D.sphere(0.004474*AU +100, 0, 0, 1.56, "Assets/europa.jpg"), 3.55, 0.00972, jupiter);
		ganymede = new AstronomicalBody(StdDraw3D.sphere(0.007136*AU +110, 0, 0, 2.6, "Assets/ganymede.jpg"), 7.15, 0.01958, jupiter);
		callisto = new AstronomicalBody(StdDraw3D.sphere(0.01255*AU + 120, 0, 0, 2.4, "Assets/callisto.jpg"), 16.7, 0.045722, jupiter);
		titan = new AstronomicalBody(StdDraw3D.sphere(0.008146*AU + 140, 0, 0, 2.6, "Assets/titan.jpg"), 15.9, 0.04353, saturn);
		
		// array to reference all bodies easily
		allBodies = new AstronomicalBody[] {mercury, venus, earth, mars, jupiter, saturn, uranus, neptune, moon, io, europa, ganymede, callisto, titan};
	}

	/**
	 * Animates the Solar System at ~60fps. Also handles user
	 * interaction with predefined control buttons.
	 */
	private void animate() {
		
		while(true) { // constantly animate until app is quit
			
			// pause button
			if(StdDraw3D.isKeyPressed('P')) {
				
				while(StdDraw3D.isKeyPressed('P')) {
					// to prevent one press being registered multiple times
					continue;
				}
				
				paused = !paused; // toggle the pause
				hud.showPaused(date); // shows paused, cleared when unpaused
				StdDraw3D.showOverlay();
			}
			
			if(paused) { // prevents any further animation when paused
				continue;
			}
			
			// toggle speed - faster
			if(StdDraw3D.isKeyPressed(KeyEvent.VK_UP)) {
				while(StdDraw3D.isKeyPressed(KeyEvent.VK_UP)) {
					// to prevent one press being registered multiple times
					continue;
				}
				
				increaseSpeed();
			}
			
			// toggle speed - slower
			if(StdDraw3D.isKeyPressed(KeyEvent.VK_DOWN)) {
				while(StdDraw3D.isKeyPressed(KeyEvent.VK_DOWN)) {
					// to prevent one press being registered multiple times
					continue;
				}
				
				decreaseSpeed();
			}
			
			// reverse time
			if(StdDraw3D.isKeyPressed(KeyEvent.VK_BACK_SPACE)) {
				// reverse time whilst backspace is held
				reverseTime(true);
				// update current date & time
				date.setTime(date.getTime() + Math.round(0.101458333333333 * MILLISECONDS_IN_A_DAY / -speed.getValue())); // negative speed to reverse time
			} else {
				// time should not be reversed when backspace isn't held
				reverseTime(false);
				// update current date & time
				date.setTime(date.getTime() + Math.round(0.101458333333333 * MILLISECONDS_IN_A_DAY / speed.getValue()));
			}
			/*
			 * In the code above, the method to update the value of the 'date' variable was
			 * researched at the following resource:
			 * 
			 * DelftStack, 2020. Add One Day to a Date in Java [online].
			 * Available from: https://www.delftstack.com/howto/java/how-to-add-one-day-to-a-date-in-java/
			 * [Accessed 28 April 2022].
			 * 
			 * The code was not copied verbatim, it was used to find that to change the value
			 * of a Date object, the setTime() method should be used that accepts a time
			 * value in milliseconds.
			 */
			
			// update date display to new value
			if(hud != null) { // ensure there is a HUD before accessing it	
				hud.updateDate(date, speed);
			}
			
			// rotate sun
			sun.rotate();
			
			// orbit and rotate planets and moons
			for(AstronomicalBody body : allBodies) {
				body.orbit();
				body.rotate();
			}
			
			// move camera (unless spaceship has launched)
			if(!StdDraw3D.hasNextKeyTyped()) { // check if ship has launched (when keys have been pressed)
				// still docked at Earth - follow Earth's orbit
				StdDraw3D.setCameraPosition(earth.getPlanetShape().getPosition().x, 6.4, earth.getPlanetShape().getPosition().z);
			}
			
			// show frame at ~60fps (1000ms / 60 ~= 17ms)
			StdDraw3D.show(17);
		}
	}
	
	/**
	 * Increases the current animation speed, up to
	 * a maximum speed of 1yr/second
	 */
	private void increaseSpeed() {
		// increase the speed
		speed = SolarAnimationSpeed.increaseSpeed(speed);
		
		// update values for all Astronomical Bodies
		sun.setSpeed(speed.getValue());
		for(AstronomicalBody body : allBodies) {
			body.setSpeed(speed.getValue());
		}
	}
	
	/**
	 * Decreases the current animation speed, down to
	 * a minimum speed matching the real speed of the
	 * Solar System
	 */
	private void decreaseSpeed() {
		// decrease the speed
		speed = SolarAnimationSpeed.decreaseSpeed(speed);
		
		// update values for all Astronomical Bodies
		sun.setSpeed(speed.getValue());
		for(AstronomicalBody body : allBodies) {
			body.setSpeed(speed.getValue());
		}
	}
	
	/**
	 * Inverts the speed value of all Astronomical Bodies
	 * to simulate time reversing.
	 * 
	 * @param reversed true when time should be reversed, false otherwise
	 */
	private void reverseTime(boolean reversed) {
		// update values for all Astronomical Bodies
		sun.setSpeed(reversed? -speed.getValue() : speed.getValue());
		for(AstronomicalBody body : allBodies) {
			body.setSpeed(reversed? -speed.getValue() : speed.getValue());
		}
	}
	
	/**
	 * Sets the HUD to use in the Solar System view
	 * 
	 * @param hud the HUD to use for the Solar System display
	 */
	public void setHud(SpaceHud hud) {
		this.hud = hud;
	}
	
}
