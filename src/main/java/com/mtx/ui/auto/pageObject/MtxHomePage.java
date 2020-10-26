package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//mtx首页_对象库类
public class MtxHomePage extends BasePage {
private static Logger log = Logger.getLogger(MtxHomePage.class);//用于eclipse工程内运行查找对象库文件路径
 public   MtxHomePage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 登录链接
* @return
* @throws IOException
*/
public  ElementBeans loginlink() throws IOException
 {
   if(locatorMap.containsKey("loginlink")||locatorMap.containsKey("ios_loginlink")){
   ElementBeans elementBeans=locatorMap.get("loginlink");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_loginlink");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【loginlink】元素信息"); return null;}
 }

/***
* 商品
* @return
* @throws IOException
*/
public  ElementBeans firstgoods() throws IOException
 {
   if(locatorMap.containsKey("firstgoods")||locatorMap.containsKey("ios_firstgoods")){
   ElementBeans elementBeans=locatorMap.get("firstgoods");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_firstgoods");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【firstgoods】元素信息"); return null;}
 }

/***
* 个人中心
* @return
* @throws IOException
*/
public  ElementBeans personcenter() throws IOException
 {
   if(locatorMap.containsKey("personcenter")||locatorMap.containsKey("ios_personcenter")){
   ElementBeans elementBeans=locatorMap.get("personcenter");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_personcenter");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【personcenter】元素信息"); return null;}
 }

/***
* 登录以后的元素
* @return
* @throws IOException
*/
public  ElementBeans modvip() throws IOException
 {
   if(locatorMap.containsKey("modvip")||locatorMap.containsKey("ios_modvip")){
   ElementBeans elementBeans=locatorMap.get("modvip");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_modvip");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【modvip】元素信息"); return null;}
 }
}