
package com.OEkrem;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Apple implements Serializable{
    private int x;
    private int y;

    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    } 
    
}
