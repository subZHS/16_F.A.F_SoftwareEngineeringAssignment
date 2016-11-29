package bl_Stub.userblservice_Stub;

import java.util.ArrayList;

import businesslogicservice.userblservice.QueryClientCreditRecordService;
import po.CreditRecordPO;

/**
 * 
 * @author 原
 * @version
 * @see
 */
public class QueryClientCreditRecordServiceImpl_Stub implements QueryClientCreditRecordService {

	public long userID;
	public String password;
    public String telNum;
    public int creditValue;
    public ArrayList<CreditRecordPO> creditRecord;
	
    public QueryClientCreditRecordServiceImpl_Stub(long userID, String password, String telNum, int creditValue,
			ArrayList<CreditRecordPO> creditRecord) {
		this.userID = userID;
		this.password = password;
		this.telNum = telNum;
		this.creditValue = creditValue;
		this.creditRecord = creditRecord;
	}
    
	@Override
	public ArrayList<CreditRecordPO> queryCreditRecord(String userID) {
		return new ArrayList<>();
	}

}
