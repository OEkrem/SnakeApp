
package com.OEkrem;

import java.io.Serializable;

/**
 *
 * @author OEkrem
 */
public class Peach implements Serializable{ // oyunun hızını azaltma
    private int x;
    private int y;

    public Peach(int x, int y) {
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
