package monster;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class MY_SLIME extends Entity {
    //constructor
    public MY_SLIME(GamePanel gp) {
        super(gp);

        type=2;//type of mob
        name="Green Slime";
        speed=1;
        maxLife=6; //his max life
        life=maxLife; //at the begging life is max life

        //setting solidArea of Smile
        solidArea.x=4;
        solidArea.y=18;
        solidArea.width=56;
        solidArea.height=38;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getImage();
    }

    public void getImage(){
        try {
            //load the imagines for monster
            up1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down1.png")); //no jump up
            up2 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down2.png")); //jump up
            down1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down1.png")); //no jump down
            down2 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down2.png")); //jump down
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down1.png")); //no jump left
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down2.png")); //jump left
            right1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down1.png")); //no jump right
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down2.png")); //jump right
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //setting slime's behavior->the way he moves
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter==100){
            Random random = new Random();
            int i = random.nextInt(100)+1;//random number from 1 to 100
            if(i<=25){
                direction="up";//25% percent of the time go up
            }
            if(i>25&&i<=50){//25% percent of the time go down
                direction="down";
            }
            if(i>50&&i<=75){//25% percent of the time go left
                direction="left";
            }
            if(i>75&&i<=100){//25% percent of the time go right
                direction="right";
            }
            actionLockCounter=0;//reset
        }
    }
    public void damageReaction(){//monster react to damage, when it is attacked, it moves away from player
        actionLockCounter=0;
        direction=gp.player.direction;//change direction away the player
    }
}
