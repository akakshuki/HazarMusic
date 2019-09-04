package Controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Optional;

import Dao.RelationShipDao;
import Entities.MusicFile;
import Entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RequestController {
	@FXML
	private Label nameSongLb;
	@FXML
	private Label singerNameLb;
	@FXML
	private Label dateAddLb;
	@FXML
	private Label songStatustLb;
	@FXML
	private Label descriptionLb;
	@FXML 
	private Button requestSentFileButton;
	@FXML
	private ImageView avatarUser;
	@FXML
	private Label fullNameUser; 
	@FXML
	private Label 	nameUserLb;
	@FXML 
	private Label mailUserLb;
	@FXML
	private ImageView addFriendLb;
	public void loadRequestForm(Stage requesFor, MusicFile inforSong, User typeUser) {
		loadUserInfor(inforSong);
		loadMusicInfo(inforSong);
		buttonAddFriend(typeUser,inforSong);
	}

	private void buttonAddFriend(User user1, MusicFile user2FromSongInfor) {
		addFriendLb.setOnMouseClicked((MouseEvent  e)->{
			{
				String messenger = helper.AlbertDiaglog.TextDialog();
				if(RelationShipDao.getNewRelationShip(user1,user2FromSongInfor,messenger,LocalDate.now())) {
					helper.AlbertDiaglog.InfoDiaglog("add friend success");
				}else {
					helper.AlbertDiaglog.AlbertDiaglog("add friend fail, try again");
				}
			}
	
		});
	}

	private void loadMusicInfo(MusicFile inforSong) {
		nameSongLb.setText(inforSong.getM_Name());
		singerNameLb.setText(inforSong.getM_Singer());
		dateAddLb.setText(inforSong.getM_Date().toString());
		if(inforSong.isM_Active()) {
			songStatustLb.setText("ready");
		}else {
			songStatustLb.setText("not yet");
			requestSentFileButton.setDisable(true);
		}
		descriptionLb.setText(inforSong.getM_Description());
			
	}

	private void loadUserInfor(MusicFile inforSong) {
		fullNameUser.setText(inforSong.getUser().getU_FullName());
		nameUserLb.setText(inforSong.getUser().getU_Name());
		mailUserLb.setText(inforSong.getUser().getU_Mail());
		helper.ImageChooser.DisplayyyyyImage(inforSong.getUser().getU_Image(), avatarUser);
	}

	
}
