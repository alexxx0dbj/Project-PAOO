package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends Entity {
    public OBJ_Boots(GamePanel gp){

        super(gp);
        name="Boots";
        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/objects/papuci2.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
