package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public  EventHandler(GamePanel gp){
        this.gp=gp;

        eventRect=new Rectangle();
        eventRect.x=24;
        eventRect.y=24;
        eventRect.width=8;
        eventRect.height=8;
        eventRectDefaultX= eventRect.x;
        eventRectDefaultY=eventRect.y;
    }

    public void checkEvent(){
       // if(hit(20,40,"left")==true){damagePit(gp.dialogueState);}
        if(hit(28,39,"right")==true){healingPool(gp.dialogueState);}
        if(hit(32,10,"right")==true){healingPool(gp.dialogueState);}
    }
    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;

        //getting player's current solidArea positions
        gp.player.solidArea.x=gp.player.worldX+gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY+gp.player.solidArea.y;

        //getting eventRect's solidArea positions
        eventRect.x=eventCol*gp.tileSize+eventRect.x;
        eventRect.y=eventRow*gp.tileSize+eventRect.y;

        //checking if player's solidArea is colliding with eventRect's solidArea
        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(reqDirection)||reqDirection.contentEquals("any")){
                hit=true;
            }
        }

        //After checking the collision reset the solidArea x and y
        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY;
        eventRect.x=eventRectDefaultX;
        eventRect.y=eventRectDefaultY;

        return hit;
    }
    /*
    public void damagePit(int gameState){
        gp.gameState=gameState;
        gp.ui.currentDialogue="You fall into a damage pit!";
        gp.player.life-=1;
    }
     */

    public void healingPool(int gameState){
       if(gp.keyH.hPressed==true){
            gp.gameState=gameState;
            gp.ui.currentDialogue="You life has been recovered!";
            gp.player.life=gp.player.maxLife;
        }
        gp.keyH.hPressed=false;
    }

}
