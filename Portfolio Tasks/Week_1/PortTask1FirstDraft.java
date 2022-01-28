
public class PortTask1FirstDraft {

	public static void main(String[] args) {
		exercise2();
	}
	
	private static void example(){
        System.out.println("Graphics class bitchez...");
        
        // the display window is scaled to min = 0 and max = 1
        
        // draw a square
        StdDraw.square(.2, .2, .1); // centre x = 0.2, y = 0.2 and half length = 0.1 of the square
        
        // Graphics 2D text
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5, 0.8, "Welcome shitbags!");
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(0.5, 0.5, "First 2D program");
    }
    
    private static void exercise1() {
        StdDraw.line(0, 0.9, 0.1, 1);
        StdDraw.rectangle(0.2, 0.8, 0.2, 0.1);
        StdDraw.ellipse(0.2, 0.6, 0.2, 0.1);
        StdDraw.circle(0.2, 0.4, 0.1);
        StdDraw.arc(0.2, 0, 0.2, 0, 160);
    }
    
    private static void exercise2() {
        // H
        StdDraw.line(0.1, 0.9, 0.1, 0.7);
        StdDraw.line(0.1, 0.8, 0.2, 0.8);
        StdDraw.line(0.2, 0.9, 0.2, 0.7);
        
        // e
        StdDraw.arc(0.3, 0.75, 0.05, 0, 300);
        StdDraw.line(0.25, 0.75, 0.35, 0.75);
        
        // l
        drawL(0.4, 0.9);
        
        // l
        drawL(0.5, 0.9);
        
        // o
        StdDraw.circle(0.65, 0.75, 0.05);
        
    }
    
    private static void drawL(double topX, double topY) {
        StdDraw.line(topX, topY, topX, topY - 0.15);
        StdDraw.arc(topX + 0.05, topY - 0.15, 0.05, 180, 300);
    }
	
}
