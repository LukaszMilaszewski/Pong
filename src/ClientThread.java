
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ClientThread implements Runnable, Serializable {

	Socket clientSocket;
	DataInputStream reader = null;
	DataOutputStream writer = null;
	Server server;
	Player player;
	Ball ball;
	int who;
	Object obj;
	
	
	public ClientThread(Object obj, Socket clientSocket, Server server, Player player, Ball ball, int who) throws IOException {
		this.clientSocket = clientSocket;
		this.server = server;
		this.player = player;
		this.ball = ball;
		this.who = who;
		this.obj = obj;
		reader = new DataInputStream(clientSocket.getInputStream());
		writer = new DataOutputStream(clientSocket.getOutputStream());
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				
				synchronized(obj) {
					Game.button = reader.readInt();
					Game.performPaddleMove(player, 600);
					Game.itemVisibility(1000, 600);

					writer.writeInt(Game.whoHasItem);
					writer.flush();
					
					writer.writeInt(ball.getX());
					writer.flush();
					writer.writeInt(ball.getY());
					writer.flush();
					writer.writeInt(ball.getXspeed());
					writer.flush();
					
	
					if (who == 1) {
						writer.writeInt(player.getX());
						writer.flush();
						writer.writeInt(player.getY());
						writer.flush();
						writer.writeInt(server.player2.getX());
						writer.flush();
						writer.writeInt(server.player2.getY());
						writer.flush();
						writer.writeInt(player.getScore());
						writer.flush();
						writer.writeInt(server.player2.getScore());
						writer.writeInt(Item.x);
						writer.flush();
						writer.writeInt(Item.y);
						writer.flush();
						writer.writeBoolean(Item.isVisible);
						writer.flush();
					}
					
					if (who == 2) {
						writer.writeInt(server.player1.getX());
						writer.flush();
						writer.writeInt(server.player1.getY());
						writer.flush();
						writer.writeInt(player.getX());
						writer.flush();
						writer.writeInt(player.getY());
						writer.flush();
						writer.writeInt(server.player1.getScore());
						writer.flush();
						writer.writeInt(player.getScore());
						writer.flush();
						writer.writeInt(Item.x);
						writer.flush();
						writer.writeInt(Item.y);
						writer.flush();
						writer.writeBoolean(Item.isVisible);
						writer.flush();
					}
					obj.wait();
				}	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
