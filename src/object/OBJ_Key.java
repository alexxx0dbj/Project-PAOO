package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp){

        super(gp);

        name="Key";
        try{
            down1= ImageIO.read(getClass().getResourceAsStream("/objects/cheie2.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
