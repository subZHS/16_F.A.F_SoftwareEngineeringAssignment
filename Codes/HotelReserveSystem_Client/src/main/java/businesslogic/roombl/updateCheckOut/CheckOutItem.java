package businesslogic.roombl.updateCheckOut;

import java.rmi.RemoteException;
import java.util.Date;

import dataservice.roomDAO.RoomDAO;
import po.CheckOutPO;
import po.RoomPO;
import po.RoomType;
import vo.CheckOutVO;
import vo.RoomVO;

/**
 * 
 * @author 双
 * @version 
 * @see
 */
public class CheckOutItem {

    private Enum<RoomType> roomType;
    private int roomNum;
    private String address;
    private Date actDepartTime;
    
    private RoomDAO checkOutDAO;
    
    public CheckOutItem(){
        
    }
    
    /**
     * 
     * @param roomPO RoomPO型，退房信息
     */
    public CheckOutItem(RoomPO roomPO) {
        super();
        CheckOutPO CheckOutPO=(CheckOutPO)roomPO;
        this.roomType = CheckOutPO.getRoomType();
        this.roomNum = CheckOutPO.getRoomNum();
        this.address = CheckOutPO.getAddress();
        this.actDepartTime = CheckOutPO.getActDepartTime();
    }
    
    /**
     * 
     * @param roomVO RoomVO型，退房信息
     */
    public CheckOutItem(RoomVO roomVO){
        super();
        CheckOutVO CheckOutVO=(CheckOutVO)roomVO;
        this.roomType = CheckOutVO.roomType;
        this.roomNum = CheckOutVO.roomNum;
        this.address = CheckOutVO.address;
        this.actDepartTime = CheckOutVO.actDepartTime;
    }
    
    /**
     * 增加退房信息
     * @param address string型，酒店地址
     * @return
     * @see
     */
    public boolean addCheckOut(String address){
        RoomPO checkOutPO=new CheckOutPO(roomType, roomNum, address, actDepartTime);
        try {
            checkOutDAO.insertRoom(checkOutPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        //更新空房信息
        
        return true;
    }
  
    /**
     * 判断该退房信息是否有效
     * @return 返回是否退房信息有效
     * @see
     */
    public boolean validCheckOut(){
        //roomNum是否小于可用客房数-空房数
        //实际离开时间小于等于当前时间
        return false;
        
    }
    
    /**
     * 转成checkInOutVO类型
     * @return RoomVO型，包含了退房信息
     * @see
     */
    public RoomVO toVO(){
        return new CheckOutVO(roomType, roomNum, address, actDepartTime);
    }
}

