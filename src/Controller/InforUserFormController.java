package Controller;

import java.sql.Date;
import java.time.LocalDate;

import Dao.UserDao;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InforUserFormController {
	@FXML
	private TextField AdressTextField;
	@FXML
	private TextField CountryTextFeild;
	@FXML
	private TextField mailTextField;
	@FXML
	private TextField PhoneTextField;
	@FXML
	private TextField searchTextFiel;
	@FXML
	private Button chooseImageBtn;
	@FXML
	private DatePicker birthDateField;
	@FXML
	private Label labelDateJoin;
	@FXML
	private TextField FullNameTextField;
	@FXML
	private ImageView avatarView;
	@FXML
	private Button updateButton;
	@FXML
	private Button searrchBtn;
	@FXML
	private Button ChangePasswordButton;
	@FXML
	private TextArea description;
	
	private byte[] data;
	public void InforUserControllerInit(Stage userInform, User userInfo) {
		loadInForUser(userInfo);
		UploadUserInfor(userInform,userInfo);
	}

	private void UploadUserInfor(Stage userInform, User userInfo) {
		chooseImageBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent p) {
				data = helper.ImageChooser.SaveImage(userInform, avatarView);
			}
		});
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent p) {
				if (mailTextField.getText().isEmpty()||! helper.regex.validValue(mailTextField.getText(), helper.consta.MAIL)) {
					helper.AlbertDiaglog.AlbertDiaglog("mail is not avalid");
				}else if (FullNameTextField.getText().isEmpty()) {
					helper.AlbertDiaglog.AlbertDiaglog("we dont know your name @@! ?");
				} else if (birthDateField.getValue() == null) {
					helper.AlbertDiaglog.AlbertDiaglog("your birth date is empty");
				} else {
					User uploadUser = new User();
					uploadUser.setU_ID(userInfo.getU_ID());
					uploadUser.setU_FullName(FullNameTextField.getText());
					uploadUser.setU_BirthDate(Date.valueOf(birthDateField.getValue()));
					if(data == null) {
						uploadUser.setU_Image(userInfo.getU_Image());
					}else {
						uploadUser.setU_Image(data);
					}
					
					uploadUser.setU_Adress(AdressTextField.getText());
					uploadUser.setU_Country(CountryTextFeild.getText());
					uploadUser.setU_Mail(mailTextField.getText());
					uploadUser.setU_Phone(PhoneTextField.getText());
					uploadUser.setU_Bio(description.getText());
					if(UserDao.UpLoadInforUser(uploadUser)) {
						helper.AlbertDiaglog.InfoDiaglog("upload info success!");
						reLoaInfor(userInfo.getU_ID());
						
					}else {
						helper.AlbertDiaglog.InfoDiaglog("upload info FAIL!");
					}
					
				}//
				
			}

			private void reLoaInfor(int u_ID) {
				User reloadUser = UserDao.LoadInforUser(u_ID);
				loadInForUser(reloadUser);
				
			}
		});
	}

	private void loadInForUser(User userInfo) {
		helper.ImageChooser.DisplayyyyyImage(userInfo.getU_Image(), avatarView);
		FullNameTextField.setText(userInfo.getU_FullName());
		AdressTextField.setText(userInfo.getU_Adress());
		CountryTextFeild.setText(userInfo.getU_Country());
		birthDateField.setValue(LocalDate.parse(userInfo.getU_BirthDate().toString()));
		labelDateJoin.setText(userInfo.getU_DateJoin().toString());
		mailTextField.setText(userInfo.getU_Mail());
		PhoneTextField.setText(userInfo.getU_Phone());
		description.setText(userInfo.getU_Bio());
	}

}
