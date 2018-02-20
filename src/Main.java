
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
    	
    	System.out.println("0 - left player");
    	System.out.println("1 - right player");
    	System.out.println("2 - server");
    	
    	Scanner reader = new Scanner(System.in);
    	int n = reader.nextInt();
    	
    	if (n == 0) {
    		Client client = new Client(n);
    	}
    	
    	if (n == 1) {
    		Client client = new Client(n);
    	}
    	
    	if (n == 2) {
    		Server server = new Server();
    	}
    	/*
    	 	
    	  n = 0 lewy gracz
    	  n = 1 prawy gracz
    	  n = 2 serwer
    	  
    	 
    	 */
    	
    	/*
    	Object object = new Object();
    	Player player1 = new Player(25, 110, 10, 50, 5);
    	Player player2 = new Player(455, 110, 10, 50, 5);
    	Ball ball = new Ball(240, 125, -3, 3, 20);	
    	GUIWrapper guiWrapper = new GUIWrapper(player1, player2, ball);
    	*/
    
    }
}