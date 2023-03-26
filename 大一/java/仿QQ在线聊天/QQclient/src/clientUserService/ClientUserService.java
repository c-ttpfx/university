package clientUserService;

import common.Message;
import common.MessageType;
import common.User;
import util.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

/**
 * @author 程梁
 * @version 1.0
 */
public class ClientUserService {
    private User user = new User();
    private Socket socket;
    private boolean flag = false;

    public boolean checkUser(String userId, String passward) {
        user.setUserID(userId);
        user.setPassword(passward);
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src\\server.properties"));
            String ip = properties.getProperty("ip");
            String port = properties.getProperty("port");
            socket = new Socket(ip, Integer.parseInt(port));
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //将数据发送到服务器端进行验证
            oos.writeObject(user);

            //服务器端返回验证消息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();

            if (message.getMessageType().equals(MessageType.QQ_LOGIN_SECCEED)) {
                //启动该用户线程
                ClientConnectServiceThread clientConnectServiceThread = new ClientConnectServiceThread(socket);
                clientConnectServiceThread.start();
                //将该对象放入集合方便管理
                ManageClientServiceThread.addThread(userId,clientConnectServiceThread);
                //将标记设置为true表示登录成功
                flag = true;

            } else {
                //登录失败,将Socket关闭
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    public void getOnlineFriend(){
        try {
            socket = ManageClientServiceThread.getClientConnectServiceThread(user.getUserID()).getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setMessageType(MessageType.GET_ONLINE_FRIEND);
            message.setSender(user.getUserID());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(){
        try {
            socket = ManageClientServiceThread.getClientConnectServiceThread(user.getUserID()).getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setMessageType(MessageType.QQ_EXIT);
            message.setSender(user.getUserID());
            oos.writeObject(message);
            System.out.println(user.getUserID()+"退出登录");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
