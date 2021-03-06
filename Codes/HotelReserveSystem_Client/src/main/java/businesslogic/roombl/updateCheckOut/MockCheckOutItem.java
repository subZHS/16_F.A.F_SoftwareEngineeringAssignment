package businesslogic.roombl.updateCheckOut;

import java.rmi.RemoteException;
import java.util.Date;

import data_Stub.RoomDAOImpl_Stub;
import dataservice.roomDAO.RoomDAO;
import po.CheckOutPO;
import po.RoomPO;
import po.RoomType;
import vo.CheckOutVO;
import vo.RoomVO;

/**
 * CheckOutItem的Mock类
 * @author 双
 * @version 
 * @see
 */
public class MockCheckOutItem extends CheckOutItem{

    private Enum<RoomType> roomType;
    private int roomNum;
    private String address;
    private Date actDepartTime;
    
    private RoomDAO checkOutDAO;
    
    @SuppressWarnings("deprecation")
    public MockCheckOutItem(){
        Date actDepartTime=new Date(2016, 11, 12, 12, 0);
        checkOutDAO=new RoomDAOImpl_Stub(RoomType.SINGLE_ROOM, 0000000000000003, 0, "江苏省南京市栖霞区仙林大道163号",null,null,actDepartTime);
    }
    
    public MockCheckOutItem(RoomPO roomPO) {
        this();
        CheckOutPO CheckOutPO=(CheckOutPO)roomPO;
        this.roomType = CheckOutPO.getRoomType();
        this.roomNum = CheckOutPO.getRoomNum();
        this.address = CheckOutPO.getAddress();
        this.actDepartTime = CheckOutPO.getActDepartTime();
    }
    
    public MockCheckOutItem(RoomVO roomVO){
        this();
        CheckOutVO CheckOutVO=(CheckOutVO)roomVO;
        this.roomType = CheckOutVO.roomType;
        this.roomNum = CheckOutVO.roomNum;
        this.address = CheckOutVO.address;
        this.actDepartTime = CheckOutVO.actDepartTime;
    }
    
    @Override
    public boolean addCheckOut(String address){
        RoomPO checkOutPO=new CheckOutPO(roomType, roomNum, address, actDepartTime);
        try {
            checkOutDAO.insertRoom(checkOutPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
  
    @Override
    public boolean validCheckOut(){
        return true;
        
    }
    
    @Override
    public RoomVO toVO(){
        return new CheckOutVO(roomType, roomNum, address, actDepartTime);
    }
}
