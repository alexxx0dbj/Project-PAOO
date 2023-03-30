package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    //constructor
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;

        screenX=gp.screenWidth/2 -(gp.tileSize/2+gp.tileSize/4); //subtract a half tile length from screenX
        screenY=gp.screenHeight/2 - (gp.tileSize-gp.tileSize/4); //subtract a half tile length from screenY

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX=gp.tileSize*30;  //start position
        worldY=gp.tileSize*30;
        speed=4; //1 e ok
        direction="down";
    }

    public void getPlayerImage() {
        try {
            //load the imagines for player
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/spate_drptl.png")); //up1=vedere spate picior drept
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/spate_stg.png")); //up12=vedere spate picior stang
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/spate_sta.png")); //up3=vedere spate sta pe loc
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/vedere_stg_picdrpt.png")); //left1=vedere stanga picior drept in fata
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/vedere_stg_picstg.png")); //left2=vedere stanga picior stang in fata
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/vedere_drpt_picdrpt.png")); //right1=vedere dreapta picior drept in fata
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/vedere_drpt_picstg.png")); //right2=vedere dreapta picior stang in fata
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/fata_drpt.png")); //down1=vedere fata picior drept
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/fata_stg.png")); //down2=vedere fata picior stang
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/fata_sta.png")); //down3=vedere fata sta pe loc
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //update method
    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true ||keyH.rightPressed == true ){
            if(keyH.upPressed==true){
                direction="up";
                worldY-=speed;
            }
            else if(keyH.downPressed==true){
                direction="down";
                worldY+=speed;
            }
            else if(keyH.leftPressed==true){
                direction="left";
                worldX-=speed;
            }
            else if(keyH.rightPressed==true){
                direction="right";
                worldX+=speed;
            }

            //sprite animation
            spriteContor++;
            if(spriteContor>30){
                if(spriteNumber==1 ){
                    spriteNumber=2;
                }
                else if(spriteNumber==2){
                    spriteNumber=1;
                }
                spriteContor=0;  //reset
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
        switch(direction){
            case "up":
                if(spriteNumber == 1){
                    image = up1;

                }
                if(spriteNumber == 2){
                    image = up2;
                }
                if(spriteNumber == 3){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNumber == 1){
                    image = down1;

                }
                if(spriteNumber == 2){
                    image = down2;
                }
                if(spriteNumber == 3){
                    image = down3;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left1;

                }
                if(spriteNumber == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image = right1;

                }
                if(spriteNumber == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,64,64,null); //imagineObservor=null;
    }
}
