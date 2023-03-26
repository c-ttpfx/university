package bomb;

import java.io.Serializable;

/**
 * @author 程梁
 * @version 1.0
 */
public class Bomb implements Runnable , Serializable {
    private int x;
    private int y;
    private int time = 9;
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void timeDown() {
        if (time > 0) {
            time--;
        } else {
            isLive = false;
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!isLive) {
                break;
            }
            timeDown();
        }
    }
}
