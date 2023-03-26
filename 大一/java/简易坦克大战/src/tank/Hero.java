package tank;

import record.Recorder;
import utility.Time;

import java.awt.*;
import java.io.Serializable;

/**
 * @author 程梁
 * @version 1.0
 */
public class Hero extends Tank implements Runnable  {

    private int lifeNum = 5;
    private int existTime = 0;

    public Hero(int x, int y, char direct, Color color) {
        super(x, y, direct, color);
        setSpeed(20);
    }

    public void setLifeNum(int lifeNum) {
        this.lifeNum = lifeNum;
    }

    public int getLifeNum() {
        return lifeNum;
    }


    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            existTime = Time.existTime;
            if (!isLive){
                lifeNum--;
                Recorder.dexreaseHeroResidueLifeNum();
                if (lifeNum <= 0){
                    break;
                }
                isLive = true;
            };
        }
        System.out.println("游戏结束");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public int getExistTime() {
        return existTime;
    }

    public void setExistTime(int existTime) {
        this.existTime = existTime;
    }
}
