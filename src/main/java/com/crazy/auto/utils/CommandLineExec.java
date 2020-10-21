package com.crazy.auto.utils;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;


public class CommandLineExec {
	private static Logger log = Logger.getLogger(CommandLineExec.class);
	public static String osName = System.getProperty("os.name");

	public static String executor(String command) throws Exception {
		try {
			String commandBasic = "cmd /c ";
			if (Command.isMac()) {
				commandBasic = "";
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
			CommandLine commandline = CommandLine.parse(commandBasic+command);//cmd /c adb devices
			DefaultExecutor exec = new DefaultExecutor();
			exec.setExitValues(null);
			PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
			exec.setStreamHandler(streamHandler);
			exec.execute(commandline);
			String out = outputStream.toString("gbk");
			String error = errorStream.toString("gbk");
			if (!"".equals(error.trim())) {
				throw new Exception("executore " + command + "failure-->" + error);
			}
			return out;

		} catch (Exception e) {
			log.error("executore " + command + " failure-->" + e.getMessage());
			throw new Exception("executore " + command + " failure-->" + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception{
		//System.out.println(executor("adb connect 127.0.0.1:62001"));
			//System.out.println(executor("adb devices"));
			String s=executor("adb devices");
			//System.out.println(s);
			String[] split=s.split("\n");
			System.out.println(split.length);
//			for(String l:split){
//				System.out.println(l);
//			}
			//3,第一行不是设备，最后一行是空行也不是设备
			if(split.length>2){
				for(int i=1;i<split.length-1;i++){
					//127.0.0.1:62001	device
					String deviceInfo=split[i];
					System.out.println(deviceInfo);
					String[] deviceArray=deviceInfo.split("\t");
					
					if(deviceArray[1].trim().equals("device")){
						System.out.println(deviceArray[0]);
					}
				}
			}

	}

}
