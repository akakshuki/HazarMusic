package Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import Dao.MusicFileDao;
import Dao.RelationShipDao;
import Entities.MusicFile;
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

public class FriendInforController2 {
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
	private TableView<MusicFile> ListView;
	@FXML
	private TableColumn<MusicFile, String> nameSongCol;
	@FXML
	private TableColumn<MusicFile, String> SingerCol;
	@FXML
	private TableColumn<MusicFile, Date> dateAddColl;
	@FXML
	private TableColumn<MusicFile, Void> requestCol;
	@FXML
	private Button unfriendButon;
	@FXML
	private Button addFriend;
	public void loadInforFriedsForm2(Stage inforFor, User infor, User typeUser) {
		if(infor.getU_ID() == typeUser.getU_ID()) {
			unfriendButon.setDisable(true);
			addFriend.setDisable(true);
		}
		if(RelationShipDao.CheckRelationShipuser(typeUser,infor)) {
			unfriendButon.setDisable(false);
			addFriend.setDisable(true);
		}else{
			unfriendButon.setDisable(true);
			addFriend.setDisable(false);
		}
		loadInforFiends(infor);
		loadMusicList(infor);
		addFriend.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				 
				String messenger = helper.AlbertDiaglog.TextDialog();
				if(RelationShipDao.getNewRelationShipUser(typeUser,infor,messenger,LocalDate.now())) {
					helper.AlbertDiaglog.InfoDiaglog("add friend success");
					
					
				}else {
					helper.AlbertDiaglog.AlbertDiaglog("add friend fail, try again");
				}
				
			}
		});
		unfriendButon.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (RelationShipDao.UnfriendFromUser(infor.getU_ID(), typeUser.getU_ID())) {
					helper.AlbertDiaglog.InfoDiaglog("delete success ");
				} else {
					helper.AlbertDiaglog.InfoDiaglog("delete FAiL ");
				}
			}
		});
	}

	private void loadMusicList(User infor) {
		ArrayList<MusicFile> MusicInfor = MusicFileDao.getMyUpLoadListByUser(infor.getU_ID());
		ObservableList<MusicFile> list = FXCollections.observableArrayList(MusicInfor);
		nameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		SingerCol.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		dateAddColl.setCellValueFactory(new PropertyValueFactory<>("M_Date"));
		
		ListView.setItems(list);
	}

	private void loadInforFiends(User infor) {
		User inforfr = RelationShipDao.getFriendedInfoByIdUser(infor.getU_ID());
		fullNameLb.setText(inforfr.getU_FullName());
		userNameLb.setText(inforfr.getU_Name());brthDateLb.setText(inforfr.getU_BirthDate().toString());
		dateJoinLb.setText(inforfr.getU_DateJoin().toString());
		countryLb.setText(inforfr.getU_Country());
		AdressLb.setText(inforfr.getU_Adress());
		helper.ImageChooser.DisplayyyyyImage(inforfr.getU_Image(), avatarUser);
		mailLb.setText(inforfr.getU_Mail());
		descriptionLb.setText(inforfr.getU_Bio());
	}

}
