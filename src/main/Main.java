package main;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //create window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this lets the window properly close when user clicks the close ("x") button
        window.setResizable(false); //we can't resize these window
        window.setTitle("The Garden of Lost Secrets");

        //add GamePanel to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //pack()->so this window to be sized to fit the preferred size and layouts of its subcomponents(=GamePanel)
        window.pack();

        window.setLocationRelativeTo(null); //window will be display at the center of the screen = not specify the location of the window
        window.setVisible(true); //ture->so we can see the window

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
