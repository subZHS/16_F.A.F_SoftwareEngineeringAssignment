package businesslogic.userbl;

import vo.UserVO;

/**
 * 
 * @author sparkler
 * @version 
 * @see
 */
public interface UserInfoService {

    /**
     * 获取普通客户信息
     * @param ID long型，界面传递过来的用户标识
     * @return 返回普通客户信息
     * @see
     */
    public UserVO getGegularVipInfo(long ID);
    
    /**
     * 获取企业客户信息
     * @param ID long型，界面传递过来的用户标识
     * @return 返回企业客户信息
     * @see
     */
    public UserVO getEnterpriseInfo(long ID);
}