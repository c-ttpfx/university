import common.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author ttpfx
 * @date 2023/3/22
 */
public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties();
        HashMap<String, User> validUsers = new HashMap<>();
        try {
            properties.load(new FileReader("src\\user.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.forEach((k, v) -> {
            System.out.println(k + "  " + v);
        });
    }
}
