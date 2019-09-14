package Controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import Dao.MusicFileDao;
import Dao.RelationShipDao;
import Dao.RequestSentFileDao;
import Dao.UserDao;
import Entities.MusicFile;
import Entities.RequestSentFile;
import Entities.User;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

public class UserMainFormController {
	@FXML
	private ImageView avatarView;
	@FXML
	private Label NameUserlb;
	@FXML
	private ImageView checkOnlineButon;
	@FXML
	private Label logo;
	@FXML
	private ImageView addFriendButon;
	@FXML
	private Label OpenNewFilelb;
	@FXML
	private Label timeNuberLb;
	@FXML
	private Slider MusicTimeSlide;
	@FXML
	private Slider volunmSlide;
	@FXML
	private ImageView ReloadMusicBtn;
	@FXML
	private ImageView playButton;
	@FXML
	private ImageView MuteButton;
	@FXML
	private Label AddNewSong;
	@FXML
	private ImageView upLoadMusicFile;
	@FXML
	private TableView<MusicFile> MusicListTable;
	@FXML
	private TableColumn<MusicFile, String> M_NameCol;
	@FXML
	private TableColumn<MusicFile, String> M_SingerCol;
	@FXML
	private TableColumn<MusicFile, String> M_DescriptionCol;
	@FXML
	private TableColumn<MusicFile, Date> M_DateAddCol;
	@FXML
	private TableColumn<MusicFile, Boolean> M_StatusCol;
	@FXML
	private TableColumn<MusicFile, String> U_NameCol;
	@FXML
	private TableColumn<MusicFile, String> U_AdressCol;
	@FXML
	private TableColumn<MusicFile, String> U_CountryCol;
	@FXML
	private TableColumn<MusicFile, Void> RequestCol;
	@FXML
	private TableView<MusicFile> LibMusicFileView;
	@FXML
	private TableColumn<MusicFile, String> LibSong;
	@FXML
	private TableColumn<MusicFile, String> LibSinger;
	@FXML
	private TableColumn<MusicFile, String> LibUser;
	@FXML
	private TableColumn<MusicFile, Void> LibLink;
	@FXML
	private TableColumn<MusicFile, Void> LibButton;
	@FXML
	private TableView<MusicFile> MusicUserIUpLoadTable;
	@FXML
	private TableColumn<MusicFile, String> NameSongCol;
	@FXML
	private TableColumn<MusicFile, String> SingerSongCol;
	@FXML
	private TableColumn<MusicFile, Date> DateSongCol;
	@FXML
	private TableColumn<MusicFile, Boolean> ActiveCol;
	@FXML
	private TableView<MusicFile> freeStyleTableView;
	@FXML
	private TableColumn<MusicFile, String> FreeListNameSongCol;
	@FXML
	private TableColumn<MusicFile, String> FreeStyleLinkCol;
	@FXML
	private ImageView nextButton;
	@FXML
	private ImageView behindButton;
	@FXML
	private Button test;
	@FXML
	private Label SongNameLb;
	@FXML
	private Label SingerLb;
	@FXML
	private Label Userlb;
	@FXML
	private ImageView noficationButton;
	@FXML
	private TableView<User> friendTableview;
	@FXML
	private TableColumn<User, String> NameFriendCol;
	@FXML
	private TableColumn<User, Boolean> OnlineCol;
	@FXML
	private Button reloadButton;
	@FXML
	private TextField searchMusicName;
	@FXML
	private ImageView searchMusicBtn;
	@FXML
	private BorderPane logoutbox;
	@FXML
	private ImageView logoutbuttn;
	@FXML
	private ImageView searchFriendBtn;
	@FXML
	private TextField searchFiend;
	
	private String LinkFile;
	private Media me;
	private MediaPlayer mp;
	private Duration duration;
	private Status status;
	private Boolean isMute;
	private double currentVolume = 100;

	public void loadForm(Stage mainFrm, User typeUser) {
		LoadUserForm(mainFrm, typeUser);
		LoadFiendedList(mainFrm, typeUser);
		LoadPlayer(mainFrm, typeUser);
		LoadTableMusicList(typeUser);
		LoadTabMusic(mainFrm, typeUser);
		LoadNoficationAndFriendList( typeUser);
		LoadFileAcceptRequest(typeUser);
		reloadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			refeshTableUpload(typeUser, MusicUserIUpLoadTable);
			refreshMusicList();	
			}
		});
	}

	private void LoadFileAcceptRequest(User typeUser) {
		ArrayList<RequestSentFile> listRequest = RequestSentFileDao.getAllAcceptRequestByUser(typeUser.getU_ID());
		for(RequestSentFile rs : listRequest) {
			if(rs.getUserTo().isU_CheckOnline()==true&&rs.getRQ_Waitting()==true) {
			 helper.SocketGetFile.socketGetFile(rs);
			}
		}
	}

	private void LoadNoficationAndFriendList( User typeUser) {
		searchFriendBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				User infor = UserDao.InfoUserByName(searchFiend.getText());
				try {
					Stage inforFor = new Stage();
					inforFor.resizableProperty().set(false);
					inforFor.initStyle(StageStyle.UTILITY);
					inforFor.initModality(Modality.APPLICATION_MODAL);
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/InforFriends2.fxml"));
					AnchorPane root = (AnchorPane) loader.load();
					Scene newScence = new Scene(root, 820, 718);
					FriendInforController2 friendInforController2 = loader.getController();
					friendInforController2.loadInforFriedsForm2(inforFor, infor,typeUser);
					inforFor.setScene(newScence);
					inforFor.showAndWait();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		noficationButton.setOnMouseClicked((MouseEvent e)->{
		try {
			Stage requesFor = new Stage();
			requesFor.resizableProperty().set(false);
			requesFor.initStyle(StageStyle.UTILITY);
			requesFor.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/NoficationListFrm.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene newScence = new Scene(root, 692, 658);
			NoficationController noficationtFormController = loader.getController();
			noficationtFormController.loadRequestForm(typeUser);
			requesFor.setScene(newScence);
			requesFor.showAndWait();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		});
	}

	private void LoadTabMusic(Stage mainFrm, User typeUser) {
		MyLibrarySong(mainFrm);
		MyUpLoadSong(typeUser);
		MyFreeStyleSong(mainFrm);
	}

	private void MyFreeStyleSong(Stage mainFrm) {
		ArrayList<MusicFile> freeListSong = MusicFileDao.GetListLibFileSong();

		AddNewSong.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event p) {
				ArrayList<MusicFile> freeListSong = helper.MusicFileChooser.ChooseListMusicFile(mainFrm);
				ObservableList<MusicFile> freeList = FXCollections.observableArrayList(freeListSong);
				FreeListNameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
				FreeStyleLinkCol.setCellValueFactory(new PropertyValueFactory<>("M_LinkFile"));
				freeStyleTableView.setItems(freeList);
			}
		});
		ObservableList<MusicFile> freeList = FXCollections.observableArrayList(freeListSong);
		FreeListNameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		FreeStyleLinkCol.setCellValueFactory(new PropertyValueFactory<>("M_LinkFile"));
		freeStyleTableView.setItems(freeList);

	}

	private void MyUpLoadSong(User typeUser) {
		ArrayList<MusicFile> listUpLoadFile = MusicFileDao.getMyUpLoadListByUser(typeUser.getU_ID());
		ObservableList<MusicFile> listTableUpLoadFile = FXCollections.observableArrayList(listUpLoadFile);

		NameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		SingerSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		DateSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Date"));
		ActiveCol.setCellValueFactory(new PropertyValueFactory<>("M_Active"));
		ActiveCol.setCellFactory(new Callback<TableColumn<MusicFile,Boolean>, TableCell<MusicFile,Boolean>>() {

			@Override
			public TableCell<MusicFile, Boolean> call(TableColumn<MusicFile, Boolean> arg0) {
				final TableCell<MusicFile, Boolean> cellC = new TableCell<MusicFile, Boolean>() {
					private final Label lb = new Label("no ready");

					@Override
					public void updateItem(Boolean item, boolean empty) {
						//
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setAlignment(Pos.CENTER);
							setGraphic(lb);
							if (item.booleanValue() == true) {
								lb.setText("ready");

							} else {
								lb.setText("not ready");

							}
						}
					}

				};
				return cellC;
			}
		});
		MusicUserIUpLoadTable.setItems(listTableUpLoadFile);
		MusicUserIUpLoadTable.setEditable(false);
		MusicUserIUpLoadTable.setOnMouseClicked((MouseEvent event) -> {
			{
				if (MusicUserIUpLoadTable.getSelectionModel().getSelectedItem() != null) {
					if (event.getClickCount() > 1) {
						int index = MusicUserIUpLoadTable.getSelectionModel().getSelectedIndex();
						MusicFile inforSong = MusicUserIUpLoadTable.getSelectionModel().getSelectedItem();
						if (mp != null)
							mp.stop();
						playMedia(index, listUpLoadFile,typeUser);
						setInforSong(inforSong);
						PlayMusicControl();
						if (MusicFileDao.setActiveForSong(inforSong.getM_ID())) {
							refeshTableUpload(typeUser, MusicUserIUpLoadTable);
							refeshAllSongTable();
						}

					}

				}
			}
		});
	}

	private TableView<MusicFile> refeshTableUpload(User typeUser, TableView<MusicFile> musicUserIUpLoadTable) {
		ArrayList<MusicFile> listUpLoadFile = MusicFileDao.getMyUpLoadListByUser(typeUser.getU_ID());
		ObservableList<MusicFile> listTableUpLoadFile = FXCollections.observableArrayList(listUpLoadFile);
		musicUserIUpLoadTable.setItems(listTableUpLoadFile);
		return musicUserIUpLoadTable;
	}

	private void MyLibrarySong(Stage mainFrm) {
		// arraylist này sai
		ArrayList<MusicFile> ListMusicYouHave = CheckMusic();
		ObservableList<MusicFile> listMusic = FXCollections.observableArrayList(ListMusicYouHave);
		LibSong.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		LibSinger.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		LibUser.setCellValueFactory(new PropertyValueFactory<>("U_Name"));
		LibLink.setCellFactory(new Callback<TableColumn<MusicFile, Void>, TableCell<MusicFile, Void>>() {

			@Override
			public TableCell<MusicFile, Void> call(TableColumn<MusicFile, Void> arg0) {
				final TableCell<MusicFile, Void> Cell = new TableCell<MusicFile, Void>() {
					private final Button openFile = new Button("open");
					{
						openFile.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								try {

								} catch (Exception e) {

									e.printStackTrace();
								}

							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(openFile);

						}
					}
				};
				return Cell;
			}
		});
//		LibButton.setCellFactory(new Callback<TableColumn<MusicFile, Void>, TableCell<MusicFile, Void>>() {
//
//			@Override
//			public TableCell<MusicFile, Void> call(TableColumn<MusicFile, Void> arg0) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
		LibMusicFileView.setItems(listMusic);

	}

	private ArrayList<MusicFile> CheckMusic() {
		ArrayList<MusicFile> ListMusic = MusicFileDao.GetListLibMusic();
		ArrayList<MusicFile> ListFile = MusicFileDao.GetListLibFileSong();
		ArrayList<MusicFile> ListMusicFileYouHave = new ArrayList<>();
		int index = 0;
		for (MusicFile file : ListFile) {
			index = index++;
			for (MusicFile SongHave : ListMusic) {

				if (file.getM_LinkFile().equals(SongHave.getM_LinkFile()) == false) {
					ListMusicFileYouHave.add(index, file);
				}
			}
		}
		return ListMusic;

	}

	private void LoadTableMusicList(User typeUser) {
		searchMusicBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				searchMusicFile(searchMusicName.getText());
				
			}
		});
		
		
		ObservableList<MusicFile> listAllMusic = MusicFileDao.ListGetAllMusic();
		M_NameCol.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("M_Name"));
		M_SingerCol.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("M_Singer"));
		M_DescriptionCol.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("M_Description"));
		M_DateAddCol.setCellValueFactory(new PropertyValueFactory<MusicFile, Date>("M_Date"));
		U_NameCol.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("U_Name"));
		U_CountryCol.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("U_Country"));
		U_AdressCol.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("U_Adress"));
		M_StatusCol.setCellValueFactory(new PropertyValueFactory<MusicFile, Boolean>("M_Active"));
		M_StatusCol.setCellFactory(new Callback<TableColumn<MusicFile, Boolean>, TableCell<MusicFile, Boolean>>() {

			@Override
			public TableCell<MusicFile, Boolean> call(TableColumn<MusicFile, Boolean> p) {
				final TableCell<MusicFile, Boolean> cellC = new TableCell<MusicFile, Boolean>() {
					private final Label lb = new Label("no ready");

					@Override
					public void updateItem(Boolean item, boolean empty) {
						//
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setAlignment(Pos.CENTER);
							setGraphic(lb);
							if (item.booleanValue() == true) {
								lb.setText("ready");

							} else {
								lb.setText("not ready");

							}
						}
					}

				};
				return cellC;
			}
		});
		RequestCol.setCellFactory(new Callback<TableColumn<MusicFile, Void>, TableCell<MusicFile, Void>>() {

			@Override
			public TableCell<MusicFile, Void> call(TableColumn<MusicFile, Void> p) {
				final TableCell<MusicFile, Void> delCell = new TableCell<MusicFile, Void>() {
					private final Button requestButton = new Button("Request");
					{
						requestButton.setOnAction((ActionEvent e) -> {
							MusicFile musicInfor = getTableView().getItems().get(getIndex());
							try {
								Stage requesFor = new Stage();
								requesFor.resizableProperty().set(false);
								requesFor.initStyle(StageStyle.UTILITY);
								requesFor.initModality(Modality.APPLICATION_MODAL);
								FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/RequestFrm.fxml"));
								BorderPane root = (BorderPane) loader.load();
								Scene newScence = new Scene(root, 487, 667);
								RequestController requestFormController = loader.getController();
								requestFormController.loadRequestForm(requesFor, musicInfor, typeUser);
								requesFor.setScene(newScence);
								requesFor.showAndWait();
							} catch (Exception e2) {

								e2.printStackTrace();
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
							setGraphic(requestButton);
						}
					}
				};

				return delCell;

			}
		});
		
		MusicListTable.setItems(listAllMusic);
		MusicListTable.setEditable(false);

	}

		private TableView<MusicFile> searchMusicFile(String text) {
		ObservableList<MusicFile> listAllMusic = null;
		listAllMusic = MusicFileDao.ListSearchMusic(text);
		LibMusicFileView.setItems(listAllMusic);
		return LibMusicFileView;
		
		
	}

	private void LoadPlayer(Stage mainFrm, User typeUser) {

		upLoadMusicFile.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {
				try {
					Stage upLoadFileForm = new Stage();
					upLoadFileForm.resizableProperty().set(false);
					upLoadFileForm.initStyle(StageStyle.UTILITY);
					upLoadFileForm.initModality(Modality.APPLICATION_MODAL);
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/UploadMusic.fxml"));
					AnchorPane root = (AnchorPane) loader.load();
					Scene newScence = new Scene(root, 569, 723);
					UpLoadMusicContoller UpLoadMusicContoller = loader.getController();
					UpLoadMusicContoller.UpLoadMusicContollerInit(upLoadFileForm, typeUser);
					upLoadFileForm.setScene(newScence);
					upLoadFileForm.showAndWait();
					if(!upLoadFileForm.isShowing()) {
						refreshMusicList();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		OpenNewFilelb.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				LinkFile = helper.MusicFileChooser.ChooseFileMusicToPlay(mainFrm);
				if (mp != null) {
					mp.stop();
				}
				me = new Media(LinkFile);
				mp = new MediaPlayer(me);
				mp.play();
				setSceneFilters(mainFrm);
				PlayMusicControl();

			}

		});

	}

	private void LoadFiendedList(Stage mainFrm, User typeUser) {
		loadtableListFriends(typeUser);
		addFriendButon.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event p) {
				try {
					Stage userInfor = new Stage();
					userInfor.resizableProperty().set(false);
					userInfor.initStyle(StageStyle.UTILITY);
					userInfor.initModality(Modality.APPLICATION_MODAL);
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/AnotherFriendList.fxml"));
					AnchorPane root = (AnchorPane) loader.load();
					Scene newScence = new Scene(root, 820, 720);
					AnotherFriendListController ListFriendsController = loader.getController();
					ListFriendsController.FriendsListControllerInit(userInfor, typeUser);
					userInfor.setScene(newScence);
					userInfor.showAndWait();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});
	}

	private void loadtableListFriends(User typeUser) {
		ArrayList<User> listFriend = RelationShipDao.getAllFriendedByIdUser(typeUser.getU_ID());
		ObservableList<User> list = FXCollections.observableArrayList(listFriend);
		friendTableview.setItems(list);
		new Timer().schedule(new TimerTask() {
		    @Override
		    public void run() {
		        try {
		        	refeshFriendList(typeUser);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}, 0, 3000);

		
		NameFriendCol.setCellValueFactory(new PropertyValueFactory<>("U_FullName"));
		OnlineCol.setCellValueFactory(new PropertyValueFactory<User,Boolean>("U_CheckOnline"));
		OnlineCol.setCellFactory(new Callback<TableColumn<User,Boolean>, TableCell<User,Boolean>>() {

			@Override
			public TableCell<User, Boolean> call(TableColumn<User, Boolean> arg0) {
				final TableCell<User, Boolean> cellC = new TableCell<User, Boolean>() {
					private final Label lb = new Label("no ready");
					@Override
					public void updateItem(Boolean item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setAlignment(Pos.CENTER);
							setGraphic(lb);
							if (item.booleanValue() == true) {
								lb.setText("Online");
								
							} else {
								lb.setText("Offline");

							}
						}
					}

				};
				return cellC;
			}
			
		});
			friendTableview.setItems(list);
		friendTableview.setEditable(false);
		friendTableview.setOnMouseClicked((MouseEvent e)->{
			if(friendTableview.getSelectionModel().getSelectedItem()!= null) {
				if(e.getClickCount()>1) {
					User userInfor = friendTableview.getSelectionModel().getSelectedItem();
					try {
						Stage inforFor = new Stage();
						inforFor.resizableProperty().set(false);
						inforFor.initStyle(StageStyle.UTILITY);
						inforFor.initModality(Modality.APPLICATION_MODAL);
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/InforFriends2.fxml"));
						AnchorPane root = (AnchorPane) loader.load();
						Scene newScence = new Scene(root, 820, 718);
						FriendInforController2 friendInforController2 = loader.getController();
						friendInforController2.loadInforFriedsForm2(inforFor, userInfor,typeUser);
						inforFor.setScene(newScence);
						inforFor.showAndWait();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
	}

	private void refeshAllSongTable() {
		ObservableList<MusicFile> listAllMusic = MusicFileDao.ListGetAllMusic();
		MusicListTable.setItems(listAllMusic);
	}

	private void LoadUserForm(Stage mainFrm, User typeUser) {
		logoutbuttn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/logoutForm.fxml"));
					Parent root = (Parent) loader.load();
					logoutController controller = loader.getController();
					Stage RegisterForm = new Stage();
					RegisterForm.setResizable(false);
					controller.init(mainFrm,typeUser);
					logoutbox.setCenter(root);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();

				}
				
			}
		});
		LoadInfo(typeUser);
		mainFrm.setOnCloseRequest(d -> {
			UserDao.setUserOffline(typeUser.getU_ID());
		});
	}

	private void LoadInfo(User typeUser) {
		User UserInfo = UserDao.loadUserOnline(typeUser);
		if (UserInfo.getU_Image() != null) {
			helper.ImageChooser.DisplayyyyyImage(UserInfo.getU_Image(), avatarView);
		}
		NameUserlb.setText(UserInfo.getU_FullName());
		if (UserInfo.isU_CheckOnline()) {
			helper.ImageChooser.SaveImage("./src/icon/online-icon-png-6.png", checkOnlineButon);
		} else {
			helper.ImageChooser.SaveImage("./src/icon/offline-icon-png-6.png", checkOnlineButon);
		}
		checkOnlineButon.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {

				if (UserInfo.isU_CheckOnline() == true) {
					UserDao.setUserOffline(UserInfo.getU_ID());
					helper.ImageChooser.SaveImage("./src/icon/offline-icon-png-6.png", checkOnlineButon);
					reloadUser(typeUser.getR_ID());
				} else {

					try {
						User UserOnline = new User();
						UserOnline.setU_ID(UserInfo.getU_ID());
						UserOnline.setU_IP(Inet4Address.getLocalHost().getHostAddress());
						UserOnline.setU_CheckOnline(true);
						UserDao.setOnlineForUser(UserOnline);

					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					helper.ImageChooser.SaveImage("./src/icon/online-icon-png-6.png", checkOnlineButon);
				}
				reloadUser(typeUser.getU_ID());
			}
		});
		avatarView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {
				LoadInforFormUser(UserInfo);

			}

		});
		NameUserlb.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				LoadInforFormUser(UserInfo);

			}
		});
		logo.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event P) {
				reloadUser(typeUser.getU_ID());

			}
		});

	}

	private void setInforSong(MusicFile inforSong) {
		SongNameLb.setText(inforSong.getM_Name());
		SingerLb.setText(inforSong.getM_Singer());
		Userlb.setText(inforSong.getU_Name());

	}

	private void LoadInforFormUser(User userInfo) {
		try {
			Stage userInfor = new Stage();
			userInfor.resizableProperty().set(false);
			userInfor.initStyle(StageStyle.UTILITY);
			userInfor.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/inforUserForm.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene newScence = new Scene(root, 999, 700);
			InforUserFormController InforController = loader.getController();
			InforController.InforUserControllerInit(userInfor, userInfo);
			userInfor.setScene(newScence);
			userInfor.showAndWait();
			userInfor.setOnCloseRequest(d -> {
				reloadUser(userInfo.getU_ID());
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void reloadUser(int u_ID) {
		User loadUser = UserDao.LoadInforUser(u_ID);
		LoadInfo(loadUser);
	}

	private void setSceneFilters(Stage mainFrm) {
		mainFrm.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (null != event.getCode())
				switch (event.getCode()) {
				case LEFT:
					event.consume();
					mp.seek(mp.getCurrentTime().subtract(Duration.seconds(5)));
					break;
				case RIGHT:
					event.consume();
					mp.seek(mp.getCurrentTime().add(Duration.seconds(5)));
					break;
				case UP:
					event.consume();
					mp.setVolume(volunmSlide.getValue() / 100 + .15);
					currentVolume = mp.getVolume();
					break;
				case DOWN:
					event.consume();
					mp.setVolume(volunmSlide.getValue() / 100 - .15);
					currentVolume = mp.getVolume();
					break;
				case SPACE:
					handlePlayButton();
					break;
				case M:
					muteFuntion();
					break;
				default:
					break;
				}
		});
	}
//playfortable

	public void playMedia(int index, ArrayList<MusicFile> listUpLoadFile, User typeUser) {
		int indexSong = index;
		Path getpath = Paths.get(listUpLoadFile.get(indexSong).getM_LinkFile());
		me = new Media(getpath.toUri().toString());
		mp = new MediaPlayer(me);
		status = mp.getStatus();
		mp.play();
		setInforSong(listUpLoadFile.get(indexSong));
		if (MusicFileDao.setActiveForSong(listUpLoadFile.get(indexSong).getM_ID())) {
			refeshTableUpload(typeUser, MusicUserIUpLoadTable);
			refeshAllSongTable();
		}

		mp.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				mp.stop();
				playMedia(indexSong + 1, listUpLoadFile, typeUser);
				return;
			}
		});
		nextButton.setOnMouseClicked((MouseEvent e) -> {
			mp.stop();
			playMedia(indexSong + 1, listUpLoadFile, typeUser);
		});
		behindButton.setOnMouseClicked((MouseEvent e) -> {
			mp.stop();
			playMedia(indexSong - 1, listUpLoadFile, typeUser);
		});
		PlayMusicControl();
		
	}

	private void PlayMusicControl() {
		helper.ImageChooser.SaveImage("./src/icon/outline_pause_white_18dp.png", playButton);
		playButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event p) {
				handlePlayButton();
			}

		});

		// get time play
		mp.currentTimeProperty().addListener((Observable ov) -> {
			updateValues();
		});
		mp.setOnReady(() -> {
			duration = mp.getMedia().getDuration();
			updateValues();
		});

		// get slide play
		MusicTimeSlide.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (MusicTimeSlide.isValueChanging()) {
					// multiply duration by percentage calculated by slider position
					if (duration != null) {
						mp.seek(duration.multiply(MusicTimeSlide.getValue() / 100.0));
					}
					updateValues();

				}
			}
			
		});
		// time slide
		MusicTimeSlide.setOnMousePressed(event -> {
			double count = event.getX() / 500;
			mp.seek(Duration.seconds(count * mp.getTotalDuration().toSeconds()));

		});

		volunmSlide.setValue(100);
		// set volummne
		mp.setVolume(currentVolume);

		volunmSlide.setOnMousePressed(event -> {
			double count = event.getX() / 86;
			mp.setVolume(count);

		});
		// mute button
		MuteButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {
				muteFuntion();

			}

		});

		// set reload button

		ReloadMusicBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {
				mp.seek(mp.getStartTime());

			}
		});

	}

	private void updateValues() {
		if (timeNuberLb != null && MusicTimeSlide != null && duration != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					Duration currentTime = mp.getCurrentTime();
					timeNuberLb.setText(formatTime(currentTime, duration));
					MusicTimeSlide.setDisable(duration.isUnknown());
					if (!MusicTimeSlide.isDisabled() && duration.greaterThan(Duration.ZERO)
							&& !MusicTimeSlide.isValueChanging()) {
						MusicTimeSlide.setValue(currentTime.divide(duration).toMillis() * 100.0);
					}
				}

			});
		}

	}

	private static String formatTime(Duration elapsed, Duration duration) {
		int intElapsed = (int) elapsed.toSeconds();
		int elapsedHours = intElapsed / (60 * 60);
		if (elapsedHours > 0) {
			intElapsed -= elapsedHours * 60 * 60;
		}
		int elapsedMinutes = intElapsed / 60;
		int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

		if (duration.greaterThan(Duration.ZERO)) {
			int intDuration = (int) duration.toSeconds();
			int durationHours = intDuration / (60 * 60);
			if (durationHours > 0) {
				intDuration -= durationHours * 60 * 60;
			}
			int durationMinutes = intDuration / 60;
			int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;
			if (durationHours > 0) {
				return String.format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds,
						durationHours, durationMinutes, durationSeconds);
			} else {
				return String.format("%02d:%02d/%02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes,
						durationSeconds);
			}
		} else {
			if (elapsedHours > 0) {
				return String.format("%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
			} else {
				return String.format("%02d:%02d", elapsedMinutes, elapsedSeconds);
			}
		}
	}

	private void nextAndprevious() {

	}

	private void handlePlayButton() {
		updateValues();
		status = mp.getStatus();

		if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED) {

			mp.play();
			helper.ImageChooser.SaveImage("./src/icon/outline_pause_white_18dp.png", playButton);

		} else {
			mp.pause();

			helper.ImageChooser.SaveImage("./src/icon/outline_play_arrow_white_18dp.png", playButton);

		}
	}

	private void muteFuntion() {
		if (mp.isMute()) {
			mp.setMute(false);
			helper.ImageChooser.SaveImage("./src/icon/speaker.png", MuteButton);
			isMute = false;
		} else {
			mp.setMute(true);
			helper.ImageChooser.SaveImage("./src/icon/speaker1.png", MuteButton);
			isMute = true;
		}
		
	}
	private TableView<User> refeshFriendList(User typeUser){
		ObservableList<User> list = null;
		list = FXCollections.observableArrayList(RelationShipDao.getAllFriendedByIdUser(typeUser.getU_ID()));
		
		friendTableview.setItems(list);
		return friendTableview;
	}
	private TableView<MusicFile> refreshMusicList(){
		
		ObservableList<MusicFile> listAllMusic = null;
		listAllMusic =MusicFileDao.ListGetAllMusic();
		MusicListTable.setItems(listAllMusic);
		
		
		return MusicListTable;
		
	}
}
