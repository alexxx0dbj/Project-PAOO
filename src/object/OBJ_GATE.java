package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_GATE extends Entity {
    public OBJ_GATE(GamePanel gp){
        super(gp);
        name="Gate";
        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/objects/poarta.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
