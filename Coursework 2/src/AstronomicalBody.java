
/**
 * A class to represent an Astronomical Body (star, planet or moon) in a 3D simulation of space.
 * 
 * @author Jack Smith - 24265241
 *
 */
public class AstronomicalBody {

	private StdDraw3D.Shape planetShape; // the shape of the planet
	private StdDraw3D.Shape planetRing; // any ring belonging to the planet
	
	private double rotationSpeed; // how fast the Astronomical Body should rotate about its own axis
	private double originalRotationSpeed; // stores the initial rotationSpeed to avoid rounding errors
	private char rotationAxis = 'y'; // the axis the Astronomical Body should rotate about, default to y
	
	private AstronomicalBody centerOfOrbit; // the Astronomical Body this Astronomical Body should orbit around
	
	private double orbitAngleIncrement; // how much to increase the orbit per frame
	private double originalOrbitAngleIncrement; // stores the initial orbitAngleIncrement to avoid rounding errors
	private double orbitRadius; // the radius of the orbit
	private double orbitAngle = 0; // initial orbit position
	
	private static final double EARTH_ORBIT_SPEED = 0.1; // 1 earth year (full rotation) in 1 minute
	private static final double EARTH_ROTATION_SPEED = 36.525; // angle the earth should rotate by per frame
	
	/**
	 * Allows an Astronomical Body to be constructed
	 * 
	 * @param shape shape object to visually present the Astronomical Body
	 * @param rotationPeriod how many Earth days should the Astronomical Body take to complete a rotation of its axis
	 * @param orbitDuration how many Earth years should it take the Astronomical Body to orbit its parent
	 * @param centerOfOrbit the parent Astronomical Body that is orbited by this Astronomical Body
	 */
	public AstronomicalBody(StdDraw3D.Shape shape, double rotationPeriod, double orbitDuration, AstronomicalBody centerOfOrbit) {
		this.planetShape = shape;
		this.rotationSpeed = EARTH_ROTATION_SPEED / rotationPeriod; // not to radians as passed into StdDraw3D method that expects degrees
		this.orbitAngleIncrement = Math.toRadians(EARTH_ORBIT_SPEED / orbitDuration); // radians as passed into java.lang.Math method
		this.orbitRadius = shape.getPosition().x;
		this.centerOfOrbit = centerOfOrbit;
		
		// backup values
		this.originalRotationSpeed = rotationSpeed;
		this.originalOrbitAngleIncrement = orbitAngleIncrement;
	}
	
	/**
	 * Allows an Astronomical Body to be constructed
	 * 
	 * @param shape shape object to visually present the Astronomical Body
	 * @param rotationPeriod how many Earth days should the Astronomical Body take to complete a rotation of its axis
	 * @param orbitDuration how many Earth years should it take the Astronomical Body to orbit its parent
	 * @param centerOfOrbit the parent Astronomical Body that is orbited by this Astronomical Body
	 * @param orbitProgresion degree angle of the Astronomical Body's initial position around its parent
	 */
	public AstronomicalBody(StdDraw3D.Shape shape, double rotationPeriod, double orbitDuration, AstronomicalBody centerOfOrbit, double orbitProgresion) {
		this(shape, rotationPeriod, orbitDuration, centerOfOrbit);
		this.orbitAngle = Math.toRadians(orbitProgresion);
	}
	
	/**
	 * Orbits the Astronomical Body around a given position in the scene
	 * 
	 * @param origin the point for the Astronomical Body to rotate around
	 * @param axis the axis to orbit around
	 */
	public void orbit(StdDraw3D.Vector3D origin, char axis) {
		
		axis = Character.toLowerCase(axis); // ensure axis is formatted correctly
		
		/**
		 * The angle is decreased by the increment value to produce an anti-clockwise
		 * orbit as this is the direction of orbit in the Solar System. This information
		 * was researched at the following resource:
		 * 
		 * Klesman, A., 2020. Ask Astro: Why do the planets orbit the Sun counterclockwise? [online].
		 * Available from: https://astronomy.com/magazine/ask-astro/2020/10/ask-astro-why-do-the-planets-orbit-the-sun-counterclockwise#:~:text=A%3A%20The%20planets%20of%20our,way%20our%20solar%20system%20formed.
		 * [Accessed 28 April 2022].
		 */
		orbitAngle -= orbitAngleIncrement;
		
		if(orbitAngle >= 2 * Math.PI || orbitAngle <= - 2 * Math.PI) {
			orbitAngle = 0; // prevents orbit angle growing/shrinking indefinitely
		}
		StdDraw3D.Vector3D newPosition = null; // initialise new position vector
		
		switch(axis) { // switch each axis
		case 'x':
			// change y and z values
			newPosition = new StdDraw3D.Vector3D(origin.x, origin.y + orbitRadius * Math.cos(orbitAngle), origin.z + orbitRadius * Math.sin(orbitAngle));
			break;
		case 'y':
			// change x and z values
			newPosition = new StdDraw3D.Vector3D(origin.x + orbitRadius * Math.cos(orbitAngle), origin.y, origin.z + orbitRadius * Math.sin(orbitAngle));
			break;
		case 'z':
			// change x and y values
			newPosition = new StdDraw3D.Vector3D(origin.x + orbitRadius * Math.cos(orbitAngle), origin.y + orbitRadius * Math.sin(orbitAngle), origin.z);
			break;
		}
		
		if(newPosition != null) { // ensure a valid position exists
			planetShape.setPosition(newPosition); // move to the new position
			if(planetRing != null) {
				// where applicable, also move the planet ring
				planetRing.setPosition(newPosition);
			}
		}
	}
	
	/**
	 * Orbits the Astronomical Body around it's parent in the y plane
	 */
	public void orbit() {
		orbit(centerOfOrbit.getPlanetShape().getPosition(), 'y');
	}
	
	/**
	 * Rotates the Astronomical Body around it's centre about the axis specified
	 * 
	 * @param axis the axis on which the Astronomical Body should rotate
	 */
	private void rotate(char axis) {
		switch(axis) { // each axis needs to call the rotate() method with different parameters
		case 'x':
			// rotate only in the x-axis
			planetShape.rotate(rotationSpeed, 0, 0);
			if(planetRing != null) { // also rotate ring, where applicable
				planetRing.rotate(rotationSpeed, 0, 0);
			}
			break;
		case 'y':
			// rotate only in the y-axis
			planetShape.rotate(0, rotationSpeed, 0);
			if(planetRing != null) { // also rotate ring, where applicable
				planetRing.rotate(0, rotationSpeed, 0);
			}
			break;
		case 'z':
			// rotate only in the z-axis
			planetShape.rotate(0, 0, rotationSpeed);
			if(planetRing != null) { // also rotate ring, where applicable
				planetRing.rotate(0, 0, rotationSpeed);
			}
			break;
		}
	}
	
	/**
	 * Rotates the Astronomical Body about its axis
	 */
	public void rotate() {
		rotate(rotationAxis);
	}
	
	/**
	 * Adds a ring to the planet. The ring is currently
	 * represented as a disk, cutting through the planet.
	 * 
	 * @param radius the radius of the ring
	 * @param height the height of the ring
	 */
	public void addRing(double radius, double height) {
		this.planetRing = StdDraw3D.tube(0, -(height / 2), 0, 0, (height / 2), 0, radius);
	}
	
	/**
	 * Retrieves the Astronomical Body's Shape object so
	 * it's position and orientation can be accessed
	 * 
	 * @return the Shape representing the Astronomical Body in the scene
	 */
	public StdDraw3D.Shape getPlanetShape() {
		return planetShape;
	}
	
	/**
	 * Allows the  axis on which the Astronomical
	 * Body rotates to be altered
	 * 
	 * @param axis the new axis for the Astronomical Body to rotate on
	 */
	public void setRotationAxis(char axis) {
		
		axis = Character.toLowerCase(axis); // ensure the character entered is formatted in lowercase
		
		if(axis == 'x' || axis == 'y' || axis == 'z') { // ensure a valid axis char is entered
			rotationAxis = axis; // update rotation axis
		}
	}
	
	/**
	 * Allows an Astronomical Body's rotation to be set to
	 * clockwise. Anti-clockwise by default.
	 * 
	 * @param clockwise true if the Astronomical Body should rotate clockwise, false otherwise
	 */
	public void setRotationClockwise(boolean clockwise) {
		// clockwise rotation requires speed to be negative, anti-clockwise should be positive
		rotationSpeed = clockwise ? - Math.abs(rotationSpeed) : Math.abs(rotationSpeed); // abs ensures the value isn't simply toggled
	}
	
	/**
	 * Allows the speed of both orbit and rotation to be adjusted
	 * by a given factor. Values > 1 decrease the speed from the one initialised,
	 * Values < 1 increase the speed from the one initialised
	 * 
	 * @param speed the value to alter the speed by
	 */
	public void setSpeed(double speed) {
		// based on original values to prevent rounding errors
		rotationSpeed = originalRotationSpeed / speed;
		// ignore infinity values (such as the orbit of the sun, which does not orbit in the scene)
		orbitAngleIncrement = orbitAngleIncrement == Double.POSITIVE_INFINITY ? Double.POSITIVE_INFINITY : originalOrbitAngleIncrement / speed;
	}
	
}
