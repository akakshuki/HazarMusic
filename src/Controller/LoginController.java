package Controller;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;
import com.sun.glass.events.MouseEvent;
import Dao.userDao;
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
					User typeUser = userDao.Login(LoginUser);
					
					switch (typeUser.getR_ID()) {
					case 1:
						helper.AlbertDiaglog.AlbertDiaglog("we will be coming soon");
						break;
					case 2:
						try {
							setOnline();
							Stage MainFrm = new Stage();
							FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/res/UserMainForm.fxml"));
							BorderPane rootMain = (BorderPane) loaderMain.load();
							Scene scene = new Scene(rootMain, 1133,735);
							Controller.UserMainFormController UserMainController = loaderMain.getController();
							UserMainController.loadForm(MainFrm,typeUser);
							MainFrm.setScene(scene);
							scene.getStylesheets().add(getClass().getResource("/css/UserMainForm.css").toExternalForm());
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

			private void setOnline() {
				User UserOnline = new User();
				UserOnline.setU_Name(userNameTextField.getText());
				UserOnline.setU_Password(passwordTextfield.getText());
				try {
					InetAddress address = InetAddress.getLocalHost();
					System.out.println(address);
					System.out.println(Inet4Address.getLocalHost().getHostAddress());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	}

}