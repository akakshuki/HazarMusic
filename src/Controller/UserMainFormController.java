package Controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.event.ChangeListener;

import Dao.UserDao;
import Entities.User;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Duration.*;

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

	private String LinkFile;
	private Media me;
	private MediaPlayer mp;
	private Duration duration;
	
	private Status status;
	private Boolean isMute;
	 private double Xlocation, Ylocation, currentVolume = .25;
	public void loadForm(Stage mainFrm, User typeUser) {
		LoadUserForm(mainFrm, typeUser);
		LoadFiendedList(mainFrm, typeUser);
		LoadPlayer(mainFrm, typeUser);

	}

	private void LoadPlayer(Stage mainFrm, User typeUser) {
		OpenNewFilelb.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				LinkFile = helper.MusicFile.ChooseFileMusicToPlay(mainFrm);
				PlayMusicControl(LinkFile);
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
            if (null != event.getCode())  switch (event.getCode()) {
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
        case  SPACE:
            handlePlayButton();
            break;
        case M:
            if(mp.isMute()) {
                mp.setMute(false);
                isMute = false;
                
            }
            else {
                mp.setMute(true);
                isMute = true;
                
            }
            break;
        default:
            break;
        }
        });
    }
	

	private void PlayMusicControl(String linkFile) {
		me = new Media(linkFile);
		mp = new MediaPlayer(me);
		mp.setAutoPlay(true);

		// play and pause button
		handlePlayButton();

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
            mp.seek(Duration.seconds(count * 
                     mp.getTotalDuration().toSeconds()));

		});
		
			
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
		playButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event p) {
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

		});
		
	}

}
