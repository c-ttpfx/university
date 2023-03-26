package view;

import record.Recorder;
import tank.EnemyTank;
import tank.Hero;
import utility.AePlayWave;
import bomb.Bomb;
import bullet.Bullet;
import utility.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

/**
 * @author 程梁
 * @version 1.0
 */
public class Mypanal extends JPanel implements KeyListener, Runnable {
    Hero hero = null;
    List<EnemyTank> enemyTanks = new Vector<>();
    List<Bomb> bombs = new Vector<>();
    int k = 1;
    public static int TANKNUM = 5;

    public Mypanal(int option) {
        new Thread(new Time()).start();
        new AePlayWave("src\\111.wav").start();
        if (option == 1) {
            hero = new Hero(100, 100, 'a', Color.RED);
            new Thread(hero).start();
            addEnemyTank(TANKNUM);
        } else {
            hero = Recorder.getHero();
            hero.setLifeNum(Recorder.getLiftNum());
            Recorder.setHitTankNum(Recorder.getTankNum());
            new Thread(hero).start();
            enemyTanks = Recorder.getEnemyTank();
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.setHero(hero);
                new Thread(enemyTank).start();
                for (Bullet bullet : enemyTank.bullets) {
                    new Thread(bullet).start();
                }
            }
        }
        Recorder.setEnemyTanks(enemyTanks);
        Recorder.setHero(hero);
    }

    public void increaseEnemyTank(int num) {
        int i = enemyTanks.size();
        enemyTanks.add(EnemyTank.getEnemyTank());
        enemyTanks.get(i).setHero(hero);
        new Thread(enemyTanks.get(i)).start();

        enemyTanks.get(i).setEnemyTanks(enemyTanks);
    }


    public void addEnemyTank(int num) {
        for (int i = 0; i < num; i++) {
            enemyTanks.add(EnemyTank.getEnemyTank());
            enemyTanks.get(i).setHero(hero);
            new Thread(enemyTanks.get(i)).start();
        }
        for (int i = 0; i < num; i++) {
            enemyTanks.get(i).setEnemyTanks(enemyTanks);
        }
    }

    public void tankBomb(Graphics g, Bomb bomb) {
        Image image1 = Toolkit.getDefaultToolkit().getImage("src\\bomb_1.gif");
        Image image2 = Toolkit.getDefaultToolkit().getImage("src\\bomb_2.gif");
        Image image3 = Toolkit.getDefaultToolkit().getImage("src\\bomb_3.gif");
        if (bomb.getTime() > 6) {
            g.drawImage(image1, bomb.getX(), bomb.getY(), 50, 50, this);
        } else if (bomb.getTime() > 3) {
            g.drawImage(image2, bomb.getX(), bomb.getY(), 50, 50, this);
        } else {
            g.drawImage(image3, bomb.getX(), bomb.getY(), 50, 50, this);
        }
        new Thread(bomb).start();
    }

    public void showInfo(Graphics g) {
//        Color color=g.getColor();
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("摧毁坦克信息", 1030, 30);
//        g.setColor(color);
        paintTank(g, 1010, 60, hero.getDirect(), hero.getColor());
        paintTank(g, 1010, 110, 'w', Color.cyan);
        g.setColor(Color.BLACK);
        g.drawString("生命:", 1060, 87);
        g.drawString(Recorder.getHeroResidueLifeNum() + "", 1130, 87);
        g.drawString(Recorder.getHitTankNum() + "", 1060, 137);
        g.drawString("存活时间", 1001, 200);
        g.drawString(hero.getExistTime() + "", 1150, 200);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (Time.addTank){
            increaseEnemyTank(1);
            Time.addTank = false;
        }
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);
        if (hero.getLifeNum() > 0) {
            paintTank(g, hero.getX(), hero.getY(), hero.getDirect(), hero.getColor());
        } else {
            if (k == 1) {
                k++;
                Bomb bomb = new Bomb(hero.getX(), hero.getY());
                bombs.add(bomb);
                new Thread(bomb).start();
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (!enemyTank.isLive()) {
                Recorder.increaseHitTankNum();
                enemyTanks.remove(enemyTank);
                Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                bombs.add(bomb);
                new Thread(bomb).start();
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isLive()) {
                bombs.remove(bombs.get(i));
            }
        }
        for (Bomb bomb : bombs) {
            tankBomb(g, bomb);
        }
        for (EnemyTank enemyTank : enemyTanks) {
            paintTank(g, enemyTank.getX(), enemyTank.getY(), enemyTank.getDirect(), enemyTank.getColor());

            for (int i = 0; i < enemyTank.bullets.size(); i++) {
                Bullet bullet = enemyTank.bullets.get(i);
                if (!bullet.isLive()) {
                    enemyTank.bullets.remove(bullet);
                }
            }
            for (Bullet bullet : enemyTank.bullets) {
                paintBullet(g, bullet.getX(), bullet.getY(), bullet.getSize(), enemyTank.getColor(), bullet);
            }
        }

        for (int i = 0; i < hero.bullets.size(); i++) {
            Bullet bullet = hero.bullets.get(i);
            if (bullet.isLive()) {
                paintBullet(g, bullet.getX(), bullet.getY(), bullet.getSize(), hero.getColor(), bullet);
            } else {
                hero.bullets.remove(bullet);
            }
        }
    }


    public void paintBullet(Graphics g, int x, int y, int size, Color color, Bullet bullet) {
        if (bullet.isLive()) {
            g.setColor(color);
            g.fillOval(x, y, size, size);
        }
    }

    public void paintTank(Graphics g, int x, int y, char direct, Color color) {
        g.setColor(color);
        switch (direct) {
            case 'w':
                g.fill3DRect(x, y, 10, 39, false);
                g.fill3DRect(x + 25, y, 10, 39, false);
                g.fill3DRect(x + 5, y + 9, 25, 22, false);
                g.fillOval(x + 8, y + 11, 17, 17);
                g.fill3DRect(x + 16, y - 10, 3, 30, true);
                break;
            case 's':
                g.fill3DRect(x, y, 10, 39, false);
                g.fill3DRect(x + 25, y, 10, 39, false);
                g.fill3DRect(x + 5, y + 9, 25, 22, false);
                g.fillOval(x + 8, y + 10, 17, 17);
                g.fill3DRect(x + 16, y + 19, 3, 30, true);
                break;
            case 'd':
                g.fill3DRect(x, y, 39, 10, false);
                g.fill3DRect(x, y + 25, 39, 10, false);
                g.fill3DRect(x + 9, y + 5, 22, 25, false);
                g.fillOval(x + 10, y + 10, 17, 17);
                g.fill3DRect(x + 18, y + 17, 30, 3, true);
                break;
            case 'a':
                g.fill3DRect(x, y, 39, 10, false);
                g.fill3DRect(x, y + 25, 39, 10, false);
                g.fill3DRect(x + 9, y + 5, 22, 25, false);
                g.fillOval(x + 10, y + 10, 17, 17);
                g.fill3DRect(x - 10, y + 17, 30, 3, true);
                break;
            default:
                System.out.println("方向错误!");
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 'A') {
            hero.moveLeft();
            hero.setDirect('a');
        } else if (e.getKeyCode() == 'D') {
            hero.moveRight();
            hero.setDirect('d');
        } else if (e.getKeyCode() == 'W') {
            hero.moveUp();
            hero.setDirect('w');
        } else if (e.getKeyCode() == 'S') {
            hero.moveDown();
            hero.setDirect('s');
        }
        if (e.getKeyCode() == 'J') {
            Bullet bullet = new Bullet(hero, 6);
            hero.bullets.add(bullet);
            new Thread(bullet).start();

        }
//        System.out.println("按下了" + (char) e.getKeyCode() + "键");
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
