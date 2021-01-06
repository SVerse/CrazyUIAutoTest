package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//mtx后台首页_对象库类
public class MtxHouHomePage extends BasePage {
private static Logger log = Logger.getLogger(MtxHouHomePage.class);//用于eclipse工程内运行查找对象库文件路径
 public   MtxHouHomePage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 订单管理
* @return
* @throws IOException
*/
public  ElementBeans orderlink() throws IOException
 {
   if(locatorMap.containsKey("orderlink")||locatorMap.containsKey("ios_orderlink")){
   ElementBeans elementBeans=locatorMap.get("orderlink");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_orderlink");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【orderlink】元素信息"); return null;}
 }

/***
* 订单管理菜单
* @return
* @throws IOException
*/
public  ElementBeans ordermenu() throws IOException
 {
   if(locatorMap.containsKey("ordermenu")||locatorMap.containsKey("ios_ordermenu")){
   ElementBeans elementBeans=locatorMap.get("ordermenu");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_ordermenu");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【ordermenu】元素信息"); return null;}
 }
}