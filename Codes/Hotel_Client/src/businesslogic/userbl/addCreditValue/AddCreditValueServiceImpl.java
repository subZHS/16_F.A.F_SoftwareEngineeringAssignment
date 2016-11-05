package businesslogic.userbl.addCreditValue;

import java.rmi.RemoteException;

import businesslogicservice.userblservice.AddCreditValueService;
import dataservice.userDAO.UserDAO;
import po.UserPO;
import po.UserType;
import vo.UserVO;

public class AddCreditValueServiceImpl implements AddCreditValueService {
    UserDAO userDAO;
    UserVO clientInfoVO;
    String userID;
    
    public AddCreditValueServiceImpl(String userID, UserType userType) {
        try {
            this.clientInfoVO = new UserVO(userDAO.getUserInfo(userID, userType));
        } catch (RemoteException e) {
            e.printStackTrace();
        }    }
    
    @Override
    public boolean addCreditValue(String userID, int creditAdded) {
        try {
            userDAO.update(new UserPO(userID, null, null, null));;
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
	

}
