package org.FAF.businesslogic.roombl.updateCheckOut;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businesslogic.roombl.updateCheckOut.UpdateCheckOutServiceImpl;
import businesslogic.strategybl.exception.WrongInputException;
import po.RoomType;
import rmi.LinkToServer;
import vo.CheckOutVO;
import vo.RoomVO;

public class UpdateCheckOutServiceImplTest {

    private UpdateCheckOutServiceImpl updateCheckOutServiceImpl;
    private String address;
    private Date actDepartTime;
    private CheckOutVO checkOutVO;
    private Date startTime;
    private Date endTime;
    
    private CheckOutVO checkOutVO1,checkOutVO2,checkOutVO3,checkOutVO4;
    
    private static LinkToServer linkToServer;
    
    @BeforeClass
    public static void set() {
        linkToServer = new LinkToServer();
        try {
			linkToServer.linkToServer();
		} catch (RemoteException e) {
			System.out.println("网络通信错误");
		}
    }
    
    @SuppressWarnings("deprecation")
    @Before
    public void setUp() throws Exception{
        updateCheckOutServiceImpl=new UpdateCheckOutServiceImpl();
        
        address = "江苏省南京市栖霞区仙林大道163号";
        actDepartTime = new Date();
        startTime=new Date(116,9,7,12,00,00);
        endTime=new Date(116,10,12,12,00,00);
        checkOutVO=new CheckOutVO(RoomType.SINGLE_ROOM, 0000000000000003, address, actDepartTime);
        
        checkOutVO1=new CheckOutVO(RoomType.SINGLE_ROOM, 0000000000000003, "江苏省南京市栖霞区仙林大道163号", new Date(116,10,12,12,00,00));
        checkOutVO2=new CheckOutVO(RoomType.STANDARD_ROOM, 4, "江苏省南京市栖霞区仙林大道163号", new Date(116,9,7,12,00,00));
        checkOutVO3=new CheckOutVO(RoomType.TRIBLE_ROOM, 1, "江苏省南京市栖霞区仙林大道163号", new Date(116,9,1,10,10,00));
        checkOutVO4=new CheckOutVO(RoomType.KING_SIZE_ROOM, 1, "江苏省南京市栖霞区仙林大道164号", new Date(116,10,16,11,00,00));
    }
    
    @Test
    public void testGetCheckOutList(){
        ArrayList<RoomVO> checkOutVOs;
		try {
			checkOutVOs = updateCheckOutServiceImpl.getCheckOutList("江苏省南京市栖霞区仙林大道163号");
			assertEquals(0000000000000003,checkOutVOs.size());
			for(RoomVO RoomVO:checkOutVOs){
				CheckOutVO checkOutVO=(CheckOutVO)RoomVO;
				if(checkOutVO.roomType==RoomType.SINGLE_ROOM)
					assertTrue(equalCheckOut(checkOutVO, checkOutVO1));
				if(checkOutVO.roomType==RoomType.STANDARD_ROOM)
					assertTrue(equalCheckOut(checkOutVO, checkOutVO2));
				if(checkOutVO.roomType==RoomType.TRIBLE_ROOM)
					assertTrue(equalCheckOut(checkOutVO, checkOutVO3));
			}
			checkOutVOs = updateCheckOutServiceImpl.getCheckOutList("江苏省南京市栖霞区仙林大道164号");
			assertEquals(1, checkOutVOs.size());
			assertTrue(equalCheckOut(checkOutVO4, (CheckOutVO)checkOutVOs.get(0)));
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
    }
    
    @Test
    public void testSearchCheckOutInfo1(){
        ArrayList<RoomVO> checkOutVOs;
		try {
			checkOutVOs = updateCheckOutServiceImpl.searchCheckOutInfo("江苏省南京市栖霞区仙林大道163号", startTime, endTime);
			assertEquals(2,checkOutVOs.size());
			for(RoomVO roomVO:checkOutVOs){
				CheckOutVO checkOutVO=(CheckOutVO)roomVO;
				if(checkOutVO.roomType==RoomType.SINGLE_ROOM){
					assertTrue(equalCheckOut(checkOutVO, checkOutVO1));
				}
				if(checkOutVO.roomType==RoomType.STANDARD_ROOM){
					assertTrue(equalCheckOut(checkOutVO, checkOutVO2));
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
    }
    
    @Test
    public void testSearchCheckOutInfo2(){
        ArrayList<RoomVO> checkOutVOs;
		try {
			checkOutVOs = updateCheckOutServiceImpl.searchCheckOutInfo("江苏省南京市栖霞区仙林大道163号", RoomType.TRIBLE_ROOM);
			assertEquals(1,checkOutVOs.size());
			CheckOutVO checkOutVOFromArray=(CheckOutVO) checkOutVOs.get(0);
			assertTrue(equalCheckOut(checkOutVO3, checkOutVOFromArray));
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
    }
    
//    @Test
//    public void testAddCheckOut(){
//        boolean added = false;
//        try {
//            added = updateCheckOutServiceImpl.addCheckOut(address, checkOutVO);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (WrongInputException e) {
//            e.printStackTrace();
//        }
//        assertTrue(added);
//    }

    @Test
    public void testValidCheckOut(){
        boolean valid = false;
        try {
            valid = updateCheckOutServiceImpl.validCheckOut(address, checkOutVO);
        } catch (WrongInputException e) {
           System.out.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        assertFalse(valid);
    }
    
    public boolean equalCheckOut(CheckOutVO checkOutVO1, CheckOutVO checkOutVO2) {
        if (checkOutVO1.roomType != checkOutVO2.roomType || checkOutVO1.roomNum != checkOutVO2.roomNum
                || checkOutVO1.roomPrice != checkOutVO2.roomPrice || !checkOutVO1.address.equals(checkOutVO2.address)
                || checkOutVO1.actDepartTime.compareTo(checkOutVO2.actDepartTime) != 0) {
            return false;
        }
        return true;
    }
}
