package org.FAF.businesslogic.orderbl.checkAbnormalOrder;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businesslogic.orderbl.checkAbnormalOrder.AbnormalOrderList;
import po.OrderState;
import po.RoomType;
import rmi.LinkToServer;
import vo.BriefOrderInfoVO;
import vo.OrderVO;

public class AbnormalOrderListTest {
	private static LinkToServer linkToServer;
	
	private AbnormalOrderList abnormalOrderList;
	
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
	
	@SuppressWarnings("unused")
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
	public void setup(){
		this.orderID = "0000000000000006";
		this.userID = "原";
		this.hotelName = "Jingling Hotel";
		this.hotelAddress = "江苏省南京市栖霞区仙林大道163号";
		this.beginDate = new Date(116, 9, 19);
		this.finishDate = new Date(116, 9, 20);
		this.roomType = RoomType.STANDARD_ROOM;
		this.num = 1;
		this.totalPrice = 200;
		this.orderState = OrderState.ABNORMAL_ORDER;
		this.orderProducedTime = new Date(116, 11, 4, 17, 11, 20);
		this.lastedOrderDoneTime = new Date(116, 11, 20, 22, 0);
		this.numOfPerson = 1;
		this.isChildren = false;
		this.isOnSale = false;
		this.isCommented = false;
		
		this.isReserved = true;

		abnormalOrderList= new AbnormalOrderList();
	}
	
	//正常情况下的Test
	@Test
	public void getAbnormalOrderListTest_1(){
		@SuppressWarnings("deprecation")
		ArrayList<BriefOrderInfoVO> briefOrderInfoList;
		try {
			briefOrderInfoList = abnormalOrderList.getAbnormalOrderList(new Date(116, 9, 19));
			BriefOrderInfoVO fisrtOrder = briefOrderInfoList.get(0);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in orderID!", orderID, fisrtOrder.orderID);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in userID!", userID, fisrtOrder.userID);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in hotelName!", hotelName, fisrtOrder.hotelName);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in hotelAddress!", hotelAddress, fisrtOrder.hotelAddress);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in beginDate!", beginDate, fisrtOrder.beginDate);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in finishDate!", finishDate, fisrtOrder.finishDate);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in roomType!", roomType, fisrtOrder.roomType);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in num!", num, fisrtOrder.num);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in totalPrice!", totalPrice, fisrtOrder.totalPrice);
			assertEquals("AbnormalOrderList.getAbnormalOrderList(Date date) has an error in orderState!", orderState, fisrtOrder.orderState);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	//正常情况下的Test
	@Test
	public void getDetailedOrderTest_1(){
		OrderVO detailedOrder;
		try {
			detailedOrder = abnormalOrderList.getDetailedOrder("0000000000000006");
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in orderID!", orderID, detailedOrder.orderID);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in userID!", userID, detailedOrder.userID);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in hotelName!", hotelName, detailedOrder.hotelName);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in hotelAddress!", hotelAddress, detailedOrder.hotelAddress);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in beginDate!", beginDate, detailedOrder.beginDate);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in finishDate!", finishDate, detailedOrder.finishDate);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in roomType!", roomType, detailedOrder.roomType);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in num!", num, detailedOrder.num);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in totalPrice!", totalPrice, detailedOrder.totalPrice);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in orderState!", orderState, detailedOrder.orderState);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in orderProducedTime!", orderProducedTime, detailedOrder.orderProducedTime);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in lastedOrderDoneTime!", lastedOrderDoneTime, detailedOrder.lastedOrderDoneTime);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in numOfPerson!", numOfPerson, detailedOrder.numOfPerson);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in isChildren!", isChildren, detailedOrder.isChildren);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in isOnSale!", isOnSale, detailedOrder.isOnSale);
			assertEquals("AbnormalOrderList.getDetailedOrder(String orderID) has an error in isCommented!", isCommented, detailedOrder.isCommented);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	//异常情况下的Test(所得订单号对应订单不是异常订单)
	@Test
	public void getDetailedOrderTest_2() {
		OrderVO detailedOrder;
		try {
			detailedOrder = abnormalOrderList.getDetailedOrder("0000000000000001");
			System.out.println(detailedOrder);
			assertEquals(null, detailedOrder);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}
}
