package record;

import tank.EnemyTank;
import tank.Hero;
import tank.Tank;

import java.io.*;
import java.util.List;
import java.util.Vector;

/**
 * @author 程梁
 * @version 1.0
 */
public class Recorder {
   private static int hitTankNum = 0;
   private static BufferedWriter bw = null;
   private static BufferedReader br = null;
   private static ObjectInputStream ois = null;
   private static ObjectOutputStream oos = null;
   private static String filePath = "src\\recordTank.dat";
   private static String infoFilePath = "src\\recordInfo.txt";
   private static int heroResidueLifeNum = 1;
   private static List<EnemyTank> enemyTanks = null;
   private static Tank hero = null;

   public static void setHero(Hero hero) {
      Recorder.hero = hero;
      heroResidueLifeNum = hero.getLifeNum();
   }

   public static void setEnemyTanks(List<EnemyTank> enemyTanks) {
      Recorder.enemyTanks = enemyTanks;
   }
   public static Hero getHero(){
      Hero hero = null;
      try {
         ois = new ObjectInputStream(new FileInputStream(filePath));
         hero=(Hero) ois.readObject();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (ois != null){
            try {
               ois.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      return hero;
   }
   public static Vector<EnemyTank> getEnemyTank(){
      Vector<EnemyTank> enemyTanks = null;
      try {
         ois = new ObjectInputStream(new FileInputStream(filePath));
         enemyTanks = new Vector<>();
         ois.readObject();
         int size = ois.readInt();
         while (size-- > 0) {
            EnemyTank enemyTank = (EnemyTank) ois.readObject();
            enemyTanks.add(enemyTank);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (ois != null)
            try {
               ois.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
      }
      return enemyTanks;
   }
   public static void recordTank(){
      try {
         oos = new ObjectOutputStream(new FileOutputStream(filePath));
         oos.writeObject(hero);
         oos.writeInt(enemyTanks.size());
         for (EnemyTank enemyTank : enemyTanks) {
            oos.writeObject(enemyTank);
         }
      } catch (IOException e) {
         System.out.println("存储异常");
      } finally {
         try {
            if (oos != null)
               oos.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
   public static int getLiftNum(){
      String liftNum = "1";
      try {
         br = new BufferedReader(new FileReader(infoFilePath));
         String s = br.readLine();
         liftNum = s.substring(s.length()-1);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            br.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      System.out.println(liftNum);
      return Integer.parseInt(liftNum);
   }
   public static int getTankNum(){
      String tankNum = "1";
      try {
         br = new BufferedReader(new FileReader(infoFilePath));
         br.readLine();
         String s = br.readLine();
         tankNum = s.substring(s.length()-1);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            br.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return Integer.parseInt(tankNum);
   }
   public static void recordGameInfo(){
      try {
         bw = new BufferedWriter(new FileWriter(infoFilePath));
         bw.write("游戏剩余生命:"+heroResidueLifeNum);
         bw.newLine();
         bw.write("击毁坦克数量"+hitTankNum);
         bw.newLine();
         recordTank();
//         for (EnemyTank enemyTank : enemyTanks) {
//            String info = enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect();
//            bw.write(info);
//            bw.newLine();
//         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            bw.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
   public static void dexreaseHeroResidueLifeNum(){
      heroResidueLifeNum--;
   }
   public static int getHeroResidueLifeNum() {
      return heroResidueLifeNum;
   }

   public static void setHeroResidueLifeNum(int heroResidueLifeNum) {
      heroResidueLifeNum = heroResidueLifeNum;
   }

   public static void increaseHitTankNum(){
      hitTankNum++;
   }
   public static int getHitTankNum() {
      return hitTankNum;
   }

   public static void setHitTankNum(int hitTankNum) {
      Recorder.hitTankNum = hitTankNum;
   }
}
