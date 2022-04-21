
public class Week4 {

	public static void main(String[] args) {
		
		// set the scale of the coordinate system
		StdDraw.setScale(-1, 1);
		
		// initial values
		double rx = 0.48, ry = 0.86; 	// position
		double vx = 0.015, vy = 0.023; 	// velocity
		double radius = 0.05;			// radius
		
		// animation loop
		while(true) {
			
			// bounce off wall according to law of elastic collision
			if(Math.abs(rx + vx) > (1 - radius)){
				vx = -vx;
			}
			if(Math.abs(ry + vy) > (1 - radius)) {
				vy = -vy;
			}
			
			// update position
			rx += vx;
			ry += vy;
			
			// clear background
			StdDraw.clear(StdDraw.GRAY);
			
			// draw the ball on the screen
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(rx, ry, radius);
			
			// display and pause for 20ms
			StdDraw.show(20);
		}
	}
	
}
