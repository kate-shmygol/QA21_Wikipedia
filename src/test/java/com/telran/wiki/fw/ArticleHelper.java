package com.telran.wiki.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ArticleHelper extends HelperBase {

	public ArticleHelper(AppiumDriver driver) {
		super(driver);
	}

	public void search() {
		// search_container
		tap(By.id("search_container"));
		type(By.id("search_src_text"), "TCP/IP");
		tap(By.id("page_list_item_container"));
		String title = driver.findElement(By.id("view_page_title_text")).getText();
		System.out.println("View page title text: " + title);
	}

	public void addToFavorites() {
		tap(By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"));
		tap(By.id("onboarding_button"));
	}

	public void createReadingList() {
		driver.findElement(By.id("text_input")).clear();
		type(By.id("text_input"), "My first reading list");
		tap(By.xpath(".//*[@resource-id='android:id/button1']"));
	}

	public void goToFavorites() {
		tap(By.id("snackbar_action"));
	}

	public void removeFromFavorites() {
		swipeLeft();
	}

	private void swipeLeft() {
		TouchAction action = new TouchAction(driver);

		Dimension size = driver.manage().window().getSize();
		int leftPoint = (int) (size.width * 0.2);
		int rightPoint = (int) (size.width * 0.8);

		WebElement element = driver.findElement(By.id("page_list_item_container"));

		int upperY = element.getLocation().getY();
		int lowerY = upperY + element.getSize().getHeight();
		int middleY = (upperY + lowerY) / 2;

		action
				.longPress(PointOption.point(rightPoint, middleY))
				.moveTo(PointOption.point(leftPoint, middleY))
				.release()
				.perform();
	}
}
