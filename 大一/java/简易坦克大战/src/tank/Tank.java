package tank;

import bomb.Bomb;
import bullet.Bullet;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * @author 程梁
 * @version 1.0
 */
public class Tank implements Serializable {
    private int x;
    private int y;
    private char direct;
    private Color color;
    private int speed = 1;
    boolean isLive = true;
    public List<Bullet> bullets = new Vector<>();
    Bomb bomb;
    private boolean isCrash = false;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 750;

    public void moveUp() {
        y -= speed;
        if (y < 0) {
            y = 0;
        }
    }

    public void moveDown() {
        y += speed;
        if (y > HEIGHT - 39) {
            y = HEIGHT - 39;
        }
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight() {
        x += speed;
        if (x > WIDTH - 39) {
            x = WIDTH - 39;
        }
    }

    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Tank(int x, int y, char direct, Color color) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public char getDirect() {
        return direct;
    }

    public void setDirect(char direct) {
        this.direct = direct;
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

    public boolean isCrash() {
        return isCrash;
    }

    public void setCrash(boolean crash) {
        isCrash = crash;
    }

    public boolean judgeCrash(Tank tank1, Tank tank2) {
        char td1 = tank1.getDirect();
        char td2 = tank2.getDirect();
        if (td1 == 'w' || td1 == 's') {
            if (td2 == 'w' || td2 == 's') {
                if (Math.abs(tank1.getX() - tank2.getX()) < 35) {
                    if (Math.abs(tank1.getY() - tank2.getY()) < 39) {
                        tank1.isCrash = true;
                        tank2.isCrash = true;
                        return true;
                    }
                }
            } else {
                if ((tank1.getX()<tank2.getX() && tank2.getX() - tank1.getX() < 35) || (tank1.getX() > tank2.getX() && tank1.getX() - tank2.getX() <39 )) {
                    if (tank1.getY() > tank2.getY()) {
                        if (tank1.getY() - tank2.getY() < 39) {
                            tank1.isCrash = true;
                            tank2.isCrash = true;
                            return true;
                        }
                    } else {
                        if (tank2.getY() - tank1.getY() < 35) {
                            tank1.isCrash = true;
                            tank2.isCrash = true;
                            return true;
                        }
                    }
                }
            }
        } else {
            if (td2 == 'w' || td2 == 's') {
                if ((tank1.getX() < tank2.getX() && tank2.getX() - tank1.getX() < 39 ) || (tank1.getX() > tank2.getX() && tank1.getX() - tank2.getX() < 35)){
                    if (tank1.getY() > tank2.getY()){
                        if (tank1.getY() - tank2.getY() < 35){
                            tank1.isCrash = true;
                            tank2.isCrash = true;
                            return true;
                        }
                    }else {
                        if (tank2.getY() - tank1.getY() < 39){
                            tank1.isCrash = true;
                            tank2.isCrash = true;
                            return true;
                        }
                    }
                }
            }else {
                if (Math.abs(tank1.getX() - tank2.getX()) < 39){
                    if (Math.abs(tank1.getY() - tank2.getY()) < 35){
                        tank1.isCrash = true;
                        tank2.isCrash = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
