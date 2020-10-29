package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//订单评论页面_对象库类
public class MtxCommentPage extends BasePage {
private static Logger log = Logger.getLogger(MtxCommentPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   MtxCommentPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 评论星号
* @return
* @throws IOException
*/
public  ElementBeans rating() throws IOException
 {
   if(locatorMap.containsKey("rating")||locatorMap.containsKey("ios_rating")){
   ElementBeans elementBeans=locatorMap.get("rating");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_rating");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【rating】元素信息"); return null;}
 }

/***
* 评论内容
* @return
* @throws IOException
*/
public  ElementBeans content() throws IOException
 {
   if(locatorMap.containsKey("content")||locatorMap.containsKey("ios_content")){
   ElementBeans elementBeans=locatorMap.get("content");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_content");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【content】元素信息"); return null;}
 }

/***
* 是否匿名
* @return
* @throws IOException
*/
public  ElementBeans amswitch() throws IOException
 {
   if(locatorMap.containsKey("amswitch")||locatorMap.containsKey("ios_amswitch")){
   ElementBeans elementBeans=locatorMap.get("amswitch");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_amswitch");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【amswitch】元素信息"); return null;}
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