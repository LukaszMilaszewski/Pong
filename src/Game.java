public class Game {
    
    static int button;
    static int ballSpeedFlag = 2;
    static int hits = 0;
    static boolean itemFlag = true;
    
    // 1 - left player
    // 2 - right player
    static int whoHasItem = -1;
    
    
    static void performPaddleMove(Player player, int height) {
        if (button == 0)
            if (player.getY() - player.getSpeed() > 0)
                player.setY(player.getY() - player.getSpeed());
        
        if (button == 1)
            if (player.getY() + player.getSpeed() + player.getH() < height)
                player.setY(player.getY() + player.getSpeed());
    }
    
    static void performBallMove(Player player1, Player player2, Ball ball, int height) {
        int nextballLeft = ball.getX() + ball.getXspeed();
        int nextballRight = ball.getX() + ball.getD() + ball.getXspeed();
        int nextballTop = ball.getY() + ball.getYspeed();
        int nextballBottom = ball.getY() + ball.getD() + ball.getYspeed();
        
        int playerOneRight = player1.getX() + player1.getW();
        int playerOneTop = player1.getY();
        int playerOneBottom = player1.getY() + player1.getH();
        int playerOneEdgeLength = player1.getH() / 5;
        
        int playerTwoLeft = player2.getX();
        int playerTwoTop = player2.getY();
        int playerTwoBottom = player2.getY() + player2.getH();
        int playerTwoEdgeLength = player2.getH() / 5;
        
        if(1 == 1) {
            // odbicie od gory albo dolu ekranu
            if (nextballTop < 0 || nextballBottom > height) {
                ball.revertYspeed();
                Sound.sound("pong.wav");
            }
            
            // lewa strona
            if (nextballLeft < playerOneRight) {
                if (nextballTop > playerOneBottom || nextballBottom < playerOneTop && !(whoHasItem == 1))
                    performRoundFinish(player2, ball);
                else {
                    if ((nextballTop + ball.getD() < playerOneTop + playerOneEdgeLength && ball.getYspeed() > 0) ||
                        (nextballBottom - ball.getD() > playerOneBottom - playerOneEdgeLength && ball.getYspeed() < 0 )) {
                        ball.revertYspeed();
                    }
                    if (nextballTop > playerOneBottom || nextballBottom < playerOneTop) {
                        whoHasItem = -1;
                        ball.revertYspeed();
                    }
                    
                    hits++;
                    ball.revertXspeed();
                    Sound.sound("pong.wav");
                }
            }
            
            // prawa strona
            if (nextballRight > playerTwoLeft) {
                if (nextballTop > playerTwoBottom || nextballBottom < playerTwoTop && !(whoHasItem == 2))
                    performRoundFinish(player1, ball);
                else {
                    if ((nextballTop + ball.getD() < playerTwoTop + playerTwoEdgeLength && ball.getYspeed() > 0) ||
                        (nextballBottom - ball.getD() > playerTwoBottom - playerTwoEdgeLength && ball.getYspeed() < 0 )) {
                        ball.revertYspeed();
                    }
                    
                    if(nextballTop > playerTwoBottom || nextballBottom < playerTwoTop) {
                        whoHasItem = -1;
                        ball.revertYspeed();
                    }
                    
                    ball.revertXspeed();
                    Sound.sound("pong.wav");
                    
                }
            }
            ball.changePosition();
        }
    }
    
    static void performRoundFinish(Player player, Ball ball) {
        player.setScore(player.getScore() + 1);
        if (player.getScore() == 2) {
            GUI.playView = false;
            GUI.stopView = true;
        }
        hits = 0;
        Item.isVisible = false;
        itemFlag = true;
        Item.x = -100;
        Item.y = -100;
        ball.defaultPosition();
        ball.defaultSpeed();
        Game.ballSpeedFlag = 2;
        ball.revertSpeed();
    }
    
    static void startingValues(Player player1, Player player2, Ball ball) {
        GUI.stopView = false;
        GUI.startView = true;
        player1.setY(250);
        player1.setScore(0);
        player2.setY(250);
        player2.setScore(0);
        ball.setX(480);
        ball.setY(280);
    }
    
    static void ballSpeedIncrease(Ball ball) {
        if(hits % 2 == 0  && ballSpeedFlag == hits) {
            ballSpeedFlag = hits + 2;
            if (ball.getXspeed() > 0)
                ball.setXspeed(ball.getXspeed() + 1);
            else
                ball.setXspeed(ball.getXspeed() - 1);
        }
    }
    
    static void itemVisibility(int width, int height) {
        if (hits == 1 && itemFlag) {
            itemFlag = false;
            Item.generateItem(width,height);
        }
    }
    
    static void checkItemGain(Ball ball) {
        int ballRightX = ball.getX() + ball.getD();
        int ballRightY = ball.getY() + (ball.getD() / 2);
        int ballLeftX = ball.getX();
        int ballLeftY = ball.getY() + (ball.getD() / 2);
        if (whoHasItem == -1) {
            if (ballRightX > Item.x && ballRightX < Item.x + Item.length && ball.getXspeed() > 0) {
                if (ballRightY > Item.y && ballRightY < Item.y + Item.length) {
                    whoHasItem = 1;
                    Item.isVisible = false;
                    Item.x = -100;
                    Item.y = -100;
                    System.out.println("Lewy");
                }
            }
            
            if (ballLeftX > Item.x && ballLeftX < Item.x + Item.length && ball.getXspeed() < 0) {
                if (ballLeftY > Item.y && ballLeftY < Item.y + Item.length) {
                    whoHasItem = 2;
                    Item.isVisible = false;
                    Item.x = -100;
                    Item.y = -100;
                    System.out.println("Prawy");
                }
            }
        }
    }
}

