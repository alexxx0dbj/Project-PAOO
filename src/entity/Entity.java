package entity;
import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

//Entity class->this stores variables that will be used in player, monster and NPC classes
public class Entity {

    public GamePanel gp;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, right1, right2; //BufferedImage -> it describes an Image with an accessible buffer on image data(we use this to store our image files)
                                                                                            //for moving poses of the character
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2; //preparing attack images

    public BufferedImage image,image2,image3; //for heart imagines display

    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);//default solidArea for all entities
                                                                                //for collision -> create an invisible rectangle for storing x,y,width, height
    public Rectangle attackArea = new Rectangle(0,0,0,0); //entity attack area
    public boolean collision = false;
    public int solidAreaDefaultX, solidAreaDefaultY; //for CollisionChecker
    String dialogues[] = new String[30]; //for npc lines dialogue



    //variables for STATE
    public int worldX, worldY;
    public String direction = "down"; //predefined direction
    public int spriteNumber = 1;
    int dialogueIndex = 0; //for npc's dialogue
    public boolean invincible = false; //for invincible methods
    public boolean collisionOn = false;
    boolean attacking = false; //for attack
    public boolean alive = true; //for death effect
    public boolean dying = false; //for death effect
    boolean hpBarOn = false; //for turn off on or hp bar


    //variables for COUNTING
    public int spriteCounter = 0; //for player
    public int actionLockCounter = 0; //for npc
    public int invincibleCounter = 0; //for invincible
    int dyingCounter = 0; //for death effect
    int hpBarCounter = 0; //for turn off on or hp bar


    //variables for MAIN CHARACTER/NPC/MONSTERS/OBJ ATTRIBUTES
    public int speed;
    public String name;
    public int maxLife; //CHARACTER STATUS->player and monsters
    public int life; //CHARACTER STATUS->player and monsters
    public int type; //0=player,1=npc,2=monster


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction(){} //priority->override in NPC_Cat and MY_SLIME

    public void damageReaction(){} //override it in MY_SLIME class

    public void speak(){
        if(dialogues[dialogueIndex] == null){
        dialogueIndex = 0; //no text->back to index zero
    }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){//fix npc position when there is a dialogue
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }

    public void update(){

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this); //pass npc (cat for example) as an entity
        gp.cChecker.checkObject(this,false); //if it's an object

        //the collision happens between monsters and npcs
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        //MONSTER ATTACKING PLAYER
        if(this.type==2 && contactPlayer == true){
            if(gp.player.invincible == false){ //we can give damage
                gp.player.life-= 1;
                gp.player.invincible = true;
            }
        }

        //IF COLLISION IS FALSE, PLAYER CAN'T MOVE
        if(collisionOn == false){
            switch (direction){
                case "up": worldY-=speed; break;
                case "down": worldY+=speed; break;
                case "left": worldX-=speed; break;
                case "right": worldX+=speed; break;
            }
        }

        //sprite animation
        spriteCounter++;
        if(spriteCounter>30){
            if(spriteNumber == 1 ){
                spriteNumber=2;
            }
            else if(spriteNumber == 2){
                spriteNumber=1;
            }
            spriteCounter =0; //reset
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter>40){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
    public void draw(Graphics2D g2){ //override in player
        //find x and y base of the player position
            int screenX=worldX-gp.player.worldX + gp.player.screenX;
            int screenY=worldY-gp.player.worldY+ gp.player.screenY;
            BufferedImage image = null;

            //if it's in camera frame->we draw it
            //to draw just the "minimap" not all the map ->render while walk
            if(worldX+4*gp.tileSize>gp.player.worldX-gp.player.screenY &&
                    worldX-2*gp.tileSize<gp.player.worldX+gp.player.screenX &&
                    worldY+gp.tileSize>gp.player.worldY-gp.player.screenY &&
                    worldY-3*gp.tileSize<gp.player.worldY+gp.player.screenY){

                switch(direction){
                    case "up":
                        if(spriteNumber == 1){image = up1;}
                        if(spriteNumber == 2){image = up2;}
                        //if(spriteNumber == 3){image = up3;}
                        break;
                    case "down":
                        if(spriteNumber == 1){image = down1;}
                        if(spriteNumber == 2){image = down2;}
                        //if(spriteNumber == 3){image = down3;}
                        break;
                    case "left":
                        if(spriteNumber == 1){image = left1;}
                        if(spriteNumber == 2){image = left2;}
                        break;
                    case "right":
                        if(spriteNumber == 1){image = right1;}
                        if(spriteNumber == 2){image = right2;}
                        break;
                }

                //monster health bar above entity
                if(type==2 && hpBarOn==true){

                    double oneScale=(double)gp.tileSize/maxLife; //divide the bar size by the monster max hp
                    double hpBarValue=oneScale*life;
                    int conversion=(int)hpBarValue;

                    g2.setColor(new Color(38,8,10));
                    g2.fillRect(screenX-1,screenY-8,gp.tileSize+3,9);

                    g2.setColor(new Color(184,11,43));
                    g2.fillRect(screenX,screenY-7,conversion,6);

                    hpBarCounter++;
                    if(hpBarCounter>600){//after 10 seconds bar disappear -> similar to reset
                        hpBarCounter=0;
                        hpBarOn=false;
                    }
                }

                if(invincible==true){
                    hpBarOn=true;
                    hpBarCounter=0;//reset
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.6f));
                }
                if(dying==true){//for death effect
                    dyingAnimation(g2);
                }

                g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

            }
    }

    public void dyingAnimation(Graphics2D g2){ //for death effect
        dyingCounter++;
        int i=5;
        if(dyingCounter<=i){changeAlpha(g2,0f);}
        if(dyingCounter>i&&dyingCounter<=i*2){changeAlpha(g2,1f);}
        if(dyingCounter>i*2&&dyingCounter<=i*3){changeAlpha(g2,0f);}
        if(dyingCounter>i*3&&dyingCounter<=i*4){changeAlpha(g2,1f);}
        if(dyingCounter>i*4&&dyingCounter<=i*5){changeAlpha(g2,0f);}
        if(dyingCounter>i*5&&dyingCounter<=i*6){changeAlpha(g2,1f);}
        if(dyingCounter>i*6&&dyingCounter<=i*7){changeAlpha(g2,0f);}
        if(dyingCounter>i*7&&dyingCounter<=i*8){changeAlpha(g2,1f);}
        if(dyingCounter>i*8){//it dies
            dying=false;
            alive=false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){//for opacity and for dyingAnimation method
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }
}
