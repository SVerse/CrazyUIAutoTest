package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//申请售后页面_对象库类
public class MtxAftersalesPage extends BasePage {
private static Logger log = Logger.getLogger(MtxAftersalesPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   MtxAftersalesPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 仅退款
* @return
* @throws IOException
*/
public  ElementBeans onlyrefund() throws IOException
 {
   if(locatorMap.containsKey("onlyrefund")||locatorMap.containsKey("ios_onlyrefund")){
   ElementBeans elementBeans=locatorMap.get("onlyrefund");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_onlyrefund");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【onlyrefund】元素信息"); return null;}
 }

/***
* 退款原因
* @return
* @throws IOException
*/
public  ElementBeans reason() throws IOException
 {
   if(locatorMap.containsKey("reason")||locatorMap.containsKey("ios_reason")){
   ElementBeans elementBeans=locatorMap.get("reason");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_reason");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【reason】元素信息"); return null;}
 }

/***
* 退款说明
* @return
* @throws IOException
*/
public  ElementBeans msg() throws IOException
 {
   if(locatorMap.containsKey("msg")||locatorMap.containsKey("ios_msg")){
   ElementBeans elementBeans=locatorMap.get("msg");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_msg");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【msg】元素信息"); return null;}
 }

/***
* 提交
* @return
* @throws IOException
*/
public  ElementBeans submit() throws IOException
 {
   if(locatorMap.containsKey("submit")||locatorMap.containsKey("ios_submit")){
   ElementBeans elementBeans=locatorMap.get("submit");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_submit");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【submit】元素信息"); return null;}
 }
}