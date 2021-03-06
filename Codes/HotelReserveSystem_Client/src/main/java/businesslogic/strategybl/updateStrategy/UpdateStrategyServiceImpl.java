package businesslogic.strategybl.updateStrategy;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.hotelbl.HotelInfoService;
import businesslogic.strategybl.exception.UnableAddStrategyException;
import businesslogic.strategybl.exception.UnableToDeleteStrategyException;
import businesslogic.strategybl.exception.UnableToModifyStrategyException;
import businesslogic.strategybl.exception.WrongInputException;
import businesslogicservice.strategyblservice.UpdateStrategyService;
import factory.FactoryService;
import factory.FactoryServiceImpl;
import po.BusinessDistrictPO;
import po.StrategyType;
import vo.StrategyVO;

/**
 * 负责实现界面层管理酒店促销策略和网站营销策略所需要的服务
 * @author 双
 * @version 
 * @see
 */
public class UpdateStrategyServiceImpl implements UpdateStrategyService{
    
    private StrategyList strategyList;
    private FactoryService factory=new FactoryServiceImpl();
    private HotelInfoService hotelInfoService=factory.createHotelInfoService();
    
    /**
     * 得到某种策略类型的列表
     * @param address string型，酒店地址
     * @param StrategyType 枚举类，策略类型
     * @return 返回策略列表
     * @see
     */
    @Override
    public ArrayList<StrategyVO> getStrategyList(String address, Enum<StrategyType> StrategyType){
        strategyList=StrategyList.getInstance(address);
        ArrayList<StrategyItem> strategyItems=strategyList.getStrategyList(address, StrategyType);
        ArrayList<StrategyVO> strategyVOs=new ArrayList<StrategyVO>();
        for(StrategyItem strategyItem:strategyItems){
            strategyVOs.add(strategyItem.toVO());
        }
        return strategyVOs;
    }
    
    /**
     * 得到对应策略名称的策略
     * @param address string型，酒店地址
     * @param name string型，策略名称
     * @return 返回策略信息
     * @see
     */
    @Override
    public StrategyVO getStrategyInfo(String address, Enum<StrategyType> strategyType, String name){
        strategyList=StrategyList.getInstance(address);
        StrategyItem strategyItem=strategyList.getStrategyInfo(address, strategyType, name);
        if(strategyItem!=null)
            return strategyItem.toVO();
        else {
            return null;
        }
    }
    
    /**
     * 增加一个策略
     * @param address string型，酒店地址
     * @param strategy StrategyVO型，包含策略信息
     * @return 返回是否增加成功
     * @throws UnableAddStrategyException 
     * @throws WrongInputException 
     * @throws RemoteException 
     * @see
     */
    @Override
    public boolean add(String address, StrategyVO strategy) throws UnableAddStrategyException, WrongInputException, RemoteException{
        if(!valid(address, strategy)){
            return false;
        }
        strategyList=StrategyList.getInstance(address);
        return strategyList.add(address,strategy);
    }
    
    /**
     * 修改一个策略
     * @param address string型，酒店地址
     * @param strategy StrategyVO型，包含策略信息
     * @return 返回是否修改成功
     * @throws UnableToModifyStrategyException 
     * @throws WrongInputException 
     * @throws RemoteException 
     * @see
     */
    @Override
    public boolean modify(String address, StrategyVO strategy) throws UnableToModifyStrategyException, WrongInputException, RemoteException{
        if(!valid(address, strategy)){
            return false;
        }
        strategyList=StrategyList.getInstance(address);
        return strategyList.modify(address,strategy);
    }
    
    /**
     * 删除一个策略
     * @param address string型，酒店地址
     * @param strategy StrategyVO型，包含策略信息
     * @return 返回是否删除成功
     * @throws UnableToDeleteStrategyException 
     * @throws WrongInputException 
     * @throws RemoteException 
     * @see
     */
    @Override
    public boolean delete(String address, StrategyVO strategy) throws UnableToDeleteStrategyException, WrongInputException, RemoteException{
        if(!valid(address, strategy)){
            return false;
        }
        strategyList=StrategyList.getInstance(address);
        return strategyList.delete(address,strategy);
    }
    
    /**
     * 判断该策略信息是否有效
     * @param address string型，酒店地址
     * @param strategy StrategyVO型，包含策略信息
     * @return 返回该策略信息是否有效
     * @throws WrongInputException 
     * @throws RemoteException 
     * @see
     */
    @Override
    public boolean valid(String address, StrategyVO strategy) throws WrongInputException, RemoteException{
        strategyList=StrategyList.getInstance(address);
        return strategyList.valid(address,strategy);
    }

    /**
     * 验证特定商圈会员专属折扣的商圈名称在某城市是否存在
     * @param city String型，城市名称
     * @param strategyVO 策略信息
     * @return 返回商圈是否存在
     * @throws WrongInputException 
     * @throws RemoteException 
     * @see
     */
    @Override
    public boolean verifyTradeArea(String city, StrategyVO strategyVO) throws WrongInputException, RemoteException {
        return strategyList.verifyTradeArea(city, strategyVO);
    }
    
    /**
     * 得到某个城市的商圈列表
     */
    @Override
    public ArrayList<BusinessDistrictPO> getBusinessDistrictList(String city) throws RemoteException {
        return hotelInfoService.getBusinessDistrictList(city);
    }
}
