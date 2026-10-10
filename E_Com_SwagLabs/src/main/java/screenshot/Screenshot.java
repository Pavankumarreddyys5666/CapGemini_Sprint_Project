
package screenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static String takeScreenshot(
	        String fileName, WebDriver driver) {

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        Path folder = Paths.get("screenshot");
        Path destination = folder.resolve(
                fileName + "_" + timestamp + ".png");

        try {
            Files.createDirectories(folder);

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(source.toPath(), destination);

            System.out.println("Screenshot saved: "
                    + destination.toAbsolutePath());

            return destination.toAbsolutePath().toString();

        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }
    }
}
