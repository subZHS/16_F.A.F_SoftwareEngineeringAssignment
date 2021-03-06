package vo;

import java.util.Date;

import po.StrategyType;

/**
 * 策略信息的VO类,负责在逻辑层和界面层传递数据
 * @author 双
 * @version 
 * @see
 */
public class StrategyVO {
    
    public String address;
    public Enum<StrategyType> strategyType;
    public String strategyName;
    public float discount;
    public int minRoomNum;
    public String enterpriseName;
    public String securityCode;
    public Date startTime;
    public Date endTime;
    public String tradeArea;
    public int vipRank;
    
    public StrategyVO(String address, Enum<StrategyType> strategyType, String strategyName, float discount){
        this.address=address;
        this.strategyName=strategyName;
        this.discount=discount;
        this.strategyType=strategyType;
    }
    
    public StrategyVO(String address, Enum<StrategyType> strategyType, String strategyName, float discount, int inputInt){
        this.address=address;
        this.strategyType=strategyType;
        this.strategyName=strategyName;
        this.discount=discount;
        if(strategyType.equals(StrategyType.MultiRoomPromotion)){
            this.minRoomNum=inputInt;
        }
        if(strategyType.equals(StrategyType.MemberRankMarket)){
            this.vipRank=inputInt;
        }
    }
    
    public StrategyVO(String address, Enum<StrategyType> strategyType, String strategyName, float discount, String enterpriseName, String securityCode){
        this.address=address;
        this.strategyType=strategyType;
        this.strategyName=strategyName;
        this.discount=discount;
        this.enterpriseName=enterpriseName;
        this.securityCode=securityCode;
    }
    
    public StrategyVO(String address, Enum<StrategyType> strategyType, String strategyName, float discount, Date startTime, Date endTime){
        this.address=address;
        this.strategyType=strategyType;
        this.strategyName=strategyName;
        this.discount=discount;
        this.startTime=startTime;
        this.endTime=endTime;
    }
    
    public StrategyVO(String address, Enum<StrategyType> strategyType, String strategyName, float discount, int vipRank, String tradeArea){
        this.address=address;
        this.strategyType=strategyType;
        this.strategyName=strategyName;
        this.discount=discount;
        this.vipRank=vipRank;
        this.tradeArea=tradeArea;
    }
    
}
