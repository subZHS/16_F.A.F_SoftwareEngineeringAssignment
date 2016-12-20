package businesslogic.userbl.loginAndSignUp;

import java.rmi.RemoteException;

import dataservice.userDAO.UserDAO;
import factory.FactoryService;
import factory.FactoryServiceImpl;
import po.ClientInfoPO;
import po.HotelStaffInfoPO;
import po.UserPO;
import po.UserType;

public class CheckLoginInfo {
    private UserDAO userDAO;
    private String userID;
    private UserPO userPO;
    private ClientInfoPO client;
    private HotelStaffInfoPO hotelStaff;
    private String password;
    private UserType userType;

    private FactoryService factoryService;
    
    public CheckLoginInfo() {
    	this.factoryService = new FactoryServiceImpl();
    	this.userDAO = factoryService.getUserDAO();
    }
    
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 验证登录信息并返回用户类型
     * 
     * @param userID
     *            String型，用户帐号
     * @param password
     *            String型，用户密码
     * @return
     * @throws RemoteException 
     * @see
     */
    public UserType checkUser(String userID, String password) throws RemoteException {
        this.userID = userID;
        this.userType = null;
        userPO = userDAO.getUserInfo(this.userID);
        client = userDAO.getClientInfo(this.userID);
        hotelStaff = userDAO.getHotelStaffInfo(this.userID);
        
        if (userPO != null) {
            this.password = userPO.getPassword();
            if (this.password.equals(password))
                userType = userPO.getUserType();
        } else if (client != null) {
            this.password = client.getPassword();
            if (this.password.equals(password))
                userType = UserType.Client;
        } else if (hotelStaff != null) {
            this.password = hotelStaff.getPassword();
            if (this.password.equals(password))
                userType = UserType.HotelStaff;
        }

        return userType;

    }
}
