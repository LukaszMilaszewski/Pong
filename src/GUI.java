
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GUI extends JPanel implements ActionListener, KeyListener {
    
    static boolean playView = false;
    static boolean stopView = false;
    static boolean startView = true;
    private Player player1 = null;
    private Player player2 = null;
    private Ball ball = null;
    private int who;
    DataInputStream reader;
    DataOutputStream writer;
    
    PlayerThread playerThread;
    Object obj;
    
    public GUI(int who, Socket socketClient) throws IOException {
        this.who = who;
        
        obj = new Object();
        int h = getHeight();
        player1 = new Player(25, 110, 20, 100, 5);
        player2 = new Player(955, 110, 20, 100, 5);
        ball = new Ball(480, 280, 3, 3, 40);
        reader = new DataInputStream(socketClient.getInputStream());
        writer = new DataOutputStream(socketClient.getOutputStream());
        playerThread = new PlayerThread(obj, who, socketClient);
        
        new Thread(playerThread).start();
        
        setBackground(Color.GRAY);
        
        setFocusable(true);
        addKeyListener(this);
        
        Timer timer = new Timer(1000/60, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        
        drawPlayView(g, player1, player2, ball);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int height = getHeight();
        
        synchronized(obj) {
            obj.notifyAll();
            int data;
            try {
                data = reader.readInt();
                Game.whoHasItem = data;
                data = reader.readInt();
                ball.setX(data);
                data = reader.readInt();
                ball.setY(data);
                data = reader.readInt();
                ball.setXspeed(data);
                data = reader.readInt();
                player1.setX(data);
                data = reader.readInt();
                player1.setY(data);
                data = reader.readInt();
                player2.setX(data);
                data = reader.readInt();
                player2.setY(data);
                data = reader.readInt();
                player1.setScore(data);
                data = reader.readInt();
                player2.setScore(data);
                data = reader.readInt();
                Item.x = data;
                data = reader.readInt();
                Item.y = data;
                boolean temp = reader.readBoolean();
                Item.isVisible = temp;
                
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            repaint();
        }
    }
    
    void drawStartView(Graphics g) {
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 72));
        g.drawString("Pong", 400, 200);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        g.drawString("Press 'P' to Play.", 350, 400);
    }
    
    void drawPlayView(Graphics g, Player player1, Player player2, Ball ball) {
        int playerOneRight = player1.getX() + player1.getW();
        int playerTwoLeft =  player2.getX();
        
        for (int lineY = 0; lineY < getHeight(); lineY += 100) {
            g.drawLine(500, lineY, 500, lineY+50);
        }
        if (Item.isVisible) {
            g.fillRect(Item.x, Item.y, 40, 40);
        }
        if (Game.whoHasItem == 1)
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
        if (Game.whoHasItem == 2)
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
        
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 72));
        g.drawString(String.valueOf(player1.getScore()), 200, 100);
        g.drawString(String.valueOf(player2.getScore()), 728, 100);
        
        g.fillOval(ball.getX(), ball.getY(), ball.getD(), ball.getD());
        g.fillRect(player1.getX(), player1.getY(), player1.getW(), player1.getH());
        g.fillRect(player2.getX(), player2.getY(), player2.getW(), player2.getH());
    }
    
    void drawStopView(Graphics g, Player player1, Player player2) {
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 72));
        g.drawString(String.valueOf(player1.getScore()), 150, 100);
        g.drawString(String.valueOf(player2.getScore()), 800, 100);
        
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 72));
        if (player1.getScore() > player2.getScore())
            g.drawString("Left Player Wins!", 200, 300);
        else
            g.drawString("Right Player Wins!", 175, 300);
        
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        g.drawString("Press space to restart.", 290, 450);
    }
    
    void checkStartKey(KeyEvent e) throws IOException {
        if(e.getKeyCode() == KeyEvent.VK_P) {
            GUI.startView = false;
            GUI.playView = true;
        }
    }
    
    void checkMoveKeys(KeyEvent e, int who) {
        
        if (who == 0) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                    PlayerThread.button = 0;
                    break;
                case KeyEvent.VK_S:
                    PlayerThread.button = 1;
                    break;
            }
        }
        
        if (who == 1) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    PlayerThread.button = 0;
                    break;
                case KeyEvent.VK_DOWN:
                    PlayerThread.button = 1;
                    break;
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        checkMoveKeys(e, who);
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        PlayerThread.button = -1;
        
    }
    
    @Override
    public void keyTyped(KeyEvent arg0) {}
}

