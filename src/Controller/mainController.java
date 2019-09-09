package Controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Button btnSignUp;
	@FXML
	private Button btnLogin;
	@FXML
	private BorderPane mainBorderPane;

	
	
	
	
	public void init(Stage primaryStage) {
		Login(primaryStage);
		SignUpFrom(primaryStage);
	}

	private void SignUpFrom(Stage primaryStage) {
		btnSignUp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/registerFrm.fxml"));
					Parent root = (Parent) loader.load();
					Controller.RegisterController controller = loader.getController();
					Stage RegisterForm = new Stage();
					RegisterForm.setResizable(false);
					controller.init(primaryStage, mainBorderPane);
					mainBorderPane.setCenter(root);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();

				}

			}
		});

	}

	public void Login(Stage primaryStage) {
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/LoginFrm.fxml"));
					Parent root = (Parent) loader.load();
					Controller.LoginController controller = loader.getController();
					Stage mainFrm = new Stage();
					mainBorderPane.setCenter(root);
					controller.LoginOn(primaryStage,mainBorderPane);
					mainFrm.setResizable(false);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();

				}

			}
		});

	}
}
