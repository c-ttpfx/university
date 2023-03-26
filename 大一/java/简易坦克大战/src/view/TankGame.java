package view;

import record.Recorder;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 程梁
 * @version 1.0
 */
public class TankGame extends JFrame{
   Mypanal mp = null;

   public TankGame(){
      Scanner sc = new Scanner(System.in);
      System.out.println("请选择新游戏or继续上局游戏( 1:新游戏 , 2:继续游戏 )");
      int option = 1;
      try {
         while (true) {
            option = sc.nextInt();
            if (option == 2){
               if(Recorder.getLiftNum() == 0){
                  System.out.println("您已经死亡，不能开始新游戏");
               }else {
                  break;
               }
            }
            if (option == 1){
               break;
            }
         }
      } catch (Exception e) {
         System.out.println("只能输入1 or 2哦,请重新输入");
      }

      mp = new Mypanal(option);
      Thread thread = new Thread(mp);
      thread.start();
      this.add(mp);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.addKeyListener(mp);
      this.setSize(1015+250,795);
      this.setVisible(true);
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Recorder.recordGameInfo();
         }
      });
   }

   public static void main(String[] args) {
      new TankGame();
   }

}
