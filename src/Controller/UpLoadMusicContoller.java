package Controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;

import Dao.MusicFileDao;
import Entities.MusicFile;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpLoadMusicContoller {
	@FXML
	private TextField nameSongTextField;
	@FXML
	private TextArea DescriptionTextArea;
	@FXML
	private TextField namSinggerTextField;
	@FXML
	private Label DateAddLb;
	@FXML
	private Button ChooseButon;
	@FXML
	private TextField LinkFIleTetxField;
	@FXML
	private Button UploadButton;

	private File LinkFile;

	public void UpLoadMusicContollerInit(Stage upLoadFileForm, User typeUser) {
		UploadFile(upLoadFileForm, typeUser);

	}

	private void UploadFile(Stage upLoadFileForm, User typeUser) {
		helper.AddLabel.DateJoin(DateAddLb);
		ChooseButon.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				LinkFile = helper.MusicFileChooser.ChooseFileMusicFile(upLoadFileForm);
				if (LinkFile == null) {
					helper.AlbertDiaglog.InfoDiaglog("you have cancel");
				} else {
					LinkFIleTetxField.setText(LinkFile.toString());
				}
			}
		});
		UploadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (nameSongTextField.getText().isEmpty()) {
					System.out.println("a");
				} else if (namSinggerTextField.getText().isEmpty()) {
					System.out.println("b");
				} else if (LinkFIleTetxField.getText().isEmpty()) {
					System.out.println("c");
				} else {
					Path target = Paths.get("./audio/" + nameSongTextField.getText() + ".mp3");
					MusicFile musicfile = new MusicFile();
					musicfile.setM_Name(nameSongTextField.getText());
					musicfile.setM_Singer(namSinggerTextField.getText());
					musicfile.setM_Date(Date.valueOf(DateAddLb.getText()));
					musicfile.setM_Description(DescriptionTextArea.getText());
					musicfile.setU_ID(typeUser.getU_ID());
					musicfile.setM_LinkFile(target.toString());
					if (MusicFileDao.UploadFileMusic(musicfile)) {
						if (helper.MusicFileChooser.saveMusicFile(upLoadFileForm, LinkFile, target,
								nameSongTextField.getText()))
							upLoadFileForm.close();
						helper.AlbertDiaglog.InfoDiaglog("upload file success");

					} else {
						helper.AlbertDiaglog.AlbertDiaglog("upload FAIL");
					}

				}

			}
		});

	}

}
