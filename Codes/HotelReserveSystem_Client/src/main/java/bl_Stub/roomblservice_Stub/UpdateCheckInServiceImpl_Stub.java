package bl_Stub.roomblservice_Stub;

import java.util.Date;
import java.util.ArrayList;

import businesslogicservice.roomblservice.UpdateCheckInService;
import po.RoomType;
import vo.CheckInVO;
import vo.RoomVO;

/**
 * 
 * @author 双
 * @version 
 * @see
 */
public class UpdateCheckInServiceImpl_Stub implements UpdateCheckInService{

    public Enum<RoomType> roomType;
    public int roomNum;
    public int roomPrice;
    public String address;
    public Date checkInTime;
    public Date expDepartTime;
    
    public UpdateCheckInServiceImpl_Stub(Enum<RoomType> roomType, int roomNum, int roomPrice, String address,
            Date checkInTime, Date expDepartTime) {
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.roomPrice = roomPrice;
        this.address = address;
        this.checkInTime = checkInTime;
        this.expDepartTime = expDepartTime;
    }

    @Override
    public ArrayList<RoomVO> getCheckInList(String address) {
        RoomVO checkInVO=new CheckInVO(roomType, roomNum, address, checkInTime, expDepartTime);
        ArrayList<RoomVO> arrayList=new ArrayList<RoomVO>();
        arrayList.add(checkInVO);
        return arrayList;
    }

    @Override
    public ArrayList<RoomVO> searchCheckInInfo(String address, Date startTime, Date endTime) {
        RoomVO checkInVO=new CheckInVO(roomType, roomNum, address, startTime, expDepartTime);
        ArrayList<RoomVO> arrayList=new ArrayList<RoomVO>();
        arrayList.add(checkInVO);
        return arrayList;
    }

    @Override
    public ArrayList<RoomVO> searchCheckInInfo(String address, Enum<RoomType> roomType) {
        RoomVO checkInVO=new CheckInVO(roomType, roomNum, address, checkInTime, expDepartTime);
        ArrayList<RoomVO> arrayList=new ArrayList<RoomVO>();
        arrayList.add(checkInVO);
        return arrayList;
    }

    @Override
    public boolean addCheckIn(String address, RoomVO checkIn, boolean updateSpareRoom) {
        return true;
    }

    @Override
    public boolean validCheckIn(String address, RoomVO checkIn) {
        return true;
    }

}
