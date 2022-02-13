import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to address all exercises for Portfolio Task 3
 * 
 * last updated: 11/2/22
 * 
 * @author Jack Smith - 24265241
 *
 */
public class PortfolioTask3 {

	public static void main(String[] args) {
		exercise4();
	}
	
	/**
	 * Draws an n-by-n checkered grid on the canvas with
	 * red and black tiles (starting with red to the lower left)
	 * 
	 * @param n the number of tiles in the grid
	 * @param gridSize the size of the grid on the canvas
	 * @param x0 the x-coordinate of the bottom left corner of the grid
	 * @param y0 the y-coordinate of the bottom left corner of the grid
	 */
	private static void redBlackGrid(int n, float gridSize, float x0, float y0) {
		
		float sideLength = gridSize / n; // how long the side of each tile should be
		float halfLength = sideLength / 2; // half size of each tile, used for sizing and positioning
		
		for(int column = 0; column < n; column++) { // loop every n number of columns
			for(int row = 0; row < n; row++) { // loop every n number of rows
				
				// set alternating colour
				if((row + column) % 2 == 0) { // where row and column are both even
					// set colour to red
					StdDraw.setPenColor(StdDraw.RED);
				} else {
					// set colour to black
					StdDraw.setPenColor(StdDraw.BLACK);
				}
				
				/*
				 * Below are the coordinates used to position each tile.
				 * 
				 * They are positioned from the start point (x0, y0) with
				 * the length of each square added for each column/row.
				 * 
				 * The half length is also added as the `filledSquare()`
				 * method requires the centre of the drawn square and not
				 * the bottom-left point
				 */
				float x = (x0 + column * sideLength) + halfLength;
				float y = (y0 + row * sideLength) + halfLength;
				
				StdDraw.filledSquare(x, y, halfLength); // draw the current square
			}
		}
	}
	
	/**
	 * Draws grids for exercise 1
	 */
	public static void exercise1() {
		// Set up canvas
		StdDraw.setCanvasSize(1000, 400);
		StdDraw.setScale(-50, 450);
		StdDraw.setYscale(-40, 160);
		
		redBlackGrid(5, 100, 0, 0); // 5x5 grid
		redBlackGrid(8, 100, 150, 0); // 8x8 grid
		redBlackGrid(25, 100, 300, 0); //25x25 grid
		
		// label grids
		StdDraw.setPenColor(StdDraw.BLACK); // reset pen colour to black
		StdDraw.text(50, 110, "N = 5"); 
		StdDraw.text(200, 110, "N = 8");
		StdDraw.text(350, 110, "N = 25");
	}
	
	/**
	 * Draws a circle of n evenly spaced points
	 * 
	 * @param n number of points to draw on the circle
	 * @param radius radius of the overall circle
	 * @param dotRadius radius of each dot that make up the circle
	 * @param x0 centre x-coordinate of the circle
	 * @param y0 centre-y coordinate of the circle
	 */
	private static void dottedCircle(int n, float radius, float dotRadius, float x0, float y0) {
		
		double angleBetweenPoints = (2 * Math.PI) / n; // angle between any 2 consecutive points
		
		/*
		 * Below will be used in the algorithm, it is declared here
		 * to save on object creation overhead within the algorithm
		 */
		int x, y;
		
		for(int i = 1; i <= n; i++) { // loop through each dot to draw
			
			/*
			 * Calculate the x and y coordinates 
			 * of each point using cos and sin with
			 * the angle between each point
			 */
			x = Math.round(x0 + radius * (float) Math.cos(i * angleBetweenPoints));
			y = Math.round(y0 + radius * (float) Math.sin(i * angleBetweenPoints));
			
			StdDraw.filledCircle(x, y, dotRadius); // draw the current dot
		}
	}
	
	/**
	 * Draws the dotted circle specified for exercise 2
	 */
	public static void exercise2() {
		// Set up canvas
		StdDraw.setCanvasSize(700, 700);
		StdDraw.setScale(-50, 450);
		
		// draw dotted circle
		dottedCircle(16, 200, 10, 200, 200);
	}
	
	private static void joinedDottedCircle(int n, float p, float radius, float dotRadius, float x0, float y0) {
		
		double angleBetweenPoints = (2 * Math.PI) / n;
		
		int x;
		int y;
		int[] dotToJoinTo;
		Random randomNumberGenerator = new Random();
		
		
		ArrayList<int[]> dots = new ArrayList<int[]>(n);
		
		for(int i = 1; i <= n; i++) {
			
			x = Math.round(x0 + radius * (float) Math.cos(i * angleBetweenPoints));
			y = Math.round(y0 + radius * (float) Math.sin(i * angleBetweenPoints));
			
			StdDraw.filledCircle(x, y, dotRadius);
			
			for(int j = 0; j < dots.size(); j++) {
				
				dotToJoinTo = dots.get(j);
				
				if(randomNumberGenerator.nextFloat() <= p) {
					StdDraw.line(x, y, dotToJoinTo[0], dotToJoinTo[1]);
				}
			}
			
			dots.add(new int[] {x, y});
			
		}
		
	}
	
	public static void exercise3() {
		
		StdDraw.setCanvasSize(1200, 600);
		StdDraw.setScale(-50, 700);
		StdDraw.setXscale(-50, 1450);
		
		joinedDottedCircle(16, 0.25f, 200, 10, 200, 300);
		StdDraw.text(200, 550, "p = 0.25");
		
		joinedDottedCircle(16, 0.5f, 200, 10, 700, 300);
		StdDraw.text(700, 550, "p = 0.5");
		
		joinedDottedCircle(16, 1.0f, 200, 10, 1200, 300);
		StdDraw.text(1200, 550, "p = 1.0");
	}
	
	public static void exercise4() {
		
		StdDraw.setCanvasSize(700, 700);
		StdDraw.setScale(0, 500);
		
		StdDraw.union(new Rectangle2D.Double(100, 400, 100, 150), new Ellipse2D.Double(70, 430, 100, 100));
		StdDraw.text(150, 230, "Union");

		StdDraw.difference(new Rectangle2D.Double(130, 150, 100, 150), new Ellipse2D.Double(100, 180, 100, 100));
		StdDraw.text(150, 50, "Difference");
		
		StdDraw.intersection(new Rectangle2D.Double(315, 150, 100, 150), new Ellipse2D.Double(285, 180, 100, 100));
		StdDraw.text(350, 50, "Intersection");
		
		StdDraw.exclusiveOr(new Rectangle2D.Double(300, 400, 100, 150), new Ellipse2D.Double(270, 430, 100, 100));
		StdDraw.text(350, 230, "ExclusiveOr");
	}
	
}
