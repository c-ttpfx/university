package tank;



import bomb.Bomb;
import bullet.Bullet;

import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * @author 程梁
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
    private Hero hero = null;
    private int size = 5;
    private int launchTime = 10;
    private List<EnemyTank> enemyTanks = new Vector<>();
    private int crashNum = 0;
    public List<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(List<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public void hitEnemyTank(Bullet bullet, Tank tank) {
        switch (tank.getDirect()) {
            case 'w':
            case 's':
                if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 35 &&
                        bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 39) {
                    bullet.setLive(false);
                    tank.isLive = false;
                }
                break;
            case 'a':
            case 'd':
                if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 39 &&
                        bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 35) {
                    bullet.setLive(false);
                    tank.isLive = false;
                }
                break;
            default:
                System.out.println("方向异常");
                break;
        }
    }
    public void randomMove(){
        setDirect(randomDirect());
        for (int i = 0; i < 30; i++) {
            for (Bullet bullet : hero.bullets) {
                hitEnemyTank(bullet,this);
            }
            for (Bullet bullet : bullets) {
                hitEnemyTank(bullet,hero);
            }
            if (hero.getLifeNum() <= 0){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (crashNum > 10){
                if (Math.random() > 0.95){
                    isLive = false;
                }
            }
            if (!isLive)return;
            judge(hero);
            if (judgeCrash(this,hero)){
                hero.isLive =false;
//                System.out.println("碰撞");
                isLive = false;
            }
            if (!isCrash()) {
                for (EnemyTank enemyTank : enemyTanks) {
                    if (enemyTank != this) {
                        if (judgeCrash(enemyTank, this)){
                            break;
                        }
                    }
                }
            }

            if (isCrash()){
                if (getDirect() == 'w'){
                    setDirect('s');
                }else if (getDirect() == 's'){
                    setDirect('w');
                }else if (getDirect() == 'a'){
                    setDirect('d');
                }else {
                    setDirect('a');
                }
                crashNum++;
                setCrash(false);
            }

            switch (getDirect()){
                case 'a':
                    moveLeft();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'd':
                    moveRight();
                    break;
                case 'w':
                    moveUp();
                    break;
                default:
                    System.out.println("方向异常");
                    break;
            }
            try {
                Thread.sleep(50);
                launchTime--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        crashNum = 0;
    }
    public char randomDirect(){
        int k = (int)(Math.random()*4);
        switch (k){
            case 0:return 'w';
            case 1:return 'a';
            case 2:return 's';
            case 3:return 'd';
        }
        return getDirect();
    }
    public EnemyTank(int x, int y, char direct, Color color) {
        super(x, y, direct, color);
        setSpeed(3);
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public static EnemyTank getEnemyTank() {
        return new EnemyTank((int) (500 * Math.random()), (int) (500 * Math.random()), 's', Color.CYAN);
    }

    public void judge(Hero hero) {
        if (hero.getX() == getX() && hero.getY() == getY()) {

        } else if (Math.abs(Math.max(hero.getX(), getX()) - Math.min(hero.getX(), getX())) <= 20) {
            if (hero.getY() > getY()) {
                setDirect('s');
            } else {
                setDirect('w');
            }
            if (bullets.size() <= size && launchTime <= 0) {
                launchTime = 10;
                Bullet bullet = new Bullet(new Tank(getX(), getY(), getDirect(), getColor()));
                bullets.add(bullet);
                new Thread(bullet).start();
            }
        } else if (Math.abs(Math.max(hero.getY(), getY()) - Math.min(hero.getY(), getY())) <= 20) {
            if (hero.getX() > getX()) {
                setDirect('d');
            } else {
                setDirect('a');
            }
            if (bullets.size() <= size && launchTime <= 0) {
                launchTime = 10;
                Bullet bullet = new Bullet(new Tank(getX(), getY(), getDirect(), getColor()));
                bullets.add(bullet);
                new Thread(bullet).start();
            }
        }

    }

    public Hero getHero() {
        return hero;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while (true) {
            if (!isLive){
                bomb = new Bomb(getX(),getY());
                break;
            }
            synchronized (this) {
                try {
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                randomMove();

            }
        }
//        System.out.println("敌人坦克销毁");
    }

}
