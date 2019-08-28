package helper;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MusicFile {
	public final static String ChooseFileMusicToPlay(Stage HaveStage) {
		String linkFile = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
				new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(HaveStage);
		if (selectedFile != null) {
			linkFile = selectedFile.toURI().toString();
		}
		return linkFile;
	}
}
