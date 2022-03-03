package com.OEkrem;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements KeyListener, ActionListener{

    private boolean helpStatus = false;
    private boolean helpStatusSnake = true;
    private boolean helpStatusFood = true;
    private boolean helpStatusBomb = true;
    private boolean helpStatusGateway = true;
    private boolean helpStatusApple = true;
    private boolean helpStatusPeach = true;
    private boolean helpStatusGalete = true;
    
    private boolean pauseStatus = false;
    private BufferedImage pauseImage;
    private boolean settingsStatus = false;
    private BufferedImage settingsImage;
    private boolean soundStatus = false;
    private BufferedImage noSoundImage;
    
    private String userName;
    private String recordName = "-";
    private String recordTime = "-";
    
    ImageIcon bombIcon = new ImageIcon("YilanOyunu/icons/bombaIcon.png", "Bomba");
    ImageIcon gatewayIcon = new ImageIcon("YilanOyunu/icons/karadelikIcon.png", "Geçit");
    ImageIcon foodIcon = new ImageIcon("YilanOyunu/icons/besinIcon.png", "Besin");
    ImageIcon snakeIcon = new ImageIcon("YilanOyunu/icons/yilanIcon.png", "Yılan");
    ImageIcon appleIcon = new ImageIcon("YilanOyunu/icons/boyutIcon.png","Boyu Kısalt");
    ImageIcon gameSpeedIcon = new ImageIcon("YilanOyunu/icons/seftali.png", "Oyun Hızı Azalt");
    ImageIcon galeteIcon = new ImageIcon("YilanOyunu/icons/galeteIcon.png", "Satir veya Sütun yok et");
    
    AudioInputStream backGroundSound;
    AudioInputStream defeatSound;
    Clip clipBackGround, clipDefeat;
    
    private int gameSpeed = 400;
    Timer timer = new Timer(gameSpeed, this);
    Random random = new Random();
    
    private int sizeOfFood = 20;
    
    private Box headOfSnake = new Box(120, 80);
    private BufferedImage headImage;
    private BufferedImage headImageRight;
    private BufferedImage headImageLeft;
    private BufferedImage headImageUp;
    private BufferedImage headImageDown;
    private BufferedImage bodyImage;
    
    private LinkedList<Box> snake = new LinkedList<>();
    private ArrayList<Food> foods = new ArrayList<>();
    private BufferedImage foodImage;
    
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private BufferedImage bombImage;
    
    private ArrayList<Gateway> gateways = new ArrayList<>();
    private BufferedImage gatewayImage1;
    private BufferedImage gatewayImage2;
    
    private ArrayList<Apple> apples = new ArrayList<>(); // elmaların saklandığı ArrayList
    private BufferedImage appleImage;
    
    private ArrayList<Peach> peaches = new ArrayList<>();
    private BufferedImage peachImage;
    
    private ArrayList<Galete> galettes = new ArrayList<>();
    private BufferedImage galeteImage;
    
    private float elapsedTime = 0;
    private int controlValue = 0;
    
    private String defaultWay = "RIGHT";
    private int lengthOfSnake = 1;
    private int marginLeftOfRecordWriting = 80;
    private int[] location = null;

    
    public void gameField(Graphics g){
        g.drawLine(0, 20, 780, 20);
        g.drawLine(0, 40, 780, 40);
        g.drawLine(0, 60, 780, 60);
        g.drawLine(0, 80, 780, 80);
        g.drawLine(0, 100, 780, 100);
        g.drawLine(0, 120, 780, 120);
        g.drawLine(0, 140, 780, 140);
        g.drawLine(0, 160, 780, 160);
        g.drawLine(0, 180, 780, 180);
        g.drawLine(0, 200, 780, 200);
        g.drawLine(0, 220, 780, 220);
        g.drawLine(0, 240, 780, 240);
        g.drawLine(0, 260, 780, 260);
        g.drawLine(0, 280, 780, 280);
        g.drawLine(0, 300, 780, 300);
        g.drawLine(0, 320, 780, 320);
        g.drawLine(0, 340, 780, 340);
        g.drawLine(0, 360, 780, 360);
        g.drawLine(0, 380, 780, 380);
        g.drawLine(0, 400, 780, 400);
        g.drawLine(0, 420, 780, 420);
        g.drawLine(0, 440, 780, 440);
        g.drawLine(0, 460, 780, 460);
        g.drawLine(0, 480, 780, 480);
        g.drawLine(0, 500, 780, 500);
        g.drawLine(0, 520, 780, 520);
        g.drawLine(0, 540, 780, 540);
        
        g.drawLine(20, 0, 20, 560);
        g.drawLine(40, 0, 40, 560);
        g.drawLine(60, 0, 60, 560);
        g.drawLine(80, 0, 80, 560);
        g.drawLine(100, 0, 100, 560);
        g.drawLine(120, 0, 120, 560);
        g.drawLine(140, 0, 140, 560);
        g.drawLine(160, 0, 160, 560);
        g.drawLine(180, 0, 180, 560);
        g.drawLine(200, 0, 200, 560);
        g.drawLine(220, 0, 220, 560);
        g.drawLine(240, 0, 240, 560);
        g.drawLine(260, 0, 260, 560);
        g.drawLine(280, 0, 280, 560);
        g.drawLine(300, 0, 300, 560);
        g.drawLine(320, 0, 320, 560);
        g.drawLine(340, 0, 340, 560);
        g.drawLine(360, 0, 360, 560);
        g.drawLine(380, 0, 380, 560);
        g.drawLine(400, 0, 400, 560);
        g.drawLine(420, 0, 420, 560);
        g.drawLine(440, 0, 440, 560);
        g.drawLine(460, 0, 460, 560);
        g.drawLine(480, 0, 480, 560);
        g.drawLine(500, 0, 500, 560);
        g.drawLine(520, 0, 520, 560);
        g.drawLine(540, 0, 540, 560);
        g.drawLine(560, 0, 560, 560);
        g.drawLine(580, 0, 580, 560);
        g.drawLine(600, 0, 600, 560);
        g.drawLine(620, 0, 620, 560);
        g.drawLine(640, 0, 640, 560);
        g.drawLine(660, 0, 660, 560);
        g.drawLine(680, 0, 680, 560);
        g.drawLine(700, 0, 700, 560);
        g.drawLine(720, 0, 720, 560);
        g.drawLine(740, 0, 740, 560);
        g.drawLine(760, 0, 760, 560);
        g.drawLine(780, 0, 780, 560);
        
    }
    
    // generates a new location that does not intersect with any properties
    public int[] createNewLocation(){
        
        int konumX =13, konumY = 13;
        boolean durum = true,durum1 = true,durum2 = true,durum3 = true, durum4 = true, durum5 = true, durum6 = true;

        while(durum || durum1 || durum2 || durum3 || durum4 || durum5 || durum6){
            if(konumX%20 == 0 && konumY%20 == 0){
                if(!foods.isEmpty()){
                    for(Food temp : foods){
                        if( !(new Rectangle(konumX,konumY,20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))) ){
                            durum = false;
                        }
                    }
                }else{durum = false;}
                if(!bombs.isEmpty()){
                    for(Bomb temp : bombs){
                        if( !(new Rectangle(konumX,konumY,20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))) ){
                            durum1 = false;
                        }
                    } 
                }else{durum1 = false;}
                if(!gateways.isEmpty()){
                    for(Gateway temp : gateways){
                        if( !(new Rectangle(konumX,konumY,20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))) ){
                            durum2 = false;
                        }
                    }
                }else{durum2 = false;}
                for(Box temp : snake){
                    if( !(new Rectangle(konumX,konumY,20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))) ){
                        durum3 = false;
                    }
                }
                if(!apples.isEmpty()){
                    for(Apple temp : apples){
                        if( !(new Rectangle(konumX, konumY, 20, 20).intersects(new Rectangle(temp.getX(), temp.getY(), 20 ,20))) ){
                            durum4 = false;
                        }
                    }
                }else{ durum4 = false;}
                if(!peaches.isEmpty()){
                    for(Peach temp : peaches){
                        if( !(new Rectangle(konumX, konumY, 20, 20).intersects(new Rectangle(temp.getX(), temp.getY(), 20 ,20))) ){
                            durum5 = false;
                        }
                    }
                }else{durum5 = false;}
                if(!galettes.isEmpty()){
                    for(Galete temp : galettes){
                        if( !(new Rectangle(konumX, konumY, 20, 20).intersects(new Rectangle(temp.getX(), temp.getY(), 20 ,20))) ){
                            durum6 = false;
                        }
                    }
                }else{durum6 = false;}

                if(durum != false || durum1 != false || durum2 != false || durum3 != false || durum4 != false || durum5 != false || durum6 != false){
                    konumX = 13; konumY = 13;
                }

            }else if(konumX%20 == 0 && konumY%20 !=0){
                konumY = random.nextInt(550); 
            }else if(konumX%20 != 0 && konumY%20 == 0){
                konumX = random.nextInt(780);
            }else if(konumX%20 != 0 && konumY%20 != 0){
                konumX = random.nextInt(780);
                konumY = random.nextInt(550); 
            }
        }
        int[] konum = {konumX, konumY};
        return konum;
        
    }
    
    public void checkRecord(){
        timer.stop();
        clipBackGround.stop();
        if(soundStatus == true){clipDefeat.start();}
        
        if(Float.valueOf(recordTime) < elapsedTime){
            JOptionPane.showMessageDialog(this, "Tebrikler...\nYeni şampiyonumuz sizsinizz :)", "Yeni Rekor", 1);
            try(FileWriter writer = new FileWriter("YilanOyunu/bin/rekor.bin")){
                writer.write(userName + "," + elapsedTime);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            //try(FileWriter writer = new FileWriter("oyun.bin")){
            //} catch (IOException ex) {}
            //try(FileWriter writer = new FileWriter("oyun.bin")){
            //} catch (IOException ex) {}
        }
    }
    
    // deletes properties in a row or column based on the entered location
    public void deleteRowStun(int konumX, int konumY, int width, int height){
        
        if(!bombs.isEmpty()){
            for(int i = 0; i < bombs.size(); i++){
                if(new Rectangle(bombs.get(i).getX(),bombs.get(i).getY(),20,20).intersects(new Rectangle(konumX, konumY, width, height))){
                    bombs.remove(i);
                }
            }
        }
        if(!foods.isEmpty()){
            for(int i = 0; i < foods.size(); i++){
                if(new Rectangle(foods.get(i).getX(),foods.get(i).getY(),20,20).intersects(new Rectangle(konumX, konumY, width, height))){
                    foods.remove(i);
                }
            }
        }
        if(!apples.isEmpty()){
            for(int i = 0; i < apples.size(); i++){
                if(new Rectangle(apples.get(i).getX(),apples.get(i).getY(),20,20).intersects(new Rectangle(konumX, konumY, width, height))){
                    apples.remove(i);
                }
            }
        }
        if(!peaches.isEmpty()){
            for(int i = 0; i < peaches.size(); i++){
                if(new Rectangle(peaches.get(i).getX(),peaches.get(i).getY(),20,20).intersects(new Rectangle(konumX, konumY, width, height))){
                    peaches.remove(i);
                }
            }
        }
        if(!galettes.isEmpty()){
            for(int i = 0; i < galettes.size(); i++){
                if(new Rectangle(galettes.get(i).getX(),galettes.get(i).getY(),20,20).intersects(new Rectangle(konumX, konumY, width, height))){
                    galettes.remove(i);
                }
            }
        }    
 
    }
    
    // clears multiple properties located in the same location
    public void checkLocation(){
        if(!bombs.isEmpty()){
            for(int i = bombs.size()-1; i >= 0; i--){
                if(!foods.isEmpty()){
                    for(Food temp : foods){
                        if(new Rectangle(bombs.get(i).getX(),bombs.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                            bombs.remove(i);
                            i = bombs.size()-1;
                            break;
                        }
                    }
                }
                for(Box temp : snake){
                    if(new Rectangle(bombs.get(i).getX(),bombs.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                        bombs.remove(i);
                        i = bombs.size()-1;
                        break;
                    }
                }
                if(!peaches.isEmpty()){
                    for(Peach temp : peaches){
                        if(new Rectangle(bombs.get(i).getX(),bombs.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                            bombs.remove(i);
                            i = bombs.size()-1;
                            break;
                        }
                    }
                }
                if(!galettes.isEmpty()){
                    for(Galete temp : galettes){
                        if(new Rectangle(bombs.get(i).getX(),bombs.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                            bombs.remove(i);
                            i = bombs.size()-1;
                            break;
                        }
                    }
                }
                if(!gateways.isEmpty()){
                    for(Gateway temp : gateways){
                        if(new Rectangle(bombs.get(i).getX(),bombs.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                            bombs.remove(i);
                            i = bombs.size()-1;
                            break;
                        }
                    }
                }    
                if(!apples.isEmpty()){
                    for(Apple temp : apples){
                        if(new Rectangle(bombs.get(i).getX(),bombs.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                            bombs.remove(i);
                            i = bombs.size()-1;
                            break;
                        }
                    }
                }
                
            }
        }
        if(!foods.isEmpty()){
            for(int i = foods.size()-1; i >=0; i--){
                for(Box temp : snake){
                    if(new Rectangle(foods.get(i).getX(),foods.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                        foods.remove(i);
                        break;
                    }
                }
            }
        }
        if(!apples.isEmpty()){
            for(int i = apples.size()-1; i >=0; i--){
                for(Box temp : snake){
                    if(new Rectangle(apples.get(i).getX(),apples.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                        apples.remove(i);
                        break;
                    }
                }
            }
        }
        if(!peaches.isEmpty()){
            for(int i = peaches.size()-1; i >=0; i--){
                for(Box temp : snake){
                    if(new Rectangle(peaches.get(i).getX(),peaches.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                        peaches.remove(i);
                        break;
                    }
                }
            }
        }
        if(!galettes.isEmpty()){
            for(int i = galettes.size()-1; i >=0; i--){
                for(Box temp : snake){
                    if(new Rectangle(galettes.get(i).getX(),galettes.get(i).getY(),20,20).intersects(new Rectangle(temp.getX(),temp.getY(),20,20))){
                        galettes.remove(i);
                        break;
                    }
                }
            }
        }
    }
    
    
    public Game() {
        snake.add(headOfSnake);
        setBackground(Color.black);

        try {
            bombImage = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/bomba.png")) );
            gatewayImage1 = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/karadelik.png")) );
            gatewayImage2 = ImageIO.read( new FileImageInputStream( new File("YilanOyunu/images/gecit2.png")));
            foodImage = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/besin.png")) );
            appleImage = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/boyut.png")) );
            peachImage = ImageIO.read( new FileImageInputStream( new File("YilanOyunu/images/seftali.png")) );
            galeteImage = ImageIO.read( new FileImageInputStream( new File("YilanOyunu/images/galete.png")) );
            pauseImage = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/pause.png")) );
            settingsImage = ImageIO.read( new FileImageInputStream( new File("YilanOyunu/images/ayarlar.png")) );
            noSoundImage = ImageIO.read( new FileImageInputStream( new File("YilanOyunu/images/sesYok.png")) );
            
            headImageRight = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/kafaSag.png")) );
            headImageLeft = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/kafaSol.png")) );
            headImageUp = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/kafaUp.png")) );
            headImageDown = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/kafaDown.png")) );
            bodyImage = ImageIO.read( new FileImageInputStream(new File("YilanOyunu/images/gövde.png")) );
            headImage = headImageRight;
            
            backGroundSound = AudioSystem.getAudioInputStream(new File("YilanOyunu/voices/arkaPlan.wav"));
            defeatSound = AudioSystem.getAudioInputStream(new File("YilanOyunu/voices/yenilgi.wav"));
            
            clipBackGround = AudioSystem.getClip();
            clipDefeat = AudioSystem.getClip();
            
            clipBackGround.open(backGroundSound);
            clipDefeat.open(defeatSound);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
        char[] isim = EntryScreen.userName.trim().toLowerCase().toCharArray();
        isim[0] = Character.toUpperCase(isim[0]);
        userName = String.valueOf(isim);
        
        helpStatus = EntryScreen.helpStatus;
        soundStatus = EntryScreen.soundStatus;
        System.out.println("Yardim durumu : - " + helpStatus);
        
        try(Scanner scanner = new Scanner( new FileReader("YilanOyunu/bin/rekor.bin"))){
            String ifade = "";
            while(scanner.hasNext()){
                ifade += scanner.nextLine();
            }
            
            String[] ifade2 = ifade.split(",");
            recordName = ifade2[0];
            recordTime = ifade2[1];
            
        } catch (FileNotFoundException ex) {
            System.out.println("Böyle bir dosya bulunamadı..");
            recordName = "---";
            recordTime = "0";
        }
        snake.add(new Box(headOfSnake.getX()-20, headOfSnake.getY()));
        snake.add(new Box(headOfSnake.getX()-40, headOfSnake.getY()));
        snake.add(new Box(headOfSnake.getX()-60, headOfSnake.getY()));
        
        GameSave game = GameSave.getFromSave();
        if(game != null){
            String[] options = {"Evet", "Hayır"};
            int x = JOptionPane.showOptionDialog(null, game.getUserName()+" adına önceden kalan bir oyununuz var.\nDevam etmek ister misiniz?",
                "Emin Misiniz?",
            JOptionPane.DEFAULT_OPTION, JOptionPane.OK_CANCEL_OPTION, null, options, options[0]);
            
            if(x == 0){
                helpStatus = false;
                foods = game.getFoods();
                bombs = game.getBombs();
                apples = game.getApples();
                peaches = game.getPeaches();
                galettes = game.getGalettes();
                defaultWay = game.getDefaultWay();
                elapsedTime = game.getelapsedTime();
                gateways = game.getGateways();
                controlValue = game.getControlValue();
                userName = game.getUserName();
                snake = game.getSnake();
                lengthOfSnake = game.getSnakeLengthScore();
                gameSpeed = game.getGameSpeed();
                timer.setDelay(gameSpeed);
                headOfSnake = game.getHead();
                if(defaultWay.equals("RIGHT")) headImage = headImageRight;
                else if(defaultWay.equals("LEFT")) headImage = headImageLeft;
                else if(defaultWay.equals("UP")) headImage = headImageUp;
                else if(defaultWay.equals("DOWN")) headImage = headImageDown;
            }
            else{
                
            }
        }
        
        timer.start();
        if(soundStatus == true){
            clipBackGround.start();    
        }
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(settingsStatus == true){
            g.drawImage(settingsImage, 700, 40, 40, 40, this);
            setBackground(Color.darkGray);
            g.setColor(Color.white);
            g.drawString(" ESC tuşu   ->  Oyundan çıkmanı sağlar", 20, 60);
            g.drawString(" Ok tuşları ->  Yılanınızın hareket edeceği yönü belirtir", 20, 100);
            g.drawString(" O tuşu     ->  Sesi açar / kapar", 20, 140);
            g.drawString(" P tuşu     ->  Oyunu durdurur / kaldığı yerden devam ettirir", 20, 180);
            g.drawString(" K tuşu     ->  Oyununuzu kaydeder ve oyundan çıkış yapar", 20, 220);
            g.drawString(" Q tuşu     ->  Ayarlar bloğunu açıp kapatır", 20, 260);
            
            g.drawString(" Oyunumuzun amacı en yüksek skor ve en yüksek yılan uzunluğu elde etmektir.", 20, 300);
            g.drawString("İyi oyunlar", 20, 340);
            g.drawString("@Tüm Hakları Saklıdır-25.09.2020-23.50-YildirimGames", 220, 550);
        }
        else{
            setBackground(Color.darkGray);
            g.setColor(Color.black);

            //System.out.println("g.getFont()" + g.getFont()); // familiy SansSerif,  name sansserif,  style plain, size 12
            // satır sütun patlatma animasyonu buraya
            //g.setColor(Color.DARK_GRAY);
            //gameField(g);
            if(!foods.isEmpty()){
                for(Food food : foods){ 
                    g.drawImage(foodImage, food.getX()+1, food.getY()+1, sizeOfFood-1, sizeOfFood-1, this);
                }   
            }
            //System.out.println(" --------------------------------------- ");
            for(Box temp : snake){
                if(snake.size() > 1){
                    if( (snake.size()-1) == snake.indexOf(temp)){
                        g.drawImage(bodyImage, temp.getX()+3, temp.getY()+3, 14, 14, this);
                        continue;
                    }else if( (snake.size()-2) == snake.indexOf(temp) && snake.size()-2 != snake.indexOf(headOfSnake)){
                        g.drawImage(bodyImage, temp.getX()+2, temp.getY()+2, 16, 16, this);
                        continue;
                    }else if( (snake.size()-3) == snake.indexOf(temp) && snake.size()-3 != snake.indexOf(headOfSnake)){
                        g.drawImage(bodyImage, temp.getX()+1, temp.getY()+1, 18, 18, this);
                        continue;
                    }
                }
                if(snake.indexOf(temp) == 0){
                    continue;
                }
                g.drawImage(bodyImage, temp.getX()+1, temp.getY()+1, 19, 19, this);
            }
            if(headImageRight == headImage){
                g.drawImage(headImage, headOfSnake.getX()+1, headOfSnake.getY()-3, 26, 26, this);
            }
            else if(headImage == headImageLeft){
                g.drawImage(headImage, headOfSnake.getX()-5, headOfSnake.getY()-2, 26, 26, this);
            }
            else if(headImage == headImageUp){
                g.drawImage(headImage, headOfSnake.getX()-3, headOfSnake.getY()-4, 26, 26, this);
            }
            else if(headImage == headImageDown){
                g.drawImage(headImage, headOfSnake.getX()-2, headOfSnake.getY()+1, 26, 26, this);
            }
            
            if(!bombs.isEmpty()){
                for(Bomb bomb : bombs){
                    g.drawImage(bombImage, bomb.getX()+1, bomb.getY()+1, 19, 19, this);
                }    
            }

            if(!gateways.isEmpty()){
                for(Gateway temp : gateways){
                    if(gateways.size() == 4 ){
                        if(gateways.indexOf(temp) == 3 || gateways.indexOf(temp) == 2){
                            g.drawImage(gatewayImage2, temp.getX()+1, temp.getY()+1, 19, 19, this);
                        }
                        else if(gateways.indexOf(temp) == 1 || gateways.indexOf(temp) == 0){
                            g.drawImage(gatewayImage1, temp.getX()+1, temp.getY()+1, 19, 19, this);
                        }
                    }else{
                        g.drawImage(gatewayImage1, temp.getX()+1, temp.getY()+1, 19, 19, this);
                    }
                }    
            }

            if(!apples.isEmpty()){
                for(Apple temp : apples){
                    g.drawImage(appleImage, temp.getX()+1, temp.getY()+1, 19, 19, this);
                }    
            }
            if(!peaches.isEmpty()){
                for(Peach temp : peaches){
                    g.drawImage(peachImage, temp.getX()+1, temp.getY()+1, 19, 19, this);
                }    
            }
            if(!galettes.isEmpty()){
                for(Galete temp : galettes){
                    g.drawImage(galeteImage, temp.getX()+1, temp.getY()+1, 19, 19, this);
                }    
            }
            

            g.setColor(Color.orange);
            g.drawString(userName, 0, 20);
            g.drawString("Yılanınızın Boyu : " + lengthOfSnake, 0, 40);
            g.drawString("Gecen sure : " + (elapsedTime), 0, 60);
            g.drawString("Rekor :", (780-marginLeftOfRecordWriting), 20);
            g.drawString("-" + recordName + "-", (780-marginLeftOfRecordWriting), 40);
            g.drawString("-" + recordTime + "sn-", (780-marginLeftOfRecordWriting), 60);
            
            if(pauseStatus == true){
                int x = (this.getWidth()-160)/2;
                int y = (this.getHeight()-160)/2;
                g.drawImage(pauseImage, x, y, this);
                setBackground(Color.black);
            } 
            if(soundStatus == false){
                g.drawImage(noSoundImage, 650, 10, 40, 40, this);
            }

        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_RIGHT){
            if(pauseStatus == false && settingsStatus == false){
                defaultWay = "RIGHT";
                headImage = headImageRight;    
            }
        }
        else if(c == KeyEvent.VK_LEFT){
            if(pauseStatus == false && settingsStatus == false){
                defaultWay = "LEFT";
                headImage = headImageLeft;
            }
        }
        else if(c == KeyEvent.VK_DOWN){
            if(pauseStatus == false && settingsStatus == false){
                defaultWay = "DOWN";
                headImage = headImageDown;
            }
        }
        else if(c == KeyEvent.VK_UP){
            if(pauseStatus == false && settingsStatus == false){
                defaultWay = "UP";  
                headImage = headImageUp;
            }
        }
        else if(c == KeyEvent.VK_P){
            if(settingsStatus != true){
                if(pauseStatus == false){
                    timer.stop();
                    pauseStatus = !pauseStatus;  
                    clipBackGround.stop();
                    repaint();
                }else{
                    timer.start();
                    pauseStatus = !pauseStatus;  
                    if(soundStatus != false){
                        clipBackGround.start();
                    }
                    repaint(); 
                }    
            }
        }
        else if(c == KeyEvent.VK_O){
            if(soundStatus == true){
                soundStatus = !soundStatus;  
                clipBackGround.stop();
            }else{
                soundStatus = !soundStatus;  
                clipBackGround.start();
            }
        }else if( c == KeyEvent.VK_K){
            timer.stop();
            GameSave.save(new GameSave(userName,elapsedTime,lengthOfSnake,snake,foods,bombs,gateways,apples,peaches,galettes,defaultWay,controlValue,gameSpeed,headOfSnake));
            JOptionPane.showMessageDialog(this, "Oyununuz başarıyla kaydedildi\nOyundan çıkış yapılıyor..");
            System.exit(0);
        }else if(c == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }else if( c == KeyEvent.VK_Q){
            if(pauseStatus != true){
                if(settingsStatus == false){
                    timer.stop();
                    settingsStatus = !settingsStatus;  
                    clipBackGround.stop();
                    repaint();
                }else{
                    timer.start();
                    settingsStatus = !settingsStatus;  
                    if(soundStatus != false){
                        clipBackGround.start();
                    }
                    repaint(); 
                }    
            } 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    // timer her çalıştığında actionPerformed fonksiyonunu çağırır
    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime += gameSpeed/1000.0;
        controlValue++;
        
        // --------------------------------------------  Movement Of The Snake ------------------------------------------
        if(helpStatus == true & helpStatusSnake==true){
            helpStatusSnake = false;
            JOptionPane.showMessageDialog(this, "Yılan :\nYılanımız ok tuşlarıyla hareket etmektedir.\nDetaylı bilgi için q tuşuna basınız."
                    + "\nAman dikkat oyunun dışına çıkma sonra kaybedersin.", "Yeni özellik Yılan", 1, snakeIcon);
        }
        
        // we move the snake
        if(snake.size() == 1){
            if(defaultWay.equals("RIGHT")){

                headOfSnake.setX(headOfSnake.getX()+ 20);
            }
            else if(defaultWay.equals("LEFT")){

                headOfSnake.setX(headOfSnake.getX()- 20);
            }
            else if(defaultWay.equals("DOWN")){

                headOfSnake.setY(headOfSnake.getY()+ 20);
            }
            else if(defaultWay.equals("UP")){

                headOfSnake.setY(headOfSnake.getY()- 20);
            }    
        }
        else{
            if(defaultWay.equals("RIGHT")){
                for(int i = snake.size()-1; i > 0; i--){
                    snake.get(i).setX(snake.get(i-1).getX());
                    snake.get(i).setY(snake.get(i-1).getY());
                }
                headOfSnake.setX(headOfSnake.getX()+ 20);
            }
            else if(defaultWay.equals("LEFT")){
                for(int i = snake.size()-1; i > 0; i--){
                    snake.get(i).setX(snake.get(i-1).getX());
                    snake.get(i).setY(snake.get(i-1).getY());
                }
                headOfSnake.setX(headOfSnake.getX()- 20);
            }
            else if(defaultWay.equals("DOWN")){
                for(int i = snake.size()-1; i > 0; i--){
                    snake.get(i).setX(snake.get(i-1).getX());
                    snake.get(i).setY(snake.get(i-1).getY());
                }
                headOfSnake.setY(headOfSnake.getY()+ 20);
            }
            else if(defaultWay.equals("UP")){
                for(int i = snake.size()-1; i > 0; i--){
                    snake.get(i).setX(snake.get(i-1).getX());
                    snake.get(i).setY(snake.get(i-1).getY());
                }
                headOfSnake.setY(headOfSnake.getY()- 20);
            }    
        }
        
        //--------------------------------------------  FOOD  ------------------------------------------
        // New feature : Food
        if(controlValue%10 == 0){
            if(helpStatus == true & helpStatusFood == true){
                helpStatusFood = false;
                JOptionPane.showMessageDialog(this, "Besin :\nEğer ki yılanınız bir besin yerse yılanınızın boyu uzayacaktır.\nDikkat et de kendini yeme sonra.", "Yeni özellik Besin", 1, foodIcon);
            }
            
            location = createNewLocation();
            foods.add( new Food(location[0], location[1]));
            
        }
        
        // If our snake eats Food - the length of the snake increases
        for(int i = 0; i < foods.size(); i++){
            if(new Rectangle(foods.get(i).getX(),foods.get(i).getY(),20,20).intersects(new Rectangle(headOfSnake.getX(),headOfSnake.getY(),20,20))){
                foods.remove(i);
                int size = snake.size();
                if(size == 1){
                    if(defaultWay.equals("LEFT")){
                        snake.add(new Box(headOfSnake.getX()+20, headOfSnake.getY()));   
                    }
                    else if(defaultWay.equals("RIGHT")){
                        snake.add(new Box(headOfSnake.getX()-20, headOfSnake.getY()));   
                    }
                    else if(defaultWay.equals("DOWN")){
                        snake.add(new Box(headOfSnake.getX(), headOfSnake.getY()-20));   
                    }
                    else if(defaultWay.equals("UP")){
                        snake.add(new Box(headOfSnake.getX(), headOfSnake.getY()+20));   
                    } 
                }
                else{
                    int x1 = snake.get(size-1).getX();
                    int y1 = snake.get(size-1).getY();
                    int x2 = snake.get(size-2).getX();
                    int y2 = snake.get(size-2).getY();
                    if(x1 == x2){
                        if(y1 > y2){
                            snake.add(new Box(x1, y1+20));
                        }else{
                            snake.add(new Box(x1, y1-20));
                        }       
                    }
                    if(y1 == y2){
                        if(x1 > x2){
                            snake.add(new Box(x1+20, y1));
                        }else{
                            snake.add(new Box(x1-20, y1));
                        } 
                    }
                }
                lengthOfSnake++;
                break;
            } 
        }
        
        // --------------------------------------------  BOMB ------------------------------------------
        // New feature : Bomb
        if(lengthOfSnake > 10 && bombs.size() < 200){
            if(controlValue%15 == 0){
                if(helpStatus == true & helpStatusBomb == true){
                    helpStatusBomb = false;
                    JOptionPane.showMessageDialog(this, "Bomba :\nEğer ki yılanınız bir bomba yerse oyunu kaybedersiniz.\nÇarpmamaya çalış patron.", "Yeni özellik Bomba", 1, bombIcon);
                }
                
                location = createNewLocation();
                bombs.add(new Bomb(location[0], location[1]));
            }
        }
        
        
        // If our snake eats Bomb - the snake dies
        if(!bombs.isEmpty()){
            for(Bomb bomb : bombs){
                if(new Rectangle(bomb.getX(),bomb.getY(),20,20).intersects(new Rectangle(headOfSnake.getX(),headOfSnake.getY(),20,20))){
                    checkRecord();
                    JOptionPane.showMessageDialog(this, "Maalesef bombayı yediniz :( \nYılanınızın boyu : " + lengthOfSnake + "\nGeçen Süre : " + elapsedTime + " saniye");
                    System.exit(0);
                }
            }    
        }

        // // --------------------------------------------  GATEWAY ------------------------------------------
        // New feature - GateWays
        if(gateways.isEmpty() || gateways.size()<4){
            if(lengthOfSnake > 15){
                if(controlValue%20 == 0){ // geçit olması çok hızlıysa bunu artırmak gerekecek
                    if(helpStatus == true & helpStatusGateway == true){
                        helpStatusGateway = false;
                        JOptionPane.showMessageDialog(this, "Geçit :\nEğer ki yılanınız bir geçide girerse diğer geçitten çıkar.\nGeçidi akıllı kullan..", "Yeni özellik Geçit", 1, gatewayIcon);
                    }
                    for(int i = 2; i > 0; i--){
                        boolean durum = true; location = createNewLocation();
                        while(durum){
                            if(!gateways.isEmpty()){
                                for(Gateway temp : gateways){
                                    if(Math.abs(temp.getX()-location[0]) < 200 && Math.abs(temp.getY()-location[1]) < 200){
                                        location = createNewLocation();
                                        durum = true;
                                        break;
                                    }
                                    else{
                                        durum = false;
                                    }
                                }
                            }
                            else{
                               durum = false; 
                            }
                        }
                        gateways.add(new Gateway(location[0], location[1]));    
                    }
                }  

            }    
        }
        

        // if our ad enters the gateway, it will be beamed to the other gateway
        if(!gateways.isEmpty()){
            boolean durum = true;
            for(int i = gateways.size()-1; i >= 0; i-=2){
                if(new Rectangle(gateways.get(i).getX(),gateways.get(i).getY(),20,20).intersects(new Rectangle(headOfSnake.getX(),headOfSnake.getY(),20,20))){
                    headOfSnake.setX(gateways.get(i-1).getX());
                    headOfSnake.setY(gateways.get(i-1).getY());
                    durum = false;
                }
            }
            if(durum == true){ // eğer ki bu durumu kontrol etmezsen yılan diğer şeride geçtiğinde diğer for ile beraber yine eski konumuna döner diye tahmin ediyorum.
                for(int i = gateways.size()-2; i >= 0; i-=2){
                    if(new Rectangle(gateways.get(i).getX(),gateways.get(i).getY(),20,20).intersects(new Rectangle(headOfSnake.getX(),headOfSnake.getY(),20,20))){
                        headOfSnake.setX(gateways.get(i+1).getX());
                        headOfSnake.setY(gateways.get(i+1).getY());
                    }
                }    
            } 
        }
        
        
        // --------------------------------------------  APPLE ------------------------------------------
        // New feature : Apple
        if(lengthOfSnake > 25){
            if(controlValue%100 == 0){
                if(helpStatus == true & helpStatusApple==true){
                    helpStatusApple = false;
                    JOptionPane.showMessageDialog(this, "Boy Kısaltma - Yarım Elma :\nYılanımız yarım elma yerse boyu belli bir miktar kısalacaktır.\nAma merak etmeyin bu skorunuza yansımayacaktır.", "Yeni özellik Boyu Kısalt", 1, appleIcon);
                }
                
                location = createNewLocation();
                apples.add(new Apple(location[0], location[1]));  
            }
        }
        
        // If our snake eats Apple - the length of the snake becomes shorter 
        if(!apples.isEmpty()){
            for(int i = 0; i < apples.size(); i++){
                if(new Rectangle(headOfSnake.getX(),headOfSnake.getY(),20,20).intersects(new Rectangle(apples.get(i).getX(),apples.get(i).getY(),20,20))){
                    apples.remove(i);
                    int size = snake.size();
                    if(size%2 == 0){
                        int sayac = size/2;
                        for(; sayac >0 ; sayac--){
                            snake.remove(snake.size()-1);
                            if(snake.size() == 4){
                                break;
                            }
                        }
                    }else{
                        int sayac = (size-1)/2;
                        for(; sayac > 0; sayac--){
                            snake.remove(snake.size()-1);
                            if(snake.size() == 4){
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        
        // --------------------------------------------  PEACH ------------------------------------------
        // New feature : Peach
        if(lengthOfSnake > 40){
            if(gameSpeed <= 150){
                if(controlValue%150 == 0){
                    if(helpStatus == true & helpStatusPeach==true){
                        helpStatusPeach = false;
                        JOptionPane.showMessageDialog(this, "Şeftali - Oyunun Hızını Azalt :\nYılanımız şeftali yerse oyunumuz yavaşlayacak sonra tekrar hızlanacaktır.\n", "Yeni özellik Oyun Yavaşlat", 1, gameSpeedIcon);
                    }

                    location = createNewLocation();
                    peaches.add(new Peach(location[0], location[1]));  
                }    
            } 
        }
        
        // if our snake eats peaches, the speed of the game slows down
        if(!peaches.isEmpty()){
            for(int i = 0; i < peaches.size(); i++){
                if(new Rectangle(headOfSnake.getX(), headOfSnake.getY(), 20, 20).intersects(new Rectangle(peaches.get(i).getX(), peaches.get(i).getY(), 20, 20))){
                    peaches.remove(i);
                    gameSpeed += 100;
                    if(gameSpeed > 400){
                        gameSpeed = 400;
                    }
                    timer.setDelay(gameSpeed);
                    break;
                }
            }
        }
        
        // --------------------------------------------  GALETE ------------------------------------------
        // New feature : Galete
        if(bombs.size() > 35 && lengthOfSnake > 45){
            if(controlValue%30 == 0){
                if(helpStatus == true & helpStatusGalete==true){
                    helpStatusGalete = false;
                    JOptionPane.showMessageDialog(this, "Galete - Satir veya Sütun yok et :\nYılanımız galete yerse eğer ilgili satırdakiler, \nsütundakiler veya ikisi birden yok olur.\n", "Yeni özellik Satır veya Sütun yok et", 1, galeteIcon);
                }
                
                location = createNewLocation();                  
                galettes.add(new Galete(location[0], location[1], (random.nextInt(3)-1)));// -1(dahil)'den  1(dahil)'e  aralarında sayı üretiyor
            }
        }
        
        // if our snake eats galete, the properties in that row or column disappear
        if(!galettes.isEmpty()){
            for(int i = 0; i < galettes.size(); i++){
                if(new Rectangle(galettes.get(i).getX(),galettes.get(i).getY(),20,20).intersects(new Rectangle(headOfSnake.getX(),headOfSnake.getY(),20,20))){
                    int konumX = galettes.get(i).getX();
                    int konumY = galettes.get(i).getY();
                    switch (galettes.get(i).getDeletionProcess()) {
                        case 0:
                            deleteRowStun(0, konumY, 780, 20);
                            deleteRowStun(konumX, 0, 20, 560);
                            break;
                        case 1:
                            deleteRowStun(0, konumY, 780, 20);
                            break;
                        case -1:
                            deleteRowStun(konumX, 0, 20, 560);
                            break;
                        default:
                            break;
                    }
                    //galeteler.remove(i);
                    break;
                }
            }
        }
        
        // If our snake eats itself
        for(int i = snake.size()-1; i > 0; i--){
            if(new Rectangle(headOfSnake.getX(),headOfSnake.getY(),20,20).intersects(new Rectangle(snake.get(i).getX(),snake.get(i).getY(),20,20))){
                checkRecord();
                JOptionPane.showMessageDialog(this, "Maalesef Kendinizi yediniz :( \nYılanınızın Boyu : " + lengthOfSnake + "\nOynanan Süre : " + elapsedTime + " saniye");
                System.exit(0);
            }
        }
        
        // If our snake gets on the playground
        if(headOfSnake.getX() < 0 || headOfSnake.getX() > 760 || headOfSnake.getY() < 0 || headOfSnake.getY() > 540){
            checkRecord();
            JOptionPane.showMessageDialog(this, "Maalesef oyunun dışına çıktınız :( \nYılanınızın Boyu : " + lengthOfSnake + "\nOynanan Süre : " + elapsedTime + " saniye");
            System.exit(0);
        }
        
        // the speed of the game is constantly increasing
        if(controlValue%50 == 0){
            if(lengthOfSnake > 50){
                if(gameSpeed > 100){
                    gameSpeed-=40; 
                    timer.setDelay(gameSpeed);
                }
                else{gameSpeed = 100; timer.setDelay(gameSpeed);} 
            }
            else{
                if(gameSpeed > 100){
                    gameSpeed-=25; 
                    timer.setDelay(gameSpeed);
                }
                else{gameSpeed = 100; timer.setDelay(gameSpeed);}   
            } 
        }
        
        checkLocation();
        repaint();
    } 
}
