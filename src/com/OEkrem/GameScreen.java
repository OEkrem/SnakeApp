package com.OEkrem;


import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class GameScreen extends JFrame{

    public GameScreen(String title) throws HeadlessException {
        super(title);
    }

    public static void Calistir(){
        
        try {
            
            GameScreen gameScreen = new GameScreen("Yılan Oyunu - Was made by Onur Yıldırım");
            gameScreen.setSize(796,600);
            
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - gameScreen.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - gameScreen.getHeight()) / 2);
            
            //System.out.println("X : (dimension-ekran) : " + x + " : ("+ dimension.getWidth()+"-" + ekran.getWidth()+ ")");
            //System.out.println("Y : (dimension-ekran) : " + y + " : ("+ dimension.getHeight()+"-" + ekran.getHeight()+ ")");
            
            BufferedImage icon = ImageIO.read(new File("YilanOyunu/icons/yilanIcon.png"));
            gameScreen.setLocation(x, y);
            gameScreen.setIconImage(icon);
            gameScreen.setResizable(false);
            gameScreen.setFocusable(false);

            gameScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            Game game = new Game();
            game.requestFocus();
            game.addKeyListener(game);
            game.setFocusable(true);
            game.setFocusTraversalKeysEnabled(false); // klavye işlemlerimizi anlamamız için gerekli bişey..
            
            gameScreen.add(game);
            gameScreen.setVisible(true);
            
            
        } catch (IOException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
