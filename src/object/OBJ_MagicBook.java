package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_MagicBook extends Entity {
    public OBJ_MagicBook(GamePanel gp){

        super(gp);
        name="MagicBook";
        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/objects/carte.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
