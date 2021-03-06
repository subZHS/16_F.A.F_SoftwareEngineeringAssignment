package businesslogic.roombl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import businesslogicservice.orderblservice.ResultMessage;
import po.RoomType;
import vo.OrderVO;
import vo.RoomVO;

/**
 * 给同层其他模块调用的接口
 * @author 双
 * @version 
 * @see
 */
public interface RoomInfoService {
    
    /**
     * 获取对应房间类型的空房数量
     * @param address String型， 同层调用传来的酒店地址
     * @param roomType 枚举类，酒店房间类型
     * @return int型，返回空房数量
     * @throws RemoteException 
     * @see
     */
    public int getAvailableRoomNum(String address, Enum<RoomType> roomType, Date day) throws RemoteException;
    
    /**
     * 获取用户预定时间段有无用户要求的房型剩余
     * @param addresss String型， 同层调用传来的酒店地址
     * @param roomType 枚举类，酒店房间类型
     * @param date 要查询的日期
     * @param num int型 所要预定的房间数
     * @return boolean型， 返回该房型在该天是否有剩余
     * @throws RemoteException
     * @see
     */
    public boolean isTimeAvailable (String addresss, Enum<RoomType> roomType, Date date, int num) throws RemoteException;
  
    /**
     * 检查订单信息能否得到满足
     * @param vo 要检查的订单的VO
     * @return boolean 返回该订单在对应酒店能否得到满足
     * @throws RemoteException 
     * @see
     */
    public ResultMessage checkOrder(OrderVO vo) throws RemoteException;
    
    /**
     * 检查搜索信息能否得到满足
     * @param hotelAddress String型，酒店地址
     * @param roomType RoomType型，房间类型
     * @param num int 型，房间数量
     * @param beginDate Date型，订单开始时间
     * @param finishDate Date型，订单结束时间
     * @return 返回搜索信息是否满足
     * @throws RemoteException
     * @see
     */
    public ResultMessage checkOrder(String hotelAddress, RoomType roomType, int num, Date beginDate, Date finishDate) throws RemoteException;
    
    /**
     * 在更新可用客房后更新空房信息
     * @param address String型， 同层调用传来的酒店地址
     * @param roomvo RoomVO类型，同层调用传来的可用客房信息的变化量
     * @return boolean型， 返回是否更新空房信息成功
     * @throws RemoteException 
     * @see
     */
    public boolean updateSpareRoom (String address, RoomVO roomvo) throws RemoteException;
    
    /**
     * 在用户生成订单，线下入住并更新入住信息或异常订单延迟入住时减少空房
     * @param address String型， 同层调用传来的酒店地址
     * @param change int型，同层传来的减少的房间数量
     * @param roomType 枚举类，酒店房间类型
     * @return boolean型，返回是否减少空房成功
     * @throws RemoteException 
     * @see
     */
    public boolean reduceRoom(String address, int change, Enum<RoomType> roomType, Date day) throws RemoteException;
    
    /**
     * 在更新退房信息，正常订单变成异常订单和用户撤销订单时增加空房
     * @param address String型， 同层调用传来的酒店地址
     * @param change int型，同层传来的减少的房间数量
     * @param roomType 枚举类，酒店房间类型
     * @return boolean型，返回是否增加空房成功
     * @throws RemoteException 
     * @see
     */
    public boolean addRoom(String address, int change, Enum<RoomType> roomType, Date day) throws RemoteException;
    
    /**
     * 得到当天的空房列表
     * @param address String型，酒店地址
     * @return 返回空房列表
     * @throws RemoteException 
     * @see
     */
    public ArrayList<RoomVO> getSpareRoomList(String address) throws RemoteException;
}
