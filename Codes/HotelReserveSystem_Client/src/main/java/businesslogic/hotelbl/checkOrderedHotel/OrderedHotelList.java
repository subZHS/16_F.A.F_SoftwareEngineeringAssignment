package businesslogic.hotelbl.checkOrderedHotel;

import java.util.ArrayList;

import businesslogic.hotelbl.OrderInfo;
import businesslogic.orderbl.MockOrderInfoImpl;
import po.OrderState;
import vo.BriefOrderInfoVO;
import vo.OrderedHotelInfoVO;

public class OrderedHotelList {
	private OrderInfo orderInfo;
	private ArrayList<BriefOrderInfoVO> orderInfoList;
	private OrderedHotelItem hotelItem;
	private String userID;
	
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	
	public OrderedHotelList(String userID) {
		this.userID = userID;
		this.setOrderInfo(new MockOrderInfoImpl());
		this.orderInfoList = orderInfo.getReservedOrderList(this.userID);
	}
	
	/**
	 * 获得地址不重复的订单列表
	 * @param orderInfoList
	 * @return
	 * @see
	 */
	private ArrayList<BriefOrderInfoVO> getAddress(ArrayList<BriefOrderInfoVO> orderInfoList) {
		ArrayList<BriefOrderInfoVO> hotelList = new ArrayList<>();
		return hotelList;
	}
	
	private ArrayList<OrderState> getStates(String hotelAddress) {
		ArrayList<OrderState> hotelState = new ArrayList<>();
		return hotelState;
	}
	
	public ArrayList<OrderedHotelInfoVO> enrollHotelBreifInfoList() {
		ArrayList<BriefOrderInfoVO> hotelList = this.getAddress(orderInfoList);
		ArrayList<OrderedHotelInfoVO> result = new ArrayList<>();
		for(BriefOrderInfoVO orderInfoVO : hotelList) {
			hotelItem = new OrderedHotelItem(orderInfoVO.hotelAddress);
			result.add(new OrderedHotelInfoVO(hotelItem.getBriefHotelInfo(), this.getStates(orderInfoVO.hotelAddress)));
		}
		return result;
	}
}

