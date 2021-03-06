package bl_Driver.orderblservice_Driver;

import java.rmi.RemoteException;

import businesslogicservice.orderblservice.CreateNewOrderService;
import businesslogicservice.orderblservice.ResultMessage;
import vo.BriefHotelInfoVO;
import vo.OrderVO;

public class CreateNewOrderServiceImpl_Driver {
	public void drive(CreateNewOrderService createNewOrderService) throws RemoteException{
		BriefHotelInfoVO hotelInfoVO = createNewOrderService.getHotelBriefInfo("江苏省南京市栖霞区仙林大道163号");
		System.out.println("确定预定位于" + hotelInfoVO.hotelAddress + "的" + hotelInfoVO.hotelName + "?");
		
		OrderVO newOrder = createNewOrderService.initNewOrder("原", "汉庭","江苏省南京市栖霞区仙林大道163号");
		
		System.out.println("该用户选择订一间大床房");
		newOrder.num = 1;
		
		System.out.println("订单总价为" + createNewOrderService.getPrice(newOrder));
		
		if(createNewOrderService.checkNewOrder(newOrder) == ResultMessage.SUCCEED){
			System.out.println("正在生成订单......");
			if(createNewOrderService.addNewOrder(newOrder))
				System.out.println("生成订单成功！");
			else
				System.out.println("生成订单失败，请稍后再试");
		}
		else if(createNewOrderService.checkNewOrder(newOrder) == ResultMessage.NUM_CANNOT_SATISFIED)
			System.out.println("数量过多，无法满足！");
		else if(createNewOrderService.checkNewOrder(newOrder) == ResultMessage.TIME_CANNOT_SATISFIED)
			System.out.println("该时间段无房间剩余！");
		else if(createNewOrderService.checkNewOrder(newOrder) == ResultMessage.TYPE_CANNOT_SATISFIED)
			System.out.println("该房型无剩余！");
		
		
	}
}
