package clientUserService;

import common.Message;
import common.MessageType;
import util.StreamUtils;
import util.Utility;

import java.io.*;
import java.util.Date;

/**
 * @author 程梁
 * @version 1.0
 */
public class MessageClientService {

   public void toOneChat(String content,String sender, String getter){
      try {
         ObjectOutputStream oos = new ObjectOutputStream(ManageClientServiceThread.
                 getClientConnectServiceThread(sender).getSocket().getOutputStream());
         Message message = new Message();
         message.setSender(sender);
         message.setGetter(getter);
         message.setContent(content);
         message.setMessageType(MessageType.MESSAGE_CHAT);
         message.setTime(new Date().toString());
         oos.writeObject(message);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void toAllChat(String content,String sender){
      try {
         ObjectOutputStream oos = new ObjectOutputStream(ManageClientServiceThread.
                 getClientConnectServiceThread(sender).getSocket().getOutputStream());
         Message message = new Message();
         message.setSender(sender);
         message.setContent(content);
         message.setMessageType(MessageType.MESSAGE_ALL_CHAT);
         message.setTime(new Date().toString());
         oos.writeObject(message);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   public void sendFile(String src,String sender,String getter){
      try {
         ObjectOutputStream oos = new ObjectOutputStream(ManageClientServiceThread.
                 getClientConnectServiceThread(sender).getSocket().getOutputStream());
         Message message = new Message();
         message.setMessageType(MessageType.SEND_FILE);
         message.setSender(sender);
         message.setGetter(getter);
         FileInputStream fileInputStream = new FileInputStream(src);
         message.setBytes(StreamUtils.streamToByteArray(fileInputStream));
         oos.writeObject(message);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   public static void conserveFile(String src,byte[] bytes){
      try {
         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(src));
         bos.write(bytes);
         System.out.println("保存成功");
         bos.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
