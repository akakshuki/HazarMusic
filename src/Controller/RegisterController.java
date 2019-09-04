package Controller;

import java.sql.Date;
import java.time.LocalDate;
import Dao.UserDao;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegisterController {
	@FXML
	private Label btnLogin;
	@FXML
	private Button btnSignUp;
	@FXML
	private CheckBox CheckAgree;
	@FXML
	private Label checkUser;
	@FXML
	private Label chekcMail;
	@FXML
	private TextField userNameTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private TextField mailTextField;
	@FXML
	private TextField fullNameTextField;
	@FXML
	private DatePicker birthDateTextField;
	@FXML
	private Label checkpassword;
	private BorderPane mainBorderPane;

	public void init(Stage mainFrm, BorderPane mainBorderPane) {
		btnSignUp.setDisable(true);
		registerForm(mainBorderPane);
		loginForm(mainBorderPane);
		CheckAgree.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				btnSignUp.setDisable(false);

			}
		});

	}

	private void loginForm(BorderPane mainBorderPane) {
		btnLogin.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/LoginFrm.fxml"));
					Parent root = (Parent) loader.load();
					Controller.LoginController controller = loader.getController();
					Stage mainFrm = new Stage();
					mainBorderPane.setCenter(root);
					controller.LoginOn(mainFrm, mainBorderPane);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}

			}
		});

	}

	private void registerForm(BorderPane mainBorderPane) {
		btnSignUp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent p) {
				if (userNameTextField.getText().isEmpty()
						|| helper.regex.validValue(userNameTextField.getText(), helper.consta.USERNAME)) {
					checkUser.setText("not ivalid");
				} else if (UserDao.checkUser(userNameTextField.getText())) {
					helper.AlbertDiaglog.AlbertDiaglog("User name are already using");
				} else if (passwordTextField.getText().isEmpty()) {
					checkpassword.setText("password not empty");
				} else if (UserDao.checkEmail(mailTextField.getText())) {
					helper.AlbertDiaglog.AlbertDiaglog("mail are already using");
				} else if (mailTextField.getText().isEmpty()
						|| helper.regex.validValue(mailTextField.getText(), helper.consta.MAIL)==true) {
					chekcMail.setText("mail not invalid");
				} else if (fullNameTextField.getText().isEmpty()) {
					helper.AlbertDiaglog.AlbertDiaglog("we dont know your name @@! ?");
				} else if (birthDateTextField.getValue() == null) {
					helper.AlbertDiaglog.AlbertDiaglog("your birth date is empty");
				} else {
					User newUser = new User();
					newUser.setU_Name(userNameTextField.getText());
					newUser.setU_Password(passwordTextField.getText());
					newUser.setU_Mail(mailTextField.getText());
					newUser.setU_FullName(fullNameTextField.getText());
					newUser.setU_BirthDate(Date.valueOf(birthDateTextField.getValue()));
					newUser.setU_DateJoin(Date.valueOf(LocalDate.now()));
					newUser.setR_ID(2);
					newUser.setU_Image(helper.ImageChooser.defaultUserImage());
					if (UserDao.AddNewUser(newUser)) {
						helper.AlbertDiaglog.InfoDiaglog("Success");
						loginForm(mainBorderPane);
					} else {
						helper.AlbertDiaglog.AlbertDiaglog("Fail, pls check your infomation again!");
					}
				}

			}
		});

	}

}
