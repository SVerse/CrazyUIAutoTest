package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//商品下单页_对象库类
public class GoodsOrderPage extends BasePage {
private static Logger log = Logger.getLogger(GoodsOrderPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   GoodsOrderPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 货到付款
* @return
* @throws IOException
*/
public  ElementBeans 货到付款() throws IOException
 {
   if(locatorMap.containsKey("货到付款")||locatorMap.containsKey("ios_货到付款")){
   ElementBeans elementBeans=locatorMap.get("货到付款");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_货到付款");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【货到付款】元素信息"); return null;}
 }

/***
* 提交订单
* @return
* @throws IOException
*/
public  ElementBeans 提交订单() throws IOException
 {
   if(locatorMap.containsKey("提交订单")||locatorMap.containsKey("ios_提交订单")){
   ElementBeans elementBeans=locatorMap.get("提交订单");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_提交订单");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【提交订单】元素信息"); return null;}
 }
}