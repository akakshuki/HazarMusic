package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Label;

public class regex {	
	public static boolean  validValue(String StringCheck, String regexStyle) {
		Pattern pattern = Pattern.compile(regexStyle, Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(StringCheck);
		if(! matcher.find()) {
			return false;
		}else {
			return true;
		}
		
	}
	
}