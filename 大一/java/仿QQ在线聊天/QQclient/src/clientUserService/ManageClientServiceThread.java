package clientUserService;

import java.util.HashMap;

/**
 * @author 程梁
 * @version 1.0
 */
public class ManageClientServiceThread {
   private static HashMap<String,ClientConnectServiceThread> hm = new HashMap<>();

   public static void addThread(String userId,ClientConnectServiceThread clientConnectServiceThread){
      hm.put(userId,clientConnectServiceThread);
   }

   public static ClientConnectServiceThread getClientConnectServiceThread(String userId){
      return hm.get(userId);
   }
}
