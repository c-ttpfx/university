package common;

import java.io.Serializable;

/**
 * @author 程梁
 * @version 1.0
 */
public class User implements Serializable {
    private String userID;
    private String password;
    private static final long serialVersionUID =1L;

    public User(){}

    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
