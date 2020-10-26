package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//商品详情页_对象库类
public class GoodsDetailPage extends BasePage {
private static Logger log = Logger.getLogger(GoodsDetailPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   GoodsDetailPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 立即购买
* @return
* @throws IOException
*/
public  ElementBeans buy() throws IOException
 {
   if(locatorMap.containsKey("buy")||locatorMap.containsKey("ios_buy")){
   ElementBeans elementBeans=locatorMap.get("buy");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_buy");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【buy】元素信息"); return null;}
 }
}