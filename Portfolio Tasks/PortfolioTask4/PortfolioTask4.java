import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.util.Random;

/**
 * Class to address all exercises for Portfolio Task 4
 * 
 * last updated: 21/2/22
 * 
 * @author Jack Smith - 24265241
 *
 */
public class PortfolioTask4 {

	public static void main(String[] args) {
		exercise2aColorSwap();
	}
	
	/**
	 * Applies the law of elastic collision to two given balls
	 * with their initial velocity and mass given, and their
	 * resulting velocities from the collision returned in an
	 * array.
	 * 	
	 * @param initialVelocity1 the current velocity of the first ball
	 * @param mass1 the mass of the first ball
	 * @param initialVelocity2 the current velocity of the second ball
	 * @param mass2 the mass of the second ball
	 * @return an array of both resulting velocities, with the first ball's velocity first, i.e., {ball1Velocity, ball2Velocity}
	 */
	private static double[] ellasticCollision(double initialVelocity1, double mass1, double initialVelocity2, double mass2) {
		/*
		 * The below equations for the resulting velocities of
		 * the elastic collision were researched at the following
		 * resource:
		 * 
		 * Khan Academy, 2016. What are elastic and inelastic collisions? [online].
		 * Available from: 
		 * https://www.khanacademy.org/science/physics/linear-momentum/elastic-and-inelastic-collisions/a/what-are-elastic-and-inelastic-collisions
		 * [Accessed 18 February 2022].
		 * 
		 * The equations provided at this site have been adapted to work with
		 * Java code. No code was copied directly.
		 */
		double finalVelocity1 = ((mass1 - mass2) / (mass1 + mass2)) * initialVelocity1 + ((2 * mass2) / (mass1 + mass2)) * initialVelocity2;
		double finalVelocity2 = ((2 * mass1) / (mass1 + mass2)) * initialVelocity1 + ((mass2 - mass1) / (mass1 + mass2)) * initialVelocity2;
		
		// return the final velocities in an array
		return new double[] {finalVelocity1, finalVelocity2};
	}

	/**
	 * Solution to exercise 1. Draws 2 moving balls on the canvas
	 * using provided pseudocode and adds collisions between
	 * the two balls.
	 */
	public static void exercise1() {
		// set up canvas
		StdDraw.setScale(-1, 1);
		
		// ball 1 initial values
		double rx1 = 0.48, ry1 = 0.86; 		// position
		double vx1 = 0.015, vy1 = 0.023; 	// velocity
		double radius1 = 0.05;				// radius
		
		// ball 2 initial values
		double rx2 = 0.52, ry2 = 0.14; 		// position
		double vx2 = 0.023, vy2 = 0.015; 	// velocity
		double radius2 = 0.1;				// radius
		
		// animation loop
		while(true) {

			// bounce off wall according to law of elastic collision
			// ball 1
			if(Math.abs(rx1 + vx1) > (1 - radius1)){
				vx1 = -vx1;
			}
			if(Math.abs(ry1 + vy1) > (1 - radius1)) {
				vy1 = -vy1;
			}
			
			// ball 2
			if(Math.abs(rx2 + vx2) > (1 - radius2)){
				vx2 = -vx2;
			}
			if(Math.abs(ry2 + vy2) > (1 - radius2)) {
				vy2 = -vy2;
			}
			
			// ball collision
			if((Math.abs((rx1 + vx1) - (rx2 + vx2)) < radius1 + radius2) && (Math.abs((ry1 + vy1) - (ry2 + vy2)) < radius1 + radius2)) {
				// law of elastic collision on x velocities
				double[] resultingVelocity = ellasticCollision(vx1, (Math.PI * Math.pow(radius1, 2)), vx2, (Math.PI * Math.pow(radius2, 2)));
				
				// apply new x velocities
				vx1 = resultingVelocity[0];
				vx2 = resultingVelocity[1];
				
				// law of elastic collision on y velocities
				resultingVelocity = ellasticCollision(vy1, (Math.PI * Math.pow(radius1, 2)), vy2, (Math.PI * Math.pow(radius2, 2)));
				
				// apply new y velocities
				vy1 = resultingVelocity[0];
				vy2 = resultingVelocity[1];
			}
			
			
			// update position
			// ball 1
			rx1 += vx1;
			ry1 += vy1;
			
			// ball 2
			rx2 += vx2;
			ry2 += vy2;
			
			// clear background
			StdDraw.clear(StdDraw.GRAY);

			// draw the balls on the screen
			// ball 1
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(rx1, ry1, radius1);
			
			// ball 2
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledCircle(rx2, ry2, radius2);

			// display and pause for 20ms
			StdDraw.show(20);
		}
		
	}
	
	/**
	 * Generates a random ball within the specified parameters
	 * 
	 * @param minX the minimum x position of the ball
	 * @param maxX the maximum x position of the ball
	 * @param minY the minimum y position of the ball
	 * @param maxY the maximum y position of the ball
	 * @param minRadius the minimum radius of the ball
	 * @param maxRadius the maximum radius of the ball
	 * @param maxVelocity the maximum velocity of the ball in either direction
	 * @return a new Ball object within the specified parameters
	 */
	private static Ball randomBallGenerator(double minX, double maxX, double minY, double maxY, double minRadius, double maxRadius, double maxVelocity) {
		Random randomNumberGenerator = new Random(); // to generate random numbers
		
		// generate attributes of a ball within the given parameters
		double xPosition = (randomNumberGenerator.nextDouble() * (maxX - minX)) + minX;
		double yPosition = (randomNumberGenerator.nextDouble() * (maxY - minY)) + minY;
		double radius = (randomNumberGenerator.nextDouble() * (maxRadius - minRadius)) + minRadius;
		double xVelocity = randomNumberGenerator.nextDouble() * maxVelocity;
		double yVelocity = randomNumberGenerator.nextDouble() * maxVelocity;
		Color color = new Color(randomNumberGenerator.nextFloat(), randomNumberGenerator.nextFloat(), randomNumberGenerator.nextFloat());
		
		// return a ball with the generated values
		return new Ball(xPosition, yPosition, radius, xVelocity, yVelocity, color);
	}
	
	/**
	 * Generates n number of random balls, within specified parameters
	 * 
	 * @param n the number of balls to generate
	 * @param minX the minimum x position of all balls
	 * @param maxX the maximum x position of all balls
	 * @param minY the minimum y position of all balls
	 * @param maxY the maximum y position of all balls
	 * @param minRadius the minimum radius of all balls
	 * @param maxRadius the maximum radius of all balls
	 * @param maxVelocity the maximum velocity of the ball in either direction
	 * @return an array of all randomly generated balls
	 */
	private static Ball[] generateNBalls(int n, double minX, double maxX, double minY, double maxY, double minRadius, double maxRadius, double maxVelocity) {
		// array to hold all generated balls
		Ball[] output = new Ball[n];
		
		double avaliableAreaX = (maxX - minX) / n;
		double avaliableAreaY = (maxY - minY) / n;
		
		// generate n balls
		for(int i = 0; i < n; i++) {
			output[i] = randomBallGenerator(minX + (i * avaliableAreaX), minX + ((i + 1) * avaliableAreaX),
					/*
					 * The parameters above and below try to improve the span
					 * positions of each ball to reduce how often balls spawn
					 * inside of each other
					 */
					minY + (i * avaliableAreaY), minY  + ((i + 1) * avaliableAreaY),
					minRadius, maxRadius, maxVelocity);
		}
		
		// return an array of all balls generated
		return output;
	}

	/**
	 * First solution to exercise 2. Uses an Array of Structures approach
	 * with a Ball class to represent each ball in the canvas.
	 * Randomly generates 10 balls to display, with collision between all balls.
	 */
	public static void exercise2a() {
		// set up canvas
		StdDraw.setScale(-1, 1);
		
		// generate balls
		Ball[] balls = generateNBalls(10, -0.8, 0.8, -0.8, 0.8, 0.05, 0.2, 0.04);
				
		// animation loop
		while(true) {
			
			// clear background
			StdDraw.clear(StdDraw.GRAY);
			
			for(int i = 0; i < balls.length; i++) { // loop every ball
				// bounce off wall according to law of elastic collision
				if(Math.abs(balls[i].nextPosition()[0]) > (1 - balls[i].getRadius())) { // vertical wall
					balls[i].changeXDirection();
				}
				if(Math.abs(balls[i].nextPosition()[1]) > (1 - balls[i].getRadius())) { // horizontal wall
					balls[i].changeYDirection();
				}
				
				// check for collision with all other balls
				for(int j = i + 1; j < balls.length; j++) {
					/*
					 * This loop goes through every following ball in
					 * the array, this prevents balls being checked
					 * twice if all the loop started at j = 0 every time
					 */
					if(balls[i].hasCollidedWith(balls[j])) {
						// where there is a collision, update both balls
						Ball.ellasticCollision(balls[i], balls[j]);
					}
				}
				
				balls[i].move(); // move the ball
				
				// draw the ball
				StdDraw.setPenColor(balls[i].getColor());
				StdDraw.filledCircle(balls[i].getxPosition(), balls[i].getyPosition(), balls[i].getRadius());
			}
			
			// display and pause for 20ms
			StdDraw.show(20);
			
		}
	}
	
	/**
	 * An extra solution for exercise 2. Works in the same way as
	 * exercise2a, but any collision causes the balls to swap
	 * colours. 3 balls are generated to showcase this.
	 */
	public static void exercise2aColorSwap() {
		// set up canvas
		StdDraw.setScale(-1, 1);
		
		// generate balls
		Ball[] balls = generateNBalls(3, -0.8, 0.8, -0.8, 0.8, 0.05, 0.2, 0.04);
				
		// animation loop
		while(true) {
			
			// clear background
			StdDraw.clear(StdDraw.GRAY);
			
			for(int i = 0; i < balls.length; i++) { // loop every ball
				// bounce off wall according to law of elastic collision
				if(Math.abs(balls[i].nextPosition()[0]) > (1 - balls[i].getRadius())) { // vertical wall
					balls[i].changeXDirection();
				}
				if(Math.abs(balls[i].nextPosition()[1]) > (1 - balls[i].getRadius())) { // horizontal wall
					balls[i].changeYDirection();
				}
				
				// check for collision with all other balls
				for(int j = i + 1; j < balls.length; j++) {
					/*
					 * This loop goes through every following ball in
					 * the array, this prevents balls being checked
					 * twice if all the loop started at j = 0 every time
					 */
					if(balls[i].hasCollidedWith(balls[j])) {
						// where there is a collision, update both balls
						Ball.ellasticCollision(balls[i], balls[j]);
						
						// swap colours
						Color temp = balls[i].getColor();
						balls[i].setColor(balls[j].getColor());
						balls[j].setColor(temp);
					}
				}
				
				balls[i].move(); // move the ball
				
				// draw the ball
				StdDraw.setPenColor(balls[i].getColor());
				StdDraw.filledCircle(balls[i].getxPosition(), balls[i].getyPosition(), balls[i].getRadius());
			}
			
			// display and pause for 20ms
			StdDraw.show(20);
			
		}
	}	
	
	/**
	 * Second solution to exercise 2. Uses a Structure of Arrays
	 * approach, where all attributes of the balls are stored across
	 * several arrays. Randomly generates 10 balls to display,
	 * with collision between all balls.
	 */
	public static void exercise2b() {
		// set up canvas
		StdDraw.setScale(-1, 1);

		int n = 10; // number of balls to generate

		// Structure of arrays for the balls
		double[][] positions = new double[n][2];
		double[][] velocities = new double[n][2];
		double[] radii = new double[n];
		double[] masses = new double[n];
		Color[] colors = new Color[n];
		
		// generate balls
		Random randomNumberGenerator = new Random(); // to generate random numbers in the loop
		
		for(int i = 0; i < n; i++) {
			// position
			positions[i] = new double[] {(randomNumberGenerator.nextDouble() * 1.6) - 0.8, // random x position
					(randomNumberGenerator.nextDouble() * 1.6) - 0.8}; // random y position
			
			// velocity
			velocities[i] = new double[] {randomNumberGenerator.nextDouble() * 0.04, // random x velocity
					randomNumberGenerator.nextDouble() * 0.04}; // random y velocity
			
			// radius
			radii[i] = (randomNumberGenerator.nextDouble() * (0.2 - 0.05)) + 0.05;
			
			// mass
			masses[i] = Math.PI * Math.pow(radii[i], 2); // generate mass from the area of the ball
			
			// color
			colors[i] = new Color(randomNumberGenerator.nextFloat(), randomNumberGenerator.nextFloat(), randomNumberGenerator.nextFloat());
		}
		
		// animation loop
		while(true) {

			// clear background
			StdDraw.clear(StdDraw.GRAY);

			for(int i = 0; i < n; i++) { // loop every ball
				// bounce off wall according to law of elastic collision
				if(Math.abs(positions[i][0]) > (1 - radii[i])) { // vertical wall
					velocities[i][0] = - velocities[i][0];
				}
				if(Math.abs(positions[i][1]) > (1 - radii[i])) { // horizontal wall
					velocities[i][1] = - velocities[i][1];
				}

				// check for collision with all other balls
				for(int j = i + 1; j < n; j++) {
					/*
					 * This loop goes through every following ball in
					 * the array, this prevents balls being checked
					 * twice if all the loop started at j = 0 every time
					 */
					if((Math.abs((positions[i][0] + velocities[i][0]) - (positions[j][0] + velocities[j][0])) < radii[i] + radii[j])
							&& (Math.abs((positions[i][1] + velocities[i][1]) - (positions[j][1] + velocities[j][1])) < radii[i] + radii[j])) {
						
						// law of elastic collision on x velocities
						double[] resultingVelocity = ellasticCollision(velocities[i][0], masses[i], velocities[j][0], masses[j]);
						
						// apply new x velocities
						velocities[i][0] = resultingVelocity[0];
						velocities[j][0] = resultingVelocity[1];
						
						// law of elastic collision on y velocities
						resultingVelocity = ellasticCollision(velocities[i][1], masses[i], velocities[j][1], masses[j]);
					
						// apply new y velocities
						velocities[i][1] = resultingVelocity[0];
						velocities[j][1] = resultingVelocity[1];
					}
				}

				// update position
				positions[i][0] += velocities[i][0];
				positions[i][1] += velocities[i][1];

				// draw the ball
				StdDraw.setPenColor(colors[i]);
				StdDraw.filledCircle(positions[i][0], positions[i][1], radii[i]);
			}

			// display and pause for 20ms
			StdDraw.show(20);
		}
	}

	/**
	 * This method animates a phrase on the circumference
	 * of a revolving circle. The direction and position
	 * can be specified. Drawn using StdDraw.
	 * 
	 * @param phrase the phrase to display on the circumference of the circle
	 * @param radius the radius of the phrase circle
	 * @param x0 the centre x coordinate of the circle
	 * @param y0 the centre y coordinate of the circle
	 * @param clockwise a boolean stating whether the circle should rotate clockwise,
	 * if not then rotation will be anti–clockwise
	 */
	private static void circlePhrase(String phrase, float radius, float x0, float y0, boolean clockwise) {
		
		// add space to end of phrase if there isn't one already
		if(phrase.charAt(phrase.length() - 1) != ' ') {
			phrase += " ";
		}
		// convert phrase to a char array
		char[] charactersInPhrase = phrase.toCharArray();

		// angle between any letter in the circle
		double angleBetweenLetters = (2 * Math.PI) / charactersInPhrase.length;

		double x, y;
		
		double rotation = 0;
		int steps = 600; // circle split into 600 steps
		/*
		 * rotationStep will hold the amount to increase the rotation
		 * with each iteration. Will be positive for clockwise and 
		 * negative for anti-clockwise
		 */
		double rotationStep = clockwise ? (2 * Math.PI) / steps : -(2 * Math.PI / steps);

		// animation loop
		while(true) {
			// clear canvas
			StdDraw.clear();
			
			// loop each letter and draw on the circle
			for(int i = 0; i < charactersInPhrase.length; i++) {

				x = x0 + radius * Math.sin(i * angleBetweenLetters + rotation);
				y = y0 + radius * Math.cos(i * angleBetweenLetters + rotation);

				// draw letter on circle
				StdDraw.text(x, y, String.valueOf(charactersInPhrase[i]));
			}
			
			// update rotation
			rotation += rotationStep;
			if(rotation == 2 * Math.PI || rotation == - 2 * Math.PI) {
				rotation = 0;
			}
			
			StdDraw.show(20);
		}
	}

	/**
	 * Shows solution to exercise 3. Simply sets up the canvas
	 * and calls the circlePhrase method where the animation
	 * is handled.
	 */
	public static void exercise3() {
		// set up frame
		StdDraw.setScale(-1.4, 1.4);
		
		// display phrase
		circlePhrase("Computer Graphics", 1, 0, 0, true);
	}
	
	/**
	 * Draws the main components of the Countdown clock.
	 * This is the border, quarter lines and the dashes every
	 * 5 seconds on the clock face. This method does not draw the
	 * clock hand or background as they need to be created elsewhere for
	 * the animation.
	 * 
	 * @param radius the radius of the clock face
	 * @param x0 the centre x coordinate of the clock face 
	 * @param y0 the centre y coordinate of the clock face
	 */
	private static void countdownClockFace(float radius, float x0, float y0) {
		
		// countdown colour
		StdDraw.setPenColor(94, 102, 150);
		
		// outer circle
		StdDraw.setPenRadius(radius * 0.03);
		StdDraw.circle(x0, y0, radius);
		
		// quarter lines
		StdDraw.setPenRadius(radius * 0.006);
		StdDraw.line(x0, y0 + radius, x0, y0 - radius);
		StdDraw.line(x0 + radius, y0, x0 - radius, y0);
		
		// dash lines (coordinates have been found using sin and cos, similar to the circle phrase method above)
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(30)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(30)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(30)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(30)));
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(60)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(60)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(60)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(60)));
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(120)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(120)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(120)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(120)));
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(150)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(150)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(150)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(150)));
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(-30)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(-30)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(-30)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(-30)));
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(-60)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(-60)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(-60)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(-60)));
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(-120)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(-120)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(-120)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(-120)));
		StdDraw.line(x0 + (radius * 0.85) * Math.cos(Math.toRadians(-150)), y0 + (radius * 0.85) * Math.sin(Math.toRadians(-150)), x0 + (radius * 0.65) * Math.cos(Math.toRadians(-150)), y0 + (radius * 0.65) * Math.sin(Math.toRadians(-150)));
	}
	
	/**
	 * Solution to exercise 4. Draw the Coundown clock, styles closely to the
	 * original and animates the countdown from 0-30 seconds. Clock hand rotates
	 * smoothly at 60fps whilst the second progress light only updates once
	 * per second.
	 */
	public static void exercise4() {
		// set up frame
		StdDraw.setScale(-1.4, 1.4);
		
		// colours
		Color countdownBlue = new Color(94, 102, 150);
		Color clockHandOutlineColor = new Color(170, 160, 160);
		
		// initialise areas for the rotating clock hand
		Area clockHand = StdDraw.triangleArea(
				new double[] {-0.07, 0, 0.07},
				new double[] {0, 0.8, 0});
		
		Area clockHandOutline = StdDraw.triangleArea(
				new double[] {-0.1, 0, 0.1},
				new double[] {0, 0.8, 0});
		
		// set up rotation for use in the loop
		double clockHandRotation = Math.PI / 1800; // rotation amount for every frame
		double[] centerOfRotation = StdDraw.scaledCoordinate(0, 0); // where to rotate from in StdDraw
		
		AffineTransform rotation = new AffineTransform(); // object to perform the rotation
		rotation.rotate(clockHandRotation, centerOfRotation[0], centerOfRotation[1]); // give values of the rotation
		
		double secondProgress = 90; // initial angle of the seconds progress arc
		BasicStroke progressStroke = new BasicStroke(0.4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // stroke to be used to draw progress
		
		// animation loop
		for(int frame = 0; frame < 1800; frame++) { // 60fps for 30secs -> 1,800 frames
			
			// clear frame
			StdDraw.clear();
			
			// draw first frame
			// clock background
			StdDraw.setPenColor(210, 200, 200);
			StdDraw.filledCircle(0, 0, 1);
			
			// progress lights
			// update progress
			if(frame % 60 == 0 && frame != 0 || frame == 1799) {
				/*
				 * frame 1799 is included to make sure the final
				 * segment is draw, it is only 1 frame early so
				 * the difference will be minimal
				 */
				secondProgress -= 6; // area of 1 second on the clock (180° / 30)
			}
			// draw progress lights
			StdDraw.setStroke(progressStroke); // change to the custom stroke
			StdDraw.setPenColor(245, 230, 190); // colour of the progress light
			StdDraw.arc(0, 0, 0.7, secondProgress, 90); // draw the progress
			
			
			// clock face			
			countdownClockFace(1, 0, 0);
			
			// clock hand outline
			StdDraw.setPenColor(clockHandOutlineColor); // grey outline colour
			StdDraw.filledCircle(0, 0, 0.12); // middle circle part
			StdDraw.drawArea(clockHandOutline); // draw clock hand outline
			
			// clock hand
			StdDraw.setPenColor(countdownBlue); // main clock hand colour
			StdDraw.filledCircle(0, 0, 0.1); // middle circle part
			StdDraw.drawArea(clockHand); // draw clock hand
			
			// pause for 1 frame
			StdDraw.show(17); // 1000 (≈1 sec) / 60 (fps) = 16.67 ≈ 17
			
			// update clock hand for next iteration
			clockHand.transform(rotation);
			clockHandOutline.transform(rotation);
		}
	}
}