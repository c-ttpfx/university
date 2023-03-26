package server;

import common.Message;
import common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author 程梁
 * @version 1.0
 */
public class ServerConnectServerThread extends Thread{
   private String userId;
   private Socket socket;

   public Socket getSocket() {
      return socket;
   }

   public ServerConnectServerThread(String userId, Socket socket) {
      this.userId = userId;
      this.socket = socket;
   }

   public void sendFriends(Message message){
      try {
         ObjectOutputStream ois = new ObjectOutputStream(socket.getOutputStream());
         ois.writeObject(message);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   @Override
   public void run() {
      while (true){
//         System.out.println("服务器和客户端"+userId+"保持连接...");
         ObjectInputStream ois = null;
         try{
            ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message)ois.readObject();
            if (message.getMessageType().equals(MessageType.GET_ONLINE_FRIEND)){
               System.out.println(message.getSender()+"要获取在线用户列表");
               Message message1 = new Message();
               message1.setMessageType(MessageType.RET_ONLINE_FRIEND);
               message1.setContent(ManageClientThreads.getUserIds());

               sendFriends(message1);

            }else if(message.getMessageType().equals(MessageType.QQ_EXIT)){
               System.out.println(message.getSender()+"退出登录");
               ManageClientThreads.remove(message.getSender());
               socket.close();
               break;
            } else if(message.getMessageType().equals(MessageType.MESSAGE_CHAT)){
               if (ManageClientThreads.isOnline(message.getGetter())) {
                  System.out.println(message.getSender() + "向" + message.getGetter() + "发送消息");
                  ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectServerThread(message.getGetter())
                          .getSocket().getOutputStream());
                  oos.writeObject(message);
               }else {
                  SendOffLineMessage.messages.add(message);
               }
            } else if(message.getMessageType().equals(MessageType.MESSAGE_ALL_CHAT)){
               System.out.println(message.getSender()+"发送群消息");
               Set<String> allId = ManageClientThreads.getAllIdExceptId(message.getSender());
               for (String id : allId) {
                  ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectServerThread(id)
                          .getSocket().getOutputStream());
                  oos.writeObject(message);
               }

            } else if(message.getMessageType().equals(MessageType.SEND_FILE)){
               System.out.println(message.getSender()+"发送文件给"+message.getGetter());
               ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectServerThread(message.getGetter())
                       .getSocket().getOutputStream());
               oos.writeObject(message);
            }else {
               System.out.println("暂时不处理");
            }
         } catch (Exception e) {
            e.printStackTrace();
            try {
               ois.close();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            break;
         }

      }

   }
}
