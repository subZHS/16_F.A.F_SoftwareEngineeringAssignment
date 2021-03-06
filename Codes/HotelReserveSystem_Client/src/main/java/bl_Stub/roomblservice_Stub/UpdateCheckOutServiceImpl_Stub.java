package bl_Stub.roomblservice_Stub;

import java.util.Date;
import java.util.ArrayList;

import businesslogicservice.roomblservice.UpdateCheckOutService;
import po.RoomType;
import vo.CheckOutVO;
import vo.RoomVO;

/**
 * 
 * @author 双
 * @version 
 * @see
 */
public class UpdateCheckOutServiceImpl_Stub implements UpdateCheckOutService{

    public Enum<RoomType> roomType;
    public int roomNum;
    public int roomPrice;
    public String address;
    public Date actDepartTime;
    
    public UpdateCheckOutServiceImpl_Stub(Enum<RoomType> roomType, int roomNum, int roomPrice, String address,
            Date actDepartTime) {
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.roomPrice = roomPrice;
        this.address = address;
        this.actDepartTime = actDepartTime;
    }

    @Override
    public ArrayList<RoomVO> getCheckOutList(String address) {
        RoomVO checkOutVO=new CheckOutVO(roomType, roomNum, address, actDepartTime);
        ArrayList<RoomVO> arrayList=new ArrayList<RoomVO>();
        arrayList.add(checkOutVO);
        return arrayList;
    }

    @Override
    public ArrayList<RoomVO> searchCheckOutInfo(String address, Date startTime, Date endTime) {
        RoomVO checkOutVO=new CheckOutVO(roomType, roomNum, address, startTime);
        ArrayList<RoomVO> arrayList=new ArrayList<RoomVO>();
        arrayList.add(checkOutVO);
        return arrayList;
    }

    @Override
    public ArrayList<RoomVO> searchCheckOutInfo(String address, Enum<RoomType> roomType) {
        RoomVO checkOutVO=new CheckOutVO(roomType, roomNum, address, actDepartTime);
        ArrayList<RoomVO> arrayList=new ArrayList<RoomVO>();
        arrayList.add(checkOutVO);
        return arrayList;
    }

    @Override
    public boolean addCheckOut(String address, RoomVO roomvo) {
        return true;
    }

    @Override
    public boolean validCheckOut(String address, RoomVO checkOut) {
        return true;
    }

}
