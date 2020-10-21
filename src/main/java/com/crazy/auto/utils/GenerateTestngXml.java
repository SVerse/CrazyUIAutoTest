package com.crazy.auto.utils;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class GenerateTestngXml {
	private static Logger log = Logger.getLogger(GenerateTestngXml.class);
	public int devicesCounts;
	public Map<String, String> devicesMap;

	public GenerateTestngXml(DevicesManager deviceManager) {
		this.devicesMap = deviceManager.devicesMap;
		this.devicesCounts = deviceManager.devicesCounts;
	}

	/**
	 *
	 * @param classnames
	 * @param testType 0：均分测试用例每个设备；1：每个设备都执行所有用例
	 */
	public void createConfig(List<String> classnames,int testType) {
		Document document = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("suite");
		document.setRootElement(root);
		root.addAttribute("name", "Suite");
		root.addAttribute("parallel", "tests");
		root.addAttribute("thread-count", String.valueOf(devicesCounts));
		Element listeners = root.addElement("listeners");
		Element listener3 = listeners.addElement("listener");
		listener3.addAttribute("class-name", "com.crazy.auto.listeners.ExtentIReporterListenerUtil");
		Element listener4 = listeners.addElement("listener");
		listener4.addAttribute("class-name", "com.crazy.auto.listeners.TestListener");
		
		//{"udid1":"android","udid2":"android","udid3":"ios"}
		Iterator iter = devicesMap.entrySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			Object key = entry.getKey();//udid
			Object val = entry.getValue();//platform
			Element test = root.addElement("test");
			test.addAttribute("name", key.toString());
			Element paramUuid = test.addElement("parameter");
			paramUuid.addAttribute("name", "udid");
			paramUuid.addAttribute("value", key.toString());
			Element paramPort = test.addElement("parameter");
			paramPort.addAttribute("name", "port");
			paramPort.addAttribute("value", String.valueOf(Port.randomPort()));
			Element paramPlatform = test.addElement("parameter");
			paramPlatform.addAttribute("name", "platform");
			paramPlatform.addAttribute("value", val.toString());
			
			Element classes = test.addElement("classes");
			//假设我们有6个用例，两台设备，这时每台3个用例{1,2,3,4,5,6,7,8,9}，{a，b,c}
			
			if (testType == 0) {
				int j = classnames.size() / devicesCounts;// 313/3=104
				int y=classnames.size() % devicesCounts;//y=1
				//每个设备分配测试用例的起始索引（j*i）+每台应该分配的个数j+余数的y==整个测试用例的个数，说明当前是最后一台设备
				if((j*i)+j+y==classnames.size()){
					for (int k = 0; k < j+y; k++) {
						Element classNode = classes.addElement("class");
						classNode.addAttribute("name", classnames.get(j * i + k));
					}
				}else{
					for (int k = 0; k < j; k++) {
						Element classNode = classes.addElement("class");
						classNode.addAttribute("name", classnames.get(j * i + k));
					}
				}
			} else {
				for (int k = 0; k < classnames.size(); k++) {
					Element classNode = classes.addElement("class");
					classNode.addAttribute("name", classnames.get(k));
				}
			}
			i++;
		}
		OutputFormat format = new OutputFormat("    ", true);
		XMLWriter xmlWrite2;
		try {
			xmlWrite2 = new XMLWriter(new FileOutputStream("testngMain111.xml"), format);
			xmlWrite2.write(document);
			xmlWrite2.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("creat testng config file failure-->" + e.getMessage());
			throw new RuntimeException("creat testng config file failure-->" + e.getMessage());
		}
	}
	
	/**
	 * 递归获取某个路径下的所有文件
	 * @param files
	 * @param path
	 * @return
	 */
	public static List<String> getFiles(List<String> files,String path) {
	    //List<String> files = new ArrayList<String>();
	    File file = new File(path);
	    File[] tempList = file.listFiles();

	    for (int i = 0; i < tempList.length; i++) {
	    	
	        if (tempList[i].isFile()) {
	        	System.out.println(tempList[i]);
	            files.add(tempList[i].toString());
	        }
	        if (tempList[i].isDirectory()) {
	            //System.out.println("文件夹：" + tempList[i]);
	        	String pathString=path+"/"+tempList[i].getName();
	        	System.out.println(pathString);
	        	//src/test/java/com/crazy
	        	List<String> aa=getFiles(files,pathString);
	        	files.addAll(aa);
	        }
	    }
	    return files;
	}

	/**
	 * 获取某个路径下的测试类
	 * @param path
	 * @return
	 */
	public static List<String> getClassNames(String path) {
		List<String> files = new ArrayList<String>();
		getFiles(files,path);
		List<String> collect = files.stream().distinct().collect(Collectors.toList());
		List<String> classnames=new ArrayList<String>();
		for (int i = 0; i < collect.size(); i++) {
			String collectString=collect.get(i);
			String replace = collectString.replace('\\', '.');//src.test.java.com.crazy.mty.tests.MTYTests.java
			int indexOf = replace.indexOf("com");
			String substring = replace.substring(indexOf,replace.length()-5);
			classnames.add(substring);
		}
		return classnames;
	}

	public static void main(String[] args) throws Exception {
//		List<String> aList=new ArrayList<String>();
//		getFiles(aList, "src/test/java");
		
		//参数1表示获取安卓设备
		DevicesManager dm = new DevicesManager(1);
//		List<String> deviceList=dm.getAndroidDevices();
		GenerateTestngXml xml=new GenerateTestngXml(dm);
		
		List<String> classnames = getClassNames("src/test/java/com/crazy");
		for (int i = 0; i < classnames.size(); i++) {
			System.out.println(classnames.get(i));
		}
		xml.createConfig(classnames, 1);
	}
}
