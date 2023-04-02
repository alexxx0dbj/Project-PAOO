package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;  //.JPanel
import java.awt.*;  //.Color, .Dimension

//this class inherites JPanel class -> basically GamePanel(have all the properties of JPanel) is a subclass of JPanel
//GamePanel works almost like a screen
public class GamePanel extends JPanel  implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 64;    //16x16 pixels tile -> default size of character or any sprite
    //catch thing -> 16x16 pixels looks small on monitors => we'll scale 16X16 by 3
    final int scale =1;
    public final int tileSize = originalTileSize*scale; //48x48   -> it needs to be "public" when you want to access from other packages
    public final int maxScreenCol=12; //□ □ □ □ □ □ □ □ □ □ □ □ □ □ □ □
    public final int maxSceenRow=8;
    public final int screenWidth = tileSize*maxScreenCol;  //48*16=768 pixels
    public final int screenHeight =tileSize*maxSceenRow;   //48*12=576 pixels


    //SET FPS
    int FPS= 170; //60 maybe


    //WORLD SETTINGS
    public final int maxWorldCol = 45;
    public final int maxWorldRow = 45;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;

TileManager tileM = new TileManager(this);

    //instantiation KeyHandler
    KeyHandler keyH = new KeyHandler(); //then add to the gamePanel
    Thread gameThread;  //Thread keeps the program running until I stop it -> Thread is used to repeat a process

    public Player player = new Player(this,keyH); //this refers to GamePanel class






    //constructor GamePanel()
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); //setPreferredSize(new Dimension(width,height))=set the size of this class(JPanel)
        this.setBackground(Color.black); //set background color
        this.setDoubleBuffered(true); //true->all the drawing from this component will be done in an offscreen painting buffer
                                      //enabling this, can improve game's rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);    //setFocusable()->with this, this GamePanel can "focused" to receive key input

    }

    public void startGameThread(){
        gameThread=new Thread(this); //this means class GamePanel
        gameThread.start();
    }
    @Override
    //when we start this gameThread it is automatically call this run method
    public void run(){
        //create GameLoop -> core of the game

        //for FPS -> "SLEEP" METHOD
        double drawInterval = 1000000000/FPS;  //1 sec=1billion nanoseconds divide by FPS (seconds)
        double nextDrawTime=System.nanoTime() + drawInterval;   //next draw time will be the current time(System.nanoTime()) plus the drawInterval(later)

        while(gameThread != null){ // as long as this game thread, it will repeat this process



            //1 UPDATE: update information such as character positions
            update();

            //2 DRAW: draw the screen with the update information
            repaint(); //this is how I call the paintComponent method


            try {
                double remainingTime = nextDrawTime-System.nanoTime(); //how much time remaining until the next draw time => SLEEP
                remainingTime=remainingTime/100000;
                //1 second = 10^9 nanoseconds
                //1 second = 1000 milliseconds

                if(remainingTime<0){
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);  //sleep() accept just double -> cast the remainingTime and milliseconds(conversion in remainingTime)
                                                    //sleep() will pause the game loop

                nextDrawTime +=drawInterval;  //update the new draw time after sleep
            } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

    }
    //Update and Draw
    public void update(){  //here we change player position

        player.update();

    }

    //Graphics -> a class that has many function to draw objects on screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //convert Graphics to Graphics2D (has more functions)
        //Graphics2D class extends the Graphics class to provide more sophisticated control over geometry,
        //coordinate transformation, color manager and text layout
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2); //before player
        player.draw(g2);

        g2.dispose(); //dispose()->dispose of the graphics context and release any system resources that it is using
    }
}
