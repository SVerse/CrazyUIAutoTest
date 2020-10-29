package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//前台订单管理页面_对象库类
public class MtxOrderPage extends BasePage {
private static Logger log = Logger.getLogger(MtxOrderPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   MtxOrderPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 收货
* @return
* @throws IOException
*/
public  ElementBeans acceptgoods() throws IOException
 {
   if(locatorMap.containsKey("acceptgoods")||locatorMap.containsKey("ios_acceptgoods")){
   ElementBeans elementBeans=locatorMap.get("acceptgoods");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_acceptgoods");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【acceptgoods】元素信息"); return null;}
 }

/***
* 弹框确定
* @return
* @throws IOException
*/
public  ElementBeans confirm() throws IOException
 {
   if(locatorMap.containsKey("confirm")||locatorMap.containsKey("ios_confirm")){
   ElementBeans elementBeans=locatorMap.get("confirm");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_confirm");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【confirm】元素信息"); return null;}
 }

/***
* 评论
* @return
* @throws IOException
*/
public  ElementBeans comment() throws IOException
 {
   if(locatorMap.containsKey("comment")||locatorMap.containsKey("ios_comment")){
   ElementBeans elementBeans=locatorMap.get("comment");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_comment");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【comment】元素信息"); return null;}
 }

/***
* 申请售后
* @return
* @throws IOException
*/
public  ElementBeans 申请售后() throws IOException
 {
   if(locatorMap.containsKey("申请售后")||locatorMap.containsKey("ios_申请售后")){
   ElementBeans elementBeans=locatorMap.get("申请售后");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_申请售后");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【申请售后】元素信息"); return null;}
 }

/***
* 选择图片按钮
* @return
* @throws IOException
*/
public  ElementBeans selectimage() throws IOException
 {
   if(locatorMap.containsKey("selectimage")||locatorMap.containsKey("ios_selectimage")){
   ElementBeans elementBeans=locatorMap.get("selectimage");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_selectimage");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【selectimage】元素信息"); return null;}
 }

/***
* 确认按钮
* @return
* @throws IOException
*/
public  ElementBeans confirmimage() throws IOException
 {
   if(locatorMap.containsKey("confirmimage")||locatorMap.containsKey("ios_confirmimage")){
   ElementBeans elementBeans=locatorMap.get("confirmimage");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_confirmimage");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【confirmimage】元素信息"); return null;}
 }

/***
* 头像
* @return
* @throws IOException
*/
public  ElementBeans headerimg() throws IOException
 {
   if(locatorMap.containsKey("headerimg")||locatorMap.containsKey("ios_headerimg")){
   ElementBeans elementBeans=locatorMap.get("headerimg");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_headerimg");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【headerimg】元素信息"); return null;}
 }
}