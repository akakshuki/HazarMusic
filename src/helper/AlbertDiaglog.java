package helper;




import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;


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
	public static boolean AwnserDialog(Alert.AlertType alertType, String statement) {
		Alert alert = new Alert(alertType, statement);
	    alert.getButtonTypes().addAll(ButtonType.CANCEL);
	    Optional<ButtonType> choose = alert.showAndWait();
	    return choose.get() == ButtonType.OK;
	
	}
}
