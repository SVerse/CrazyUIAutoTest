package com.mtx.ui.auto.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
//个人中心页面_对象库类
public class MtxPersonCenterPage extends BasePage {
private static Logger log = Logger.getLogger(MtxPersonCenterPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   MtxPersonCenterPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 个人资料
* @return
* @throws IOException
*/
public  ElementBeans personinfo() throws IOException
 {
   if(locatorMap.containsKey("personinfo")||locatorMap.containsKey("ios_personinfo")){
   ElementBeans elementBeans=locatorMap.get("personinfo");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_personinfo");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【personinfo】元素信息"); return null;}
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
* 修改
* @return
* @throws IOException
*/
public  ElementBeans change() throws IOException
 {
   if(locatorMap.containsKey("change")||locatorMap.containsKey("ios_change")){
   ElementBeans elementBeans=locatorMap.get("change");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_change");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【change】元素信息"); return null;}
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