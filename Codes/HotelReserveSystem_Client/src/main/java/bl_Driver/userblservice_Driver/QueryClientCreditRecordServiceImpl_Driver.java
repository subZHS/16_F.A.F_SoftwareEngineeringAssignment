package bl_Driver.userblservice_Driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.userblservice.QueryClientCreditRecordService;
import vo.CreditRecordVO;

/**
 * 
 * @author sparkler
 * @version 
 * @see
 */
public class QueryClientCreditRecordServiceImpl_Driver {
    public void drive(QueryClientCreditRecordService queryClientCreditService) throws RemoteException{
        ArrayList<CreditRecordVO> userVO = queryClientCreditService.queryCreditRecord("原");
        System.out.println("The client's credit value and records are: "+userVO);
    }
}
