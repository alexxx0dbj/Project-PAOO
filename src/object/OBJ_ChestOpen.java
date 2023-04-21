package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ChestOpen extends Entity {
    public OBJ_ChestOpen(GamePanel gp){
        super(gp);
        name="ChestOpen";
        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/objects/cufar_deschis.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
