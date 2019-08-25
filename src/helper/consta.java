package helper;

public class consta {
	public final static String TEXT = "^[a-zA-Z0-9._-]{3,}$\r\n";
	public final static String NUM = "[0-9]+";
	public final static String DECIMAL = "[0-9].+";
	public final static String DATE = "\\d{4}[-|/]\\d{2}[-|/]\\d{2}";
	public final static String MAIL = "^[a-zA-Z]\\w+@([a-z]+\\.[a-z]+|[a-z]+\\.[a-z]+\\.[a-z]+)";
}
