package Controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import Dao.MusicFileDao;
import Dao.UserDao;
import Entities.MusicFile;
import Entities.RelationShipUser;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
	@FXML
	private TableView<MusicFile> TableWiewer;
	@FXML
	private TableColumn<MusicFile, String> nameSongCol;
	@FXML
	private TableColumn<MusicFile, String> SingerCol;
	@FXML
	private TableColumn<MusicFile, Date> dateAddCol;
	@FXML
	private TableColumn<MusicFile, Void> buttonCol;
	
	private byte[] data;

	public void InforUserControllerInit(Stage userInform, User userInfo) {
		loadInForUser(userInfo);
		UploadUserInfor(userInform, userInfo);
		LoadTableMusic(userInfo);
	}

	private void LoadTableMusic(User userInfo) {
		ArrayList<MusicFile> listMusic = MusicFileDao.getMyUpLoadListByUser(userInfo.getU_ID());
		ObservableList<MusicFile> list = FXCollections.observableArrayList(listMusic);
		nameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		SingerCol.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		dateAddCol.setCellValueFactory(new PropertyValueFactory<>("M_Date"));
		buttonCol.setCellFactory(new Callback<TableColumn<MusicFile, Void>, TableCell<MusicFile, Void>>() {

			@Override
			public TableCell<MusicFile, Void> call(TableColumn<MusicFile, Void> arg0) {
				final TableCell<MusicFile, Void> delCell = new TableCell<MusicFile, Void>() {
					private final Button deleteBtn = new Button("delete");
					{
						deleteBtn.setOnAction((ActionEvent e) -> {
							MusicFile inforMusic = getTableView().getItems().get(getIndex());	
							if(MusicFileDao.deleteMusic(inforMusic.getM_ID())) {
									helper.AlbertDiaglog.InfoDiaglog("delete success");
						           try {
						        	   if(Files.deleteIfExists(Paths.get(inforMusic.getM_LinkFile()))) {
						        		   helper.AlbertDiaglog.InfoDiaglog("deleted your file");
						        	   }
								} catch (Exception e2) {
									e2.printStackTrace();
								} 
								}else {
									helper.AlbertDiaglog.AlbertDiaglog("delete Fail try again");
								}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						//
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(deleteBtn);
						}
					}

				};

				return delCell;
			}
		});
		TableWiewer.setItems(list);
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
				if (mailTextField.getText().isEmpty()
						|| !helper.regex.validValue(mailTextField.getText(), helper.consta.MAIL)) {
					helper.AlbertDiaglog.AlbertDiaglog("mail is not avalid");
				} else if (FullNameTextField.getText().isEmpty()) {
					helper.AlbertDiaglog.AlbertDiaglog("we dont know your name @@! ?");
				} else if (birthDateField.getValue() == null) {
					helper.AlbertDiaglog.AlbertDiaglog("your birth date is empty");
				} else {
					User uploadUser = new User();
					uploadUser.setU_ID(userInfo.getU_ID());
					uploadUser.setU_FullName(FullNameTextField.getText());
					uploadUser.setU_BirthDate(Date.valueOf(birthDateField.getValue()));
					if (data == null) {
						uploadUser.setU_Image(userInfo.getU_Image());
					} else {
						uploadUser.setU_Image(data);
					}

					uploadUser.setU_Adress(AdressTextField.getText());
					uploadUser.setU_Country(CountryTextFeild.getText());
					uploadUser.setU_Mail(mailTextField.getText());
					uploadUser.setU_Phone(PhoneTextField.getText());
					uploadUser.setU_Bio(description.getText());
					if (UserDao.UpLoadInforUser(uploadUser)) {
						helper.AlbertDiaglog.InfoDiaglog("upload info success!");
						reLoaInfor(userInfo.getU_ID());

					} else {
						helper.AlbertDiaglog.InfoDiaglog("upload info FAIL!");
					}

				} //

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
