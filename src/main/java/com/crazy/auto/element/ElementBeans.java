package com.crazy.auto.element;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

/** 
* @version 创建时间：2020年2月15日 下午4:25:36
*/
public class ElementBeans {
	private String value;
	private int timout;
	private String elementName;
	private ByType byType;
	private OperateType operateType;

	/**
	 * create a enum variable for By
	 */
	public enum ByType {
		xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName,iosnspredicatestring,uiautomator,accessibilityId
	}
	
	public enum OperateType {
		sendkeys,click,swipe
	}

	public ElementBeans(String value, int waitSec, ByType byType, String elementName) {
		this.timout = waitSec;
		this.value = value;
		this.byType = byType;
		this.elementName = elementName;
	}

	public String getValue() {
		return value;
	}
	public int getWaitSec() {
		return timout;
	}
	public ByType getType() {
		return byType;
	}
	public void setType(ByType byType) {
		this.byType = byType;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getTimout() {
		return timout;
	}
	public void setTimout(int timout) {
		this.timout = timout;
	}
	public OperateType getOperateType() {
		return operateType;
	}
	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public ByType getByType() {
		return byType;
	}
	public void setByType(ByType byType) {
		this.byType = byType;
	}
	
	public By getBy() {
		switch (byType) {
		case xpath :
            return By.xpath(value);
        case id:
            return By.id(value);
        case cssSelector:
            return By.cssSelector(value);
        case name:
            return By.name(value);
        case className:
            return By.className(value);
        case linkText:
            return By.linkText(value);
        case partialLinkText:
            return By.partialLinkText(value);
        case tagName:
            return By.tagName(value);
        case accessibilityId:
            return MobileBy.AccessibilityId(value);
        case iosnspredicatestring:
            return MobileBy.iOSNsPredicateString(value);
        case uiautomator:
            return MobileBy.AndroidUIAutomator(value);
        default :
            return By.xpath(value);
		}
	}
}
