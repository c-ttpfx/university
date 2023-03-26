package bullet;

import tank.Tank;

import java.io.Serializable;

/**
 * @author 程梁
 * @version 1.0
 */
public class Bullet implements Runnable , Serializable {
    private int x;
    private int y;
    private int speed;
    private char direct;
    private Tank tank;
    private int size = 6;
    private boolean isLive = true;

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Bullet(Tank tank) {
        size = 6;
        speed = 2;
        this.tank = tank;

        direct = tank.getDirect();
        switch (tank.getDirect()) {
            case 'w':
                x = tank.getX()+13;
                y =tank.getY()-11;
                break;
            case 's':
                x = tank.getX()+13;
                y =tank.getY()+47;
                break;
            case 'd':
                x = tank.getX()+48;
                y =tank.getY()+15;
                break;
            case 'a':
                x = tank.getX()-12;
                y =tank.getY()+15;
                break;
            default:
                System.out.println("方向错误!");
                break;
        }
    }
    public Bullet(Tank tank,int speed){
        size = 6;
        this.speed = speed;
        this.tank = tank;

        direct = tank.getDirect();
        switch (tank.getDirect()) {
            case 'w':
                x = tank.getX()+13;
                y =tank.getY()-11;
                break;
            case 's':
                x = tank.getX()+13;
                y =tank.getY()+47;
                break;
            case 'd':
                x = tank.getX()+48;
                y =tank.getY()+15;
                break;
            case 'a':
                x = tank.getX()-12;
                y =tank.getY()+15;
                break;
            default:
                System.out.println("方向错误!");
                break;
        }
    }
    public Bullet(int x, int y, int speed, char direct) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direct = direct;
    }

    public char getDirect() {
        return direct;
    }

    public void setDirect(char direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while (true){

            if (x < 0 || x>1000 || y < 0 || y> 750 || !isLive){
                isLive = false;
                break;
            }
            synchronized (this) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (direct){
                    case 'a':
                        x -= speed;
                        break;
                    case 'd':
                        x += speed;
                        break;
                    case 'w':
                        y -= speed;
                        break;
                    case 's':
                        y+=speed;
                        break;
                    default:
                        System.out.println("方向异常");
                        break;
                }
            }
        }
//        System.out.println("子弹销毁");
    }
}
