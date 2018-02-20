
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	Player player;

	public Client(int who) throws UnknownHostException, IOException {
		Socket socketClient = new Socket("localhost",11111);
		GUIWrapper guiWrapper = new GUIWrapper(who, socketClient);
	}
}
