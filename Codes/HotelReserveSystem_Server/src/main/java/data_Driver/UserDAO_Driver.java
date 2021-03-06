package data_Driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.userDAO.UserDAO;
import po.CreditRecordPO;
import po.UserPO;
import po.UserType;

public class UserDAO_Driver {
    public void driver(UserDAO userDAO){
        try {
            UserPO userPO = userDAO.getUserInfo("原");
            System.out.println("得到了" + userPO.getUserID() + "的信息");
        } catch (RemoteException e) {
            System.out.println("网络通信失败");
            e.printStackTrace();
        }
        
        try {
            ArrayList<CreditRecordPO> userPO = userDAO.queryCreditRecord("原");
            System.out.println("得到了的信用值记录"+userPO);
        } catch (RemoteException e) {
            System.out.println("网络通信失败");
            e.printStackTrace();
        }
        
        try {
            UserPO userPO = userDAO.getUserInfo("原");
            int creditValue = userDAO.getCreditValue("原");
            System.out.println("得到了" + userPO.getUserID() + "的信用值"+creditValue);
        } catch (RemoteException e) {
            System.out.println("网络通信失败");
            e.printStackTrace();
        }
        
        
        try {
            UserPO userPO = new UserPO("原", "qwe123", "12345678900", UserType.Client);
            userDAO.insertUser(userPO);
            System.out.println("酒店信息更新成功！\n");
        } catch (RemoteException e) {
            System.out.println("网络通信失败");
            System.out.println("酒店信息更新失败！\n");
            e.printStackTrace();
        }
        
        
        try {
            UserPO userPO = userDAO.getUserInfo("原");
            userDAO.updateUser(userPO, "原");
            System.out.println("初始化持久化数据存储成功！\n");
        } catch (RemoteException e) {
            System.out.println("网络通信失败");
            System.out.println("初始化持久化数据存储失败！\n");
            e.printStackTrace();
        }
    }
}
