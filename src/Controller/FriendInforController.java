package Controller;

import java.sql.Date;
import java.util.ArrayList;

import Dao.MusicFileDao;
import Dao.RelationShipDao;
import Dao.UserDao;
import Entities.MusicFile;
import Entities.RelationShipUser;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FriendInforController {
	@FXML
	private Label fullNameLb;
	@FXML
	private Label userNameLb;
	@FXML
	private Label AdressLb;
	@FXML
	private Label countryLb;
	@FXML
	private Label brthDateLb;
	@FXML
	private Label dateJoinLb;
	@FXML
	private Label descriptionLb;
	@FXML
	private Label mailLb;
	@FXML
	private ImageView avatarUser;
	@FXML
	private TableView<MusicFile >ListView;
	@FXML
	private TableColumn<MusicFile, String> nameSongCol;
	@FXML
	private TableColumn<MusicFile, String> SingerCol;
	@FXML
	private TableColumn<MusicFile, Date> dateAddColl;
	@FXML
	private TableColumn<MusicFile, String> StatusCol;
	@FXML 
	private Button Acceptbtn;
	public void loadInforFriedsForm(Stage inforFor, RelationShipUser infor) {
		loadInforFiends(infor);
		loadMusicList(infor);
		acceptFuntion(infor);
	}

	private void acceptFuntion(RelationShipUser infor) {
		Acceptbtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(RelationShipDao.AcceptRelationShip(infor)) {
					helper.AlbertDiaglog.InfoDiaglog("Accept succes");
				}else {
					helper.AlbertDiaglog.InfoDiaglog("Accept fail");
				}
			}
		});
	}

	private void loadMusicList(RelationShipUser infor) {
		ArrayList<MusicFile> MusicInfor = MusicFileDao.getMyUpLoadListByUser(infor.getU_ID1());
		ObservableList<MusicFile> list = FXCollections.observableArrayList(MusicInfor);
		nameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		SingerCol.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		dateAddColl.setCellValueFactory(new PropertyValueFactory<>("M_Date"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<>("M_Status"));
		ListView.setItems(list);
	}

	private void loadInforFiends(RelationShipUser infor) {
		User friendInfor = UserDao.LoadInforUser(infor.getU_ID1());
		fullNameLb.setText(friendInfor.getU_FullName());
		userNameLb.setText(friendInfor.getU_Name());
		brthDateLb.setText(friendInfor.getU_BirthDate().toString());
		dateJoinLb.setText(friendInfor.getU_DateJoin().toString());
		countryLb.setText(friendInfor.getU_Country());
		AdressLb.setText(friendInfor.getU_Adress());
		helper.ImageChooser.DisplayyyyyImage(friendInfor.getU_Image(), avatarUser);
		mailLb.setText(friendInfor.getU_Mail());
		descriptionLb.setText(friendInfor.getU_Bio());	
	}
	

}
