package viewMenu;

import clientUserService.ClientUserService;
import clientUserService.MessageClientService;
import util.Utility;

/**
 * @author 程梁
 * @version 1.0
 */
public class ViewMenu {
    private boolean loop = true;
    private String option;
    private ClientUserService clientUserService = new ClientUserService();
    private MessageClientService messageClientServicec = new MessageClientService();
    private String content;

    public static void main(String[] args) {
        new ViewMenu().viewMenu();
        System.out.println("退出成功");
    }

    private void viewMenu() {
        while (loop) {
            System.out.println("=================欢迎登录网络通讯系统===============");
            System.out.println("\t\t\t 1 登录系统");
            System.out.println("\t\t\t 9 退出系统");
            System.out.print("请输入您的选择:");
            option = Utility.readString(1);
            switch (option) {
                case "1":
                    System.out.print("请输入您的用户名:");
                    String userID = Utility.readString(10);
                    System.out.print("请输入您的密码:");
                    String passward = Utility.readString(10);

                    if (clientUserService.checkUser(userID, passward)) {
                        System.out.println("===================欢迎(" + userID + ")登陆成功==============\n");
                        while (loop) {
                            System.out.println("=================网络通讯系统二级菜单(" + userID + ")===============");
                            System.out.println("\t\t\t 1 显示在线用户列表");
                            System.out.println("\t\t\t 2 群发消息");
                            System.out.println("\t\t\t 3 私聊消息");
                            System.out.println("\t\t\t 4 发送文件");
                            System.out.println("\t\t\t 9 退出系统");
                            System.out.print("请输入您的选择:");
                            option = Utility.readString(1);
                            switch (option) {
                                case "1":
                                    clientUserService.getOnlineFriend();
                                    break;
                                case "2":
                                    System.out.print("请输入群发内容:");
                                    content = Utility.readString(100);
                                    messageClientServicec.toAllChat(content, userID);
                                    System.out.println("群发成功");
                                    break;
                                case "3":
                                    System.out.print("请输入想要聊天的用户id(在线):");
                                    String getter = Utility.readString(10);
                                    System.out.print("请输入聊天内容:");
                                    content = Utility.readString(100);
                                    messageClientServicec.toOneChat(content, userID, getter);
                                    System.out.println("发送成功");
                                    break;
                                case "4":
                                    System.out.print("请输入发送文件的位置:");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入想要聊天的用户id(在线):");
                                    String receive = Utility.readString(10);
                                    messageClientServicec.sendFile(src, userID, receive);
                                    System.out.println("发送成功");
                                    break;
                                case "9":
                                    clientUserService.logout();
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("输入有误");
                            }

                            //按回车继续
                            char s = Utility.readChar('a');
                        }
                    } else {
                        System.out.println("用户名或密码有误 OR 重复登录 ");
                    }
                    break;
                case "9":
                    System.out.println("退出登录");
                    break;
                default:
                    System.out.println("输入有误");
            }
        }
    }
}
