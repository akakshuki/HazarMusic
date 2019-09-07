package Controller;

import java.sql.Date;
import java.util.ArrayList;

import Dao.MusicFileDao;

import Entities.MusicFile;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	private TableView<MusicFile >ListView;
	@FXML
	private TableColumn<MusicFile, String> nameSongCol;
	@FXML
	private TableColumn<MusicFile, String> SingerCol;
	@FXML
	private TableColumn<MusicFile, Date> dateAddColl;
	@FXML
	private TableColumn<MusicFile, Void> requestCol;
	
	public void loadInforFriedsForm2(Stage inforFor, User infor) {
		loadInforFiends(infor);
		loadMusicList(infor);
	}


	private void loadMusicList(User infor) {
		ArrayList<MusicFile> MusicInfor = MusicFileDao.getMyUpLoadListByUser(infor.getU_ID());
		ObservableList<MusicFile> list = FXCollections.observableArrayList(MusicInfor);
		nameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		SingerCol.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		dateAddColl.setCellValueFactory(new PropertyValueFactory<>("M_Date"));
		requestCol.setCellFactory(new Callback<TableColumn<MusicFile,Void>, TableCell<MusicFile,Void>>() {
			
			@Override
			public TableCell<MusicFile, Void> call(TableColumn<MusicFile, Void> p) {
				final TableCell<MusicFile, Void> Cell = new TableCell<MusicFile, Void>() {
					private final Button openFile = new Button("open");
					{
						openFile.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								try {
									
								} catch (Exception e) {

									e.printStackTrace();
								}

							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(openFile);

						}
					}
				};
				return Cell;
			}
		});
		ListView.setItems(list);
	}

	private void loadInforFiends(User infor) {
		fullNameLb.setText(infor.getU_FullName());
		userNameLb.setText(infor.getU_Name());
		brthDateLb.setText(infor.getU_BirthDate().toString());
		dateJoinLb.setText(infor.getU_DateJoin().toString());
		countryLb.setText(infor.getU_Country());
		AdressLb.setText(infor.getU_Adress());
		helper.ImageChooser.DisplayyyyyImage(infor.getU_Image(), avatarUser);
		mailLb.setText(infor.getU_Mail());
		descriptionLb.setText(infor.getU_Bio());	
	}
	

	

}
