package businesslogic.userbl.signVip;

import java.rmi.RemoteException;

import businesslogic.userbl.VerifyEnterpriseVip;
import businesslogicservice.userblservice.SignVipService;
import dataservice.userDAO.UserDAO;
import po.EnterpriseVipPO;
import po.RegularVipPO;
import rmi.RemoteHelper;
import vo.EnterpriseVipVO;
import vo.RegularVipVO;

/**
 * 
 * @author sparkler
 * @version
 * @see
 */
public class SignVipServiceImpl implements SignVipService {

    private UserDAO userDAO;
    private VerifyEnterpriseVip verifyEnterpriseVip;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setVerifyEnterpriseVip(VerifyEnterpriseVip verify) {
        this.verifyEnterpriseVip = verify;
    }

    @Override
    public boolean signRegularVip(RegularVipVO regularVip) {
        // userDAO = RemoteHelper.getInstance().getUserDAO();
        try {
            userDAO.signRegularVip(new RegularVipPO(regularVip));
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean signEnterpriseVip(EnterpriseVipVO enterpriseVip) {
        if (verifyEnterpriseVip.verifyEnterpriseMember(enterpriseVip.enterpriseID, enterpriseVip.enterprisePassword)) {
            try {
                userDAO.signEnterpriseVip(new EnterpriseVipPO(enterpriseVip));
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else
            return false;
    }
}
