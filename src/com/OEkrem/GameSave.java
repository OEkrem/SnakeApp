
package com.OEkrem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author OEkrem
 */
public class GameSave implements Serializable {
    private String userName;
    private float elapsedTime;
    private int snakeLengthScore;
    private LinkedList<Box> snake;
    private ArrayList<Food> foods;
    private ArrayList<Bomb> bombs;
    private ArrayList<Gateway> gateways;
    private ArrayList<Apple> apples;
    private ArrayList<Peach> peaches;
    private ArrayList<Galete> galettes;
    private String defaultWay;
    private int controlValue;
    private int gameSpeed;
    private Box head;

    public GameSave(String userName, float elapsedTime, int snakeLengthScore, LinkedList<Box> snake, ArrayList<Food> foods, ArrayList<Bomb> bombs, ArrayList<Gateway> gateways, ArrayList<Apple> apples, ArrayList<Peach> peaches, ArrayList<Galete> galettes, String defaultWay, int controlValue, int gameSpeed, Box head) {
        this.userName = userName;
        this.elapsedTime = elapsedTime;
        this.snakeLengthScore = snakeLengthScore;
        this.snake = snake;
        this.foods = foods;
        this.bombs = bombs;
        this.gateways = gateways;
        this.apples = apples;
        this.peaches = peaches;
        this.galettes = galettes;
        this.defaultWay = defaultWay;
        this.controlValue = controlValue;
        this.gameSpeed = gameSpeed;
        this.head = head;
    }


    public Box getHead() {
        return head;
    }

    public String getUserName() {
        return userName;
    }

    public float getelapsedTime() {
        return elapsedTime;
    }

    public int getSnakeLengthScore() {
        return snakeLengthScore;
    }

    public LinkedList<Box> getSnake() {
        return snake;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public ArrayList<Gateway> getGateways() {
        return gateways;
    }

    public ArrayList<Apple> getApples() {
        return apples;
    }

    public ArrayList<Peach> getPeaches() {
        return peaches;
    }

    public ArrayList<Galete> getGalettes() {
        return galettes;
    }

    public String getDefaultWay() {
        return defaultWay;
    }

    public int getControlValue() {
        return controlValue;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    
    
    public static void save(GameSave game){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("YilanOyunu/bin/oyun.bin"))) {

            out.writeObject(game);

        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadi...");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static GameSave getFromSave(){
        GameSave game;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("YilanOyunu/bin/oyun.bin"))) {

            game = (GameSave) in.readObject();
            return game;

        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Böyle bir class bulunamadı...");
        }
        return null;
    } 
}
