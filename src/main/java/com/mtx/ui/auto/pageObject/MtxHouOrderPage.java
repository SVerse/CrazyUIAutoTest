package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//mtx后台订单管理页面_对象库类
public class MtxHouOrderPage extends BasePage {
private static Logger log = Logger.getLogger(MtxHouOrderPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   MtxHouOrderPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 发货
* @return
* @throws IOException
*/
public  ElementBeans sendgoods() throws IOException
 {
   if(locatorMap.containsKey("sendgoods")||locatorMap.containsKey("ios_sendgoods")){
   ElementBeans elementBeans=locatorMap.get("sendgoods");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_sendgoods");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【sendgoods】元素信息"); return null;}
 }

/***
* 第一个快递
* @return
* @throws IOException
*/
public  ElementBeans express() throws IOException
 {
   if(locatorMap.containsKey("express")||locatorMap.containsKey("ios_express")){
   ElementBeans elementBeans=locatorMap.get("express");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_express");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【express】元素信息"); return null;}
 }

/***
* 快递单号
* @return
* @throws IOException
*/
public  ElementBeans express_number() throws IOException
 {
   if(locatorMap.containsKey("express_number")||locatorMap.containsKey("ios_express_number")){
   ElementBeans elementBeans=locatorMap.get("express_number");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_express_number");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【express_number】元素信息"); return null;}
 }

/***
* 发货确认
* @return
* @throws IOException
*/
public  ElementBeans expressconfirm() throws IOException
 {
   if(locatorMap.containsKey("expressconfirm")||locatorMap.containsKey("ios_expressconfirm")){
   ElementBeans elementBeans=locatorMap.get("expressconfirm");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_expressconfirm");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【expressconfirm】元素信息"); return null;}
 }

/***
* 订单页面的iframe id
* @return
* @throws IOException
*/
public  ElementBeans orderiframe() throws IOException
 {
   if(locatorMap.containsKey("orderiframe")||locatorMap.containsKey("ios_orderiframe")){
   ElementBeans elementBeans=locatorMap.get("orderiframe");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_orderiframe");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【orderiframe】元素信息"); return null;}
 }
}