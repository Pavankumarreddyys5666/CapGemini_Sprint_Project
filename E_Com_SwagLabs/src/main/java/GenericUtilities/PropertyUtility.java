package GenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {

	private Properties properties;

	public PropertyUtility() {

		properties = new Properties();

		try {

			FileInputStream file = new FileInputStream("./src/main/resources/commonData.properties");

			properties.load(file);

			file.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String readDataFromPropertyFile(String key) {

		return properties.getProperty(key);
	}

}