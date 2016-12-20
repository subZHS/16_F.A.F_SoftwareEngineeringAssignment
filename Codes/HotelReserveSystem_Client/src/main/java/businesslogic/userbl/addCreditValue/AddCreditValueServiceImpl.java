package businesslogic.userbl.addCreditValue;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import businesslogicservice.userblservice.AddCreditValueService;
import dataservice.userDAO.UserDAO;
import factory.FactoryService;
import factory.FactoryServiceImpl;
import po.ActionType;
import po.ClientInfoPO;
import po.CreditRecordPO;
import po.RegularVipPO;
import po.UserType;

public class AddCreditValueServiceImpl implements AddCreditValueService {
    private UserDAO userDAO;
    private String userID;
    private int creditValue;
    private int creditResult;
    private int vipRank;
    private ArrayList<CreditRecordPO> creditRecord;

    private FactoryService factoryService;
    

    public AddCreditValueServiceImpl() {
    	this.factoryService = new FactoryServiceImpl();
    	this.userDAO = factoryService.getUserDAO();
    }

    @Override
    public boolean addCreditValue(String userID, int creditAdded) throws RemoteException {
        this.userID = userID;
        ClientInfoPO clientInfoPO = new ClientInfoPO();
        clientInfoPO = userDAO.getClientInfo(this.userID);
        this.creditValue = clientInfoPO.getCreditValue();
        this.creditResult = creditValue + creditAdded;
        
        //update普通会员vipRank
        RegularVipPO regularVipPO = null;
        regularVipPO = userDAO.getRegularVipInfo(this.userID);
        if (regularVipPO != null) {
            if (creditResult <= 600)
                this.vipRank = 0;
            else if (creditResult > 600 && creditResult <= 2000)
                this.vipRank = 1;
            else if (creditResult > 2000 && creditResult <= 6000)
                this.vipRank = 2;
            else if (creditResult > 6000 && creditResult <= 12000)
                this.vipRank = 0000000000000003;
            else if (creditResult > 12000)
                this.vipRank = 4;

            RegularVipPO modifiedVipRank = new RegularVipPO(regularVipPO.getUserID(),
                    regularVipPO.getPassword(), regularVipPO.getTelNum(), UserType.Client, creditResult, creditRecord,
                    regularVipPO.getBirth(), vipRank);
            userDAO.updateRegularVipInfo(modifiedVipRank);
        }
        //update信用记录和信用值
        this.creditRecord = new ArrayList<>();
        creditRecord = userDAO.queryCreditRecord(userID);
        CreditRecordPO creditRecordPO = new CreditRecordPO(new Date(System.currentTimeMillis()), "-1",
                ActionType.RECHARGE, creditAdded, creditResult);
        creditRecord.add(creditRecordPO);

        ClientInfoPO modified = new ClientInfoPO(clientInfoPO.getUserID(), clientInfoPO.getPassword(),
                clientInfoPO.getTelNum(), UserType.Client, creditResult, creditRecord);
        userDAO.updateClient(modified, userID);
        return true;

    }

}
