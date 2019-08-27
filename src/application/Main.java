package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/mainFrm.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,754,635);
			Controller.MainController mainController = loader.getController();
			mainController.init(primaryStage);
			primaryStage.getIcons().add(new Image("./icon/download.png")); 
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
