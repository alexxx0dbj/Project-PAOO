package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {  //KeyListener -> the listener interface for receiving keyboard events(keystrokes)

    public boolean upPressed, downPressed, leftPressed, rightPressed;


    @Override
    public void keyTyped(KeyEvent e) {
        //I don't need it in these game

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();    //getKeyCode()->returns the integer keyCode associated with the key in this event

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
