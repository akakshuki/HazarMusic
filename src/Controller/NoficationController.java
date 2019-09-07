package Controller;

import java.sql.Date;
import java.util.ArrayList;

import Dao.RelationShipDao;
import Entities.RelationShipUser;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NoficationController {
	@FXML
	private TableView<RelationShipUser> addFriendsList;
	@FXML
	private TableColumn<RelationShipUser, String> UserNameCol;
	@FXML
	private TableColumn<RelationShipUser, Date> DateAddFriendCol;
	@FXML
	private TableColumn<RelationShipUser, String> MessengerCol;

	public void loadRequestForm(User typeUser) {
		loadListNofiCation(typeUser.getU_ID());

	}

	private void loadListNofiCation(int u_ID) {
		ArrayList<RelationShipUser> listNoficationForUser = RelationShipDao.getNofiCationFromUser1(u_ID);
		ObservableList<RelationShipUser> list = FXCollections.observableArrayList(listNoficationForUser);
		UserNameCol.setCellValueFactory(new PropertyValueFactory<>("U_FullName1"));
		DateAddFriendCol.setCellValueFactory(new PropertyValueFactory<>("RL_DateAdd"));
		MessengerCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
		addFriendsList.setItems(list);
		addFriendsList.setEditable(false);
		addFriendsList.setOnMouseClicked((MouseEvent e) -> {
			
				if (e.getClickCount() > 1) {
					RelationShipUser infor = addFriendsList.getSelectionModel().getSelectedItem();
					try {
						Stage inforFor = new Stage();
						inforFor.resizableProperty().set(false);
						inforFor.initStyle(StageStyle.UTILITY);
						inforFor.initModality(Modality.APPLICATION_MODAL);
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/InforFriends.fxml"));
						AnchorPane root = (AnchorPane) loader.load();
						Scene newScence = new Scene(root, 820, 718);
						FriendInforController friendInforController = loader.getController();
						friendInforController.loadInforFriedsForm(inforFor, infor);
						inforFor.setScene(newScence);
						inforFor.showAndWait();
					} catch (Exception e2) {
						e2.printStackTrace();
					}

			}
		});
	}
}