package clientUserService;

import common.Message;
import common.MessageType;
import util.Utility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.management.MemoryType;
import java.net.Socket;

/**
 * @author 程梁
 * @version 1.0
 */
public class ClientConnectServiceThread extends Thread{
    private Socket socket;

    public ClientConnectServiceThread(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true){
            try {
                //等待服务器返回消息，保持运行
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();

                if (message.getMessageType().equals(MessageType.RET_ONLINE_FRIEND)){
                    String[] friends = message.getContent().split(" ");
                    System.out.println("\n==========在线用户列表========");
                    for (String friend : friends) {
                        System.out.println("用户ID:"+friend);
                    }
                }else if(message.getMessageType().equals(MessageType.MESSAGE_CHAT)){
                    System.out.println("\n============聊天界面========");
                    System.out.println(message.getSender()+" : "+message.getContent());
                    System.out.print("请输入您的选择:");
                } else if(message.getMessageType().equals(MessageType.MESSAGE_ALL_CHAT)){
                    System.out.println("\n============群聊界面========");
                    System.out.println(message.getSender()+" 对大伙说 : "+message.getContent());
                    System.out.print("请输入您的选择:");
                }else if(message.getMessageType().equals(MessageType.SEND_FILE)){
//                    System.out.print("\n输入想要存放文件的路径:");
                    System.out.println(message.getSender()+"给您发送了一个文件!");
                    System.out.println("将放在默认位置");
                    String src = "C:\\Users\\14722\\Desktop\\学习资料\\新建文件夹\\接收.jpg";
                    MessageClientService.conserveFile(src,message.getBytes());
                    System.out.print("请输入您的选择:");
                }else if(message.getMessageType().equals(MessageType.SERVER_INFO_ALL_USER)){
                    System.out.println("服务器公告:"+message.getContent());
                }else {
                    System.out.println("暂时不处理");
                }

            } catch (Exception e) {
                System.out.println("连接异常！！！程序结束");
                System.exit(0);
            }

        }
    }
}
