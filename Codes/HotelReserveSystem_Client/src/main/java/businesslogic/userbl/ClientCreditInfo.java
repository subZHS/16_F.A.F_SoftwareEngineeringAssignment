package businesslogic.userbl;

import java.rmi.RemoteException;

import po.ActionType;

/**
 * 供同层间调用的关于信用值的接口
 * @author sparkler
 * @version 
 * @see
 */
public interface ClientCreditInfo {

    /**
     * 获取客户的信用值
     * @param userID String型，业务逻辑层传递过来的用户标识
     * @return 返回客户当前信用值
     * @throws RemoteException 
     * @see
     */
    public int getCreditValue(String userID) throws RemoteException;
    
    /**
     * 改变客户的信用值（有订单变化）
     * @param userID String型，业务逻辑层传递过来的用户标识
     * @param num int型，业务逻辑层传递过来的增加的信用值
     * @param orderID String型，业务逻辑层传递过来的订单号
     * @param actionType ActionType型，业务逻辑层传递过来的订单变化类型
     * @return 改变成功则返回true，改变失败则返回false
     * @throws RemoteException 
     * @see
     */
    public boolean changeCreditValue(String userID,int num,String orderID, ActionType actionType) throws RemoteException;
    

}
