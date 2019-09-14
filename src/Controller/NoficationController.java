package Controller;

import java.sql.Date;
import java.util.ArrayList;



import Dao.RelationShipDao;
import Dao.RequestSentFileDao;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
	private TableColumn<RequestSentFile, Void> ButtonCol;
	@FXML
	private TableView<RequestSentFile> requestSentFileTable;
	@FXML
	private TableColumn<RequestSentFile, String> UserCol;
	@FXML
	private TableColumn<RequestSentFile, String> FileNameCol;
	@FXML
	private TableColumn<RequestSentFile, String> MessengerRquestCol;
	@FXML
	private TableColumn<RequestSentFile, Void> ButtonGetCol;
	@FXML
	private Label fullNameLb, userNameLb, AddressLb, userMailLb, OnlineLb, songName, singerlb;
	@FXML
	private ImageView avatarView;
	@FXML
	private Button sentButton;
	@FXML
	private TableView<RequestSentFile> addAcceptList;
	@FXML
	private TableColumn<RequestSentFile, String> UserNameToCol;
	@FXML
	private TableColumn<RequestSentFile, String> nameSongCol;
	@FXML
	private TableColumn<RequestSentFile, Date> DateRequestCol;
	@FXML
	private TableColumn<RequestSentFile, String> MessengerRequestCol;
	@FXML
	private TableColumn<RequestSentFile, Void> AcceptButtonCol;
	@FXML
	private TableColumn<RequestSentFile, Void> ButtonGetAcceptCol;
	public void loadRequestForm(User typeUser) {
		loadListNofiCationOfUser(typeUser.getU_ID());
		LoadListNoficationOfRequestFile(typeUser.getU_ID());
		LoadHistory(typeUser);
	}

	private void LoadHistory(User typeUser) {
		ArrayList<RequestSentFile> listNoficationAcceptRequestFile = RequestSentFileDao
				.getNofiCationAcceptSentFileFromUser(typeUser.getU_ID());
		ObservableList<RequestSentFile> list = FXCollections.observableArrayList(listNoficationAcceptRequestFile);
		UserNameToCol.setCellValueFactory(new PropertyValueFactory<>("U_FullNameFrom"));
		nameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		DateRequestCol.setCellValueFactory(new PropertyValueFactory<>("RQ_Date"));
		MessengerRequestCol.setCellValueFactory(new PropertyValueFactory<>("RQ_MessengerFrom"));
		ButtonGetAcceptCol.setCellFactory(new Callback<TableColumn<RequestSentFile, Void>, TableCell<RequestSentFile, Void>>() {
					@Override
					public TableCell<RequestSentFile, Void> call(TableColumn<RequestSentFile, Void> p) {
						final TableCell<RequestSentFile, Void> request = new TableCell<RequestSentFile, Void>() {
							private final Button getAcceptRequestButton = new Button("Go!");
							{

								getAcceptRequestButton.setOnAction((ActionEvent e) -> {
									RequestSentFile info = getTableView().getItems().get(getIndex());
									helper.SocketGetFile.socketGetFile(info);
									
								});

							}

							@Override
							public void updateItem(Void item, boolean empty) {
							super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
								} else {
									setGraphic(getAcceptRequestButton);
									RequestSentFile infor = getTableView().getItems().get(getIndex());
									if (infor.getRQ_Waitting()==true) {
										getAcceptRequestButton.setDisable(false);
									}else {
										getAcceptRequestButton.setDisable(true);

									}
								}
							}
						};
						return request;
					}
				});
		addAcceptList.setItems(list);
	}

	private void LoadListNoficationOfRequestFile(int u_ID) {
		ArrayList<RequestSentFile> listNoficationRequestFile = RequestSentFileDao.getNofiCationSentFileFromUser(u_ID);
		ObservableList<RequestSentFile> list = FXCollections.observableArrayList(listNoficationRequestFile);
		UserCol.setCellValueFactory(new PropertyValueFactory<>("U_FullNameFrom"));
		FileNameCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		DateRequestCol.setCellValueFactory(new PropertyValueFactory<>("RQ_Date"));
		MessengerRequestCol.setCellValueFactory(new PropertyValueFactory<>("RQ_MessengerFrom"));
		AcceptButtonCol
				.setCellFactory(new Callback<TableColumn<RequestSentFile, Void>, TableCell<RequestSentFile, Void>>() {

					@Override
					public TableCell<RequestSentFile, Void> call(TableColumn<RequestSentFile, Void> arg0) {
						final TableCell<RequestSentFile, Void> request = new TableCell<RequestSentFile, Void>() {

							private final Button acceptRequestButton = new Button("Accept");
							{

								acceptRequestButton.setOnAction((ActionEvent e) -> {
									RequestSentFile info = getTableView().getItems().get(getIndex());
									String messenger = helper.AlbertDiaglog.TextDialog();
									if (RequestSentFileDao.AcceptRequestSentFile(info, messenger)) {
										helper.AlbertDiaglog.InfoDiaglog("success");
										refreshAcceptrquestTable(u_ID);
									} else {
										helper.AlbertDiaglog.AlbertDiaglog("Fail. Try again");
									}
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
									RequestSentFile infor = getTableView().getItems().get(getIndex());
									if (infor.getRQ_Accept()) {
										acceptRequestButton.setDisable(true);
									}
								}
							}

						
						};
						return request;
					}
				});

		requestSentFileTable.setItems(list);
		requestSentFileTable.setOnMouseClicked((MouseEvent e) -> {
			if (requestSentFileTable.getSelectionModel().getSelectedItem() != null) {
				if (e.getClickCount() > 1) {
					RequestSentFile infor = requestSentFileTable.getSelectionModel().getSelectedItem();
					fullNameLb.setText(infor.getUserFrom().getU_FullName());
					userNameLb.setText(infor.getUserFrom().getU_Name());
					AddressLb.setText(infor.getUserFrom().getU_Adress());
					userMailLb.setText(infor.getUserFrom().getU_Mail());
					songName.setText(infor.getMusicFile().getM_Name());
					singerlb.setText(infor.getMusicFile().getM_Singer());
					if (infor.getUserFrom().isU_CheckOnline()) {
						OnlineLb.setText("Online");
					} else {
						OnlineLb.setText("Offline");
					}
					helper.ImageChooser.DisplayyyyyImage(infor.getUserFrom().getU_Image(), avatarView);
					if (infor.getRQ_Accept() == false || infor.getM_ID() == 0) {
						sentButton.setDisable(true);
					} else {
						sentButton.setDisable(false);
					}
					sentButton.setOnAction((ActionEvent e1) -> {
						//RequestSentFile inforReQuest = RequestSentFileDao.getInforRequest(infor);
						if (RequestSentFileDao.setSendingFile(infor)) {
							Thread newthread = helper.SocketSentFile.SocketSentFile(infor.getRQ_Port(), infor,
									infor.getMusicFile().getM_LinkFile());
							newthread.start();
							helper.AlbertDiaglog.InfoDiaglog("wait to sent file success");
							RequestSentFileDao.setSendingFile(infor);

						}
					});
				}
			}
		});
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
	private  TableView<RequestSentFile> refreshAcceptrquestTable(int u_ID){
		ObservableList<RequestSentFile> list =null;
		list =FXCollections.observableArrayList(RequestSentFileDao.getNofiCationSentFileFromUser(u_ID));
		requestSentFileTable.setItems(list);
		return requestSentFileTable;
	}
}