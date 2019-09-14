package Controller;

import java.sql.Date;

import Dao.MusicFileDao;
import Dao.UserDao;
import Entities.MusicFile;
import Entities.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController {

	@FXML
	private TableView<MusicFile> MusiccFile;
	@FXML
	private TableColumn<MusicFile, String> NameM;
	@FXML
	private TableColumn<MusicFile, String> SingleM;
	@FXML
	private TableColumn<MusicFile, String> DescriptionM;
	@FXML
	private TableColumn<MusicFile, Date> DateAddM;
	@FXML
	private TableColumn<MusicFile, String> LinkFileM;
	@FXML
	private TableColumn<MusicFile, String> ActiveM;
	@FXML
	private TableColumn<MusicFile, Boolean> StatusM;
	@FXML
	private TableView<User> AccountT;
	@FXML
	private TableColumn<User, String> UserNameAcc;
	@FXML
	private TableColumn<User, String> AddressAcc;
	@FXML
	private TableColumn<User, String> CountryAcc;
	@FXML
	private TableColumn<User, String> MailAcc;
	@FXML
	private TableColumn<User, String> PhoneAcc;
	@FXML
	private TableColumn<User, Date> DateJoinAcc;
	@FXML
	private TableColumn<User, Date> BirthDateAcc;
	@FXML
	private TableColumn<User, String> FullNameAcc;
	@FXML
	private TableColumn<User, Boolean> StatusAcc;

	public void loadForm() {
		loadUser();
		loadFile();
	}

	private void loadFile() {
		ObservableList<MusicFile> listAllMusic = MusicFileDao.ListGetAllMusic();
		NameM.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		SingleM.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		DescriptionM.setCellValueFactory(new PropertyValueFactory<>("M_Description"));
		DateAddM.setCellValueFactory(new PropertyValueFactory<>("M_Date"));
		LinkFileM.setCellValueFactory(new PropertyValueFactory<>("M_LinkFile"));
		ActiveM.setCellValueFactory(new PropertyValueFactory<>("M_Active"));
		StatusM.setCellValueFactory(new PropertyValueFactory<>("M_Status"));
		MusiccFile.setItems(listAllMusic);
	}

	private void loadUser() {
		ObservableList<User> loadUserOnline = UserDao.ListGetAllUser();
		UserNameAcc.setCellValueFactory(new PropertyValueFactory<>("U_Name"));
		AddressAcc.setCellValueFactory(new PropertyValueFactory<>("U_Adress"));
		CountryAcc.setCellValueFactory(new PropertyValueFactory<>("U_Country"));
		MailAcc.setCellValueFactory(new PropertyValueFactory<>("U_Mail"));
		PhoneAcc.setCellValueFactory(new PropertyValueFactory<>("U_Phone"));
		DateJoinAcc.setCellValueFactory(new PropertyValueFactory<>("U_DateJoin"));
		BirthDateAcc.setCellValueFactory(new PropertyValueFactory<>("U_BirthDate"));
		FullNameAcc.setCellValueFactory(new PropertyValueFactory<>("U_FullName"));
		StatusAcc.setCellValueFactory(new PropertyValueFactory<>("U_Status"));
		AccountT.setItems(loadUserOnline);
	}

}
