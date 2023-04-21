package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp){
    super(gp);
    name="Heart";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2= ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3= ImageIO.read(getClass().getResourceAsStream("/objects/heart_empty.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
