package server;

import java.util.HashMap;
import java.util.Set;

/**
 * @author 程梁
 * @version 1.0
 */
public class ManageClientThreads {
    private static HashMap<String,ServerConnectServerThread> hm = new HashMap<>();

    public static boolean isOnline(String id){
        return hm.containsKey(id);
    }
    public static Set<String> getAllIdExceptId(String id){

        Set<String> strings = hm.keySet();
        strings.remove(id);
        return strings;
    }
    public static Set<String> getAllId(){

        Set<String> strings = hm.keySet();
        return strings;
    }
    public static void addThread(String userId, ServerConnectServerThread serverConnectServerThread){
        hm.put(userId, serverConnectServerThread);
    }
    public static ServerConnectServerThread getServerConnectServerThread(String userId){
        return hm.get(userId);
    }

    public static String getUserIds(){
        String friends = "";
        Set<String> userId = hm.keySet();
        for (String s : userId) {
            friends += s + " ";
        }
        return friends;

    }

    public static void remove(String userId){
        hm.remove(userId);
    }
}
