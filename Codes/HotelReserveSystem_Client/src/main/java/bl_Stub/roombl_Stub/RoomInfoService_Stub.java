package bl_Stub.roombl_Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.roombl.RoomInfoService;
import businesslogicservice.orderblservice.ResultMessage;
import po.RoomType;
import vo.OrderVO;
import vo.RoomVO;

/**
 * 
 * @author 双
 * @version 
 * @see
 */
public class RoomInfoService_Stub implements RoomInfoService{
    @Override
    public int getAvailableRoomNum(String address, Enum<RoomType> roomType,Date day) {
        System.out.println("获取该房间类型的空房数量成功");
        return 0;
    }

    @Override
    public boolean isTimeAvailable(String addresss, Enum<RoomType> roomType, Date date, int num) {
        return true;
    }

    @Override
    public boolean updateSpareRoom(String address, RoomVO roomvo) {
        return true;
    }

    @Override
    public boolean reduceRoom(String address, int change, Enum<RoomType> roomType,Date day) {
        return true;
    }

    @Override
    public boolean addRoom(String address, int change, Enum<RoomType> roomType,Date day) {
        return true;
    }

	@Override
	public ResultMessage checkOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

    @Override
    public ArrayList<RoomVO> getSpareRoomList(String address) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultMessage checkOrder(String hotelAddress, RoomType roomType, int num, Date beginDate, Date finishDate)
            throws RemoteException {
        return null;
    }

}
