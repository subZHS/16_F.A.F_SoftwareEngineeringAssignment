package presentation.userui.login;

import java.rmi.RemoteException;

import businesslogic.userbl.loginAndSignUp.CheckLoginInfo;
import businesslogicservice.userblservice.LoginAndSignUpService;
import businesslogicservice.userblservice.ManageUserInfoService;
import factory.UserUIFactoryService;
import factory.UserUIFactoryServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import po.UserType;
import presentation.MainApp;
import presentation.userui.JudgeFormat;

public class LoginController {
	private MainApp mainApp;
	private LoginAndSignUpService login;
	private CheckLoginInfo check;
	private ManageUserInfoService manageUserInfo;
	private UserUIFactoryService userFactory;
	private String userID;
	private String password;
	private UserType userType;
	private String address;
	private JudgeFormat judge = new JudgeFormat();
	@FXML
	private TextField userIDTextArea;

	@FXML
	private Button registerButton;

	@FXML
	private Button loginButton;

	@FXML
	private PasswordField passwordTextArea;

	@FXML
	public void initialize() {
		userFactory = new UserUIFactoryServiceImpl();
		check = new CheckLoginInfo();
		login = userFactory.createLoginAndSignUpService();
		manageUserInfo = userFactory.createManageUserInfoService();

		userIDTextArea.setText("");
		passwordTextArea.setText("");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void verifyLogin() {
		this.userID = userIDTextArea.getText();
		boolean isValid = false;
		isValid = judge.isLetterDigitOrChinese(userID);
		int userIDLength = 0;
		userIDLength = judge.getStringLength(userID);
		boolean isPasswordValid = judge.isLetterOrDigit(password);
		int passwordLength = judge.getStringLength(password);

		this.password = passwordTextArea.getText();
		this.userType = null;
		if (userID.equals("") || password.equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("wrong");
			alert.setHeaderText("信息填写不完整！");
			alert.setContentText("请重新输入！");
			alert.showAndWait();
			return;
		} else if (isValid != true) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("wrong");
			alert.setHeaderText("用户名包含非法字符（只能是数字、字母或中文）！");
			alert.setContentText("请重新输入！");
			alert.showAndWait();
			return;
		} else if (0 >= userIDLength || userIDLength > 20) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("wrong");
			alert.setHeaderText("用户名长度不合理（1~20）！");
			alert.setContentText("请重新输入！");
			alert.showAndWait();
			return;
		} else if (isPasswordValid != true) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("wrong");
			alert.setHeaderText("密码包含非法字符（只能是数字或字母）！");
			alert.setContentText("请重新输入！");
			alert.showAndWait();
			return;
		} else if (5 >= passwordLength || passwordLength > 16) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("wrong");
			alert.setHeaderText("密码长度不合理（6~16）！");
			alert.setContentText("请重新输入！");
			alert.showAndWait();
			return;
		} else {
			try {
				userType = check.checkUser(userID, password);
			} catch (RemoteException e1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("NetWork Warning");
				alert.setHeaderText("Fail to connect with the server!");
				alert.setContentText("Please check your network connection!");
				alert.showAndWait();
			}
			boolean result = false;
			try {
				result = login.login(userID, password);
			} catch (RemoteException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("NetWork Warning");
				alert.setHeaderText("Fail to connect with the server!");
				alert.setContentText("Please check your network connection!");
				alert.showAndWait();
			}

			if (result == false) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("wrong");
				alert.setHeaderText("用户名或密码错误！");
				alert.setContentText("请重新输入！");
				alert.showAndWait();
				return;
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("login info");
				alert.setHeaderText("登录成功！");
				alert.showAndWait();

				if (userType == UserType.Client)
					mainApp.showClientMainApp(userID);
				else if (userType == UserType.HotelStaff) {
					try {
						this.address = manageUserInfo.getHotelStaffInfo(userID).hotelAddress;
					} catch (RemoteException e) {
						Alert alert1 = new Alert(AlertType.WARNING);
						alert1.setTitle("NetWork Warning");
						alert1.setHeaderText("Fail to connect with the server!");
						alert1.setContentText("Please check your network connection!");
						alert1.showAndWait();
					}
					mainApp.showHotelMainApp(userID, address);
				} else if (userType == UserType.WebMarketStaff)
					mainApp.showWebsitePromotionMainApp(userID);
				else if (userType == UserType.WebManageStaff)
					mainApp.showWebsiteManageMainApp(userID);
			}
		}
	}

	public void loginButtonAction() {
		verifyLogin();
	}

	public void showRegisterPanel() {
		mainApp.showRegisterPanel();
	}

}
