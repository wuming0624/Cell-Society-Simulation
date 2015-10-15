package locale;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Resource {
	private final String common_filename = "common";
	private final String fire_filename = "fire";
	private final String gameOfLife_filename ="gameOfLife";
	private final String predatorPrey_filename = "predatorPrey";
	private final String segregation_filename = "segregation";
	private final String sugar_filename = "sugar";
	private final String LOCALE_PACKAGE = "locale";
	private String  location;

	public Resource(String language){
		  location = LOCALE_PACKAGE + "."+language+".";
		}
	public HashMap<String,ResourceBundle> getAllResourceBundles(){

		  HashMap<String,ResourceBundle> allResourceBundles = new HashMap<String,ResourceBundle>();
		  allResourceBundles.put(common_filename, ResourceBundle.getBundle(location+common_filename));
		  allResourceBundles.put(fire_filename, ResourceBundle.getBundle(location+fire_filename));
		  allResourceBundles.put(gameOfLife_filename, ResourceBundle.getBundle(location+gameOfLife_filename));
		  allResourceBundles.put(predatorPrey_filename, ResourceBundle.getBundle(location+predatorPrey_filename));
		  allResourceBundles.put(segregation_filename, ResourceBundle.getBundle(location+segregation_filename));
		  allResourceBundles.put(sugar_filename, ResourceBundle.getBundle(location+sugar_filename));
		  
		  return allResourceBundles;
	}
}