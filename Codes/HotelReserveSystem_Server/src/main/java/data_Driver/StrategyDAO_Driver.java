package data_Driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.strategyDAO.StrategyDAO;
import po.StrategyPO;
import po.StrategyType;

/**
 * 
 * @author 双
 * @version 
 * @see
 */
public class StrategyDAO_Driver {
    public void drive(StrategyDAO strategyDAO) throws RemoteException{
        ArrayList<StrategyPO> strategyList=strategyDAO.getStrategyList("江苏省南京市栖霞区仙林大道163号", StrategyType.BirthdayPromotion);
        if(strategyList.isEmpty())
            System.out.println("This kind of strategy doesn't exit!\n");
        else
            System.out.println("There are " + strategyList.size() + " checkIns in this hotel!\n");
        
        StrategyPO strategyPO1=strategyDAO.getStrategyInfo("江苏省南京市栖霞区仙林大道163号", StrategyType.SpecificTimePromotion, "双十一折扣");
        System.out.println("The strategy "+strategyPO1.getStrategyName()+" has discount "+strategyPO1.getDiscount()+"/n");
        
        
        StrategyPO strategyPO=new StrategyPO("江苏省南京市栖霞区仙林大道163号",StrategyType.BirthdayPromotion, "普通会员生日折扣", 0.8f);
        strategyDAO.updateStrategy(strategyPO);
        strategyDAO.insertStrategy(strategyPO);
        strategyDAO.deleteStrategy(strategyPO);
    }
}
