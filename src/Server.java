
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.Timer;

public class Server implements ActionListener  {

	ServerSocket myServerSocket;
	Player player1;
	Player player2;
	Ball ball;
	Object obj = new Object();
	boolean start = false;
	
	public Server() throws IOException  { 
		
		
		
    	player1 = new Player(25, 110, 20, 100, 5);
    	player2 = new Player(955, 110, 20, 100, 5);
    	ball = new Ball(480, 280, 3, 3, 40);
    	
		myServerSocket = new ServerSocket(11111); 
		System.out.println("Server is running");
		Socket clientSocket1 = myServerSocket.accept();
		Socket clientSocket2 = myServerSocket.accept();
		
		Thread thread1 = new Thread(new ClientThread(obj, clientSocket1, this, player1, ball, 1), "Thread 1");
		thread1.start();

		Thread thread2 = new Thread(new ClientThread(obj, clientSocket2, this, player2, ball, 2), "Thread 2");
		thread2.start();
		
     	Timer timer = new Timer(1000/60, this);
    	timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		synchronized(obj) {
		Game.performBallMove(player1, player2, ball, 600);
		Game.ballSpeedIncrease(ball);
		Game.checkItemGain(ball);
		obj.notifyAll();
		}
	}
}
