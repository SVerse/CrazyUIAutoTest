package com.crazy.auto.driver;

import com.crazy.auto.element.ElementBeans;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class SwipeScreenOrElement {
	private static Logger log = Logger.getLogger(SwipeScreenOrElement.class);
	private DriverBase driver;
	private int appWidth;
	private int appHeight;

	public SwipeScreenOrElement(DriverBase driver){
		this.driver = driver;
		this.appWidth = ((AppiumDriver)driver.getDriver()).manage().window().getSize().width;
		this.appHeight = ((AppiumDriver)driver.getDriver()).manage().window().getSize().height;
	}

	/**
	 * 向左滑动
	 * @param duration 滑动时间，单位是毫秒
	 */
	@SuppressWarnings("deprecation")
	private void swipeToLeft(int duration) {
		int startx = appWidth*9/10;
		int endx = appWidth*1/10;
		int y = appHeight*1/2;
		try {
			Duration dur=Duration.ofMillis(duration);
			this.swipe(startx,y,endx,y,dur);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("swipeToLeft is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToLeft is failure -> "+e.getMessage());
		}
	}

	/**
	 * 向右滑动
	 * @param duration 滑动时间，单位是毫秒
	 */
	@SuppressWarnings("deprecation")
	private void swipeToRight(int duration){
		int startx=appWidth*1/10;
		int endx=appWidth*9/10;
		int y=appHeight*1/2;
		try {
			Duration dur=Duration.ofMillis(duration);
			this.swipe(startx,y,endx,y,dur);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("swipeToRight is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToRight is failure -> "+e.getMessage());
		}
	}

	/**
	 * 向上滑动
	 * @param duration 持续时间，单位毫秒
	 */
	@SuppressWarnings("deprecation")
	private void swipeToUp(int duration){
		int starty=appHeight*9/10;
		int endy=appHeight*1/10;
		int x=appWidth*1/2;
		try {
			Duration dur=Duration.ofMillis(duration);
			this.swipe(x,starty,x,endy,dur);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("swipeToUp is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToUp is failure -> "+e.getMessage());
			
		}
	}

	/**
	 * 向下滑动
	 * @param duration 持续时间，单位毫秒
	 */
	@SuppressWarnings("deprecation")
	private void swipeToDown(int duration){
		int starty=appHeight*3/10;
		int endy=appHeight*9/10;
		int x=appWidth*1/2;
		try {
			Duration dur=Duration.ofMillis(duration);
			this.swipe(x,starty,x,endy,dur);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("swipeToDown is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToDown is failure -> "+e.getMessage());
		}
	}

	/**
	 * 滑动方法，通过参数实现各方向滑动
	 * @param direction 方向参数，值为"up"、"down"、"left"、"right"
	 * @param duration 滑动时间，单位毫秒
	 */
	public void swipe(String direction,int duration){
		String direc=direction.toLowerCase();
		switch(direc){
		case "up":
			this.swipeToUp(duration);
			break;
		case "down":
		    this.swipeToDown(duration);
		    break;
		case "left":
			this.swipeToLeft(duration);
			break;
		case "right":
			this.swipeToRight(duration);
			break;
		default:
			//System.out.println("方向参数错误");
			log.error("direction of swipe,direction must is up or down or left or right");
			throw new RuntimeException("direction of swipe,direction must is up or down or left or right");
		}
	}
	
	public void swipe(int startx,int starty,int endx,int endy,Duration duration){
		TouchAction ta=new TouchAction(((AppiumDriver)driver.getDriver()));
		
		ta.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endx, endy)).release().perform();
	}

	public void swipeOnElement(ElementBeans elementBeans,String direction) {
		WebElement element = driver.findElement(elementBeans);
		//起始点坐标(X,Y)
		int startX=element.getLocation().getX();
		int startY=element.getLocation().getY();
		
		int w=element.getSize().getWidth();
		int h=element.getSize().getHeight();
		Duration duration = Duration.ofMillis(500);
		switch (direction.toLowerCase()) {
		case "up":
			this.swipe(startX+w/2, startY+8*h/10, startX+w/2, startY+2*h/10, duration);
			break;
		case "down":
			this.swipe(startX+w/2, startY+2*h/10, startX+w/2, startY+8*h/10, duration);
			break;
		case "left":
			this.swipe(startX+8*w/10, startY+h/2, startX+2*w/10, startY+h/2, duration);
			break;
		case "right":
			this.swipe(startX+2*w/10, startY+h/2, startX+8*w/10, startY+h/2, duration);
			break;
		default:
			log.error("direction of swipe,direction must is up or down or left or right");
			throw new RuntimeException("direction of swipe,direction must is up or down or left or right");
		}	
	}	
}
