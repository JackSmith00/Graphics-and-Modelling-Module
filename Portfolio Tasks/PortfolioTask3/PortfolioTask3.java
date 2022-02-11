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
		StdDraw.setCanvasSize(700, 700);
		StdDraw.setScale(-50, 450);
		exercise3();
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
				 */
				float x = (x0 + column * sideLength) + halfLength;
				float y = (y0 + row * sideLength) + halfLength;
				
				StdDraw.filledSquare(x, y, halfLength);
			}
		}
	}
	
	public static void exercise1() {
		redBlackGrid(8, 5, -2, 0);
	}
	
	private static void dottedCircle(int n, float radius, float dotRadius, float x0, float y0) {
		
		double angleBetweenPoints = (2 * Math.PI) / n;
		
		int x;
		int y;
		
		for(int i = 1; i <= n; i++) {
			
			x = Math.round(x0 + radius * (float) Math.cos(i * angleBetweenPoints));
			y = Math.round(y0 + radius * (float) Math.sin(i * angleBetweenPoints));
			
			StdDraw.filledCircle(x, y, dotRadius);
		}
	}
	
	public static void exercise2() {
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
		joinedDottedCircle(16, 0.5f, 200, 10, 200, 200);
	}
}
