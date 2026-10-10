package GenericUtilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtility {

	public static String captureScreenshot(WebDriver driver, String screenshotName) {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

		File folder = new File("screenshot");

		if (!folder.exists()) {
			folder.mkdirs();
		}

		String filePath = "screenshot/" + screenshotName + "_" + timeStamp + ".png";

		try {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			File destinationFile = new File(filePath);

			FileUtils.copyFile(sourceFile, destinationFile);

			System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());

			return destinationFile.getAbsolutePath();

		} catch (Exception e) {
			System.out.println("Screenshot capture failed: " + e.getMessage());

			return null;
		}
	}
}
