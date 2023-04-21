package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][]; //will store the map01.txt

    //constructor
    public TileManager(GamePanel gp){
        this.gp= gp;
        tile= new Tile[100]; //100 tiles
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){
        try{
            //instantiation tile array
            tile[10]=new Tile();
            tile[10].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_basic.png"));

            tile[11]=new Tile();
            tile[11].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_jos_drpt.png"));
            tile[12]=new Tile();
            tile[12].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_jos_stg.png"));
            tile[13]=new Tile();
            tile[13].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_sus_drpt.png"));
            tile[14]=new Tile();
            tile[14].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_sus_stg.png"));

            tile[15]=new Tile();
            tile[15].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_piatra_jos_drpt.png"));
            tile[16]=new Tile();
            tile[16].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_piatra_jos_stg.png"));
            tile[17]=new Tile();
            tile[17].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_piatra_sus_stg.png"));
            tile[18]=new Tile();
            tile[18].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_piatra_sus_drpt.png"));

            tile[19]=new Tile();
            tile[19].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_iarba_lateral_sus.png"));
            tile[20]=new Tile();
            tile[20].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_iarba_lateral_jos.png"));
            tile[21]=new Tile();
            tile[21].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_iarba_lateral_drpt.png"));
            tile[22]=new Tile();
            tile[22].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_iarba_lateral_stg.png"));


            tile[23]=new Tile();
            tile[23].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_coliziune.png")); //!for the collision
            tile[23].collision=true;

            tile[24]=new Tile();
            tile[24].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_sus_stg.png"));
            tile[24].collision=true;
            tile[25]=new Tile();
            tile[25].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_sus_drpt.png"));
            tile[25].collision=true;
            tile[26]=new Tile();
            tile[26].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_mijloc_stg.png"));
            tile[26].collision=true;
            tile[27]=new Tile();
            tile[27].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_mijloc_drpt.png"));
            tile[27].collision=true;
            tile[28]=new Tile();
            tile[28].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_jos_stg.png"));
            tile[28].collision=true;
            tile[29]=new Tile();
            tile[29].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_jos_drpt.png"));
            tile[29].collision=true;

            tile[30]=new Tile();
            tile[30].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_basic.png")); //!the grass I can walk

            tile[31]=new Tile();
            tile[31].image= ImageIO.read(getClass().getResourceAsStream("/tiles/apa_basic.png"));
            tile[31].collision=true;

            tile[32]=new Tile();
            tile[32].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_putina_cotita_apa_jos_stg.png"));
            tile[32].collision=true;
            tile[33]=new Tile();
            tile[33].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_putina_cotita_apa_sus_stg.png"));
            tile[33].collision=true;
            tile[34]=new Tile();
            tile[34].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_putina_cotita_apa_jos_drpt.png"));
            tile[34].collision=true;
            tile[35]=new Tile();
            tile[35].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_putina_cotita_apa_sus_drpt.png"));
            tile[35].collision=true;

            tile[36]=new Tile();
            tile[36].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_multa_cotita_apa_jos_stg.png"));
            tile[37]=new Tile();
            tile[37].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_multa_cotita_apa_jos_drpt.png"));
            tile[38]=new Tile();
            tile[38].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_multa_cotita_apa_sus_stg.png"));
            tile[39]=new Tile();
            tile[39].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_multa_cotita_apa_sus_drpt.png"));

            tile[40]=new Tile();
            tile[40].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_apa_lateral_jos.png"));
            tile[40].collision=true;
            tile[41]=new Tile();
            tile[41].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_apa_lateral_drpt.png"));
            tile[41].collision=true;
            tile[42]=new Tile();
            tile[42].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_apa_lateral_stg.png"));
            tile[42].collision=true;
            tile[43]=new Tile();
            tile[43].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_apa_lateral_sus.png"));
            tile[43].collision=true;

            tile[44]=new Tile();
            tile[44].image= ImageIO.read(getClass().getResourceAsStream("/tiles/pod_multa_iarba.png"));
            tile[45]=new Tile();
            tile[45].image= ImageIO.read(getClass().getResourceAsStream("/tiles/pod_doua_picioare.png"));
            tile[46]=new Tile();
            tile[46].image= ImageIO.read(getClass().getResourceAsStream("/tiles/pod_un_picior.png"));

            tile[47]=new Tile();
            tile[47].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_mijloc_drpt_gard.png"));
            tile[48]=new Tile();
            tile[48].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_mijloc_stg_gard.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is=getClass().getResourceAsStream(filePath); //import txt file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //read the content of this txt file

            int col=0;
            int row=0;

            while(col< gp.maxWorldCol && row<gp.maxWorldRow){
                String line=br.readLine(); //read a line of a text
                while(col<gp.maxWorldCol){
                    String numbers[] = line.split(" "); //splits this string at space

                    int num = Integer.parseInt(numbers[col]); //use col as an index for numbers[] array

                    //we store the extracted number in the mapTileNum[][]
                    mapTileNum[col][row]=num;
                    col++; //continue this until everything in the numbers[] is stored in the mapTileNum[][]
                }
                if(col==gp.maxWorldCol){
                    col = 0;
                    row++;
                }

            }

            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow=0;


        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){

            int tilenum = mapTileNum[worldCol][worldRow];//extract a tile number which is stored in mapTileNum[0][0]

            int worldX=worldCol*gp.tileSize;  //check the worldX tiles   
            int worldY=worldRow*gp.tileSize;
            //where on the screen when need to draw
            int screenX=worldX-gp.player.worldX + gp.player.screenX;
            int screenY=worldY-gp.player.worldY+ gp.player.screenY;

            //to draw just the "minimap" not all the map ->render while walk
            if(worldX+4*gp.tileSize>gp.player.worldX-gp.player.screenY &&
                    worldX-2*gp.tileSize<gp.player.worldX+gp.player.screenX &&
                    worldY+gp.tileSize>gp.player.worldY-gp.player.screenY &&
                    worldY-3*gp.tileSize<gp.player.worldY+gp.player.screenY){

                g2.drawImage(tile[tilenum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }
            worldCol++;  //going to draw the next line
            if(worldCol==gp.maxWorldCol){
                worldCol=0;  //reset
                worldRow++;
            }
        }

    }
}

