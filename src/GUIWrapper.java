
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class GUIWrapper {
    
    public GUIWrapper(int who, Socket clientSocket) throws IOException {
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	JFrame frame = new JFrame("Pong");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLayout(new BorderLayout());
    	frame.setResizable(false);
    	 	
    	GUI gui = new GUI(who, clientSocket);
    	
    	frame.add(gui, BorderLayout.CENTER);
    		
    	frame.setSize(1010, 633);
    	frame.setVisible(true);	
    }
    
}
