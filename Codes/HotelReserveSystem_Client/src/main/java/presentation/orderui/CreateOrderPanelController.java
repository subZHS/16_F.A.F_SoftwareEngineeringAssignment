package presentation.orderui;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import businesslogicservice.orderblservice.CreateNewOrderService;
import businesslogicservice.orderblservice.ResultMessage;
import factory.OrderUIFactoryService;
import factory.OrderUIFactoryServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import po.RoomType;
import presentation.ClientMainApp;
import vo.OrderVO;

public class CreateOrderPanelController {

	@FXML
	private ChoiceBox<String> strategyNameChoicer;
	
	@FXML
	private ChoiceBox<String> latestOrderDoneTimeChoicer;

	@FXML
	private Label hotelNameLabel;

	@FXML
	private ChoiceBox<String> isChildrenChoicer;

	@FXML
	private DatePicker beginDatePicker;

	@FXML
	private ChoiceBox<Integer> numChoicer;

	@FXML
	private Label hotelAddressLabel;

	@FXML
	private Button createOrderButton;

	@FXML
	private DatePicker finishDatePicker;

	@FXML
	private ChoiceBox<Integer> numOfPersonChoicer;

	@FXML
	private Button returnButton;

	@FXML
	private ChoiceBox<String> roomTypeChoicer;

	private OrderVO newOrderVO;
	private ClientMainApp mainApp;
	private OrderUIFactoryService factory;
	private CreateNewOrderService newOrderCreater;

	@FXML
	private void initialize() {
		factory = new OrderUIFactoryServiceImpl();
		newOrderCreater = factory.createOrderCreateService();

		// 设置订单默认值
		beginDatePicker.setValue(LocalDate.now());
		beginDatePicker.setEditable(false);
		finishDatePicker.setEditable(false);
		beginDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(LocalDate.now()) || item.isAfter(LocalDate.now().plusDays(6))) {
							setDisable(true);
							setStyle("-fx-background-color: rgb(160,160,160);");
						}
					}
				};
			}
		});

		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(LocalDate.now().plusDays(1)) || item.isAfter(LocalDate.now().plusDays(7))
								|| item.isAfter(beginDatePicker.getValue().plusDays(7))) {
							setDisable(true);
							setStyle("-fx-background-color: rgb(160,160,160);");
						}
					}
				};
			}
		};
		finishDatePicker.setDayCellFactory(dayCellFactory);
		finishDatePicker.setValue(beginDatePicker.getValue().plusDays(1));

		roomTypeChoicer.setItems(FXCollections.observableArrayList("单人房", "标准间", "三人房", "大床房"));
		roomTypeChoicer.setValue("标准间");

		numChoicer.setItems(FXCollections.observableArrayList(1, 2, 3, 4));
		numChoicer.setValue(1);
		numChoicer.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {				
				newOrderVO.num = (int)newValue + 1;		
				try {
					strategyNameChoicer.setItems(FXCollections.observableArrayList("自动", newOrderCreater.getAvailbleMarketStrategyName(newOrderVO), newOrderCreater.getAvailbleMarketStrategyName(newOrderVO)));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				strategyNameChoicer.setValue("自动");
			}
		});

		latestOrderDoneTimeChoicer.setItems(FXCollections.observableArrayList("16:00", "19:00", "22:00"));
		latestOrderDoneTimeChoicer.setValue("16:00");

		numOfPersonChoicer.setItems(FXCollections.observableArrayList(1, 2, 3, 4));
		numOfPersonChoicer.setValue(1);

		isChildrenChoicer.setItems(FXCollections.observableArrayList("否", "是"));
		isChildrenChoicer.setValue("否");
	}

	public void createAction() {
		newOrderVO.beginDate = getDate(beginDatePicker.getValue());
		newOrderVO.finishDate = getDate(finishDatePicker.getValue());
		newOrderVO.isChildren = getTF(isChildrenChoicer.getValue());
		newOrderVO.lastedOrderDoneTime = getLastedDoneTime(newOrderVO.beginDate, latestOrderDoneTimeChoicer.getValue());
		newOrderVO.num = numChoicer.getValue();
		newOrderVO.numOfPerson = numOfPersonChoicer.getValue();
		newOrderVO.orderProducedTime = new Date();
		newOrderVO.roomType = RoomType.chinToEnum(roomTypeChoicer.getValue());

		ResultMessage resultMessage = newOrderCreater.checkNewOrder(newOrderVO);
		showResult(resultMessage);
	}

	public boolean initOrder(String userID, String hotelName, String hotelAddress) {
		hotelNameLabel.setText(hotelName);
		hotelAddressLabel.setText(hotelAddress);
		try {
			newOrderVO = newOrderCreater.initNewOrder(userID, hotelName, hotelAddress);
		} catch (RemoteException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("NetWork Warning");
			alert.setHeaderText("Fail to connect with the server!");
			alert.setContentText("Please check your network connection!");
			alert.showAndWait();
		}
		
		if(newOrderVO == null)
			return false;
		else 
			return true;
	}

	public void setMainApp(ClientMainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void returnAction() {
		mainApp.showSearchView();
	}

	public void dateFilter() {
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(beginDatePicker.getValue().plusDays(1))
								|| item.isAfter(LocalDate.now().plusDays(7))) {
							setDisable(true);
							setStyle("-fx-background-color: rgb(160,160,160);");
						}
					}
				};
			}
		};
		finishDatePicker.setDayCellFactory(dayCellFactory);
		finishDatePicker.setValue(beginDatePicker.getValue().plusDays(1));
		setStrategy();
	}

	@SuppressWarnings("deprecation")
	private Date getDate(LocalDate l) {
		return new Date(l.getYear() - 1900, l.getMonthValue() - 1, l.getDayOfMonth(), 0, 0, 0);
	}

	private boolean getTF(String s) {
		if (s.equals("是"))
			return true;
		else
			return false;
	}

	@SuppressWarnings("deprecation")
	private Date getLastedDoneTime(Date d, String s) {
		Date result = new Date(d.getYear(), d.getMonth(), d.getDate());
		String time = s.split(":")[0];
		if (time.equals("16"))
			result.setHours(16);
		else if (time.equals("19"))
			result.setHours(19);
		else
			result.setHours(22);

		return result;
	}

	/**
	 * 显示提示信息，并完成操作
	 * 
	 * @param resultMessage
	 * @see
	 */
	private void showResult(ResultMessage resultMessage) {
		if (resultMessage == ResultMessage.SUCCEED) {
			try {
				newOrderVO.totalPrice = newOrderCreater.getOriginalPrice(newOrderVO.hotelAddress,
						(RoomType) newOrderVO.roomType) * newOrderVO.num
						* getGap(newOrderVO.beginDate, newOrderVO.finishDate);
				int price = newOrderCreater.getPrice(newOrderVO);
				newOrderVO.totalPrice = newOrderCreater.getPrice(newOrderVO);
				newOrderVO.totalPrice = price;

				Alert conf = new Alert(AlertType.CONFIRMATION);
				conf.setTitle("生成订单");
				conf.setContentText("系统已经自动为您选择最优的优惠策略\n最优订单价格为 " + price + "\n" + "确认生成订单？");

				Optional<javafx.scene.control.ButtonType> result = conf.showAndWait();
				if (result.get() == javafx.scene.control.ButtonType.OK) {
					// 判断订单生成是否成功
					if (newOrderCreater.addNewOrder(newOrderVO)) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("操作成功");
						alert.setContentText("订单已经成功生成！");

						Optional<javafx.scene.control.ButtonType> re = alert.showAndWait();

						if (re.get() == javafx.scene.control.ButtonType.OK) {
							mainApp.showUserOrderPanel(ClientMainApp.userID);
						}
					} else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("操作失败");
						alert.setContentText("请检查您的网络连接！");

						alert.showAndWait();
					}
				}
			} catch (RemoteException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("NetWork Warning");
				alert.setHeaderText("Fail to connect with the server!");
				alert.setContentText("Please check your network connection!");
				alert.showAndWait();
			}
		} else if (resultMessage == ResultMessage.NUM_CANNOT_SATISFIED) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("生成订单失败");
			alert.setContentText("房间数量不足！");
			alert.showAndWait();
		} else if (resultMessage == ResultMessage.TIME_CANNOT_SATISFIED) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("生成订单失败");
			alert.setContentText("所选时间过长，无法满足！");
			alert.showAndWait();
		} else if (resultMessage == ResultMessage.TYPE_CANNOT_SATISFIED) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("生成订单失败");
			alert.setContentText("无所选房型剩余，请更换房型！");
			alert.showAndWait();
		}
	}

	private int getGap(Date begin, Date finish) {
		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(begin);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

		aCalendar.setTime(finish);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day2 - day1;
	}
	
	public void setStrategy() {
		newOrderVO.beginDate = getDate(beginDatePicker.getValue());
		newOrderVO.finishDate = getDate(finishDatePicker.getValue());
		newOrderVO.isChildren = getTF(isChildrenChoicer.getValue());
		newOrderVO.lastedOrderDoneTime = getLastedDoneTime(newOrderVO.beginDate, latestOrderDoneTimeChoicer.getValue());
		newOrderVO.num = numChoicer.getValue();
		newOrderVO.numOfPerson = numOfPersonChoicer.getValue();
		newOrderVO.orderProducedTime = new Date();
		newOrderVO.roomType = RoomType.chinToEnum(roomTypeChoicer.getValue());
		
		ObservableList<String> stretegyList = FXCollections.observableArrayList("自动择优");
		try {
			if(newOrderCreater.getAvailbleMarketStrategyName(newOrderVO) != null)
				stretegyList.add(newOrderCreater.getAvailbleMarketStrategyName(newOrderVO));
			if(newOrderCreater.getAvailblePromotionName(newOrderVO) != null)
				stretegyList.add(newOrderCreater.getAvailblePromotionName(newOrderVO));
			strategyNameChoicer.setItems(stretegyList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		strategyNameChoicer.setValue("自动择优");
	}
}
