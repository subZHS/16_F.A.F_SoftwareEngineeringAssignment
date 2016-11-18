package hoteldata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import data.hoteldata.HotelDAOImpl;
import po.BriefHotelInfoPO;
import po.HotelPO;
import po.RoomType;

public class HotelDAOImplTest {

	private HotelDAOImpl hotelDAO;
	private String hotelName;
	private String businessDistrict;
	private String hotelAddress;
	private int starLevel;
	private float mark;
	private String briefIntroduction;
	private String facilityAndService;
	private HashMap<RoomType, Integer> roomTypeAndPrice;
	private HashMap<RoomType, Integer> roomTypeAndNums;
	private HashMap<String, String> comments;
	private HotelPO po;
	private HotelPO updatePO;
	
	@Before
	public void setUp() throws Exception {
		hotelDAO = new HotelDAOImpl();
		this.hotelName = "Jingling Hotel";
		this.businessDistrict = "新街口";
		this.hotelAddress = "江苏省南京市栖霞区仙林大道163号";
		this.starLevel = 5;
		this.mark = 5.0f;
		this.briefIntroduction = "南京最好的酒店";
		this.facilityAndService = "wifi;washer;park;air-condition;elevator";
		HashMap<RoomType, Integer> roomTypeAndPrice = new HashMap<>();
		roomTypeAndPrice.put(RoomType.SINGLE_ROOM, 100);
		roomTypeAndPrice.put(RoomType.STANDARD_ROOM, 200);
		roomTypeAndPrice.put(RoomType.TRIBLE_ROOM, 300);
		roomTypeAndPrice.put(RoomType.KING_SIZE_ROOM, 400);
		this.roomTypeAndPrice = roomTypeAndPrice;
		HashMap<RoomType, Integer> roomTypeAndNums = new HashMap<>();
		roomTypeAndNums.put(RoomType.SINGLE_ROOM, 50);
		roomTypeAndNums.put(RoomType.STANDARD_ROOM, 50);
		roomTypeAndNums.put(RoomType.TRIBLE_ROOM, 50);
		roomTypeAndNums.put(RoomType.KING_SIZE_ROOM, 50);
		this.roomTypeAndNums = roomTypeAndNums;
		HashMap<String, String> comments = new HashMap<>();
		comments.put("原", "环境一流，服务贴心");
		this.comments = comments;
		po = new HotelPO("格林豪泰", "栖霞区", "江苏省南京市栖霞区仙林大道166号", 4, 4.5f, "中规中矩", facilityAndService, roomTypeAndPrice, roomTypeAndNums, comments);
		comments.put("Accident", "不愧是南京市最好的酒店");
		comments.put("Superman", "舒服的我都不想飞走了");
		updatePO = new HotelPO(hotelName, businessDistrict, hotelAddress, starLevel, 5.0f, briefIntroduction, facilityAndService, roomTypeAndPrice, roomTypeAndNums, comments);
	}

	@Test
	public void testGetHotelBriefInfo() {
		try {
			BriefHotelInfoPO briefHotelInfoPO = hotelDAO.getHotelBriefInfo(this.hotelAddress);
			assertEquals("HotelDAOImpl.getHotelBriefInfo(String addtrss) has an error in hotelName!", hotelName, briefHotelInfoPO.getHotelName());
			assertEquals("HotelDAOImpl.getHotelBriefInfo(String addtrss) has an error in businessDistrict!", businessDistrict, briefHotelInfoPO.getBusinessDistrict());
			assertEquals("HotelDAOImpl.getHotelBriefInfo(String addtrss) has an error in hotelAddress!", hotelAddress, briefHotelInfoPO.getHotelAddress());
			assertEquals("HotelDAOImpl.getHotelBriefInfo(String addtrss) has an error in starLevel!", starLevel, briefHotelInfoPO.getStarLevel());
			assertEquals("HotelDAOImpl.getHotelBriefInfo(String addtrss) has an error in mark!", mark, briefHotelInfoPO.getMark(), 0);	
		} catch (RemoteException e) {
			e.printStackTrace();
			fail("RemoteException has happened!");
		}
	}
	
	@Test
	public void testGetHotelDetails() {
		try {
			HotelPO hotelDetails = hotelDAO.getHotelDetails(this.hotelAddress);
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in hotelName!", hotelName, hotelDetails.getHotelName());
		 	assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in businessDistrict!", businessDistrict, hotelDetails.getBusinessDistrict());
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in hotelAddress!", hotelAddress, hotelDetails.getHotelAddress());
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in starLevel!", starLevel, hotelDetails.getStarLevel());
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in mark!", mark, hotelDetails.getMark(), 0);
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in briefIntroduction!", briefIntroduction, hotelDetails.getBriefIntroduction());
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in facilityAndService!", facilityAndService, hotelDetails.getFacilityAndService());
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in roomTypeAndPrice!", roomTypeAndPrice, hotelDetails.getRoomTypeAndPrice());
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in roomTypeAndNums!", roomTypeAndNums, hotelDetails.getRoomTypeAndNums());
			assertEquals("HotelInfoServiceImpl.getHotelBriefInfo(String addtrss) has an error in comments!", comments, hotelDetails.getComments());
		} catch(RemoteException e) {
			e.printStackTrace();
			fail("RemoteException has happened!");
		}
	}
	
	@Test
	public void testInsert() {
		try {
			hotelDAO.insert(po);
		} catch(RemoteException e) {
			e.printStackTrace();
			fail("RemoteException has happened!");
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			hotelDAO.update(updatePO);
		} catch(RemoteException e) {
			e.printStackTrace();
			fail("RemoteException has happened!");
		}
	}

}
