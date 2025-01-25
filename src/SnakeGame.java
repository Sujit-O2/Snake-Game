import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener,KeyListener{
    int BWid,BHei;
    int tileSize=25;
    class tile{
        int x;
        int y;
        tile(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    Timer gameLoop;

    tile SnakeH;
    ArrayList<tile> SnakeBody;
    tile food;
    Random rr;
    int velocityX,velocityY;
    boolean gameOver =false;
    int score=0;
    SnakeGame(int BWid,int BHei){
        this.BHei=BHei;
        this.BWid=BWid;
        this.setPreferredSize(new Dimension(BWid,BHei));
        this.setBackground(Color.BLACK);
        SnakeH=new tile(5,5);
        food=new tile(10,10);
        rr=new Random();
        PlaceFood();
        gameLoop=new Timer(200,this);
        gameLoop.start();
        velocityX=0;
        velocityY=0;
        this.addKeyListener(this);
        setFocusable(true);
        SnakeBody=new ArrayList<tile>();
            }
    public void move(){

        if(collision(SnakeH,food)){
            SnakeBody.add(new tile(food.x,food.y));
            score+=10;
            PlaceFood();
        }
        for(int i=SnakeBody.size()-1;i>=0;i--){
            tile snakepart=SnakeBody.get(i);

            if(i==0){
             snakepart.x=SnakeH.x;
             snakepart.y=SnakeH.y;
            }
            else{
                tile PrevSnakeP=SnakeBody.get(i-1);
                    snakepart.x=PrevSnakeP.x;
                    snakepart.y=PrevSnakeP.y; 
            }
        }
        SnakeH.x+=velocityX;
        SnakeH.y+=velocityY;
        for(tile gB: SnakeBody){
            if(collision(SnakeH,gB)){
                gameOver=true;
            }
        }
        if(SnakeH.x*tileSize<0||SnakeH.x*tileSize>BWid||
        SnakeH.y*tileSize<0||SnakeH.y*tileSize>BHei){
            gameOver=true;
        }

    }
    public boolean collision(tile a,tile b){
        return a.x==b.x && a.y==b.y;

    }
    private void PlaceFood() {
        food.x=rr.nextInt(BWid/tileSize);
        food.y=rr.nextInt(BHei/tileSize);
            }
            @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i=0;i<BWid/tileSize;i++){
            g.drawLine(i*tileSize,0,i*tileSize,BWid);
            g.drawLine(0,i*tileSize,BHei,i*tileSize);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arisal",Font.BOLD,20));
        if(!gameOver){
        g.setColor(Color.WHITE);
        g.drawString("Your Score : "+score,5,20);
        }
        else{
        g.setColor(Color.RED);
        g.drawString("GAME OVER !! Score : "+score,5,20);
        }

        g.setColor(Color.RED);
        g.fillRect(food.x*tileSize,food.y*tileSize,tileSize,tileSize);

        g.setColor(Color.GREEN);
        g.fillRect(SnakeH.x*tileSize,SnakeH.y*tileSize,tileSize,tileSize);
        for(tile snakeB:SnakeBody){
            g.drawRect(snakeB.x*tileSize,snakeB.y*tileSize,tileSize,tileSize);
        }
    }
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
               this.repaint();
               if(gameOver){
                gameLoop.stop();

               }
            }
           
            @Override
            public void keyPressed(KeyEvent e) {
                if(gameOver){
                    score=0;
                    gameOver=false;
                    velocityX=0;
                    velocityY=0;
                    SnakeH.x=5;
                    SnakeH.y=5;
                    SnakeBody.clear();
                    gameLoop.start();

                }
                if(e.getKeyCode()==KeyEvent.VK_UP && velocityY!=1){
                    velocityX=0;
                    velocityY=-1;
                }
                
                else if(e.getKeyCode()==KeyEvent.VK_DOWN && velocityY!=-1){
                    velocityX=0;
                    velocityY=1;
                }
                else if(e.getKeyCode()==KeyEvent.VK_LEFT && velocityX!=1){
                    velocityX=-1;
                    velocityY=0;
                }
                else if(e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX!=-1){
                    velocityX=1;
                    velocityY=0;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) { 
            }
            @Override
            public void keyTyped(KeyEvent e) {  
            }
    
}
