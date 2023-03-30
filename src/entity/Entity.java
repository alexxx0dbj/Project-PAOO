package entity;

import java.awt.image.BufferedImage;

//Entity class->this stores variables that will be used in player, monster and NPC classes
public class Entity {

    public int worldX, worldY;
    public int speed;

    //BufferedImage -> it describes an Image with an accesible buffer on image data(we use this to store our image files)
    public BufferedImage up1, up2,up3, down1, down2,down3, left1, left2, right1,right2;
    public String direction;

    public int spriteContor = 0;
    public int spriteNumber = 1;
}
