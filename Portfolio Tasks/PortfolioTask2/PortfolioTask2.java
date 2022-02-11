/**
 * Class to address all exercises for Portfolio Task 2
 * 
 * last updated: 9/2/22
 * 
 * @author Jack Smith - 24265241
 *
 */
public class PortfolioTask2 {

	public static void main(String[] args) {
		setUpCanvas();
		drawAxis();
	}
	
	/**
	 * Resizes the canvas to make the lines easier to see
	 * and changes the scale to allow the lines to appear
	 * inside of the canvas
	 */
	private static void setUpCanvas() {
		StdDraw.setCanvasSize(700, 700);
		StdDraw.setScale(-100, 600);
	}
	
	/**
	 * Draws axis lines and labels to help show placement in the canvas
	 */
	private static void drawAxis() {
		StdDraw.line(-100, 0, 600, 0); // x-axis
		StdDraw.line(0, -100, 0, 600); // y-axis
		
		// origin label
		StdDraw.textRight(-2, -12, "0");
		
		// x-axis labels
		StdDraw.text(100, -12, "100");
		StdDraw.text(200, -12, "200");
		StdDraw.text(300, -12, "300");
		StdDraw.text(400, -12, "400");
		StdDraw.text(500, -12, "500");
		
		// y-axis labels
		StdDraw.textRight(-2, 100, "100"); 
		StdDraw.textRight(-2, 200, "200"); 
		StdDraw.textRight(-2, 300, "300"); 
		StdDraw.textRight(-2, 400, "400");
		StdDraw.textRight(-2, 500, "500"); 
		
	}
	
	/**
	 * Draws a line on the canvas using the Brute Force Algorithm.
	 * Drawing points is done using the {@link StdDraw} class.
	 * 
	 * @param x1 Start x-coordinate to draw the line from
	 * @param x2 End x-coordinate to draw the line to
	 * @param m Gradient of the line
	 * @param c Constant value of the line, where the line should pass
	 * through the y-axis
	 */
	private static void bruteForceLine(double x1, double x2, double m, double c) {
		/*
		 * Below ensures that calculations start from the smallest
		 * value of x, allowing x1 to be input as a larger value
		 * that x2 and still work with the algorithm
		 */
		double startX = Math.min(x1, x2);
		double endX = Math.max(x1, x2);
		
		/*
		 * Below will be used in the algorithm, it is declared here
		 * to save on object creation overhead within the algorithm
		 */
		double y;
		
		// brute force algorithm
		for(double x = startX; x <= endX; x++) { // for every value of x in the line
			
			y = (m * x) + c; // equation of a straight line
			
			StdDraw.point(x, Math.round(y)); // draw point on the canvas
		}
	}
	
	/**
	 * Use the brute force line drawing algorithm to draw the lines
	 * with coordinates specified in the brief
	 * 
	 * x1 = 0, x2 = 500, c = 10 and m = 0, 0.5, 1, 2, 5, 10
	 */
	public static void exercise1() {
		
		bruteForceLine(0, 500, 0, 10); // line where m = 0
		bruteForceLine(0, 500, 0.5, 10); // line where m = 0.5
		bruteForceLine(0, 500, 1, 10); // line where m = 1
		bruteForceLine(0, 500, 2, 10); // line where m = 2
		bruteForceLine(0, 500, 5, 10); // line where m = 5
		bruteForceLine(0, 500, 10, 10); // line where m = 10
		
	}
	
	/**
	 * Draws a line on the canvas using the Incremental Line Drawing Algorithm.
	 * Drawing points is done using the {@link StdDraw} class.
	 * 
	 * @param x1 Start x-coordinate to draw the line from
	 * @param x2 End x-coordinate to draw the line to
	 * @param m Gradient of the line
	 * @param c Constant value of the line, where the line should pass
	 * through the y-axis
	 */
	private static void incrementalLine(double x1, double x2, double m, double c) {
		/*
		 * Below ensures that calculations start from the smallest
		 * value of x, allowing x1 to be input as a larger value
		 * that x2 and still work with the algorithm
		 */
		double startX = Math.min(x1, x2);
		double endX = Math.max(x1, x2);
		
		double y = (startX * m) + c; // calculate the initial value of y, where x has its smallest value
		
		for(double x = startX; x <= endX; x++) { // loop through all x-coordinates of the line
			
			StdDraw.point(x, Math.round(y)); // plot the current point
			y += m; // increment y by m, ready for the next iteration
		}
	}
	
	/**
	 * Use the incremental line drawing algorithm to draw the lines
	 * with coordinates specified in the brief
	 * 
	 * x1 = 0, x2 = 500, c = 10 and m = 0, 0.5, 1
	 */
	public static void exercise3() {

		incrementalLine(0, 500, 0, 10);
		incrementalLine(0, 500, 0.5, 10);
		incrementalLine(0, 500, 1, 10);
		
	}
	
	/**
	 * Draws a line on the canvas from a given start coordinate
	 * to a given end coordinate
	 * 
	 * @param x1 Start x-coordinate of the line
	 * @param y1 Start y-coordinate of the line
	 * @param x2 End x-coordinate of the line
	 * @param y2 End y-coordinate of the line
	 */
	private static void lineCoordinates(double x1, double y1, double x2, double y2) {
		
		double dy = y2 - y1; // calculate the vertical distance of the line
		double dx = x2 - x1; // calculate the horizontal distance of the line
		
		double m = dy / dx; // calculate m using the formula m = Dx / Dy
		
		/*
		 * calculate the value of c by rearranging the formula of a straight line:
		 * y = mx + c
		 * => c = y - mx
		 * 
		 * use a known point on the line, i.e. the start point (x1, y1)
		 */
		double c = y1 - (m * x1);
		
		/*
		 * draw the line using the incremental algorithm
		 * as this is more efficient than the brute force
		 * method
		 */
		incrementalLine(x1, x2, m, c);
	}
	
	/**
	 * Use the coordinate based algorithm to draw lines with
	 * the following start/end points:
	 * 
	 * (1) (10, 10) -> (40, 30)
	 * (2) (10, 10) -> (40, 90)
	 */
	public static void exercise4() {
		lineCoordinates(10, 10, 40, 30); // draw line (1)
		lineCoordinates(10, 10, 40, 90); // draw line (2)
	}
	
	/**
	 * Draws a line on the canvas from a given start coordinate
	 * to a given end coordinate using Bresenham's Midpoint algorithm.
	 * <br><br>
	 * Researched at the following resources:<br>
	 * <a href = "https://medium.com/geekculture/bresenhams-line-drawing-algorithm-2e0e953901b3">https://medium.com/geekculture/bresenhams-line-drawing-algorithm-2e0e953901b3</a><br>
	 * <a href = "https://iq.opengenus.org/bresenham-line-drawining-algorithm/">https://iq.opengenus.org/bresenham-line-drawining-algorithm/</a>
	 * 
	 * @param x1 Start x-coordinate of the line
	 * @param y1 Start y-coordinate of the line
	 * @param x2 End x-coordinate of the line
	 * @param y2 End y-coordinate of the line
	 */
	private static void bresenhamsMidpointLine(double x1, double y1, double x2, double y2) {
		/*
		 * To work for values of m > 1, the algorithm needs to plot with respect
		 * to the x-axis, therefore using the midpoint of the x value, rather
		 * than y, so this method handles both cases
		 */
		
		double dy = y2 - y1; // calculate the vertical distance of the line
		double dx = x2 - x1; // calculate the horizontal distance of the line
		
		double d1 = 2 * dy - dx; // decision variable with respect to the y axis -> positive above the midpoint, negative below
		double d2 = 2 * dx - dy; // decision variable with respect to the x axis -> positive above the midpoint, negative below
		
		double yde = 2 * dy; // amount to change d when moving east with respect to y axis
		double ydne = 2 * (dy - dx); // amount to change d when moving north-east with respect to y axis
		
		double xde = 2 * dx; // amount to change d when moving east with respect to x axis
		double xdne = 2 * (dx - dy);// amount to change d when moving north-east with respect to x axis
		
		double x = x1; // hold the current x position
		double y = y1; // hold the current y position
		
		double m = dy / dx; // the gradient of the line, determines if line is plot against the x or y axis
		
		StdDraw.point(x, y); // draw initial point

		if(m < 1) { // where m < 1, plot with respect to the y axis
			x++; // move to the next x value, moving east
			while(x <= x2) { // only draw up to the end x value
				if(d1 < 0) { // where the actual line is below the midpoint
					d1 += yde; // moves east, update d
				} else { // where the actual line is above the midpoint
					d1 += ydne; // moves north-east, update d
					y++; // update the value of y as moving north
				}
				StdDraw.point(x, y); // draw new point
				x++; // move east ready for the next point
			}
		}
		/*
		 * The following part of the algorithm is adapted from
		 * pseudo code, found at:
		 * 
		 * https://iq.opengenus.org/bresenham-line-drawining-algorithm/
		 */
		else { // where m >= 1, plot with respect to the x axis
			y++; // move to the next x value, moving east
			while(y <= y2) { // only draw up to the end y value
				if(d2 < 0) { // where the actual line is before the midpoint
					d2 += xde; // moves north, update d
				} else { // where the actual line is after the midpoint
					d2 += xdne; // moves north east, update d
					x++; // update the value of x as moving east
				}
				StdDraw.point(x, y); // draw new point
				y++; // move north ready for the next point
			}
		}
	}
	
	/**
	 * Use  Bresenham's Midpoint algorithm to draw lines with
	 * the following start/end points:
	 * 
	 * (1) (10, 10) -> (40, 30)
	 * (2) (10, 10) -> (40, 90)
	 */
	public static void exercise5() {
		bresenhamsMidpointLine(10, 10, 40, 30); // draw line (1)
		bresenhamsMidpointLine(10, 10, 40, 90); // draw line (2)
	}
	
	/**
	 * Draws a circle using the eight-way symmetry algorithm
	 * 
	 * @param x1 centre x-coordinate of the circle
	 * @param y1 centre y-coordinate of the circle
	 * @param radius The desired radius of the circle
	 */
	private static void eightWayCircle(double x1, double y1, double radius) {
    	
    	/*
    	 * Use Trigonometry to find the maximum value of x:
    	 * 
    	 * sohCAHtoa
    	 * 
    	 * cos(Ø) = A/H
    	 * 
    	 * cos(45) = maximumX / radius	-> (45° at max x value)
    	 * radius * cos(45) = maximumX
    	 */
    	double maximumX = radius * Math.cos(Math.toRadians(45)); // need to convert 45° to radians for the `cos()` method
    	
    	/*
		 * Below will be used in the algorithm loop, it is declared here
		 * to save on object creation overhead within the algorithm
		 */
    	double y;
    	
    	for(double x = 0; x <= maximumX; x++) { // loop every x value needed to draw the circle
    		
    		/*
    		 * rearrange the formula of a circle to find the positive y-coordinate:
    		 * 
    		 * r^2 = y^2 + x^2
    		 * y^2 = r^2 - x^2
    		 * y = √(r^2 - x^2)
    		 */
    		y = Math.round(Math.sqrt(Math.pow(radius, 2) - Math.pow(x, 2)));
    		
    		/* 
    		 * draw at each of the points of eight-way symmetry
    		 * need to add x1/y1 to the x/y coordinate so that
    		 * the circle is drawn from the given centre point
    		 */
    		StdDraw.point(x1 + x, y1 + y);
    		StdDraw.point(x1 + x, y1 - y);
    		StdDraw.point(x1 - x, y1 - y);
    		StdDraw.point(x1 - x, y1 + y);
    		StdDraw.point(x1 + y, y1 + x);
    		StdDraw.point(x1 + y, y1 - x);
    		StdDraw.point(x1 - y, y1 - x);
    		StdDraw.point(x1 - y, y1 + x);
    		
    	}
    }
    
	/**
	 * Demonstrates the eight-way symmetry algorithm created
	 */
    public static void exercise6() {
    	eightWayCircle(300, 200, 200); // draw a circle with radius 200 at (300, 200)
    }
    
	
	/**
	 * Draws a line on the canvas from a given start coordinate
	 * to a given end coordinate using Bresenham's Midpoint algorithm.
	 * <br><br>
	 * Assumes that the value for 'm' is: 0 <= m <= 1
	 * <br><br> 
	 * Researched at the following resource:<br>
	 * <a href = "https://medium.com/geekculture/bresenhams-line-drawing-algorithm-2e0e953901b3">https://medium.com/geekculture/bresenhams-line-drawing-algorithm-2e0e953901b3</a>
	 * 
	 * @param x1 Start x-coordinate of the line
	 * @param y1 Start y-coordinate of the line
	 * @param x2 End x-coordinate of the line
	 * @param y2 End y-coordinate of the line
	 */
	@SuppressWarnings("unused")
	private static void oldBresenhamsMidpointLine(double x1, double y1, double x2, double y2) {
		
		double dy = y2 - y1; // calculate the vertical distance of the line
		double dx = x2 - x1; // calculate the horizontal distance of the line
		
		double d = 2 * dy - dx; // decision variable -> positive above the midpoint, negative below
		
		double de = 2 * dy; // amount to change d when moving east
		double dne = 2 * (dy - dx); // amount to change d when moving north-east
		
		double x = x1; // hold the current x position
		double y = y1; // hold the current y position
		
		StdDraw.point(x, y); // plot the start point
		x++; // move to the next x value, moving east
		
		while(x <= x2) { // only draw up to the end x value
			if(d < 0) { // where the actual line is below the midpoint
				d += de; // moves east, update d
			} else { // where the actual line is above the midpoint
				d += dne; // moves north-east, update d
				y++; // update the value of y as moving north
			}
			StdDraw.point(x, y); // draw new point
			x++; // move east ready for the next point
		}
	}

}
