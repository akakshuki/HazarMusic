package Controller;

import java.sql.Date;
import java.time.LocalDate;

import Dao.RequestSentFileDao;
import Entities.MusicFile;
import Entities.RequestSentFile;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SentRequestController {
	@FXML
	private Label nameSongLb;
	@FXML
	private Label SingerLb;
	@FXML
	private Label User1Lb;
	@FXML
	private Label User2Lb;
	@FXML
	private Label DateGetLb;
	@FXML
	private TextArea messenger;
	@FXML
	private Button sentRequestButon;
	public void loadInforFriedsForm(User typeUser, MusicFile inforSong) {
		loadForm(typeUser,inforSong);
		sentRequestBtn(typeUser,inforSong);
	}

	private void sentRequestBtn(User typeUser, MusicFile inforSong) {
		sentRequestButon.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				RequestSentFile sent = new RequestSentFile();
				sent.setM_ID(inforSong.getM_ID());
				sent.setU_IDFrom(typeUser.getU_ID());
				sent.setU_IDTo(inforSong.getU_ID());
				sent.setRQ_Date(Date.valueOf(LocalDate.now()));
				sent.setRQ_Messenger(messenger.getText());
				sent.setRQ_Port(String.valueOf(helper.AddLabel.ramdomnumber(9999, 1000)));
								if(RequestSentFileDao.SentNewRequest(sent)) {
					helper.AlbertDiaglog.InfoDiaglog("sent request sussces");
				}else {
					helper.AlbertDiaglog.AlbertDiaglog("sent request FAIL");
				}
				
			}
		});
		
	}

	private void loadForm(User typeUser, MusicFile inforSong) {
		nameSongLb.setText(inforSong.getM_Name());
		SingerLb.setText(inforSong.getM_Singer());
		User1Lb.setText(typeUser.getU_FullName());
		User2Lb.setText(inforSong.getUser().getU_FullName());
		DateGetLb.setText(LocalDate.now().toString());

		
	}

}
