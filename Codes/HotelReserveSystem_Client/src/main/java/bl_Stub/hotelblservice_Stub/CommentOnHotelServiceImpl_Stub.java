package bl_Stub.hotelblservice_Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import businesslogicservice.hotelblservice.CommentOnHotelService;
import po.OrderState;
import po.RoomType;
import vo.OrderVO;

public class CommentOnHotelServiceImpl_Stub implements CommentOnHotelService{

	public String userID;
	public String orderID;
	public String hotelName;
	public String hotelAddress;
	public Date beginDate;
	public Date finishDate;
	public RoomType roomType;
	public int num;
	public int totalPrice;
	public Date orderProducedTime;
	public Date lastedOrderDoneTime;
	public int numOfPerson;
	public boolean isChildren;
	public boolean isOnSale;
	public OrderState orderState;
	public boolean isCommented;
	
	public CommentOnHotelServiceImpl_Stub(String userID, String orderID, String hotelName, String hotelAddress,
			Date beginDate, Date finishDate, RoomType roomType, int num, int totalPrice, Date orderProducedTime,
			Date lastedOrderDoneTime, int numOfPerson, boolean isChildren, boolean isOnSale, OrderState orderState,
			boolean isCommented) {
		this.userID = userID;
		this.orderID = orderID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.beginDate = beginDate;
		this.finishDate = finishDate;
		this.roomType = roomType;
		this.num = num;
		this.totalPrice = totalPrice;
		this.orderProducedTime = orderProducedTime;
		this.lastedOrderDoneTime = lastedOrderDoneTime;
		this.numOfPerson = numOfPerson;
		this.isChildren = isChildren;
		this.isOnSale = isOnSale;
		this.orderState = orderState;
		this.isCommented = isCommented;
	}

	@Override
	public ArrayList<OrderVO> getCommentableOrderList(String userID) {
		ArrayList<OrderVO> orderVOList = new ArrayList<>();
		orderVOList.add(new OrderVO(userID, orderID, hotelName, hotelAddress, beginDate, finishDate, roomType, num, totalPrice,  orderState,orderProducedTime, lastedOrderDoneTime, numOfPerson, isChildren, isOnSale, isCommented));
		return orderVOList;
	}

	@Override
	public boolean confirmComment(String username, float mark, String comment, String hotelAddress, String orderID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	

}
