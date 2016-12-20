package presentation.hotelui.commenthotel;

import java.rmi.RemoteException;

import businesslogicservice.hotelblservice.CommentOnHotelService;
import factory.HotelUIFactoryService;
import factory.HotelUIFactoryServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import presentation.ClientMainApp;

public class CommentOnHotelController {
	private HotelUIFactoryService hotelFactory;
	private ClientMainApp mainApp;
	private CommentOnHotelService comment;
	private String userID;
	private String hotelAddress;
	private float mark;
	private String comments;

	@FXML
	private Label commentLabel;

	@FXML
	private Button cancelButton;

	@FXML
	private TextArea commentArea;

	@FXML
	private TextField markField;

	@FXML
	private GridPane commentField;

	@FXML
	private Button confirmButton;

	@FXML
	public void initialize() {
		hotelFactory = new HotelUIFactoryServiceImpl();
		comment = hotelFactory.createCommentOnHotelService();
		
		markField.setText("");
		commentArea.setText("");
		
	}

	public void setMainApp(ClientMainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setuserIDAndAddress(String userID, String address){
		this.userID = userID;
		this.hotelAddress = address;
	}
	
	public void commentOnHotel() {
		this.mark = Float.parseFloat(markField.getText());
		this.comments = commentArea.getText();
		if(markField.getText().equals("")|| comments.equals("")){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("wrong");
			alert.setHeaderText("信息填写不完整！");
			alert.setContentText("请重新输入！");
			alert.show();
			return;
		}
		else if(mark<0||mark>5){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("wrong");
			alert.setHeaderText("评分不在规定范围内（0~5之间）！");
			alert.setContentText("请重新输入！");
			alert.show();
			return;
		}
		boolean result = false;
		try {
			result = comment.confirmComment(userID, mark, comments, hotelAddress);
		} catch (RemoteException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("NetWork Warning");
			alert.setHeaderText("Fail to connect with the server!");
			alert.setContentText("Please check your network connection!");
			alert.showAndWait();
		}
		if (result == true) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("comment info");
			alert.setHeaderText("评价成功！");
			alert.show();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("wrong");
			alert.setHeaderText("评价失败！");
			alert.setContentText("请重试！");
			alert.show();
		}
	}

	@FXML
	void cancelButtonAction(ActionEvent event) {
		return;
	}

	@FXML
	void confirmButtonAction(ActionEvent event) {
		commentOnHotel();
	}
}