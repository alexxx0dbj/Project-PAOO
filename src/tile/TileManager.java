package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][]; //will store the map01.txt

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
            //instantiere tile array
            for(int i=1;i<=9;i++)
            {
                tile[i]=new Tile();
                tile[i].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_jos_drpt.png"));
            }
            tile[10]=new Tile();
            tile[10].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_jos_drpt.png"));

            tile[11]=new Tile();
            tile[11].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_jos_drpt.png"));
            tile[12]=new Tile();
            tile[12].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_jos_stg.png"));
            tile[13]=new Tile();
            tile[13].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_sus_drpt.png"));
            tile[14]=new Tile();
            tile[14].image= ImageIO.read(getClass().getResourceAsStream("/tiles/poteca_colt_iarba_sus_stg.png"));

            tile[23]=new Tile();
            tile[23].image= ImageIO.read(getClass().getResourceAsStream("/tiles/iarba_basic.png"));
            tile[24]=new Tile();
            tile[24].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_sus_stg.png"));
            tile[25]=new Tile();
            tile[25].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_sus_drpt.png"));
            tile[26]=new Tile();
            tile[26].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_mijloc_stg.png"));
            tile[27]=new Tile();
            tile[27].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_mijloc_drpt.png"));
            tile[28]=new Tile();
            tile[28].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_jos_stg.png"));
            tile[29]=new Tile();
            tile[29].image= ImageIO.read(getClass().getResourceAsStream("/tiles/copac_jos_drpt.png"));






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

            int worldX=worldCol*gp.tileSize;  //check the worldX tiles   0*48??modificat la mine
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

