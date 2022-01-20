package zcom.crm.comcast.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
/**
 * Utility property file
 * @author Rashmi T K
 *
 */

public class FileUtility {
	/**
	 * this method will fetch the data from property file based on the key passed
	 * @param key
	 * @return
	 * @throws Throwable
	 */

	public String getPropertyFileData(String key) throws Throwable {
		FileInputStream file=new FileInputStream("./src/test/resources/VtigercrmCommomData.properties");
		Properties property=new Properties();
		property.load(file);
		return property.getProperty(key);

	}
	
}
