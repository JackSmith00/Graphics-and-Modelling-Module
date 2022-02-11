/**
 * Class to address all exercises for Portfolio Task 1
 * 
 * last updated: 1/2/22
 * 
 * @author Jack Smith - 24265241
 *
 */
public class PortfolioTask1 {

	public static void main(String[] args) {
		exercise2(); // can be replaced with `exercise2` or `exercise3` to see the corresponding output
	}
    
	/**
	 * Demonstrates different lines that can be drawn using StdDraw.
	 * This includes a line, rectangle, ellipse, circle and arc.
	 */
    public static void exercise1() {
    	
    	// shapes
        StdDraw.line(0.1, 0.85, 0.5, 0.95); // draw a line in towards the top left of the frame
        StdDraw.rectangle(0.3, 0.75, 0.2, 0.05); // draw a rectangle under the line in the top left
        StdDraw.ellipse(0.3, 0.55, 0.2, 0.1); // draw an ellipse under the rectangle
        StdDraw.circle(0.3, 0.3, 0.1); // draw a circle under the ellipse
        StdDraw.arc(0.3, -0.05, 0.2, 40, 140); // draw an arch from the bottom of the pane
        
        // labels
        StdDraw.text(0.61, 0.9, "line"); // label for line
        StdDraw.text(0.65, 0.75, "rectangle"); // label for rectangle
        StdDraw.text(0.63, 0.55, "ellipse"); // label for ellipse
        StdDraw.text(0.62, 0.3, "circle"); // label for circle
        StdDraw.text(0.61, 0.1, "arc"); // label for arc
    }
    
    /**
     * Spells "HELLO WORLD" using StdDraw
     */
    public static void exercise2() {
    	
    	double topOfLine = 0.8; // top coord of first line
    	
    	drawH(0.15, topOfLine);
    	drawE(0.3, topOfLine);
        drawL(0.45, topOfLine);
        drawL(0.6, topOfLine);
        drawO(0.8, topOfLine - 0.1);
        
        topOfLine = 0.5; // top coord of second line
        
        drawW(0.1, topOfLine);
        drawO(0.4, topOfLine - 0.1);
        drawR(0.5, topOfLine);
        drawL(0.65, topOfLine);
        drawD(0.8, topOfLine);
        
    }
    
    /**
     * Draws a 2D picture involving a couple of cars on a road
     */
    public static void exercise3() {
    	
    	// cars
    	draw3DPerspectiveCar(0.1, 0.8); // car 1
    	draw3DPerspectiveCar(0.2, 0.425); // car 2
    	
    	// road
    	StdDraw.line(0, 0.1, 1, 0.1); // bottom road
    	StdDraw.line(0, 0.65, 0.09, 0.65); // top road pt1
    	StdDraw.line(0.815, 0.65, 1, 0.65); // top road pt2
    	horizontalLine(0.05, 0.45); // road dash 1
    	horizontalLine(0.25, 0.45); // road dash 2
    	horizontalLine(0.45, 0.45); // road dash 3
    	horizontalLine(0.65, 0.45); // road dash 4
    	horizontalLine(0.85, 0.45); // road dash 5
    	
    	// sun
    	StdDraw.circle(0.15, 0.9, 0.08);
    	// sun rays
    	StdDraw.line(0.24, 0.95, 0.26, 0.964);
    	StdDraw.line(0.25, 0.9, 0.28, 0.9);
    	StdDraw.line(0.24, 0.85, 0.26, 0.836);
    	StdDraw.line(0.2, 0.81, 0.215, 0.79);
    	StdDraw.line(0.15, 0.8, 0.15, 0.77);
    	StdDraw.line(0.1, 0.81, 0.085, 0.79);
    	StdDraw.line(0.06, 0.85, 0.04, 0.836);
    	StdDraw.line(0.05, 0.9, 0.02, 0.9);
    	StdDraw.line(0.06, 0.95, 0.04, 0.964);
    }
    
    /**
     * Used to draw a vertical line with a standard height of 0.2
     * @param x the x-coord to draw the line on
     * @param y the top (highest) y-coord to draw the line from
     */
    private static void verticalLine(double x, double y) {
    	StdDraw.line(x, y, x, y - 0.2); // x-coord remains equal, y-coord changes by -0.2
    }
    
    /**
     * Used to draw a horizontal line with a standard length of 0.1
     * @param x the starting x-coord (left-most) to draw the line from
     * @param y the y-coord to draw the line on
     */
    private static void horizontalLine(double x, double y) {
    	StdDraw.line(x, y, x + 0.1, y); // y-coord remains equal, x-coord changes by +0.1
    }
    
    /**
     * Used to draw a 2D 'H' shape
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void drawH(double x, double y) {
    	verticalLine(x, y);
    	horizontalLine(x, y - 0.1);
    	verticalLine(x + 0.1, y);
    }
    
    /**
     * Used to draw a 2D 'E' shape
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void drawE(double x, double y) {
    	drawL(x, y); // draw 'L' shape as starting point of an 'E'
    	horizontalLine(x, y); // top horizontal line
    	horizontalLine(x, y - 0.1); // middle horizontal line
    }
    
    /**
     * Used to draw a 2D 'L' shape
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void drawL(double x, double y) {
    	verticalLine(x, y); // vertical line of 'L'
    	horizontalLine(x, y - 0.2); // horizontal line of the 'L', staring at the base of the letter (y - 0.2)
    }
    
    /**
     * Used to draw a 2D 'O' shape
     * @param x the centre x-coord to draw from
     * @param y the centre y-coord to draw from
     */
    private static void drawO(double x, double y) {
    	StdDraw.ellipse(x, y, 0.075, 0.1); // has the standard size for all 'O's
    }
    
    /**
     * Used to draw a 2D 'W' shape
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void drawW(double x, double y) {
    	StdDraw.line(x, y, x + 0.05, y - 0.2); // first (left-most) line of 'W'
        StdDraw.line(x + 0.1, y - 0.1, x + 0.05, y - 0.2); // second line - same end point as previous
        StdDraw.line(x + 0.1, y - 0.1, x + 0.15, y - 0.2); // third line - same start point as previous
        StdDraw.line(x + 0.2, y, x + 0.15, y - 0.2); // final line - same end point as previous
    }
    
    /**
     * Used to draw a 2D 'R' shape
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void drawR(double x, double y) {
    	verticalLine(x, y);
    	StdDraw.line(x, y, x + 0.05, y); // top horizontal line of 'R'
    	StdDraw.line(x, y - 0.1, x + 0.05, y - 0.1); // middle line of 'R'
    	StdDraw.arc(x + 0.05, y - 0.05, 0.05, -90, 90); // curve of the 'R'
    	StdDraw.line(x + 0.05, y - 0.1, x + 0.1, y - 0.2); // diagonal line of the 'R'
    }
    
    /**
     * Used to draw a 2D 'D' shape
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void drawD(double x, double y) {
    	verticalLine(x, y); // left vertical line of 'D'
    	StdDraw.line(x, y, x + 0.05, y); // top horizontal line of 'D'
    	StdDraw.arc(x + 0.05, y - 0.05, 0.05, 0, 90); // top right curve in 'D'
    	StdDraw.line(x + 0.1, y - 0.05, x + 0.1, y - 0.15); // right vertical line of 'D'
    	StdDraw.line(x, y - 0.2, x + 0.05, y - 0.2); // bottom horizontal line of 'D'
    	StdDraw.arc(x + 0.05, y - 0.15, 0.05, -90, 0); // bottom right curve in 'D'
    }
    
    /**
     * Used to draw a 2D car shape
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void draw2DCar(double x, double y) {
    	
    	// bonnet drawn clockwise
    	StdDraw.line(x, y - 0.1, x, y - 0.2);
    	StdDraw.line(x, y - 0.1, x + 0.15, y - 0.1);
    	
    	// windshield
    	StdDraw.line(x + 0.25, y, x + 0.15, y - 0.1);
    	
    	// roof and boot
    	StdDraw.line(x + 0.53, y, x + 0.25, y);
    	StdDraw.arc(x + 0.53, y - 0.15, 0.15, 19, 90);
    	StdDraw.line(x + 0.672, y - 0.1, x + 0.7, y - 0.2);
    	
    	// bottom
    	StdDraw.line(x, y - 0.2, x + 0.7, y - 0.2);
    	
    	// wheels
    	StdDraw.arc(x + 0.15, y - 0.2, 0.05, 180, 0); // front outer
    	StdDraw.arc(x + 0.15, y - 0.2, 0.01, 180, 0); // front inner
    	StdDraw.arc(x + 0.55, y - 0.2, 0.05, 180, 0); // back outer
    	StdDraw.arc(x + 0.55, y - 0.2, 0.01, 180, 0); // back inner
    	
    	// door
    	StdDraw.line(x + 0.27, y, x + 0.27, y - 0.19); // left vertical for door 1
    	StdDraw.line(x + 0.4, y, x + 0.4, y - 0.14); // right vertical for door 1
    	StdDraw.arc(x + 0.35, y - 0.14, 0.05, -90, 0); // curve for door 1
    	StdDraw.line(x + 0.35, y - 0.19, x + 0.27, y - 0.19); // horizontal for door 1
    	StdDraw.rectangle(x + 0.335, y - 0.055, 0.05, 0.045); // window for door 1
    	StdDraw.line(x + 0.38, y - 0.115, x + 0.365, y - 0.115); // handle for door 1
    	
    	StdDraw.line(x + 0.42, y, x + 0.42, y - 0.19); // left vertical for door 1
    	StdDraw.line(x + 0.55, y - 0.005, x + 0.55, y - 0.14); // right vertical for door 2
    	StdDraw.arc(x + 0.5, y - 0.14, 0.05, -90, 0); // curve for door 2
    	StdDraw.line(x + 0.5, y - 0.19, x + 0.42, y - 0.19); // horizontal for door 2
    	StdDraw.rectangle(x + 0.485, y - 0.055, 0.05, 0.045); // window for door 2
    	StdDraw.line(x + 0.53, y - 0.115, x + 0.515, y - 0.115); // handle for door 2

    	// front window
    	StdDraw.line(x + 0.15, y - 0.1, x + 0.25, y - 0.1);
    	StdDraw.line(x + 0.25, y, x + 0.25, y - 0.1);

    	// rear window
    	StdDraw.line(x + 0.6, y - 0.1, x + 0.67, y - 0.1);
    	StdDraw.line(x + 0.6, y - 0.02, x + 0.6, y - 0.1);
    }
    
    /**
     * Used to draw a 3D perspective car shape in 2D
     * @param x the left-most x-coord to draw from
     * @param y the top (highest) y-coord to draw from
     */
    private static void draw3DPerspectiveCar(double x, double y) {
    	
    	// front view of car
    	draw2DCar(x, y - 0.05);
    	
    	// far view of car
    	x += 0.02;
    	// bonnet drawn clockwise
    	StdDraw.line(x, y - 0.1, x + 0.15, y - 0.1);
    	
    	// windshield
    	StdDraw.line(x + 0.25, y, x + 0.15, y - 0.1);
    	
    	// roof and boot
    	StdDraw.line(x + 0.53, y, x + 0.25, y);
    	StdDraw.arc(x + 0.53, y - 0.15, 0.15, 19, 90);
    	StdDraw.line(x + 0.672, y - 0.1, x + 0.7, y - 0.2);
    	
    	// wheels
    	StdDraw.arc(x + 0.14, y - 0.24, 0.05, -40, -15); // front wheel
    	StdDraw.arc(x + 0.54, y - 0.24, 0.05, -40, -15); // back wheel

    	// linking lines
    	StdDraw.line(x, y - 0.1, x - 0.02, y - 0.15); // front of bonnet
    	StdDraw.line(x + 0.15, y - 0.1, x + 0.13, y - 0.15); // bottom of windshield
    	StdDraw.line(x + 0.25, y, x + 0.23, y - 0.05); // top of windshield
    	StdDraw.line(x + 0.67, y - 0.1, x + 0.65, y - 0.15); // bottom of rear window
    	StdDraw.line(x + 0.6, y - 0.02, x + 0.58, y - 0.07); // top of rear window
    	StdDraw.line(x + 0.7, y - 0.2, x + 0.68, y - 0.25); // back bumper
    }
	
}
