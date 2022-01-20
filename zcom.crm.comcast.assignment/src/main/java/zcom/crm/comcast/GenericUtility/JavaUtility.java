package zcom.crm.comcast.GenericUtility;


import java.util.Date;
import java.util.Random;
/**
 * java utility class
 * @author Rashmi T K
 *
 */
public class JavaUtility {
	/**
	 * This method will return random number
	 * @return
	 */
	
	public int getRandomNumber() {
		Random random=new Random();
		int randomNumber=random.nextInt(5000);
		return randomNumber;
	}
	/**
	 * This method will return system data and time
	 * @return
	 */
	public String getSystemDateAndTime() {
	Date date = new Date();
	String dateAndTime = date.toString();
	return dateAndTime;
}
}
