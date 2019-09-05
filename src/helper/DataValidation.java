package helper;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DataValidation {
	/*
	 * NULL FROM  VALIDATION
	 * Dose not  allow null input
	 */
	
	public static boolean datePicker(DatePicker inputDatePicker, Label inputLabel, String validationText) {
		boolean isNull = false;
		String validationString = null;
		if (inputDatePicker.getValue()==null) {	
			isNull = true;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNull;
	}
	
	public static boolean toggleGroup(ToggleGroup inputToggleGroup, Label inputLabel, String validationText) {
		boolean isNull = false;
		String validationString = null;
		if (inputToggleGroup.getSelectedToggle()==null) {
			isNull = true;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNull;
	}
	
	public static boolean textFiedlNull(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isNull = false;
		String validationString = null;
		if (inputTextField.getText().isEmpty()) {
			isNull = true;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNull;
	}
	public static boolean PasswordFiedlNull(PasswordField inputPField, Label inputLabel, String validationText) {
		boolean isNull = false;
		String validationString = null;
		if (inputPField.getText().isEmpty()) {
			isNull = true;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNull;
	}
	
	/*
	 * DATALENGTH FROM VALIDATION
	 * Only  allows  input with  a specified  length
	 */
	public static boolean  dataLength(TextField inputTextField, Label inputLabel,String validationText, String requirecLength) {
		boolean isDataLength = true;
		String validationString = null;
		if (!inputTextField.getText().matches("\\b\\w" + "{" + requirecLength + "}" +"\\b") && !inputTextField.getText().matches("[1-9]+")) {
			isDataLength = false;
			validationString = validationText;
		}
		
		inputLabel.setText(validationString);
		return isDataLength;
	}
	public static boolean  dataLength1(PasswordField inputPasswordField, Label inputLabel,String validationText, String requirecLength) {
		boolean isDataLength = true;
		String validationString = null;
		if (!inputPasswordField.getText().matches("\\b\\w" + "{" + requirecLength + "}" +"\\b")) {
			isDataLength = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isDataLength;
	}
	
	/*
	 * ALPHABET FROM VALIDATION
	 * only allows  inputs with  alphabet letters only
	 */
	public static boolean textAlphabet(TextField inputTextField, Label inputLabel, String validationText) {
		boolean  isAlphabet = true;
		String validationString = null;
		if (!inputTextField.getText().matches("[a-z A-Z]+")) {
			isAlphabet = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		System.out.println(inputTextField.getText().matches("[a-z A-Z]+"));
		return isAlphabet;
		
	}
	
	/*NUMERIC FROM  VALIDATION
	 * Only allows  inputs with number only
	 */
	public static boolean textNumeric(TextField inputTextField, Label inputLabel, String validationText) {
		boolean isNumeric = true;
		String validationString = null;
		int length = inputTextField.getText().length();
		final int MAX = 9;
		if (!inputTextField.getText().matches("[0-9]") ) {
			isNumeric = false;
			validationString = validationText;
		}
		inputLabel.setText(validationString);
		return isNumeric;
	}
}


