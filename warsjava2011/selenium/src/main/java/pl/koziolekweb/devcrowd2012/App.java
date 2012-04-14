package pl.koziolekweb.devcrowd2012;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://rick.measham.id.au/paste/explain.pl");
		WebElement regex = driver.findElement(By.name("regex"));
		regex.sendKeys("\\d\\d[. +-]\\s\\S\\D\\D[^0-3a-n]+?[a-z]");
		regex.submit();
		WebElement result = driver.findElement(By.tagName("pre"));
		System.out.println(result.getText());
		driver.close();
	}
}
