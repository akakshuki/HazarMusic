package helper;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlbertDiaglog {
	public static void AlbertDiaglog(String mesenger) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Hey!");
		alert.setHeaderText(null);
		alert.setContentText(mesenger);
		
		alert.showAndWait();
	}
	public static void InfoDiaglog(String mesenger) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(":))");
		alert.setHeaderText(null);
		alert.setContentText(mesenger);
		alert.showAndWait();
	}
	public static String TextDialog() {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("input here");
        dialog.setHeaderText("Enter your messenge:");
        dialog.setContentText("messenge:");
        dialog.showAndWait();
		TextField messenger = dialog.getEditor();
		
        return messenger.getText().toString();
 }
}
