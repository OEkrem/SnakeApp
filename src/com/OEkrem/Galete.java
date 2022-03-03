
package com.OEkrem;

import java.io.Serializable;

/**
 *
 * @author OEkrem
 */
public class Galete implements Serializable{ // satır veya sütun yok etmek için
    private int x;
    private int y;
    private int deletionProcess; // 1 satır / -1 sütun / 0 ikisi bir

    public Galete(int x, int y, int satir_sütun_ikisibir) {
        this.x = x;
        this.y = y;
        this.deletionProcess = satir_sütun_ikisibir;
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

    public int getDeletionProcess() {
        return deletionProcess;
    }
    
}