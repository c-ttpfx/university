package server;

import common.Message;
import common.MessageType;
import utils.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 程梁
 * @version 1.0
 */
public class ServerMessage extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("==============服务器管理===========");
            System.out.println("\t\t\t 1:群发消息");
            System.out.println("\t\t\t 9:关闭服务器");
            System.out.print("请输入选择:");
            String option = Utility.readString(1);
            if (option.equals("1")){
                System.out.print("请输入想要发送的公告:");
                String s = Utility.readString(100);
                serverSendMessage(s);
                System.out.println("发送公告成功");
            }else if (option.equals("9")){

            }
        }
    }
    public void serverSendMessage(String s) {
        try {
            Message message = new Message();
            message.setMessageType(MessageType.SERVER_INFO_ALL_USER);
            message.setContent(s);
            Set<String> allId = ManageClientThreads.getAllIdExceptId(message.getSender());
            for (String id : allId) {
                ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectServerThread(id)
                        .getSocket().getOutputStream());
                oos.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
