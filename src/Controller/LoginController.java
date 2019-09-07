package Controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import Dao.UserDao;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private PasswordField passwordTextfield;
	@FXML
	private Button btnLogin;
	@FXML
	private TextField userNameTextField;
	@FXML
	private Label CheckLogin;
	@FXML
	private Label signUpBtn;
	@FXML
	private Label resetPasswordBtn;

	public void LoginOn(Stage mainFrm, BorderPane mainBorderPane) {

		signUpBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/registerFrm.fxml"));
					Parent root = (Parent) loader.load();
					Controller.RegisterController controller = loader.getController();
					mainBorderPane.setCenter(root);
					mainFrm.setResizable(false);
					controller.init(mainFrm, mainBorderPane);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}

			}
		});
		loginForm(mainFrm);

	}

	private void loginForm(Stage mainForm) {
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent p) {
				if (userNameTextField.getText().isEmpty()) {
					helper.AlbertDiaglog.AlbertDiaglog("User name is not empty");
				} else if (passwordTextfield.getText().isEmpty()) {
					helper.AlbertDiaglog.AlbertDiaglog("password is not empty");
				} else {
					User LoginUser = new User();
					LoginUser.setU_Name(userNameTextField.getText());
					LoginUser.setU_Password(passwordTextfield.getText());
					User typeUser = UserDao.Login(LoginUser);

					switch (typeUser.getR_ID()) {
					case 1:
						try {
							setOnline(typeUser.getU_ID());
							Stage MainFrmAdmin = new Stage();
							FXMLLoader loaderMainAdmin = new FXMLLoader(getClass().getResource("/res/AdminFrm.fxml"));
							BorderPane rootMainAdmin = (BorderPane) loaderMainAdmin.load();
							Scene sceneAdmin = new Scene(rootMainAdmin, 1224, 758);
							AdminController adminController = loaderMainAdmin.getController();
							adminController.loadForm();
							MainFrmAdmin.setScene(sceneAdmin);
							sceneAdmin.getStylesheets()
									.add(getClass().getResource("/css/UserMainForm.css").toExternalForm());
							MainFrmAdmin.setResizable(false);
							MainFrmAdmin.getIcons().add(new Image("./icon/download.png"));
							MainFrmAdmin.setTitle("Hazard Music!!!!");
							MainFrmAdmin.show();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 2:
						try {
							setOnline(typeUser.getU_ID());
							Stage MainFrm = new Stage();
							FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/res/UserMainForm.fxml"));
							BorderPane rootMain = (BorderPane) loaderMain.load();
							Scene scene = new Scene(rootMain, 1224, 758);
							Controller.UserMainFormController UserMainController = loaderMain.getController();
							UserMainController.loadForm(MainFrm, typeUser);
							MainFrm.setScene(scene);
							scene.getStylesheets()
									.add(getClass().getResource("/css/UserMainForm.css").toExternalForm());
							MainFrm.setResizable(false);
							MainFrm.getIcons().add(new Image("./icon/download.png"));
							MainFrm.setTitle("Hazard Music!!!!");
							MainFrm.show();

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						mainForm.close();
						break;
					default:
						CheckLogin.setText("User or Password is NOT correct... Try again! ><");
					}
				}

			}

			private void setOnline(int i) {
				try {
					User UserOnline = new User();
					UserOnline.setU_ID(i);
					UserOnline.setU_IP(Inet4Address.getLocalHost().getHostAddress());
					UserOnline.setU_CheckOnline(true);
					UserDao.setOnlineForUser(UserOnline);

				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

			}
		});

	}
}
