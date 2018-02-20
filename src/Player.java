
import java.io.Serializable;

public class Player implements Serializable {
	private int x;
	private int y;
	private int w;
	private int h;
	private int speed;
	private int score;

	private boolean upPressed;
	private boolean downPressed;
     
	public Player(int x, int y, int w, int h, int speed) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.speed = speed;
		score = 0;
		upPressed = false;
		downPressed = false;
	}  
     
	void setX(int x) { this.x = x; }
	int getX() { return x; }
	
	void setY(int y) { this.y = y; }
	int getY() { return y; }
	
	void setH(int h) { this.h = h; }
	int getH() { return h; }
	
	void setW(int w) { this.w = w; }
	int getW() { return w; }
	
	void setSpeed(int speed) { this.speed = speed; }
	int getSpeed() { return speed; }
	
	void setScore(int score) { this.score = score; }
	int getScore() { return score; }

	void setUpPressed(boolean upPressed) { this.upPressed = upPressed; }
	boolean getUpPressed() { return upPressed; }
	
	void setDownPressed(boolean downPressed) { this.downPressed = downPressed; }
	boolean getDownPressed() { return downPressed; }
}