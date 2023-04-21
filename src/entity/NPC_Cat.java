package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_Cat extends Entity{
    //constructor
    public NPC_Cat(GamePanel gp){
        super(gp);

        direction="down";
        speed=1;
        getNpcCatImage();
        setDialogue();
    }
    public void getNpcCatImage() {
        try {
            //load the imagines for player
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/spate_drept.png")); //up1=view from back right foot
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/spate_stang.png")); //up2=view from back left foot
            up3 = ImageIO.read(getClass().getResourceAsStream("/npc/spate_drept.png")); //up3=view from back when it sits
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/stanga_picior_spate.png")); //left1=view from left, right foot in front
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/stanga_picior_fata.png")); //left2=view from left, left foot in front
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/dreapta_picior_fata.png")); //right1=view from right,right foot in front
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/dreapta_picior_spate.png")); //right2=view from right,left foot in front
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/fata_dreptul.png")); //down1=view back right foot
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/fata_stang.png")); //down2=view back left foot
            down3 = ImageIO.read(getClass().getResourceAsStream("/npc/fata_dreptul.png")); //down3=view back when it sits
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setDialogue(){
        dialogues[0]="Hello, queen Freya!\nI am Meow-Meow the talking cat!";
        dialogues[1]="So you've come to The Garden of Lost Secrets\nto recover The Magic Stone?";
        dialogues[2]="In these adventure I'll be one of your friends\nand guides!";
        dialogues[3]="Good luck, Freya!";
    }

    public void speak(){ //create for costume stuff
        super.speak();
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter==100){
            Random random = new Random();
            int i = random.nextInt(100)+1; //random number from 1 to 100

            if(i<=25){ direction="up"; }  //25% percent of the time go up
            if(i>25&&i<=50){direction="down"; }  //25% percent of the time go down
            if(i>50&&i<=75){ direction="left"; }  //25% percent of the time go left
            if(i>75&&i<=100){ direction="right"; }  //25% percent of the time go right

            actionLockCounter=0;//reset
        }
    }

}
