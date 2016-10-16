package businesslogicservice.orderblservice;

import java.util.ArrayList;

import po.OrderState;
import po.OrderType;
import vo.BriefOrderInfoVO;
import vo.OrderVO;

/**
 * 订单执行服务
 * @author Accident
 * @version 1.0
 * @see
 */
public interface GetOrderDoneService {
	
	/**
	 * 得到该酒店的所有订单OrderVO的ArrayList
	 * @param address String型 酒店地址
	 * @param orderType 订单类型
	 * @return 该酒店的所有订单OrderVO的ArrayList
	 * @see
	 */
	public ArrayList<BriefOrderInfoVO>  getHotelOrderList (String address, Enum<OrderType> orderType);

	/**
	 * 得到酒店订单详情
	 * @param address String型 酒店地址
	 * @param orderID String
	 * @return 检测订单号是否存在，如果存在，则返回对应订单的VO，否则返回null
	 * @see
	 */
	public OrderVO getSingleOrder(String address, String orderID);

	/**
	 * 设置订单状态
	 * @param orderstate 订单状态类型
	 * @param vo OrderVO
	 * @return 是否设置成功
	 * @see
	 */
	public boolean setOrderState(Enum<OrderState> orderstate, OrderVO vo);
	
	/**
	 * 执行订单（更新当前订单状态为已执行订单，并为客户增加与订单金额等值的信用值）
	 * @param vo OrderVO
	 * @return 是否成功
	 * @see
	 */
	public boolean getOrderDone(OrderVO vo);

	/**
	 * 延迟入住
	 * @param vo OrderVO
	 * @param hours 延迟入住时间（hours）
	 * @return 是否延迟成功
	 * @see
	 */
	public boolean delayCheckIn(OrderVO vo, int hours);
}