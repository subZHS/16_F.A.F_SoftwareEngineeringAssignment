package po;

import java.util.Date;

public class CheckInPO extends RoomPO {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3726783419011491822L;
	private Date checkInTime;
    private Date expDepartTime;

    public CheckInPO(Enum<RoomType> roomType, int roomNum, String address, Date checkInTime, Date expDepartTime) {
        super(roomType, roomNum, address);
        this.checkInTime = checkInTime;
        this.expDepartTime = expDepartTime;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getExpDepartTime() {
        return expDepartTime;
    }

    public void setExpDepartTime(Date expDepartTime) {
        this.expDepartTime = expDepartTime;
    }

}
