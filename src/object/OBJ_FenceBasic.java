package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_FenceBasic extends Entity {
    public OBJ_FenceBasic(GamePanel gp){

        super(gp);
        name="FenceBasic";
        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/objects/gard_full.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
