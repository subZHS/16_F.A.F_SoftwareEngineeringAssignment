package vo;

import po.UserPO;
import po.UserType;
/**
 * 用户信息的值对象
 * @author sparkler
 * @version 1.0
 */
public class UserVO {
  
    public String userID;
    public String password;
    public String telNum;
    public UserType userType;
 
    public UserVO(String userID, String password, String telNum, UserType userType) {
        this.userID = userID;
        this.password = password;
        this.telNum = telNum;
        this.userType = userType;
    }
    public UserVO(UserPO userPO){
        this.userID = userPO.getUserID();
        this.password = userPO.getPassword();
        this.telNum = userPO.getTelNum();
        this.userType = userPO.getUserType();
    }
}
