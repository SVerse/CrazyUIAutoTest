package com.mtx.ui.auto.pageObject;

import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
import com.crazy.auto.utils.XmlReadUtil;
import com.crazy.auto.utils.YamlReadUtil;

import java.util.HashMap;

public class BasePage {

	public DriverBase driver;
	public String platform;
	public String pagePath = "src/main/resources/pageObjectFiles/xml/UILibrary-MTX0510.xml";
	HashMap<String, ElementBeans> locatorMap;//{"username":elementbeans,"password":elementbeans,"loginBtn":elementbeans}

	public BasePage(DriverBase driver) {
		this.driver = driver;
		this.getLocatorMap();
	}

	public void getLocatorMap() {
		XmlReadUtil xmlReadUtil = new XmlReadUtil();
		YamlReadUtil yamlReadUtil = new YamlReadUtil();
		try {

			if (pagePath.contains(".xml")) {
				locatorMap = xmlReadUtil.readXMLDocument(pagePath, this.getClass().getCanonicalName());
			} else if (pagePath.contains(".yml")) {
				locatorMap = yamlReadUtil.getLocatorMap(pagePath, this.getClass().getCanonicalName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

	}

}
