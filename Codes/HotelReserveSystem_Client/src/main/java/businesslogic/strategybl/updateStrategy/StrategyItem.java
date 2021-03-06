package businesslogic.strategybl.updateStrategy;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import businesslogic.hotelbl.HotelInfoService;
import businesslogic.strategybl.exception.UnableAddStrategyException;
import businesslogic.strategybl.exception.WrongInputException;
import dataservice.strategyDAO.StrategyDAO;
import factory.FactoryService;
import factory.FactoryServiceImpl;
import po.BusinessDistrictPO;
import po.RoomType;
import po.StrategyPO;
import po.StrategyType;
import vo.HotelVO;
import vo.StrategyVO;

/**
 * 策略的Item类
 * @author 双
 * @version
 * @see
 */
public class StrategyItem {

    private String address;
    private Enum<StrategyType> strategyType;
    private String strategyName;
    private float discount;
    private int minRoomNum;
    private String enterpriseName;
    private String securityCode;
    private Date startTime;
    private Date endTime;
    private String tradeArea;
    private int vipRank;

    private int maxVipRank = 4, minVipRank = 0;

    private StrategyDAO strategyDAO;
    //hotelbl给同层调用的接口
    private HotelInfoService hotelInfoService;
    
    private FactoryService factoryService;

    public StrategyItem() {
        //用工厂初始化同层接口和DAO
         factoryService=new FactoryServiceImpl();
         strategyDAO=factoryService.getStrategyDAO();
         hotelInfoService=factoryService.createHotelInfoService();
    }

    /**
     * 构造函数
     * @param strategyPO  PO类，包含策略信息
     */
    public StrategyItem(StrategyPO strategyPO) {
        this();
        this.address = strategyPO.getAddress();
        this.strategyName = strategyPO.getStrategyName();
        this.discount = strategyPO.getDiscount();
        this.strategyType = strategyPO.getStrategyType();
        if (strategyType.equals(StrategyType.MultiRoomPromotion)) {
            this.minRoomNum = strategyPO.getMinRoomNum();
        } else if (strategyType.equals(StrategyType.CooperationEnterprisePromotion)) {
            this.enterpriseName = strategyPO.getEnterpriseName();
            this.securityCode = strategyPO.getSecurityCode();
        } else if (strategyType.equals(StrategyType.SpecificTimePromotion)
                || strategyType.equals(StrategyType.SpecificTimeMarket)) {
            this.startTime = strategyPO.getStartTime();
            this.endTime = strategyPO.getEndTime();
        } else if (strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            this.vipRank = strategyPO.getVipRank();
            this.tradeArea = strategyPO.getTradeArea();
        } else if (strategyType.equals(StrategyType.MemberRankMarket)) {
            this.vipRank = strategyPO.getVipRank();
        }
    }

    /**
     * 构造函数
     * @param strategyVO VO类，包含策略信息
     */
    public StrategyItem(StrategyVO strategyVO) {
        this();
        this.address = strategyVO.address;
        this.strategyName = strategyVO.strategyName;
        this.discount = strategyVO.discount;
        this.strategyType = strategyVO.strategyType;
        if (strategyType.equals(StrategyType.MultiRoomPromotion)) {
            this.minRoomNum = strategyVO.minRoomNum;
        } else if (strategyType.equals(StrategyType.CooperationEnterprisePromotion)) {
            this.enterpriseName = strategyVO.enterpriseName;
            this.securityCode = strategyVO.securityCode;
        } else if (strategyType.equals(StrategyType.SpecificTimePromotion)
                || strategyType.equals(StrategyType.SpecificTimeMarket)) {
            this.startTime = strategyVO.startTime;
            this.endTime = strategyVO.endTime;
        } else if (strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            this.vipRank = strategyVO.vipRank;
            this.tradeArea = strategyVO.tradeArea;
        } else if (strategyType.equals(StrategyType.MemberRankMarket)) {
            this.vipRank = strategyVO.vipRank;
        }
    }

    /**
     * 增加一个策略
     * @param address  string型，酒店地址
     * @return 返回是否增加成功
     * @throws UnableAddStrategyException 
     * @see
     */
    public boolean add(String address) throws UnableAddStrategyException {
        //防御式
        //如果地址不等于Web,则不能修改网站营销策略
        if (!address.equals("Web")) {
            if (strategyType == StrategyType.MemberRankMarket
                    || strategyType == StrategyType.SpecificTimeMarket
                    || strategyType == StrategyType.VipTradeAreaMarket) {
                throw new UnableAddStrategyException("hotel staff cannot make website market strategy");
            }
        }
        //如果地址等于Web,则不能修改酒店促销策略
        if (address.equals("Web")) {
            if (strategyType == StrategyType.BirthdayPromotion
                    || strategyType == StrategyType.CooperationEnterprisePromotion
                    ||strategyType == StrategyType.MultiRoomPromotion
                    || strategyType == StrategyType.SpecificTimePromotion) {
                throw new UnableAddStrategyException("website staff cannot make hotel's strategy");
            }
        }
        StrategyPO strategyPO;
        if (strategyType.equals(StrategyType.BirthdayPromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount);
        } else if (strategyType.equals(StrategyType.MultiRoomPromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, minRoomNum);
        } else if (strategyType.equals(StrategyType.CooperationEnterprisePromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, enterpriseName, securityCode);
        } else if (strategyType.equals(StrategyType.SpecificTimePromotion)
                || strategyType.equals(StrategyType.SpecificTimeMarket)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, startTime, endTime);
        } else if (strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, vipRank, tradeArea);
        } else {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, vipRank);
        }
        boolean added=false;
        try {
            added=strategyDAO.insertStrategy(strategyPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return added;
    }

    /**
     * 修改一个策略
     * @param address  string型，酒店地址
     * @return 返回是否修改成功
     * @throws RemoteException 
     * @see
     */
    public boolean modify(String address) throws RemoteException {
        StrategyPO strategyPO;
        if (strategyType.equals(StrategyType.BirthdayPromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount);
        } else if (strategyType.equals(StrategyType.MultiRoomPromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, minRoomNum);
        } else if (strategyType.equals(StrategyType.CooperationEnterprisePromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, enterpriseName, securityCode);
        } else if (strategyType.equals(StrategyType.SpecificTimePromotion)
                || strategyType.equals(StrategyType.SpecificTimeMarket)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, startTime, endTime);
        } else if (strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, vipRank, address);
        } else {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, vipRank);
        }
        boolean updated;
        updated=strategyDAO.updateStrategy(strategyPO);
        return updated;
    }

    /**
     * 删除一个策略 
     * @param address  string型，酒店地址
     * @return 返回是否删除成功
     * @throws RemoteException 
     * @see
     */
    public boolean delete(String address) throws RemoteException {
        StrategyPO strategyPO;
        if (strategyType.equals(StrategyType.BirthdayPromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount);
        } else if (strategyType.equals(StrategyType.MultiRoomPromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, minRoomNum);
        } else if (strategyType.equals(StrategyType.CooperationEnterprisePromotion)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, address, address);
        } else if (strategyType.equals(StrategyType.SpecificTimePromotion)
                || strategyType.equals(StrategyType.SpecificTimeMarket)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, startTime, endTime);
        } else if (strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, vipRank, address);
        } else {
            strategyPO = new StrategyPO(address, strategyType, strategyName, discount, vipRank);
        }
        boolean deleted;
        deleted=strategyDAO.deleteStrategy(strategyPO);
        return deleted;
    }

    /**
     * 判断该策略信息是否有效
     * @return 返回该策略信息是否有效
     * @throws RemoteException 
     * @see
     */
    public boolean valid() throws WrongInputException, RemoteException {
        // 格式验证
        //验证折扣类型是否与地址相对应
        if (!address.equals("Web")) {
            if (strategyType == StrategyType.MemberRankMarket
                    || strategyType == StrategyType.SpecificTimeMarket
                    || strategyType == StrategyType.VipTradeAreaMarket) {
                throw new WrongInputException("hotel staff cannot make website market strategy");
            }
        }
        if (address.equals("Web")) {
            if (strategyType == StrategyType.BirthdayPromotion
                    || strategyType == StrategyType.CooperationEnterprisePromotion
                    ||strategyType == StrategyType.MultiRoomPromotion
                    || strategyType == StrategyType.SpecificTimePromotion) {
                throw new WrongInputException("website staff cannot make hotel's strategy");
            }
        }
        // 验证地址是否正确,地址长度小于50
        if (address.length() > 50 || address.length() < 1) {
            throw new WrongInputException("the address can't be longer than 50 characters");
        }
        for (char c : address.toCharArray())
            if (c >= '0' && c <= '9') {// 判断是否是数字
                continue;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {// 判断是否是字母
                continue;
            } else if (c == '_') {// 判断是否是下划线
                continue;
            } else if (c >= 0x4e00 && c <= 0x9fbb) {// 判断是否是中文
                continue;
            } else {
                throw new WrongInputException(
                        "the address only includes number,letter, Chinese characters and underline");
            }
        // 验证折扣名称是否含非法字符
        // 最短长度为：1个字符,最长长度为：20个字符
        if (strategyName.length() < 1 || strategyName.length() > 20) {
            throw new WrongInputException("the length of strategyName can't be longer than 20 or smaller than 1");
        }
        if (!isRightName(strategyName)) {
            throw new WrongInputException(
                    "the strategyName only includes number,letter, Chinese characters and underline");
        }
        // 验证折扣百分比是否0<x<100
        if (!(discount > 0 && discount < 100)) {
            throw new WrongInputException("the discount must satisfy the formula 0<discount<100");
        }
        //若是房间数折扣，则房间数为正整数
        if(StrategyType.MultiRoomPromotion.equals(strategyType)){
            if(minRoomNum<=0){
                throw new WrongInputException("the minimum room number should be larger than 0");
            }
        }
        // 若是企业折扣，验证企业名称是否合理，验证码是否是8位
        if (strategyType.equals(StrategyType.CooperationEnterprisePromotion)) {
            // 最短长度为：1个字符,最长长度为：20个字符
            if (enterpriseName.length() < 1 || enterpriseName.length() > 20) {
                throw new WrongInputException("the length of enterpriseName can't be longer than 20 or smaller than 1");
            }
            if (!isRightName(enterpriseName))
                throw new WrongInputException(
                        "the enterpriseName only includes numbers,letters, Chinese characters and underlines");
            if (!isRightSecurityCode(securityCode))
                return false;
        }
        // 如果是商圈折扣，验证商圈名称正确
        if (strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            // 最短长度为：1个字符,最长长度为：20个字符
            if (tradeArea.length() < 1 || tradeArea.length() > 20) {
                throw new WrongInputException("the length of tradeArea can't be longer than 20 or smaller than 1");
            }
            if (!isRightName(tradeArea))
                throw new WrongInputException(
                        "the tradeAreaName only includes number,letter, Chinese characters and underline");
        }
        // 如果是特定商圈专属折扣和会员等级折扣，验证会员等级是否minVIP<vip<maxVIP
        if (strategyType.equals(StrategyType.VipTradeAreaMarket)
                || strategyType.equals(StrategyType.MemberRankMarket)) {
            if (vipRank < minVipRank || vipRank > maxVipRank)
                throw new WrongInputException(
                        "the vipRank must larger than " + minVipRank + " and less than " + maxVipRank);
        }
        // 如果是特殊期间折扣，验证时间是否合法，验证起始时间是否小于结束时间
        if (strategyType.equals(StrategyType.SpecificTimeMarket)
                || strategyType.equals(StrategyType.SpecificTimePromotion)) {
            if (startTime.compareTo(endTime) > 0)
                throw new WrongInputException("the startTime must before endTime");
        }

        // 非格式验证

        // 如果是房间数折扣，最少房间数是否小于可用客房数量大于0
        if (strategyType.equals(StrategyType.MultiRoomPromotion)) {
            int totalRoomNum = 0;
            HotelVO hotelVO = hotelInfoService.getHotelDetails(address);
            HashMap<RoomType, Integer> roomTypeAndNums = hotelVO.roomTypeAndNums;
            for (RoomType roomType : RoomType.class.getEnumConstants()) {
                if(roomTypeAndNums.get(roomType)!=null)
                    totalRoomNum += roomTypeAndNums.get(roomType);
            }
            if (totalRoomNum < minRoomNum) {
                throw new WrongInputException("the minRoomNum is larger than the number of all rooms");
            }
        }
        return true;

    }

    /**
     * 验证特定商圈会员专属折扣的商圈名称在某城市是否存在
     * 
     * @param city
     *            String型，城市名称
     * @param strategyVO
     *            策略信息
     * @return 返回商圈是否存在
     * @throws WrongInputException
     * @throws RemoteException 
     * @see
     */
    public boolean verifyTradeArea(String city) throws WrongInputException, RemoteException {
        // 调用hotelbl方法得到该城市的商圈列表，然后判断该商圈是否存在
        if (!strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            throw new WrongInputException("the type of strategy is not vip discount in special trade area");
        }
        boolean tradeAreaExist = false;
        ArrayList<BusinessDistrictPO> tradeAreaList = hotelInfoService.getBusinessDistrictList(city);
        if (tradeAreaList == null) {
            throw new WrongInputException("the city doesn't exist");
        }
        if (tradeAreaList.size() == 0) {
            throw new WrongInputException("there is no trade area in this city");
        }
        for (BusinessDistrictPO po : tradeAreaList) {
            if (this.tradeArea.equals(po.getBusinessDistrictName())) {
                tradeAreaExist = true;
                break;
            }
        }
        if (!tradeAreaExist) {
            throw new WrongInputException("the tradeArea doesn't exist");
        }
        return tradeAreaExist;
    }

    private boolean isRightSecurityCode(String securityCode) throws WrongInputException {
        if (securityCode.length() != 8)
            throw new WrongInputException("the length of securityCode must be 8");
        for (char c : securityCode.toCharArray()) {
            if (c >= '0' && c <= '9') {// 判断是否是数字
                continue;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {// 判断是否是字母
                continue;
            }
            throw new WrongInputException("the securityCode only contains numbers and letters");
        }
        return true;
    }

    /**
     * 判断名称是否正确
     * 
     * @param name
     *            传入的名称
     * @return 如果包含非法字符，返回false
     * @throws WrongInputException
     * @see
     */
    public boolean isRightName(String name) throws WrongInputException {
        for (char c : name.toCharArray())
            if (c >= '0' && c <= '9') {// 判断是否是数字
                continue;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {// 判断是否是字母
                continue;
            } else if (c == '_') {// 判断是否是下划线
                continue;
            } else if (c >= 0x4e00 && c <= 0x9fbb) {// 判断是否是中文
                continue;
            } else {
                throw new WrongInputException("the name only includes number,letter, Chinese characters and underline");
            }
        return true;
    }

    /**
     * 转成StrategyVO型的策略信息
     * @return 返回StrategyVO，包含策略信息
     * @see
     */
    public StrategyVO toVO() {
        if (strategyType.equals(StrategyType.BirthdayPromotion)) {
            return new StrategyVO(address, strategyType, strategyName, discount);
        } else if (strategyType.equals(StrategyType.MultiRoomPromotion)) {
            return new StrategyVO(address, strategyType, strategyName, discount, minRoomNum);
        } else if (strategyType.equals(StrategyType.CooperationEnterprisePromotion)) {
            return new StrategyVO(address, strategyType, strategyName, discount, enterpriseName, securityCode);
        } else if (strategyType.equals(StrategyType.SpecificTimePromotion)
                || strategyType.equals(StrategyType.SpecificTimeMarket)) {
            return new StrategyVO(address, strategyType, strategyName, discount, startTime, endTime);
        } else if (strategyType.equals(StrategyType.VipTradeAreaMarket)) {
            return new StrategyVO(address, strategyType, strategyName, discount, vipRank, tradeArea);
        } else if (strategyType.equals(StrategyType.MemberRankMarket)) {
            return new StrategyVO(address, strategyType, strategyName, discount, vipRank);
        }
        return null;
    }

}
