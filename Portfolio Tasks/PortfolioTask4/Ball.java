import java.awt.Color;

public class Ball {
	
	// Attributes
	private double xPosition;
	private double yPosition;
	
	private double xVelocity;
	private double yVelocity;
	
	private double radius;
	private double mass;
	
	private Color color;
	
	// Constructors
	/**
	 * Constructor for a Ball object
	 * 
	 * @param x the x position of the centre of ball in the frame
	 * @param y the y position of the centre of ball in the frame
	 * @param radius the radius of the ball
	 * @param mass the mass of the ball
	 * @param xVelocity the velocity of the ball in the x direction 
	 * @param yVelocity the velocity of the ball in the y direction
	 * @param color the color of the ball
	 */
	public Ball(double x, double y, double radius, double mass, double xVelocity, double yVelocity, Color color) {
		this.xPosition = x;
		this.yPosition = y;
		this.radius = radius;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.mass = mass;
		this.color = color;
	}
	
	/**
	 * Constructor for a Ball object with a default
	 * mass calculated for the ball based on its area
	 * 
	 * @param x the x position of the centre of ball in the frame
	 * @param y the y position of the centre of ball in the frame
	 * @param radius the radius of the ball
	 * @param xVelocity the velocity of the ball in the x direction 
	 * @param yVelocity the velocity of the ball in the y direction
	 * @param color the color of the ball
	 */
	public Ball(double x, double y, double radius, double xVelocity, double yVelocity, Color color) {
		// auto-generate a mass based on the area of the ball
		this(x, y, radius, Math.PI * Math.pow(radius, 2), xVelocity, yVelocity, color);
	}
	
	// Methods
	/**
	 * Updates both directional velocities in
	 * one method call, rather than using both
	 * <code>setxVelocity</code> and <code>setyVelocity</code>
	 * 
	 * @param xVelocity the new velocity of the ball in the x direction
	 * @param yVelocity the new velocity of the ball in the y direction
	 */
	public void updateVelocities(double xVelocity, double yVelocity) {
		setxVelocity(xVelocity);
		setyVelocity(yVelocity);
	}
	
	/**
	 * @return the next position the ball would move to in
	 * an array in the format {nextX, nextY}
	 */
	public double[] nextPosition() {
		return new double[] {xPosition + xVelocity, yPosition + yVelocity};
	}
	
	/**
	 * Finds the next move of two balls and determines
	 * if there has been a collision
	 * 
	 * @param otherBall the ball to check for collision with
	 * @return whether the balls have collided or not
	 */
	public boolean hasCollidedWith(Ball otherBall) {
		// get next position of each ball
		double[] nextPosition = nextPosition();
		double[] otherBallNextPosition = otherBall.nextPosition();
		
		// find distance between moved balls
		double distanceBetweenBalls = Math.sqrt(
				Math.pow((nextPosition[0] - otherBallNextPosition[0]), 2) // difference in x squared
				+ Math.pow((nextPosition[1] - otherBallNextPosition[1]), 2) // difference in y squared
				);
		/*
		 * The equation above finds the distance between two points.
		 * It was researched at the following location:
		 * 
		 * Khan Academy, 2017. Distance formula [online].
		 * Available from: 
		 * https://www.khanacademy.org/math/geometry/hs-geo-analytic-geometry/hs-geo-distance-and-midpoints/a/distance-formula
		 * [Accessed 19 February 2022].
		 */
		
		if(distanceBetweenBalls < this.radius + otherBall.radius) {
			// where there has been a collision
			return true;
		} else {
			// where there is no collision
			return false;
		}
		
	}
	
	/**
	 * Inverts the x velocity of the ball
	 */
	public void changeXDirection() {
		this.xVelocity = - this.xVelocity;
	}
	
	/**
	 * Inverts the y velocity of the ball
	 */
	public void changeYDirection() {
		this.yVelocity = - this.yVelocity;
	}
	
	/**
	 * Moves the ball with its current velocity
	 */
	public void move() {
		xPosition += xVelocity; // update x position
		yPosition += yVelocity; // update y position
	}
	
	// Static methods
	/**
	 * Updates the velocities of 2 Balls after an
	 * elastic collision
	 * 
	 * @param ball1 one ball in the collision
	 * @param ball2 the other ball in the collision
	 */
	public static void ellasticCollision(Ball ball1, Ball ball2) {
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
		
		double initialBall1XVelocity = ball1.xVelocity;
		double initialBall2XVelocity = ball2.xVelocity;
		double initialBall1YVelocity = ball1.yVelocity;
		double initialBall2YVelocity = ball2.yVelocity;
		/*
		 * these variables have been declared as originally
		 * they were directly accessed, however, as the
		 * velocity of ball 1 is updated first, this updated
		 * value would then be used to calculate the new velocity
		 * of ball 2, leading to inaccurate results
		 */
		
		// update x velocities
		ball1.xVelocity = ((ball1.mass - ball2.mass) / (ball1.mass + ball2.mass)) * initialBall1XVelocity + ((2 * ball2.mass) / (ball1.mass + ball2.mass)) * initialBall2XVelocity;	
		ball2.xVelocity = ((2 * ball1.mass) / (ball1.mass + ball2.mass)) * initialBall1XVelocity + ((ball2.mass - ball1.mass) / (ball1.mass + ball2.mass)) * initialBall2XVelocity;
		
		// update y velocities
		ball1.yVelocity = ((ball1.mass - ball2.mass) / (ball1.mass + ball2.mass)) * initialBall1YVelocity+ ((2 * ball2.mass) / (ball1.mass + ball2.mass)) * initialBall2YVelocity;	
		ball2.yVelocity = ((2 * ball1.mass) / (ball1.mass + ball2.mass)) * initialBall1YVelocity + ((ball2.mass - ball1.mass) / (ball1.mass + ball2.mass)) * initialBall2YVelocity;
	}

	
	// Getters
	/**
	 * @return the xPosition of the centre of the ball
	 */
	public double getxPosition() {
		return xPosition;
	}

	/**
	 * @return the yPosition of the centre of the ball
	 */
	public double getyPosition() {
		return yPosition;
	}

	/**
	 * @return the xVelocity
	 */
	public double getxVelocity() {
		return xVelocity;
	}

	/**
	 * @return the yVelocity
	 */
	public double getyVelocity() {
		return yVelocity;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}


	// Setters
	/**
	 * @param xPosition the xPosition to set
	 */
	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * @param yPosition the yPosition to set
	 */
	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	/**
	 * @param xVelocity the xVelocity to set
	 */
	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	/**
	 * @param yVelocity the yVelocity to set
	 */
	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
