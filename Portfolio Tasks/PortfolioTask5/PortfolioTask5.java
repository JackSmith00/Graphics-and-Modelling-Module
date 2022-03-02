
/**
 * Class to address all exercises for Portfolio Task 5
 * 
 * last updated: 23/2/22
 * 
 * @author Jack Smith - 24265241
 *
 */
public class PortfolioTask5 {

	
	public static void main(String[] args) {
		
		exercise1();
		
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
	
	public static void exercise2() {
		// set the scale
		StdDraw3D.setScale(-10, 10);
		
		// sun
		StdDraw3D.Shape sun = StdDraw3D.wireSphere(0, 0, 0, 4);
		
		// planet
		StdDraw3D.Shape planet = StdDraw3D.sphere(7, 0, 0, 1);
		
		while(true) {
			// rotate the sun
			sun.rotate(6, 0, 0);
			
			// move the planet
			planet.move(1, 0, 0);
		}
		
	}
}
