package Controller;





import java.io.File;

import Dao.UserDao;
import Entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserMainFormController {
	@FXML 
	private ImageView avatarView;
	@FXML
	private Label NameUserlb;
	@FXML
	private ImageView checkOnlineButon; 
	
	public void loadForm(Stage mainFrm, User typeUser) {
		LoadUserForm(mainFrm,typeUser);
	}

	/**
	 * @param mainFrm
	 * @param typeUser
	 */
	private void LoadUserForm(Stage mainFrm, User typeUser) {
		LoadInfo(typeUser);
		
		
		
	}

	private void LoadInfo(User typeUser) {
		User UserInfo = UserDao.loadUserOnline(typeUser);
		if(UserInfo.getU_Image()!= null) {
			helper.ImageChooser.DisplayyyyyImage(UserInfo.getU_Image(), avatarView);
		}
		NameUserlb.setText(UserInfo.getU_FullName());
		if(UserInfo.isU_CheckOnline()) {
			helper.ImageChooser.SaveImage(".", checkOnlineButon);
		}
	}

	

	

}
