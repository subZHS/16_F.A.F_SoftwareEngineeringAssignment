package po;

/**
 * 
 * @author 原
 * @version 1.0
 * @see
 */
public class BriefHotelInfoPO {
	private String hotelName;
	private String businessDistrict;
	private String hotelAddress;
	private int starLevel;
	private float mark;
	
	public BriefHotelInfoPO(String hotelName, String businessDistrict, String hotelAddress, int starLevel, float mark) {
		this.hotelName = hotelName;
		this.businessDistrict = businessDistrict;
		this.hotelAddress = hotelAddress;
		this.starLevel = starLevel;
		this.mark = mark;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getBusinessDistrict() {
		return businessDistrict;
	}
	public void setBusinessDistrict(String businessDistrict) {
		this.businessDistrict = businessDistrict;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public int getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}
	public float getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}


}
