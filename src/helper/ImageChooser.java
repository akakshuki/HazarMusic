package helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageChooser {
	public static byte[] SaveImage(Stage primaryStage, ImageView imgView) {
		byte[] data = null;
		FileChooser filechooser = new FileChooser();
		filechooser.setInitialDirectory(new File("C:\\Users\\user\\Pictures\\"));
		File file = filechooser.showOpenDialog(primaryStage);
		if (file != null) {
			BufferedImage bImage;
			try {
				imgView.setImage(new Image(file.toURI().toURL().toString()));
				imgView.setSmooth(true);
				bImage = ImageIO.read(new File(file.getAbsolutePath()));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bImage, "png", baos);
				data = baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public static void SaveImage(String link, ImageView imgView) {
		File file = new File(link);
		if (file != null) {
			BufferedImage bImage;
			try {
				imgView.setImage(new Image(file.toURI().toURL().toString()));
				imgView.setSmooth(true);
				bImage = ImageIO.read(new File(file.getAbsolutePath()));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bImage, "png", baos);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void DisplayyyyyImage(byte[] dataImage, ImageView imgView) {
		Image img = new Image(new ByteArrayInputStream(dataImage));
		imgView.setImage(img);
		imgView.setSmooth(true);
	}

	public static byte[] defaultUserImage() {
		byte[] data = null;
		File file = new File("./src/icon/profile_default.png");
		if (file != null) {
			BufferedImage bImage;
			try {
				bImage = ImageIO.read(new File(file.getAbsolutePath()));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bImage, "png", baos);
				data = baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;

	}
}
