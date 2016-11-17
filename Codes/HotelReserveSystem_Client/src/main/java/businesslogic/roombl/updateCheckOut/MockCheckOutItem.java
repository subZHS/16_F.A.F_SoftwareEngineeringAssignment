package businesslogic.roombl.updateCheckOut;

import java.rmi.RemoteException;
import java.util.Date;

import data_Stub.RoomDAOImpl_Stub;
import dataservice.roomDAO.RoomDAO;
import po.CheckInOutPO;
import po.RoomPO;
import po.RoomType;
import vo.CheckInOutVO;
import vo.RoomVO;

public class MockCheckOutItem extends CheckOutItem{

    private Enum<RoomType> roomType;
    private int roomNum;
    private String address;
    private Date actDepartTime;
    
    private RoomDAO checkOutDAO;
    
    @SuppressWarnings("deprecation")
    public MockCheckOutItem(){
        Date actDepartTime=new Date(2016, 11, 6, 12, 0);
        checkOutDAO=new RoomDAOImpl_Stub(RoomType.SINGLE_ROOM, 3, 400, "江苏省南京市栖霞区仙林大道163号",null,null,actDepartTime);
    }
    
    public MockCheckOutItem(RoomPO roomPO) {
        this();
        CheckInOutPO CheckOutPO=(CheckInOutPO)roomPO;
        this.roomType = CheckOutPO.getRoomType();
        this.roomNum = CheckOutPO.getRoomNum();
        this.address = CheckOutPO.getAddress();
        this.actDepartTime = CheckOutPO.getActDepartTime();
    }
    
    public MockCheckOutItem(RoomVO roomVO){
        this();
        CheckInOutVO CheckOutVO=(CheckInOutVO)roomVO;
        this.roomType = CheckOutVO.roomType;
        this.roomNum = CheckOutVO.roomNum;
        this.address = CheckOutVO.address;
        this.actDepartTime = CheckOutVO.actDepartTime;
    }
    
    @Override
    public boolean addCheckOut(String address){
        RoomPO checkOutPO=new CheckInOutPO(roomType, roomNum, address, actDepartTime);
        try {
            checkOutDAO.insert(checkOutPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @Override
    public boolean modifyCheckOut(String address){
        RoomPO checkOutPO=new CheckInOutPO(roomType, roomNum, address, actDepartTime);
        try {
            checkOutDAO.update(checkOutPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @Override
    public boolean delCheckOut(String address){
        RoomPO checkOutPO=new CheckInOutPO(roomType, roomNum, address, actDepartTime);
        try {
            checkOutDAO.delete(checkOutPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @Override
    public boolean validCheckOut(){
        return false;
        
    }
    
    @Override
    public RoomVO toVO(){
        return new CheckInOutVO(roomType, roomNum, address, actDepartTime);
    }
}