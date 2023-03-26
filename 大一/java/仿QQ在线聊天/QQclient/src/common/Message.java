package common;

import java.io.Serializable;

/**
 * @author 程梁
 * @version 1.0
 */
public class Message implements Serializable {
   private String sender;
   private String getter;
   private String time;
   private String messageType;
   private String content;

   private byte[] bytes;

   public byte[] getBytes() {
      return bytes;
   }

   public void setBytes(byte[] bytes) {
      this.bytes = bytes;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   private static final long serialVersionUID =1L;

   public Message(){}
   public Message(String sender, String getter, String time, String messageType) {
      this.sender = sender;
      this.getter = getter;
      this.time = time;
      this.messageType = messageType;
   }

   public String getSender() {
      return sender;
   }

   public void setSender(String sender) {
      this.sender = sender;
   }

   public String getGetter() {
      return getter;
   }

   public void setGetter(String getter) {
      this.getter = getter;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public String getMessageType() {
      return messageType;
   }

   public void setMessageType(String messageType) {
      this.messageType = messageType;
   }
}
