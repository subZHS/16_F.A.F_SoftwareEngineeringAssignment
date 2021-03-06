package businesslogic.roombl.updateCheckOut;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.strategybl.exception.WrongInputException;
import businesslogicservice.roomblservice.UpdateCheckOutService;
import po.RoomType;
import vo.RoomVO;

/**
 * 负责实现界面层办理退房需要的服务
 * @author 双
 * @version 
 * @see
 */
public class UpdateCheckOutServiceImpl implements UpdateCheckOutService{

    private CheckOutList checkOutList=new CheckOutList();
    
    /**
     * 得到退房信息列表
     * @param address String型，酒店地址
     * @return ArrayList<RoomVO>型，退房信息VO列表 
     * @throws RemoteException 
     * @see
     */
    @Override
    public ArrayList<RoomVO> getCheckOutList(String address) throws RemoteException{
        ArrayList<CheckOutItem> checkOutItems=checkOutList.getCheckOutList(address);
        ArrayList<RoomVO> checkOutVOs=new ArrayList<RoomVO>();
        for(CheckOutItem checkOutItem:checkOutItems){
            checkOutVOs.add(checkOutItem.toVO());
        }
        return checkOutVOs;
    }
    
    /**
     * 根据实际离开时间搜索符合条件的退房信息列表
     * @param address String型，酒店地址
     * @param time Date型，退房时间
     * @return ArrayList<RoomVO>型，返回符合条件的退房信息列表
     * @throws RemoteException 
     * @see
     */
    @Override
    public ArrayList<RoomVO> searchCheckOutInfo(String address , Date startTime, Date endTime) throws RemoteException{
        ArrayList<CheckOutItem> checkOutItems=checkOutList.searchCheckOutInfo(address, startTime, endTime);
        ArrayList<RoomVO> checkOutVOs=new ArrayList<RoomVO>();
        for(CheckOutItem checkOutItem:checkOutItems){
            checkOutVOs.add(checkOutItem.toVO());
        }
        return checkOutVOs;
    }
    
    /**
     * 根据房间类型搜索符合条件的退房信息列表
     * @param address String型，酒店地址
     * @param roomType 枚举类，房间类型
     * @return ArrayList<RoomVO>型，符合条件的退房信息列表
     * @throws RemoteException 
     * @see
     */
    @Override
    public ArrayList<RoomVO> searchCheckOutInfo(String address ,Enum<RoomType> roomType) throws RemoteException{
        ArrayList<CheckOutItem> checkOutItems=checkOutList.searchCheckOutInfo(address, roomType);
        ArrayList<RoomVO> checkOutVOs=new ArrayList<RoomVO>();
        for(CheckOutItem checkOutItem:checkOutItems){
            checkOutVOs.add(checkOutItem.toVO());
        }
        return checkOutVOs;
    }
    
    /**
     * 增加退房信息，即办理入住
     * @param address String型，酒店地址
     * @param checkOut Room VO型，退房信息
     * @return 返回是否增加成功
     * @throws RemoteException 
     * @throws WrongInputException 当退房信息有误时抛出该异常 
     * @see
     */
    @Override
    public boolean addCheckOut(String address, RoomVO checkOut) throws RemoteException, WrongInputException{
        if(!validCheckOut(address, checkOut)){
            return false;
        }
        return checkOutList.addCheckOut(address, checkOut);
    }
  
    /**
     * 验证从界面层传来的新增的退房信息是否有效
     * @param address String型，酒店地址
     * @param checkOut Room VO型，退房信息
     * @return 返回是否退房信息有
     * @throws WrongInputException 当退房信息有误时抛出异常s
     * @throws RemoteException 
     * @see
     */
    @Override
    public boolean validCheckOut(String address, RoomVO checkOut) throws WrongInputException, RemoteException{
        return checkOutList.validCheckOut(address, checkOut);
    }

}

