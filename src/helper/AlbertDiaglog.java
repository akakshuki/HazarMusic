package helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
}
