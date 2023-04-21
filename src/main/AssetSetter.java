package main;

import entity.NPC_Cat;
import monster.MY_SLIME;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setObject(){
        //keys
            //1
        gp.obj[0]=new OBJ_Key(gp);
        gp.obj[0].worldX = 20*gp.tileSize;
        gp.obj[0].worldY=49*gp.tileSize;
            //2
        gp.obj[1]=new OBJ_Key(gp);
        gp.obj[1].worldX = 48*gp.tileSize;
        gp.obj[1].worldY=35*gp.tileSize;
            //3
        gp.obj[2]=new OBJ_Key(gp);
        gp.obj[2].worldX = 47*gp.tileSize;
        gp.obj[2].worldY=47*gp.tileSize;

        //magic books
        gp.obj[3]=new OBJ_MagicBook(gp);
        gp.obj[3].worldX = 6*gp.tileSize;
        gp.obj[3].worldY=22*gp.tileSize;


        //fence 1
            //basic from left
        gp.obj[4]=new OBJ_FenceBasic(gp);
        gp.obj[4].worldX = 8*gp.tileSize;
        gp.obj[4].worldY=19*gp.tileSize;
            //gate
        gp.obj[5]=new OBJ_GATE(gp);
        gp.obj[5].worldX = 9*gp.tileSize;
        gp.obj[5].worldY=19*gp.tileSize;
            //basic next to the door right
        gp.obj[6]=new OBJ_FenceBasic(gp);
        gp.obj[6].worldX = 10*gp.tileSize;
        gp.obj[6].worldY=19*gp.tileSize;
            //basic from right
        gp.obj[7]=new OBJ_FenceBasic(gp);
        gp.obj[7].worldX = 11*gp.tileSize;
        gp.obj[7].worldY=19*gp.tileSize;

        //fence 2
            //basic from left
        gp.obj[8]=new OBJ_FenceBasic(gp);
        gp.obj[8].worldX = 8*gp.tileSize;
        gp.obj[8].worldY=16*gp.tileSize;
            //gate
        gp.obj[9]=new OBJ_GATE(gp);
        gp.obj[9].worldX = 9*gp.tileSize;
        gp.obj[9].worldY=16*gp.tileSize;
            //basic next to the door right
        gp.obj[10]=new OBJ_FenceBasic(gp);
        gp.obj[10].worldX = 10*gp.tileSize;
        gp.obj[10].worldY=16*gp.tileSize;
            //basic from right
        gp.obj[11]=new OBJ_FenceBasic(gp);
        gp.obj[11].worldX = 11*gp.tileSize;
        gp.obj[11].worldY=16*gp.tileSize;

        //fence 3
            //basic from left
        gp.obj[12]=new OBJ_FenceBasic(gp);
        gp.obj[12].worldX = 8*gp.tileSize;
        gp.obj[12].worldY=13*gp.tileSize;
            //gate
        gp.obj[13]=new OBJ_GATE(gp);
        gp.obj[13].worldX = 9*gp.tileSize;
        gp.obj[13].worldY=13*gp.tileSize;
            //basic next to the door right
        gp.obj[14]=new OBJ_FenceBasic(gp);
        gp.obj[14].worldX = 10*gp.tileSize;
        gp.obj[14].worldY=13*gp.tileSize;
            //basic from right
        gp.obj[15]=new OBJ_FenceBasic(gp);
        gp.obj[15].worldX = 11*gp.tileSize;
        gp.obj[15].worldY=13*gp.tileSize;

        //chest
        gp.obj[16]=new OBJ_Chest(gp);
        gp.obj[16].worldX = 20*gp.tileSize;
        gp.obj[16].worldY=7*gp.tileSize;
    }

    public void setNPC(){  //method->instantiation npc cat
        gp.npc[0]=new NPC_Cat(gp);
        gp.npc[0].worldX=gp.tileSize*24;
        gp.npc[0].worldY=gp.tileSize*12;
    }

    public void setMonster(){  //placing monsters on the map
        gp.monster[0]=new MY_SLIME(gp);
        gp.monster[0].worldX=gp.tileSize*22;
        gp.monster[0].worldY=gp.tileSize*15;

        gp.monster[1]=new MY_SLIME(gp);
        gp.monster[1].worldX=gp.tileSize*38;
        gp.monster[1].worldY=gp.tileSize*18;

        gp.monster[2]=new MY_SLIME(gp);
        gp.monster[2].worldX=gp.tileSize*33;
        gp.monster[2].worldY=gp.tileSize*20;
    }
}
