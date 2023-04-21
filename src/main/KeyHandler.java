package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {  //KeyListener -> the listener interface for receiving keyboard events(keystrokes)

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,enterPressed,hPressed,fPressed;
    //DEBUG
    boolean checkDrawTime=false;

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //I don't need it in these game

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();    //getKeyCode()->returns the integer keyCode associated with the key in this event

        //TITLE STATE
        if(gp.gameState==gp.titleState){

            if(code == KeyEvent.VK_W){ //if I press W
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=2;
                }
            }

            if(code == KeyEvent.VK_S){ //if I press S
                gp.ui.commandNum++;
                if(gp.ui.commandNum>2){
                    gp.ui.commandNum=0;
                }
            }

            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.commandNum==0){
                    gp.gameState=gp.playState;
                    gp.playMusic(5);
                }
                if(gp.ui.commandNum==1){
                    //add later for load game
                }
                if(gp.ui.commandNum==2) {//exit
                    System.exit(0);
                }
            }
        }

        //PLAY STATE
        else if(gp.gameState==gp.playState){
            if(code == KeyEvent.VK_W){ //if I press W
                upPressed=true;
            }

            if(code == KeyEvent.VK_S){ //if I press S
                downPressed=true;
            }

            if(code == KeyEvent.VK_A){ //if I press A
                leftPressed=true;
            }

            if(code == KeyEvent.VK_D){ //if I press D
                rightPressed=true;
            }

            if(code == KeyEvent.VK_P){ //if I press P -> PAUSE
                gp.gameState= gp.pauseState;
                gp.stopMusic();
            }
            if(code == KeyEvent.VK_ENTER){//press to start dialogue
                enterPressed=true;
            }
            if(code == KeyEvent.VK_H){//press to HEAL
                hPressed=true;
            }
            if(code==KeyEvent.VK_F){//FIGHT
                fPressed=true;
            }
            if(code==KeyEvent.VK_ESCAPE){
                gp.gameState=gp.titleState;
                gp.stopMusic();
            }
        }

        //PAUSE STATE
        else if(gp.gameState==gp.pauseState){
            if(code==KeyEvent.VK_P){
                gp.gameState=gp.playState;
                gp.playMusic(5);
            }
        }

        //DIALOGUE STATE
        else if(gp.gameState==gp.dialogueState){
            if(code==KeyEvent.VK_ENTER){//we finished the dialogue state
                gp.gameState=gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){ //if I release W
            upPressed=false;
        }

        if(code == KeyEvent.VK_S){ //if I release S
            downPressed=false;
        }

        if(code == KeyEvent.VK_A){ //if I release A
            leftPressed=false;
        }

        if(code == KeyEvent.VK_D){ //if I release D
            rightPressed=false;
        }

    }
}
