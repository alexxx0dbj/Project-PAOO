package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Boots;
import object.OBJ_ChestOpen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    //GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey=0; //number of keys that we collect
    public int hasBook=0; //number of magic books that we collect


    //constructor
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp); //call constructor of the superclass of this class and passing these gp
        //this.gp=gp;
        this.keyH=keyH;

        screenX=gp.screenWidth/2 -(gp.tileSize/2+gp.tileSize/4); //subtract a half tile length from screenX
        screenY=gp.screenHeight/2 - (gp.tileSize-gp.tileSize/4); //subtract a half tile length from screenY

        //instance for rectangular for collision
        solidArea = new Rectangle();
        solidArea.x=22;
        solidArea.y=20;
        solidArea.width=30;
        solidArea.height=40;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        //player attack area
        attackArea.width=40;
        attackArea.height=40;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        //start position
        worldX=gp.tileSize*20;  //left-right
        worldY=gp.tileSize*39; //up-down
        speed=3;
        direction="down";

        //PLAYER STATUS
            //1 life->half heart
            //2 lives->full heart
            //8 lives->4 hearts
        maxLife = 8;
        life=maxLife;
    }

    public void getPlayerImage() {
            //load the imagines for player
            up1 = setup("/player/spate_drptl.png",gp.tileSize,gp.tileSize); //up1=view from back right foot
            up2 = setup("/player/spate_stg.png",gp.tileSize,gp.tileSize); //up2=view from back left foot
            up3 = setup("/player/spate_sta.png",gp.tileSize,gp.tileSize); //up3=view from back when sits
            left1 = setup("/player/vedere_stg_picdrpt.png",gp.tileSize,gp.tileSize); //left1=view from left, right foot in front
            left2 = setup("/player/vedere_stg_picstg.png",gp.tileSize,gp.tileSize); //left2=view from left, left foot in front
            right1 = setup("/player/vedere_drpt_picdrpt.png",gp.tileSize,gp.tileSize); //right1=view from right,right foot in front
            right2 = setup("/player/vedere_drpt_picstg.png",gp.tileSize,gp.tileSize); //right2=view from right,left foot in front
            down1 = setup("/player/fata_drpt.png",gp.tileSize,gp.tileSize); //down1=view back right foot
            down2 = setup("/player/fata_stg.png",gp.tileSize,gp.tileSize); //down2=view back left foot
            down3 = setup("/player/fata_sta.png",gp.tileSize,gp.tileSize); //down3=view back when sits
    }

    public void getPlayerAttackImage(){
        attackUp1=setup("/player/attack_up1.png",gp.tileSize,gp.tileSize*2); //view up with weapon withdraw
        attackUp2=setup("/player/attack_up2.png",gp.tileSize,gp.tileSize*2); //view up with weapon in attack
        attackDown1=setup("/player/attack_down1.png",gp.tileSize,gp.tileSize*2); //view down with weapon withdraw
        attackDown2=setup("/player/attack_down2.png",gp.tileSize,gp.tileSize*2); //view down with weapon in attack
        attackLeft1=setup("/player/attack_left1.png",gp.tileSize*2,gp.tileSize); //view left with weapon withdraw
        attackLeft2=setup("/player/attack_left2.png",gp.tileSize*2,gp.tileSize); //view left with weapon in attack
        attackRight1=setup("/player/attack_right1.png",gp.tileSize*2,gp.tileSize); //view right with weapon withdraw
        attackRight2=setup("/player/attack_right2.png",gp.tileSize*2,gp.tileSize); //view right with weapon in attack
    }

    public BufferedImage setup(String imageName, int width,int height){ //for getting imagine of attack player
        UtilityTool uTool = new UtilityTool();
        BufferedImage image=null;
        try{
            image=ImageIO.read(getClass().getResourceAsStream(imageName));
            image=uTool.scaleImage(image,width,height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    //update method
    public void update(){
        if(keyH.fPressed==true&& attacking ==false) {
            attacking =true;
            gp.playSoundEffect(6);
            keyH.fPressed=false;
        }
        if(attacking ==true){
            attacking();
        }

        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true ||keyH.rightPressed == true  || keyH.enterPressed==true){

            if(keyH.upPressed==true){ direction="up"; }
            else if(keyH.downPressed==true){ direction="down"; }
            else if(keyH.leftPressed==true){ direction="left"; }
            else if(keyH.rightPressed==true){ direction="right"; }

            //CHECK TILE COLLISION
            collisionOn=false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex=gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex=gp.cChecker.checkEntity(this,gp.npc); //npc array as target, this->player
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION -> checking Player->Monster collision
            int  monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            //IF COLLISION IS FALSE, PLAYER CAN'T MOVE
            if(collisionOn ==false&&keyH.enterPressed==false){
                switch (direction){
                    case "up": worldY-=speed; break;
                    case "down": worldY+=speed; break;
                    case "left": worldX-=speed; break;
                    case "right": worldX+=speed; break;
                }
            }

            gp.keyH.enterPressed=false; //reset after checking

            //sprite animation
            spriteCounter++;
            if(spriteCounter >15){//15
                if(spriteNumber==1 ){
                    spriteNumber=2;
                }
                else if(spriteNumber==2){
                    spriteNumber=1;
                }
                spriteCounter =0;  //reset
            }
        }

        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }

    public void attacking(){ //adjust in case I don't like the animation
    if(attacking ==true){
        spriteCounter++;
        if(spriteCounter <=10){
            spriteNumber=1;
        }
        else if(spriteCounter>10 && spriteCounter<=35){
            spriteNumber=2;

            //SAVE THE CURRENT worldX, worldY, solidArea for hit detection
            int currentWorldX=worldX; //current player's world X
            int currentWorldY=worldY; //current player's world Y
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight=solidArea.height;

            //ADJUSTING PLAYER'S worldX/worldY for the attackArea
            switch (direction){
                case "up": worldY-=attackArea.height;break;
                case "down": worldY+=attackArea.height;break;
                case "left": worldX-=attackArea.width; break;
                case "right": worldX+=attackArea.width; break;
            }

            //attackArea becomes solidArea
            solidArea.width=attackArea.width;
            solidArea.height=attackArea.height;

            //checking monster collision with the update worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //After checking the collision, we restore original position data
            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;
        }
        else if(spriteCounter >35){
            spriteNumber=1;
            spriteCounter =0;
            attacking =false;
        }
    }
    }

    public void contactMonster(int i){   //player receives damage
        if(i!=999){
            if(invincible==false){
                life-=1;
                gp.playSoundEffect(8);
                invincible=true;
            }
        }
    }

    public void damageMonster(int i){
        if(i!=999){
            if(gp.monster[i].invincible==false){
                gp.monster[i].life-=1;
                gp.playSoundEffect(7);
                gp.monster[i].invincible=true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life<=0){
                    gp.monster[i].dying=true;
                }
            }
        }
    }

    public void pickUpObject(int i){
        if(i!=999){
            String objectName=gp.obj[i].name;
            switch (objectName){
                case "Key":
                    gp.playSoundEffect(3);
                    hasKey++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You collect a key!");
                    break;
                case"MagicBook":
                    gp.playSoundEffect(3);
                    hasBook++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You collect a magic book!");
                    break;
                case"Gate":
                    if(hasKey>0){
                        gp.playSoundEffect(2);
                        gp.obj[i]=null;
                        hasKey--;
                        gp.ui.showMessage("You open a door!");
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                    //System.out.println("Key:"+hasKey); //for test
                    break;
                case"Chest":
                    if(hasBook>0){
                        gp.playSoundEffect(1);
                        gp.obj[i]=gp.obj[17]=new OBJ_ChestOpen(gp);
                        gp.obj[17].worldX = 20*gp.tileSize;
                        gp.obj[17].worldY=7*gp.tileSize;
                        hasBook--;
                        gp.ui.showMessage("You open the supply chest!");
                        gp.obj[18]=new OBJ_Boots(gp);
                        gp.obj[18].worldX = 21*gp.tileSize;
                        gp.obj[18].worldY=7*gp.tileSize;
                    }
                    //System.out.println("MagicBook:"+hasKey); //for test
                    break;
                case"Boots":
                    gp.playSoundEffect(4);
                    speed+=1;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Movement speed increased!");
                   // gp.ui.gameFinished=true;
                    break;
            }
        }
    }

    public void interactNPC(int i){
        if(i!=999){
            //System.out.println("You are hitting a npc!");
            if(gp.keyH.enterPressed==true){
                gp.gameState=gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    //draw method
    public void draw(Graphics2D g2){
        //set color
        //g2.setColor(Color.blue);      //setColor(Color c)->sets a color to use for drawing objects
        //draw a rectangular
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize);//x,y,width,height
        BufferedImage image = null;
        int tempScreenX=screenX;
        int tempScreenY=screenY;
        switch(direction){
            case "up":
                if(attacking ==false){
                    if(spriteNumber == 1){image = up1;}
                    if(spriteNumber == 2){image = up2;}
                    //if(spriteNumber == 3){image = up3;}

                }
                if(attacking ==true){
                    tempScreenY=tempScreenY-gp.tileSize;
                    if(spriteNumber == 1){image = attackUp1;}
                    if(spriteNumber == 2){image = attackUp2;}
                }
                break;
            case "down":
                if(attacking ==false){
                    if(spriteNumber == 1){image = down1;}
                    if(spriteNumber == 2){image = down2;}
                    //if(spriteNumber == 3){image = down3;}
                }
                if(attacking ==true){
                    if(spriteNumber == 1){image = attackDown1;}
                    if(spriteNumber == 2){image = attackDown2;}
                }
                break;
            case "left":
                if(attacking ==false){
                    if(spriteNumber == 1){image = left1;}
                    if(spriteNumber == 2){image = left2;}
                }
                if(attacking ==true){
                    tempScreenX=tempScreenX-gp.tileSize;
                    if(spriteNumber == 1){image = attackLeft1;}
                    if(spriteNumber == 2){image = attackLeft2;}
                }
                break;
            case "right":
                if(attacking ==false){
                    if(spriteNumber == 1){image = right1;}
                    if(spriteNumber == 2){image = right2;}
                }
               if(attacking ==true){
                    if(spriteNumber == 1){image = attackRight1;}
                    if(spriteNumber == 2){image = attackRight2;}
                }
                break;
        }

        //visual effect to invincible state->opacity
        if(invincible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.6f));
        }

        if(image == attackUp1 || image == attackUp2 || image == attackDown1 || image == attackDown2)
        {
            g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize, 2*gp.tileSize, null);
        }
        else if(image == attackLeft1  || image == attackLeft2  || image == attackRight1  || image == attackRight2){
            g2.drawImage(image, tempScreenX, tempScreenY, 2* gp.tileSize, gp.tileSize, null);
        }
        else{
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

        //RESET OPACITY
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
}
