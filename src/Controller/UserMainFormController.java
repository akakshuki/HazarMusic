package Controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import Dao.UserDao;
import Entities.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserMainFormController {
	@FXML
	private ImageView avatarView;
	@FXML
	private Label NameUserlb;
	@FXML
	private ImageView checkOnlineButon;

	public void loadForm(Stage mainFrm, User typeUser) {
		LoadUserForm(mainFrm, typeUser);
	}

	/**
	 * @param mainFrm
	 * @param typeUser
	 */
	private void LoadUserForm(Stage mainFrm, User typeUser) {
		LoadInfo(typeUser);
		mainFrm.setOnCloseRequest(d -> {
			UserDao.setUserOffline(typeUser.getU_ID());
		});
	}

	private void LoadInfo(User typeUser) {
		User UserInfo = UserDao.loadUserOnline(typeUser);
		if (UserInfo.getU_Image() != null) {
			helper.ImageChooser.DisplayyyyyImage(UserInfo.getU_Image(), avatarView);
		}
		NameUserlb.setText(UserInfo.getU_FullName());
		if (UserInfo.isU_CheckOnline()) {
			helper.ImageChooser.SaveImage("./src/icon/online-icon-png-6.png", checkOnlineButon);
		} else {
			helper.ImageChooser.SaveImage("./src/icon/offline-icon-png-6.png", checkOnlineButon);
		}
		checkOnlineButon.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {

				if (UserInfo.isU_CheckOnline() == true) {
					UserDao.setUserOffline(UserInfo.getU_ID());
					helper.ImageChooser.SaveImage("./src/icon/offline-icon-png-6.png", checkOnlineButon);
					LoadInfo(typeUser);
				} else {

					try {
						User UserOnline = new User();
						UserOnline.setU_ID(UserInfo.getU_ID());
						UserOnline.setU_IP(Inet4Address.getLocalHost().getHostAddress());
						UserOnline.setU_CheckOnline(true);
						UserDao.setOnlineForUser(UserOnline);

					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					helper.ImageChooser.SaveImage("./src/icon/online-icon-png-6.png", checkOnlineButon);
				}
				LoadInfo(typeUser);
			}
		});
		avatarView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {
				LoadInforFormUser();

			}

		});

	}

	private void LoadInforFormUser() {
		try {
			Stage userInfor = new Stage();
			userInfor.resizableProperty().set(false);
			userInfor.initStyle(StageStyle.UTILITY);
			userInfor.initModality(Modality.APPLICATION_MODAL);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/inforUserForm.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene newScence = new Scene(root, 977,628);
			userInfor.setScene(newScence);
			userInfor.showAndWait();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
