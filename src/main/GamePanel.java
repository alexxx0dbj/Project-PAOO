package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;


import javax.swing.*;  //.JPanel
import java.awt.*;  //.Color, .Dimension
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//this class inherites JPanel class -> basically GamePanel(have all the properties of JPanel) is a subclass of JPanel
//GamePanel works almost like a screen
public class GamePanel extends JPanel implements Runnable{

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
    int FPS= 60; //170 maybe best //150 alta faza


    //WORLD SETTINGS
    public final int maxWorldCol = 57;
    public final int maxWorldRow = 57; //57
    // public final int worldWidth = tileSize*maxWorldCol;
    // public final int worldHeight = tileSize*maxWorldRow;

    //SYSTEM
    TileManager tileM = new TileManager(this);

         //instantiation KeyHandler
    public KeyHandler keyH = new KeyHandler(this); //then add to the gamePanel

        //for music
    Sound music = new Sound();
    Sound soundeeffect = new Sound();

    public EventHandler eHandler = new EventHandler(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
        //UI
    public UI ui = new UI(this);
    Thread gameThread;  //Thread keeps the program running until I stop it -> Thread is used to repeat a process


    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH); //this refers to GamePanel class
    public Entity obj[] = new Entity[20];
    public Entity npc[]=new Entity[10];//npc array


    //create monster array
    public Entity monster[] = new Entity[20];

    //we sort the order of the array; The entity that has the lowest worldY comes to index 0
    ArrayList<Entity> entityList=new ArrayList<>();//creating an array list of entity

    //GAME STATE
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;



    //constructor GamePanel()
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); //setPreferredSize(new Dimension(width,height))=set the size of this class(JPanel)
        this.setBackground(Color.black); //set background color
        this.setDoubleBuffered(true); //true->all the drawing from this component will be done in an offscreen painting buffer
                                      //enabling this, can improve game's rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);    //setFocusable()->with this, this GamePanel can "focused" to receive key input

    }


    public void setupGame() { //for obj -> create this method -> we can add other setup stuff in the future
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();

        gameState=titleState;
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
                remainingTime=remainingTime/1000000;//or 100000 depends on fps choose
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



    public void update(){  //here we change player position
        if(gameState==playState){

            //PLAYER update
            player.update();

            //NPC update
            for(int i=0;i<npc.length;i++){
                if(npc[i]!=null){
                    npc[i].update();
                }
            }

            //UPDATE MONSTER
            for(int i=0;i<monster.length;i++){
                if(monster[i]!=null){
                    if(monster[i].alive==true && monster[i].dying==false){//monster is alive or not
                        monster[i].update();
                    }
                    if(monster[i].alive==false){
                        monster[i]=null;
                    }

                }
            }
        }

        if(gameState==pauseState){
            //nothing
        }

    }

    //Graphics -> a class that has many function to draw objects on screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //convert Graphics to Graphics2D (has more functions)
        //Graphics2D class extends the Graphics class to provide more sophisticated control over geometry,
        //coordinate transformation, color manager and text layout
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if(gameState==titleState){
            ui.draw(g2);
        }

        //OTHERS
        else{
            //TILE
            tileM.draw(g2); //before player

            //ADD ENTITIES TO THE LIST
            entityList.add(player);
            for(int i=0;i<npc.length;i++){
                if(npc[i]!=null){
                    entityList.add(npc[i]);
                }
            }

            for(int i=0;i<obj.length;i++){
                if(obj[i]!=null){
                    entityList.add(obj[i]);
                }
            }

            for(int i=0;i<monster.length;i++){
                if(monster[i]!=null){
                    entityList.add(monster[i]);
                }
            }

            //sorting the order of the ArrayList
            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST->otherwise the entityList gets larger in every loop
            //for(int i=0;i<entityList.size();i++){
               // entityList.remove(i);
            //}
            entityList.clear();

            //UI
            ui.draw(g2);
            //g2.dispose(); //dispose()->dispose of the graphics context and release any system resources that it is using

        }
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        soundeeffect.setFile(i);
        soundeeffect.play();
    }
}
