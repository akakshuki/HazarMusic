package Controller;

import java.sql.Date;
import java.util.ArrayList;

import Dao.RelationShipDao;
import Dao.RequestSentFileDao;
import Entities.MusicFile;
import Entities.RelationShipUser;
import Entities.RequestSentFile;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class NoficationController {
	@FXML
	private TableView<RelationShipUser> addFriendsList;
	@FXML
	private TableColumn<RelationShipUser, String> UserNameCol;
	@FXML
	private TableColumn<RelationShipUser, Date> DateAddFriendCol;
	@FXML
	private TableColumn<RelationShipUser, String> MessengerCol;
	@FXML
	private  TableColumn<RequestSentFile, Void> ButtonCol;
	@FXML
	private TableView<RequestSentFile> requestSentFileTable;
	@FXML
	private TableColumn<RequestSentFile, String> UserCol;
	@FXML
	private TableColumn<RequestSentFile, String> FileNameCol;
	@FXML
	private TableColumn<RequestSentFile, Date> DateRequestCol;
	@FXML
	private TableColumn<RequestSentFile, String> MessengerRequestCol;
	@FXML
	private TableColumn<RequestSentFile, Void> AcceptButtonCol;
	
	
	
	
	public void loadRequestForm(User typeUser) {
		loadListNofiCationOfUser(typeUser.getU_ID());
		LoadListNoficationOfRequestFile(typeUser.getU_ID());
	}

	private void LoadListNoficationOfRequestFile(int u_ID) {
		ArrayList<RequestSentFile> listNoficationRequestFile = RequestSentFileDao.getNofiCationSentFileFromUser(u_ID);
		ObservableList<RequestSentFile> list = FXCollections.observableArrayList(listNoficationRequestFile);
		UserCol.setCellValueFactory(new PropertyValueFactory<>("U_FullNameFrom"));
		FileNameCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		DateRequestCol.setCellValueFactory(new PropertyValueFactory<>("RQ_Date"));
		MessengerRequestCol.setCellValueFactory(new PropertyValueFactory<>("RQ_Messenger"));
		AcceptButtonCol.setCellFactory(new Callback<TableColumn<RequestSentFile,Void>, TableCell<RequestSentFile,Void>>() {
			
			@Override
			public TableCell<RequestSentFile, Void> call(TableColumn<RequestSentFile, Void> arg0) {
				final TableCell<RequestSentFile, Void> request = new TableCell<RequestSentFile, Void>() {
					private final Button acceptRequestButton = new Button("Accept");
					{
						acceptRequestButton.setOnAction((ActionEvent e) -> {
								
							
						});

					}

					@Override
					public void updateItem(Void item, boolean empty) {
						//
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(acceptRequestButton);
						}
					}
				};
				return request;
			}
		});
		
		
		requestSentFileTable.setItems(list);
	}

	private void loadListNofiCationOfUser(int u_ID) {
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