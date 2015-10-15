package locale;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceTest {

	public static void main(String[] args) {
			Locale supportedLocale = Locale.ENGLISH;
			ResourceBundle resourceBundle = ResourceBundle.getBundle("locale.predatorPrey_en_us",supportedLocale);
			Enumeration<String> bundleKeys = resourceBundle.getKeys();
			while (bundleKeys.hasMoreElements()) {
			    String key = (String)bundleKeys.nextElement();
			    String value = resourceBundle.getString(key);
			    ////System.out.println("key = " + key + ", " + "value = " + value);
			}
	}
}