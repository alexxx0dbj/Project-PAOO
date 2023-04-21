package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_MagicBook;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font myfont;
    Font myfont2;

    BufferedImage keyImage;
    Graphics2D g2;
    BufferedImage bookImage;
    public boolean messageOn=false;
    public String message = "";
    int messageCounter=0;
    public boolean gameFinished = false;
    public String currentDialogue="";
    public int commandNum=0; // for menu

    BufferedImage heart_full, heart_half, heart_blank;

    public UI(GamePanel gp){
        this.gp=gp;
        myfont=new Font("Montserrat", Font.PLAIN,28);
        myfont2=new Font("Papyrus", Font.BOLD,38);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage=key.down1;
        OBJ_MagicBook book = new OBJ_MagicBook(gp);
        bookImage=book.down1;

        //CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;
    }

    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){
        if(gameFinished==true){
            g2.setFont(myfont);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x; int y;
        }
        else{
            // display number of keys and books -> I create a method

            g2.setFont(myfont);
            g2.setColor(Color.WHITE);

            //message
            if(messageOn==true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tileSize*3+gp.tileSize/2,gp.tileSize);

                messageCounter++;

                if(messageCounter>100){ //frame
                    messageCounter=0;
                    messageOn=false;
                }
            }
        }

        this.g2=g2;

        g2.setFont(myfont);
        g2.setColor(Color.WHITE);

        //TITLE STATE
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }

        //PLAY STATE
        if(gp.gameState==gp.playState){
            drawPlayerLife();
            drawKeyBooks(g2);
        }

        //PAUSE STATE
        if(gp.gameState==gp.pauseState){
            drawPauseScreen();
            drawPlayerLife();
            drawKeyBooks(g2);
        }

        //DIALOG STATE
        if(gp.gameState==gp.dialogueState){
            drawDialogueScreen();
            drawPlayerLife();
            drawKeyBooks(g2);
        }
    }

    public void drawPlayerLife(){

        int x=gp.tileSize/7;
        int y=gp.tileSize/10;
        int i=0;

        //DRAW MAX LIFE
        while(i<gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,48,48,null);
            i++;
            x+=gp.tileSize*0.6;
        }

        //RESET
        x=gp.tileSize/7;
        y=gp.tileSize/10;
        i=0;

        //DRAW CURRENT LIFE
        while(i<gp.player.life){
            g2.drawImage(heart_half,x,y,48,48,null);
            i++;
            if(i<gp.player.life){//replace half_heart with full_heart
                g2.drawImage(heart_full,x,y,48,48,null);
            }
            i++;
            x+=gp.tileSize*0.6;//next heart
        }
    }

    public void drawTitleScreen(){
        //COLOUR
        g2.setColor(new Color(33,27,33));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String text="The Garden of Lost Secrets";
        int x=getXforCenteredText(text);
        int y=gp.tileSize+gp.tileSize/5;

        //SHADOW->to the text
        g2.setColor(Color.gray);
        g2.drawString(text,x+3,y+3);

        //MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        //main character imagine
        x=gp.screenWidth/2-gp.screenWidth/8;
        y+=gp.tileSize-gp.tileSize/2;
        g2.drawImage(gp.player.down3,x,y,gp.tileSize*3,gp.tileSize*3,null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text="NEW GAME";
        x=getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3)); //defines the width of outlines of graphics which are rendered with a Graphics 2D -> 4 pixels
            g2.drawRoundRect(x-11,y-34,190,44,29,29);
        }

        text="LOAD GAME";
        x=getXforCenteredText(text);
        y+=gp.tileSize/2+gp.tileSize/8;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3)); //defines the width of outlines of graphics which are rendered with a Graphics 2D -> 4 pixels
            g2.drawRoundRect(x-12,y-33,214,44,29,29);
        }

        text="QUIT";
        x=getXforCenteredText(text);
        y+=gp.tileSize/2+gp.tileSize/8;
        g2.drawString(text,x,y);
        if(commandNum==2){
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3)); //defines the width of outlines of graphics which are rendered with a Graphics 2D -> 4 pixels
            g2.drawRoundRect(x-10,y-33,99,44,29,29);
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60));
        String text = "PAUSED";
        int x=getXforCenteredText(text);

        int y=gp.screenHeight/2;

        g2.drawString(text,x,y);
    }

    public int getXforCenteredText(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }

    public void drawDialogueScreen(){
        //WINDOW -> mini window dialog
        int x=gp.tileSize*2+gp.tileSize/2+gp.tileSize/5;
        int y=gp.tileSize/3;
        int width=gp.screenWidth-(gp.tileSize*4);
        int height=gp.tileSize*2;//+gp.tileSize/4;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,17F));
        x+=gp.tileSize/3;
        y+=gp.tileSize-gp.tileSize/2;

        //split the text inside the currentDialogue at these regex \n
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=25;//space between lines
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        //set colour
        Color c = new Color(0,0,0,195); //create a rgb color -> black  220->adjusting the opacity of the window
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255,215);//white
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4)); //defines the width of outlines of graphics which are rendered with a Graphics 2D -> 4 pixels
        g2.drawRoundRect(x+4,y+4,width-8,height-8,27,27);


    }

    public void drawKeyBooks(Graphics2D g2){
        if(gameFinished==true){
            g2.setFont(myfont);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x; int y;

            text="You finish the level!";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2+(gp.tileSize+gp.tileSize/3);
            g2.drawString(text,x,y);

            g2.setFont(myfont2);
            g2.setColor(Color.yellow);
            text="Good job!";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2+(gp.tileSize*2);
            g2.drawString(text,x,y);

            gp.gameThread=null;//end game
        }
        else {

            // display number of keys and books
            g2.setFont(myfont);
            g2.setColor(Color.WHITE);

            g2.drawImage(keyImage, gp.tileSize / 7, gp.tileSize / 7+gp.tileSize/2, 45, 45, null);
            g2.drawString("x = " + gp.player.hasKey, 50, 74);
            g2.drawImage(bookImage, gp.tileSize / 8, gp.tileSize / 5 + gp.tileSize / 2+gp.tileSize/2, 45, 45, null);
            g2.drawString("x = " + gp.player.hasBook, 52, 105);
        }
    }
}
