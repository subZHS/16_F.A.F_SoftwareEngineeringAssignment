package businesslogic.userbl;

import businesslogic.strategybl.StrategyInfoService;

public class EnterpriseInfoImpl implements EnterpriseInfo{
   
    private StrategyInfoService strategyInfoService;
    
    @Override
    public boolean verifyEnterpriseMember(String enterpriseName, String securityCode) {
        return strategyInfoService.verifyEnterpriseMember(enterpriseName, securityCode);
    }

}
