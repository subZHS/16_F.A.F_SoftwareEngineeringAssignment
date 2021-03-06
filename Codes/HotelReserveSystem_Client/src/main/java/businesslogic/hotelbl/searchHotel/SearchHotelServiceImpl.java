package businesslogic.hotelbl.searchHotel;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import businesslogic.hotelbl.OrderInfo;
import businesslogic.roombl.RoomInfoService;
import businesslogicservice.hotelblservice.SearchHotelService;
import businesslogicservice.orderblservice.ResultMessage;
import dataservice.hotelDAO.HotelDAO;
import factory.FactoryService;
import factory.FactoryServiceImpl;
import po.BriefHotelInfoPO;
import po.BriefOrderInfoPO;
import po.BusinessDistrictPO;
import po.OrderState;
import po.RoomType;
import vo.BriefHotelInfoVO;
import vo.BriefOrderInfoVO;
import vo.OrderedHotelInfoVO;

public class SearchHotelServiceImpl implements SearchHotelService {

	private FactoryService factory;
	
	private OrderInfo orderInfo;
	private RoomInfoService roomInfoService;
	private ArrayList<BriefOrderInfoVO> orderList;
	private HotelDAO hotelDAO;
	protected String userID;
	
	/**
	 * 将储存房间类型的int型转换成RoomType型
	 * @param roomType
	 * @return
	 * @see
	 */
	private RoomType convertFromIntToRoomType(int roomType) {
		switch(roomType) {
		case 0:
			return RoomType.SINGLE_ROOM;
		case 1:
			return RoomType.STANDARD_ROOM;
		case 2:
			return RoomType.TRIBLE_ROOM;
		default:
			return RoomType.KING_SIZE_ROOM;
		}
	}	
	
	/**
	 * 获得地址不重复的订单列表
	 * @param orderInfoList
	 * @return
	 * @see
	 */
	private ArrayList<BriefOrderInfoPO> getAddress() {
		//拷贝一份订单列表供方法内部操作，防止操作修改原有的数据
		ArrayList<BriefOrderInfoPO> hotelList = new ArrayList<>();
		for(BriefOrderInfoVO order: orderList) {
			hotelList.add(new BriefOrderInfoPO(order));
		}
		
		//通过酒店地址，检查是否有相同的酒店，若有，则在拷贝的那一份中删除
		for(int i = 0; i < hotelList.size(); i++) {
			for(int j = i + 1; j < hotelList.size(); j++) {
				if(hotelList.get(i).getHotelAddress().equals(hotelList.get(j).getHotelAddress())) {
					hotelList.remove(j);
					j--;
				}
			}
		}
		return hotelList;
	}
	
	/**
	 * 获得该用户在该酒店的所有订单类型
	 * @param hotelAddress
	 * @return
	 * @see
	 */
	private Set<Enum<OrderState>> getStates(String hotelAddress) {
		Set<Enum<OrderState>> hotelState = new HashSet<>();
		for(BriefOrderInfoVO orderInfoVO : orderList) {
			if(orderInfoVO.hotelAddress.equals(hotelAddress)) {
				hotelState.add(orderInfoVO.orderState);
			}
		}
		return hotelState;
	}
	
	public SearchHotelServiceImpl(String userID) throws RemoteException {
		this.userID = userID;
		this.factory = new FactoryServiceImpl();
		this.hotelDAO = factory.getHotelDAO();
		this.orderInfo = factory.createOrderInfo();
		orderList = orderInfo.getReservedOrderList(userID);
		this.roomInfoService = factory.createRoomInfoService();
	}
	
	@Override
	public ArrayList<OrderedHotelInfoVO> getHotelBriefInfoListBySearching(String[] condition) throws RemoteException {
		ArrayList<OrderedHotelInfoVO> result = new ArrayList<>();
		ArrayList<BriefOrderInfoPO> orderedHotelList = this.getAddress();
		
		ArrayList<BriefHotelInfoPO> list = hotelDAO.getHotelBriefInfoListBySearching(condition, orderedHotelList);
		//日期的格式变量
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date finishDate = null;
		try {
			beginDate = formatter.parse(condition[12]);
			finishDate = formatter.parse(condition[13]);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		RoomType roomType = convertFromIntToRoomType(Integer.parseInt(condition[10]));
		int roomNum = Integer.parseInt(condition[11]);
		for(BriefHotelInfoPO hotelInfoPO : list) {
			if(roomInfoService.checkOrder(hotelInfoPO.getHotelAddress(), roomType, roomNum, beginDate, finishDate) == ResultMessage.SUCCEED) {
				result.add(new OrderedHotelInfoVO(new BriefHotelInfoVO(hotelInfoPO), this.getStates(hotelInfoPO.getHotelAddress())));
			}
		}
		return result;
	}

	@Override
	public ArrayList<BusinessDistrictPO> getBusinessDistrictList(String city) throws RemoteException {
		return hotelDAO.getBusinessDistrictList(city);
	}

}
