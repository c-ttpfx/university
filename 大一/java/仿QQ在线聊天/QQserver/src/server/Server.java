package server;

import common.Message;
import common.MessageType;
import common.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author 程梁
 * @version 1.0
 */
public class Server {
    private ServerSocket serverSocket = null;
    private static HashMap<String,User> validUsers = new HashMap<>();
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src\\user.properties"));
            properties.forEach((k,v)->{
                validUsers.put((String) k, new User((String)k,(String)v));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean checkUser(String userId,String passward){
        User user = validUsers.get(userId);
        if (user==null ) {
            return false;
        }
        return validUsers.get(userId).getPassword().equals(passward);
    }
    public Server() {
        try {
            //开启服务器
            serverSocket = new ServerSocket(9998);
            /*
            * 下面2条语句是服务器推送消息
            * */
//            ServerMessage serverMessage = new ServerMessage();
//            serverMessage.start();
            //推送离线消息
            new SendOffLineMessage().start();
            System.out.println("服务器监听9999窗口");
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //得到客户端发送的验证消息
                User user = (User) ois.readObject();
                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //根据客户端发送的消息返回登录是否成功的消息
                if (checkUser(user.getUserID(), user.getPassword()) && !ManageClientThreads.isOnline(user.getUserID())) {
                    message.setMessageType(MessageType.QQ_LOGIN_SECCEED);
                    oos.writeObject(message);
                    //启动线程保持与客户端连接
                    ServerConnectServerThread serverConnectServerThread = new ServerConnectServerThread(user.getUserID(), socket);
                    serverConnectServerThread.start();
                    //将线程加入集合，方便管理
                    ManageClientThreads.addThread(user.getUserID(), serverConnectServerThread);
                    System.out.println(user.getUserID()+"登录成功");
                } else {
                    System.out.println("用户:"+user.getUserID()+"  密码:"+user.getPassword()+"  登录失败");
                    message.setMessageType(MessageType.QQ_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
