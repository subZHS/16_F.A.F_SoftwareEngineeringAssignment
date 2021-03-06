package businesslogicservice.orderblservice;


import java.rmi.RemoteException;
import java.util.Date;

import po.RoomType;
import vo.BriefHotelInfoVO;
import vo.OrderVO;

/**
 * 为生成订单界面提供所需要的所有服务
 * @author Accident
 * @version 1.0
 * @see
 */
public interface CreateNewOrderService {
	
	/**
	 * 获取该酒店简要信息 (调用HotelInfoService)
	 * @param address String型， 同层调用传来的酒店地址
	 * @return 返回该酒店简要信息
	 * @throws RemoteException 
	 * @see
	 */
	public BriefHotelInfoVO getHotelBriefInfo(String address) throws RemoteException;
	
	/**
	 * 初始化新订单(需要查询信用值是否>0)
	 * @param userID String型 客户ID
	 * @param address String型 酒店地址
	 * @return 被初始化的OrderVO 若用户信用值<0,则返回null
	 * @throws RemoteException 
	 * @see
	 */
	public OrderVO initNewOrder(String userID, String hotelName, String hotelAddress) throws RemoteException;
	
	/**
	 * 得到所有房型的RoomVO (调用RoomInfoService)
	 * @param address String型 酒店地址
	 * @param roomType RoomType枚举类型
	 * @return 所有房型的RoomVO的ArrayList
	 * @throws RemoteException 
	 * @see
	 */
	 public int getAvailableRoomNum(String address, Enum<RoomType> roomType, Date day) throws RemoteException;
	
	/**
	 * 获取订单总价
	 * @param vo 订单VO
	 * @return 订单总价
	 * @throws RemoteException 
	 * @see
	 */
	public int getPrice(OrderVO vo) throws RemoteException; 
	
	/**
	 * 获取该酒店对应房型的原始价格
	 * @param hotelAddress
	 * @param roomType
	 * @return
	 * @throws RemoteException 
	 * @see
	 */
	public int getOriginalPrice(String hotelAddress, RoomType roomType) throws RemoteException;
	
	/**
	 * 检查订单可否被满足,及是否所需拥有全部信息
	 * @param vo 订单VO
	 * @return ResultMessage
	 * @see
	 */
	public ResultMessage checkNewOrder(OrderVO vo);
	
	/**
	 * 添加新的订单
	 * @param vo 订单VO
	 * @return boolean 是否生成成功
	 * @throws RemoteException 
	 * @see
	 */
	public boolean addNewOrder(OrderVO vo) throws RemoteException;
	
	/**
     * 获取某订单能享受的唯一酒店促销策略折扣名称
     * @param order OrderVO型，同层调用传来的订单信息
     * @return String型，返回酒店促销策略名称，若没有，则返回null
     * @throws RemoteException 
     * @see
     */
    public String getAvailblePromotionName(OrderVO order) throws RemoteException;
    
    /**
     * 获取某订单能享受的唯一网站营销策略折扣名称
     * @param order OrderVO型，同层调用传来的订单信息
     * @return String型，返回网站营销策略名称，若没有，则返回null
     * @throws RemoteException 
     * @see
     */
    public String getAvailbleMarketStrategyName (OrderVO order) throws RemoteException;
}
