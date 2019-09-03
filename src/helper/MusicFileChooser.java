package helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import Entities.MusicFile;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MusicFileChooser {
	public final static String ChooseFileMusicToPlay(Stage HaveStage) {
		String linkFile = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
		File selectedFile = fileChooser.showOpenDialog(HaveStage);
		if (selectedFile != null) {
			linkFile = selectedFile.toURI().toString();
		}
		return linkFile;
	}

	public final static File ChooseFileMusicFile(Stage HaveStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
				new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(HaveStage);
		return selectedFile;

	}

	public final static boolean saveMusicFile(Stage primaryStage, File file, Path target, String string) {
		boolean verify = false;
		if (file != null) {
			try {
				if (Files.copy(file.toPath(), target) != null) {

					verify = true;

				} else {
					verify = false;
				}
			} catch (FileAlreadyExistsException fae) {
				fae.printStackTrace();
				verify = false;
			} catch (IOException e) {
				e.printStackTrace();
				verify = false;
			}
		}
		return verify;

	}

	public static ArrayList<MusicFile> ChooseListMusicFile(Stage mainFrm) {
		ArrayList<MusicFile> list = new ArrayList<>();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
				new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(mainFrm);
		if(selectedFile != null) {
			MusicFile file = new MusicFile();
			file.setM_Name(selectedFile.getName().toString());
			file.setM_LinkFile(selectedFile.toString());
			list.add(file);			
		}
		return list;
	}

	
}
