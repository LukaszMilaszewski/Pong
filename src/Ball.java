
import java.io.Serializable;

public class Ball implements Serializable {
	 private int x;
	 private int y;
	 private int xSpeed;
	 private int ySpeed;
	 private int d; 
	 private int initXspeed;
	 private int initYspeed;
	 
	 public Ball(int x, int y, int xSpeed, int ySpeed, int d) {
		 this.x = x;
		 this.y = y;
		 this.xSpeed = xSpeed;
		 this.ySpeed = ySpeed;
		 this.d = d;
		 initXspeed = xSpeed;
		 initYspeed = ySpeed;
	 }
	 
	 void setX(int x) { this.x = x; }
	 int getX() { return x; }
	 
	 void setY(int y) { this.y = y; }
	 int getY() { return y; }
	 
	 void setXspeed(int xSpeed) { this.xSpeed = xSpeed; }
	 int getXspeed() { return xSpeed; }
	 
	 void setYspeed(int ySpeed) { this.ySpeed = ySpeed; }
	 int getYspeed() { return ySpeed; }
	 
	 void setD(int d) { this.d = d; }
	 int getD() { return d; }
	 
	 void revertXspeed() {
		 xSpeed *= -1;
	 }
	 
	 void revertYspeed() {
		 ySpeed *= -1;
	 }
	 
	 void revertSpeed() {
		 revertXspeed();
		 revertYspeed();
	 }
	 
	 void changePosition() {
	     x += xSpeed;
	     y += ySpeed; 
	 }
	 
	 void defaultPosition() {
		 x = 480;
		 y = 280;
	 }
	 
	 void defaultSpeed() {
		 xSpeed = initXspeed;
		 ySpeed = initYspeed;
	 }
}
