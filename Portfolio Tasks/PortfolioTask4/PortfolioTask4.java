import java.awt.Color;
import java.util.Random;

public class PortfolioTask4 {

	public static void main(String[] args) {
		exercise2a();
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
		Random randomNumberGenerator = new Random();
		double xPosition = (randomNumberGenerator.nextDouble() * (maxX - minX)) + minX;
		double yPosition = (randomNumberGenerator.nextDouble() * (maxY - minY)) + minY;
		double radius = (randomNumberGenerator.nextDouble() * (maxRadius - minRadius)) + minRadius;
		double xVelocity = randomNumberGenerator.nextDouble() * maxVelocity;
		double yVelocity = randomNumberGenerator.nextDouble() * maxVelocity;
		Color color = new Color(randomNumberGenerator.nextFloat(), randomNumberGenerator.nextFloat(), randomNumberGenerator.nextFloat());
		
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
		
		// generate n balls
		for(int i = 0; i < n; i++) {
			output[i] = randomBallGenerator(minX, maxX, minY, maxY, minRadius, maxRadius, maxVelocity);
		}
		
		// return an array of all balls generated
		return output;
	}
	
	public static void exercise2a() {
		// set up canvas
		StdDraw.setScale(-1, 1);
		
		// generate balls
		Ball[] balls = generateNBalls(5, -0.8, 0.8, -0.8, 0.8, 0.05, 0.2, 0.04);
				
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
	
	
	
	
}
