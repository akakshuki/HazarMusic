package Controller;

import Dao.UserDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ResetPassworkController {
	@FXML
	private Label checkLb;
	@FXML
	private TextField mailTextField;

	@FXML
	private TextField OldPasswordTextField;
	@FXML
	private TextField NewPasswordTextField;
	@FXML
	private TextField RetypePasswordTextField;
	@FXML
	private Button applyBuutton;
	@FXML
	private Button CancelBtn;

	public void init(Stage mainFrm, BorderPane mainBorderPane) {
		applyBuutton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!UserDao.checkEmail(mailTextField.getText())) {
					checkLb.setText("mail is not correct");
				}  else {
					if (!NewPasswordTextField.getText().equals(RetypePasswordTextField.getText())) {
						checkLb.setText(" new password is not correct");
					} else {
						if (UserDao.resetPassword(mailTextField.getText(),
								OldPasswordTextField.getText(), NewPasswordTextField.getText())) {
								helper.AlbertDiaglog.InfoDiaglog("change password success");
						}else {
							checkLb.setText("password is not correct");
						}
					}
				}

			}
		});
		CancelBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
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

}
