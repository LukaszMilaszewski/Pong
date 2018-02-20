
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class PlayerThread implements Runnable {
	
	Object obj;
	int who;
	static int button;
	Socket socketClient;
	
	
	public PlayerThread(Object obj, int who, Socket socketClient ) {
		this.obj = obj;
		this.who = who;
		this.socketClient = socketClient;
		button = -1;
	}
	
	@Override
	public void run() {
		try {
			
			DataOutputStream writer = new DataOutputStream(socketClient.getOutputStream());
			while(true) {
				synchronized (obj) {
						// nie wykonujemy ruchu
						// tutaj wysy³amy keyListenery
						//Game.performPaddleMove(player, height);	
					writer.writeInt(button);
					writer.flush();
					
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}

	

	/*
	void restart(KeyEvent e, Player player1, Player player2, Ball ball) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) 
			Game.startingValues(player1, player2, ball);
	}*/

