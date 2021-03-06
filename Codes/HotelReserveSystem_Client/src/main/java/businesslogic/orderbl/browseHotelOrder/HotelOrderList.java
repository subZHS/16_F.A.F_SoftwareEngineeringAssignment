package businesslogic.orderbl.browseHotelOrder;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.utilitybl.POList2VOList;
import dataservice.orderDAO.OrderDAO;
import factory.FactoryService;
import factory.FactoryServiceImpl;
import po.OrderType;
import vo.BriefOrderInfoVO;
import vo.OrderVO;

public class HotelOrderList {
	private OrderDAO orderDaoService;
	private ArrayList<BriefOrderInfoVO> briefOrderlist;

	// PO to VO 转换类
	private POList2VOList poTransformer;
	private OrderVO detailedOrderVO;
	
	private FactoryService factory;

	public HotelOrderList() {
		poTransformer = new POList2VOList();
		this.factory = new FactoryServiceImpl();
		orderDaoService = factory.getOrderDAO();
		briefOrderlist = new ArrayList<BriefOrderInfoVO>();
	}

	/**
	 * 得到简要酒店订单列表
	 * @param address
	 * @param orderType
	 * @return
	 * @throws RemoteException 
	 * @see
	 */
	public ArrayList<BriefOrderInfoVO> getHotelOrderList(String address, Enum<OrderType> orderType) throws RemoteException {
		briefOrderlist = poTransformer.briefPo2voList(orderDaoService.getHotelOrderList(address, orderType));
		return briefOrderlist;
	}

	public OrderVO getSingleOrder(String address, String orderID) {
		try {
			detailedOrderVO = poTransformer.orderPO2VO(orderDaoService.getSingleOrder(address, orderID));
		} catch (RemoteException e) {
			// 异常捕捉代码
			e.printStackTrace();
			return null;
		}
		return detailedOrderVO;
	}
}
