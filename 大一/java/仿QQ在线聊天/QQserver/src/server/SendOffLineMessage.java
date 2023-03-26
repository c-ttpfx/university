package server;

import common.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 程梁
 * @version 1.0
 */
public class SendOffLineMessage extends Thread{
    public static final List<Message> messages = new LinkedList<>();

    @Override
    public void run() {
        while (true){
            sendMessage();
        }
    }
    public void sendMessage(){
        List<Message> list = new ArrayList<>();
        try {
            for (Message message : messages) {
                if (ManageClientThreads.isOnline(message.getGetter())) {
                    System.out.println(message.getSender() + "向" + message.getGetter() + "发送消息");
                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectServerThread(
                            message.getGetter()).getSocket().getOutputStream());
                    oos.writeObject(message);
                    list.add(message);
                }
            }
            for (Message message : list) {
                messages.remove(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
