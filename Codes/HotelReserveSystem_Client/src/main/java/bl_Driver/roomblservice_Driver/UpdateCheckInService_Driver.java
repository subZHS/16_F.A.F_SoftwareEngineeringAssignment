package bl_Driver.roomblservice_Driver;

import java.util.Date;

import businesslogic.strategybl.exception.WrongInputException;

import java.rmi.RemoteException;
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
public class UpdateCheckInService_Driver {
    
    @SuppressWarnings("deprecation")
    public void drive(UpdateCheckInService updateCheckInService) throws RemoteException{
        ArrayList<RoomVO> checkInInfoList=updateCheckInService.getCheckInList("江苏省南京市栖霞区仙林大道163号");
        if(checkInInfoList.isEmpty())
            System.out.println("This hotel doesn't have checkIn!\n");
        else
            System.out.println("There are " + checkInInfoList.size() + " checkIns in this hotel!\n");
        
        Date checkInTime= new Date(2016, 11, 11, 12, 0);
        ArrayList<RoomVO> checkInVOList1= updateCheckInService.searchCheckInInfo("江苏省南京市栖霞区仙林大道163号", checkInTime, checkInTime);
        CheckInVO checkInVO1=(CheckInVO) checkInVOList1.get(0);
        System.out.println("the checkInInfo includes "+checkInVO1.roomNum + " 间"+ checkInVO1.roomType);
        System.out.println("checkin time is "+checkInVO1.checkInTime);
        System.out.println("expected time is "+checkInVO1.expDepartTime+"/n");
        
        ArrayList<RoomVO> checkInVOList2=updateCheckInService.searchCheckInInfo("江苏省南京市栖霞区仙林大道163号", RoomType.SINGLE_ROOM);
        CheckInVO checkInVO2=(CheckInVO) checkInVOList2.get(0);
        System.out.println("the checkInInfo includes "+checkInVO2.roomNum + " 间"+ checkInVO2.roomType);
        System.out.println("checkin time is "+checkInVO2.checkInTime);
        System.out.println("expected time is "+checkInVO2.expDepartTime+"/n");
        
        Date expDepartTime=new Date(2016, 11, 11, 12, 0);
        CheckInVO checkIn=new CheckInVO(RoomType.SINGLE_ROOM, 0000000000000003, "江苏省南京市栖霞区仙林大道163号", checkInTime, expDepartTime);
        boolean addCheckIn = false;
        try {
            addCheckIn = updateCheckInService.addCheckIn("江苏省南京市栖霞区仙林大道163号", checkIn,true);
        } catch (RemoteException e1) {
            System.out.println(e1.getMessage());
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
        if(addCheckIn)
            System.out.println("add checkIn Succeed!\n");
        else
            System.out.println("add checkIn Failed!\n");
       
        boolean validCheckIn = false;
        try {
            validCheckIn = updateCheckInService.validCheckIn("江苏省南京市栖霞区仙林大道163号", checkIn);
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        if(validCheckIn)
            System.out.println("This checkIn valid!\n");
        else
            System.out.println("This checkIn doesn't valid!\n");
    }
}
