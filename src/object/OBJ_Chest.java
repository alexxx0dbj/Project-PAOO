package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {
    public OBJ_Chest(GamePanel gp){
        super(gp);

        name="Chest";
        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/objects/cufar_inchis.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
