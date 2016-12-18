package org.FAF.businesslogic.orderbl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businesslogic.orderbl.OrderInfoImpl;
import po.OrderState;
import po.RoomType;
import rmi.LinkToServer;
import vo.BriefOrderInfoVO;
import vo.OrderVO;

public class OrderInfoImplTest {
	private static LinkToServer linkToServer;
	
	private OrderInfoImpl orderInfoImpl;
	private String userID;
	private String orderID;
	private String hotelName;
	private String hotelAddress;
	private Date beginDate;
	private Date finishDate;
	private RoomType roomType;
	private int num;
	private int totalPrice;
	private Enum<OrderState> orderState;
	private Date orderProducedTime;
	private Date lastedOrderDoneTime;
	private int numOfPerson;
	private boolean isChildren;
	private boolean isOnSale;
	private boolean isCommented;

	private boolean isReserved;
	
	@BeforeClass
	public static void set() {
		linkToServer = new LinkToServer();
		try {
			linkToServer.linkToServer();
		} catch (RemoteException e) {
			System.out.println("网络通信错误");
		}
	}
	
	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		this.orderID = "0000000000000001";
		this.userID = "原";
		this.hotelName = "Jingling Hotel";
		this.hotelAddress = "江苏省南京市栖霞区仙林大道163号";
		this.beginDate = new Date(116, 9, 19);
		this.finishDate = new Date(116, 9, 20);
		this.roomType = RoomType.STANDARD_ROOM;
		this.num = 1;
		this.totalPrice = 200;
		this.orderState = OrderState.NOT_DONE_ORDER;
		this.orderProducedTime = new Date(116, 11, 15, 18, 0);
		this.lastedOrderDoneTime = new Date(116, 11, 20, 21, 0);
		this.numOfPerson = 1;
		this.isChildren = false;
		this.isOnSale = false;
		this.isCommented = false;

		this.isReserved = true;
		orderInfoImpl = new OrderInfoImpl();
	}

	@Test
	public void getAllOrdersTest_1() {
		ArrayList<OrderVO> allOrders;
		try {
			allOrders = orderInfoImpl.getAllOrders("原");
			OrderVO firstOrder = allOrders.get(0);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderID!", orderID,
					firstOrder.orderID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in userID!", userID,
					firstOrder.userID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelName!", hotelName,
					firstOrder.hotelName);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelAddress!", hotelAddress,
					firstOrder.hotelAddress);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in beginDate!", beginDate,
					firstOrder.beginDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in finishDate!", finishDate,
					firstOrder.finishDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in roomType!", roomType,
					firstOrder.roomType);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in num!", num, firstOrder.num);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in totalPrice!", totalPrice,
					firstOrder.totalPrice);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderState!", orderState,
					firstOrder.orderState);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderProducedTime!",
					orderProducedTime, firstOrder.orderProducedTime);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in lastedOrderDoneTime!",
					lastedOrderDoneTime, firstOrder.lastedOrderDoneTime);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in numOfPerson!", numOfPerson,
					firstOrder.numOfPerson);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isChildren!", isChildren,
					firstOrder.isChildren);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isOnSale!", isOnSale,
					firstOrder.isOnSale);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isCommented!", isCommented,
					firstOrder.isCommented);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void isReservedTest_1() {
		boolean result;
		try {
			result = orderInfoImpl.isReserved("原", "江苏省南京市栖霞区仙林大道163号");
			assertEquals(isReserved, result);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void getCommentableOrderListTest_1() {
		ArrayList<OrderVO> result;
		try {
			result = orderInfoImpl.getCommentableOrderList(userID);
			OrderVO firstOrder = result.get(0);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderID!", orderID,
					firstOrder.orderID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in userID!", userID,
					firstOrder.userID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelName!", hotelName,
					firstOrder.hotelName);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelAddress!", hotelAddress,
					firstOrder.hotelAddress);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in beginDate!", beginDate,
					firstOrder.beginDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in finishDate!", finishDate,
					firstOrder.finishDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in roomType!", roomType,
					firstOrder.roomType);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in num!", num, firstOrder.num);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in totalPrice!", totalPrice,
					firstOrder.totalPrice);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderState!", orderState,
					firstOrder.orderState);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderProducedTime!",
					orderProducedTime, firstOrder.orderProducedTime);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in lastedOrderDoneTime!",
					lastedOrderDoneTime, firstOrder.lastedOrderDoneTime);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in numOfPerson!", numOfPerson,
					firstOrder.numOfPerson);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isChildren!", isChildren,
					firstOrder.isChildren);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isOnSale!", isOnSale,
					firstOrder.isOnSale);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isCommented!", isCommented,
					firstOrder.isCommented);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void getOrderListTest_1(){
		ArrayList<OrderVO> result;
		try {
			result = orderInfoImpl.getOrderList("原", "江苏省南京市栖霞区仙林大道163号");
			OrderVO firstOrder = result.get(0);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderID!", orderID,
					firstOrder.orderID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in userID!", userID,
					firstOrder.userID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelName!", hotelName,
					firstOrder.hotelName);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelAddress!", hotelAddress,
					firstOrder.hotelAddress);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in beginDate!", beginDate,
					firstOrder.beginDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in finishDate!", finishDate,
					firstOrder.finishDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in roomType!", roomType,
					firstOrder.roomType);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in num!", num, firstOrder.num);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in totalPrice!", totalPrice,
					firstOrder.totalPrice);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderState!", orderState,
					firstOrder.orderState);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderProducedTime!",
					orderProducedTime, firstOrder.orderProducedTime);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in lastedOrderDoneTime!",
					lastedOrderDoneTime, firstOrder.lastedOrderDoneTime);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in numOfPerson!", numOfPerson,
					firstOrder.numOfPerson);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isChildren!", isChildren,
					firstOrder.isChildren);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isOnSale!", isOnSale,
					firstOrder.isOnSale);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in isCommented!", isCommented,
					firstOrder.isCommented);	
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void getReservedOrderListTest_1(){
		ArrayList<BriefOrderInfoVO> result;
		try {
			result = orderInfoImpl.getReservedOrderList("原");
			BriefOrderInfoVO firstOrder = result.get(0);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderID!", orderID,
					firstOrder.orderID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in userID!", userID,
					firstOrder.userID);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelName!", hotelName,
					firstOrder.hotelName);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in hotelAddress!", hotelAddress,
					firstOrder.hotelAddress);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in beginDate!", beginDate,
					firstOrder.beginDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in finishDate!", finishDate,
					firstOrder.finishDate);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in roomType!", roomType,
					firstOrder.roomType);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in num!", num, firstOrder.num);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in totalPrice!", totalPrice,
					firstOrder.totalPrice);
			assertEquals("OrderInfoImpl.getReservedOrderList(String userID) has an error in orderState!", orderState,
					firstOrder.orderState);	
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}
}
