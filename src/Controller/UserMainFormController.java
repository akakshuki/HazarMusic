package Controller;

import java.io.File;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;

import javax.sound.midi.Soundbank;

import Dao.MusicFileDao;
import Dao.UserDao;
import Entities.MusicFile;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.DirectoryChooser;
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
	private Button test;

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
		LoadTableMusicList();
		LoadTabMusic(mainFrm, typeUser);
		test();
	}

	private void test() {
		test.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// tét
			}
		});

	}

	private void LoadTabMusic(Stage mainFrm, User typeUser) {
		MyLibrarySong(mainFrm);
		MyUpLoadSong(typeUser,mainFrm);
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

	private void MyUpLoadSong(User typeUser, Stage mainFrm) {
		ArrayList<MusicFile> listUpLoadFile = MusicFileDao.getMyUpLoadListByUser(typeUser.getU_ID());
		ObservableList<MusicFile> listTableUpLoadFile = FXCollections.observableArrayList(listUpLoadFile);

		NameSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Name"));
		SingerSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Singer"));
		DateSongCol.setCellValueFactory(new PropertyValueFactory<>("M_Date"));
		ActiveCol.setCellValueFactory(new PropertyValueFactory<>("M_Active"));

		MusicUserIUpLoadTable.setItems(listTableUpLoadFile);
		MusicUserIUpLoadTable.setEditable(false);
		MusicUserIUpLoadTable.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() > 1) {
				if (MusicUserIUpLoadTable.getSelectionModel().getSelectedItem() != null) {
						MusicFile inforMusic = MusicUserIUpLoadTable.getSelectionModel().getSelectedItem();
						int index = MusicUserIUpLoadTable.getSelectionModel().getSelectedIndex();
						System.out.println(index);
						Path getpath = Paths.get(inforMusic.getM_LinkFile());
						me = new Media(getpath.toUri().toString());
						mp = new MediaPlayer(me);
						mp.play();
						PlayMusicControl();
						setSceneFilters(mainFrm);
				}
			}
		});
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

	private void LoadTableMusicList() {
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
						requestButton.setOnAction(new EventHandler<ActionEvent>() {

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
						//
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(requestButton);
							MusicFile musicInfor = getTableView().getItems().get(getIndex());
							if (musicInfor.isM_Active() == false) {
								requestButton.setDisable(true);
							}
						}
					}
				};

				return delCell;

			}
		});
		MusicListTable.setItems(listAllMusic);
		MusicListTable.setEditable(false);
		MusicListTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		OpenNewFilelb.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				LinkFile = helper.MusicFileChooser.ChooseFileMusicToPlay(mainFrm);
				me = new Media(LinkFile);
				mp = new MediaPlayer(me);
				mp.autoPlayProperty();
				setSceneFilters(mainFrm);

			}

		});

	}

	private void LoadFiendedList(Stage mainFrm, User typeUser) {
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
					Scene newScence = new Scene(root, 839, 717);
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

	private void LoadUserForm(Stage mainFrm, User typeUser) {
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
}
