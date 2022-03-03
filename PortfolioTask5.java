
/**
 * Class to address all exercises for Portfolio Task 5
 * 
 * last updated: 3/3/22
 * 
 * @author Jack Smith - 24265241
 *
 */
public class PortfolioTask5 {

	
	public static void main(String[] args) {
		exercise3(); // can be changed to display the relevant exercise
	}
	
	/**
	 * Method for displaying the solution to Exercise 1.
	 * Showcases the different 3D primitive shapes using
	 * StdDraw3D.
	 */
	public static void exercise1() {
		// set the scale
		StdDraw3D.setScale(-8, 8);
		
		// draw spheres
		StdDraw3D.sphere(-6, 4, 0, 1);
		StdDraw3D.wireSphere(-3, 4, 0, 1);
		
		// draw cube
		StdDraw3D.cube(-6, 0, 0, 1);
		StdDraw3D.wireCube(-3, 0, 0, 1);
		
		// draw box
		StdDraw3D.box(-6, -4, 0, 1, 2, 1);
		StdDraw3D.wireBox(-3, -4, 0, 1, 2, 1);
		
		// draw cylinder
		StdDraw3D.cylinder(3, 4, 0, 1, 2);
		StdDraw3D.wireCylinder(6, 4, 0, 1, 2);
		
		// draw cone
		StdDraw3D.cone(3, 0, 0, 1, 2);
		StdDraw3D.wireCone(6, 0, 0, 1, 2);
		
		// draw ellipsoid
		StdDraw3D.ellipsoid(3, -4, 0, 1, 2, 1);
		StdDraw3D.wireEllipsoid(6, -4, 0, 1, 2, 1);
		
		// finish
		StdDraw3D.finished();
	}
	
	/**
	 * Allows a shape to rotate around a given point
	 * 
	 * @param shape the shape that will rotate around the point
	 * @param origin the point for the shape to rotate around
	 * @param radius the radius of the rotation circle
	 * @param angle the angle to rotate by
	 * @param axis the axis to rotate around
	 */
	private static void rotateAroundPoint(StdDraw3D.Shape shape, StdDraw3D.Vector3D origin, double radius, double angle, char axis) {
		
		StdDraw3D.Vector3D newPosition = null; // intitalise new position vector
		
		switch(axis) { // switch each axis
		case 'x':
			newPosition = new StdDraw3D.Vector3D(origin.x, origin.y + radius * Math.cos(angle), origin.z + radius * Math.sin(angle));
			break;
		case 'y':
			newPosition = new StdDraw3D.Vector3D(origin.x + radius * Math.cos(angle), origin.y, origin.z + radius * Math.sin(angle));
			break;
		case 'z':
			newPosition = new StdDraw3D.Vector3D(origin.x + radius * Math.cos(angle), origin.y + radius * Math.sin(angle), origin.z);
			break;
		}
		
		if(newPosition != null) { // if no valid position, do not move
			shape.move(newPosition.minus(shape.getPosition())); // move by the difference between the new position and the current positon
		}
	}
	
	/**
	 * Method for displaying the solution to Exercise 2.
	 * Draws a rotating sun and 
	 */
	public static void exercise2() {
		// set the scale
		StdDraw3D.setScale(-10, 10);
		
		// sun
		StdDraw3D.setPenColor(StdDraw3D.ORANGE);
		StdDraw3D.Shape sun = StdDraw3D.wireSphere(0, 0, 0, 4);
		
		// planet
		StdDraw3D.setPenColor(StdDraw3D.BLUE);
		StdDraw3D.Shape planet = StdDraw3D.sphere(7, 0, 0, 1);
		
		double angle = 0; // planets current orbit around the sun
		double planetOrbitSpeed = 0.02;
		
		double sunRotationSpeed = 0.5;
		double planetRotationSpeed = 0.2;
		
		// animation loop
		while(true) {
			// rotate the sun
			sun.rotate(0, 0, -sunRotationSpeed); // negative for clockwise
			
			// move the planet
			rotateAroundPoint(planet, new StdDraw3D.Vector3D(0, 0, 0), 12, angle, 'z');
			angle += planetOrbitSpeed; // higher value => higher speed of orbit
			if(angle >= 2 * Math.PI) { // prevent angle continuously growing
				angle = 0;
			}
			
			// rotate the planet
			planet.rotate(0, 0, -planetRotationSpeed); // negative for clockwise
			
			// show the frame (~60fps)
			StdDraw3D.show(17);
		}
	}
	
	public static void exercise3() {
		// set the scale
		StdDraw3D.setScale(-10, 10);
		
		// sun
		StdDraw3D.setPenColor(StdDraw3D.ORANGE);
		StdDraw3D.Shape sun = StdDraw3D.wireSphere(0, 0, 0, 4);
		
		// planet
		StdDraw3D.setPenColor(StdDraw3D.BLUE);
		StdDraw3D.Shape planet = StdDraw3D.sphere(7, 0, 0, 1);
		
		double angle = 0; // planets current orbit around the sun
		double planetOrbitSpeed = 0.02;
		
		double sunRotationSpeed = 0.5;
		double planetRotationSpeed = 0.2;
		
		// animation loop
		while(true) {
			// rotate the sun
			sun.rotate(0, 0, -sunRotationSpeed); // negative for clockwise

			// move the planet
			rotateAroundPoint(planet, new StdDraw3D.Vector3D(0, 0, 0), 12, angle, 'z');
			
			if(StdDraw3D.mousePressed()) { // when mouse is pressed, invert the direction
				angle += planetOrbitSpeed;
			} else {
				angle -= planetOrbitSpeed;
			}
			
			if(angle >= 2 * Math.PI || angle <= -2 * Math.PI) { // prevent angle continuously growing
				angle = 0;
			}

			// rotate the planet
			planet.rotate(0, 0, -planetRotationSpeed); // negative for clockwise

			// show the frame (~60fps)
			StdDraw3D.show(17);
		}
	}
}
