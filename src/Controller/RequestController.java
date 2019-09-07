package Controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Optional;

import Dao.RelationShipDao;
import Entities.MusicFile;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	private Label nameUserLb;
	@FXML
	private Label mailUserLb;
	@FXML
	private ImageView addFriendLb;

	public void loadRequestForm(Stage requesFor, MusicFile inforSong, User typeUser) {
		loadUserInfor(inforSong);
		loadMusicInfo(inforSong);
		buttonAddFriend(typeUser, inforSong);
		buttonRequestFile(typeUser,inforSong);
		sentRequest(inforSong,typeUser);
		
	}

	private void sentRequest(MusicFile inforSong, User typeUser) {
		// TODO Auto-generated method stub
		
	}

	private void buttonRequestFile(User typeUser, MusicFile inforSong) {
		requestSentFileButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					Stage inforFor = new Stage();
					inforFor.resizableProperty().set(false);
					inforFor.initStyle(StageStyle.UTILITY);
					inforFor.initModality(Modality.APPLICATION_MODAL);
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/SentRequestForm.fxml"));
					AnchorPane root = (AnchorPane) loader.load();
					Scene newScence = new Scene(root, 820, 718);
					SentRequestController sentRequestController = loader.getController();
					sentRequestController.loadInforFriedsForm(typeUser, inforSong);
					inforFor.setScene(newScence);
					inforFor.showAndWait();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	private void buttonAddFriend(User user1, MusicFile user2FromSongInfor) {
		
		if(RelationShipDao.CheckRelationShip(user1,user2FromSongInfor)||user1.getU_ID() == user2FromSongInfor.getU_ID()){
			addFriendLb.isDisabled();
			addFriendLb.setImage(null);
			requestSentFileButton.setDisable(false);
		}else {
			requestSentFileButton.setDisable(true);
			addFriendLb.setOnMouseClicked((MouseEvent  e)->{
				 {
					 
					String messenger = helper.AlbertDiaglog.TextDialog();
					if(RelationShipDao.getNewRelationShip(user1,user2FromSongInfor,messenger,LocalDate.now())) {
						helper.AlbertDiaglog.InfoDiaglog("add friend success");
						buttonRequestFile(user1,user2FromSongInfor);
						
					}else {
						helper.AlbertDiaglog.AlbertDiaglog("add friend fail, try again");
					}
				}
		
			});
		}
		
	}

	private void loadMusicInfo(MusicFile inforSong) {
		nameSongLb.setText(inforSong.getM_Name());
		singerNameLb.setText(inforSong.getM_Singer());
		dateAddLb.setText(inforSong.getM_Date().toString());
		if (inforSong.isM_Active()) {
			songStatustLb.setText("ready");
		} else {
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
