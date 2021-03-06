package businesslogic.roombl.updateCheckIn;

import java.rmi.RemoteException;
import java.util.Date;

import data_Stub.RoomDAOImpl_Stub;
import dataservice.roomDAO.RoomDAO;
import po.CheckInPO;
import po.RoomPO;
import po.RoomType;
import vo.CheckInVO;
import vo.RoomVO;

/**
 * CheckInItem的Mock类
 * @author 双
 * @version 
 * @see
 */
public class MockCheckInItem extends CheckInItem{

    private Enum<RoomType> roomType;
    private int roomNum;
    private String address;
    private Date checkInTime;
    private Date expDepartTime;
    
    private RoomDAO checkInDAO;
    
    @SuppressWarnings("deprecation")
    public MockCheckInItem(){
        Date checkInTime=new Date(116, 11, 11, 12, 0);
        Date expDepartTime=new Date(116, 11, 12, 12, 0);
        checkInDAO=new RoomDAOImpl_Stub(RoomType.SINGLE_ROOM, 0000000000000003, 400, "江苏省南京市栖霞区仙林大道163号",checkInTime,expDepartTime,null);
    }
    
    public MockCheckInItem(RoomPO roomPO) {
        this();
        CheckInPO checkInPO=(CheckInPO)roomPO;
        this.roomType = checkInPO.getRoomType();
        this.roomNum = checkInPO.getRoomNum();
        this.address = checkInPO.getAddress();
        this.checkInTime = checkInPO.getCheckInTime();
        this.expDepartTime = checkInPO.getExpDepartTime();
    }
    
    public MockCheckInItem(RoomVO roomVO){
        this();
        CheckInVO checkInVO=(CheckInVO)roomVO;
        this.roomType = checkInVO.roomType;
        this.roomNum = checkInVO.roomNum;
        this.address = checkInVO.address;
        this.checkInTime = checkInVO.checkInTime;
        this.expDepartTime = checkInVO.expDepartTime;
    }
    
    @Override
    public boolean addCheckIn(String address, boolean updateSpareRoom){
        RoomPO checkInPO=new CheckInPO(roomType, roomNum, address, checkInTime, expDepartTime);
        try {
            checkInDAO.insertRoom(checkInPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
      
    @Override
    public boolean validCheckIn(){
        return true;
    }
    
    @Override
    public RoomVO toVO(){
        return new CheckInVO(roomType, roomNum, address, checkInTime, expDepartTime);
    }
}
