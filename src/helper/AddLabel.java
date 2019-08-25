package helper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JLabel;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AddLabel {
		public static void  DateJoin(Label label) {
			final DatePicker datePicker = new DatePicker(LocalDate.now());
			LocalDate date = datePicker.getValue();
			label.setText(date.toString());
								
		
		
			
		}
		public static int ramdomnumber(int max, int min, Label billNo) {
			int ramdomNumber =0;
			ramdomNumber = min + (int)(Math.random() * ((max - min) + 1));
			
			billNo.setText(String.valueOf(ramdomNumber));
			return ramdomNumber;
			
		}
		public static int ramdomnumber(int max, int min) {
			int ramdomNumber =0;
			ramdomNumber = min + (int)(Math.random() * ((max - min) + 1));
			
			
			return ramdomNumber;
			
		}
}
