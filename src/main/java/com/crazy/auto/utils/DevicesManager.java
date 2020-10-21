package com.crazy.auto.utils;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.Map.Entry;


public class DevicesManager {

	private static Logger log = Logger.getLogger(DevicesManager.class);
	public Map<String, String> devicesMap;
	public int devicesCounts;
	
	/**
	 * 
	 * @param deviceType   0:android and ios;  1:android;   2:ios
	 */
	public DevicesManager(int deviceType) {
		this.devicesMap = getDevices(deviceType);
		this.devicesCounts = devicesMap.size();
	}

	/**
	 * 根据传入的测试设备类型获取当前可用的设备udid
	 * {"udid1":"android","udid2":"android","udid3":"ios"}
	 * @param deviceType
	 *            0:android and ios;1:android;2:ios
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getDevices(int deviceType) {
		Map<String, String> deviceMap = new HashMap<String, String>();
		if (deviceType == 0 && Command.isMac()) {
			List<String> androidList = getAndroidDevices();
			// {key:value} {"hdgt12ff":"android"}
			for (String s : androidList) {
				deviceMap.put(s, "android");
			}

			List<String> iosList = getIOSDevices();
			for (String s : iosList) {
				deviceMap.put(s, "ios");
			}

		} else if (deviceType == 1) {
			List<String> androidList = getAndroidDevices();
			for (String s : androidList) {
				deviceMap.put(s, "android");
			}
		} else if (deviceType == 2 && Command.isMac()) {
			List<String> iosList = getIOSDevices();
			for (String s : iosList) {
				deviceMap.put(s, "ios");
			}

		} else {
			log.error("deviceType is error or deviceType and os compatible");
			throw new RuntimeException("deviceType is error or deviceType and os compatible");
		}
		return deviceMap;
	}

	public List<String> getIOSDevices() {
		String iosResult;
		try {
			iosResult = CommandLineExec.executor(Command.getIOSDevices());
			if(iosResult.equals("")){
				log.error("No ios device");
				throw new RuntimeException("No ios device");
			}
		} catch (Exception e) {
			log.error(("No ios device-->" + e.getMessage()));
			throw new RuntimeException("No ios device-->" + e.getMessage());
		}
		String[] result = iosResult.split("\n");
		List<String> deviceRes = new ArrayList<String>();
		if (result.length > 1) {
			for (String s : result) {
				deviceRes.add(s);
			}
		} else {
			log.error("No ios device");
			throw new RuntimeException("No ios device or device more than one ");
		}
		return deviceRes;
	}

	public List<String> getAndroidDevices() {
		String androidResult;
		try {
			androidResult = CommandLineExec.executor(Command.getAndroidDevices());
			
		} catch (Exception e) {
			log.error(("No android device-->" + e.getMessage()));
			throw new RuntimeException("No android device-->" + e.getMessage());
		}
		String[] result = androidResult.split("\n");
		List<String> deviceRes = new ArrayList<String>();
		int total = 2;
		if (Command.isMac()) {
			total = 1;
		}
		if (result.length >2) {
			
			for (int i = 1; i < result.length - total + 1; i++) {
				System.out.println(result[i]);
				String deviceInfo[] = result[i].split("\t");
				// System.out.println(deviceInfo[0]);127.0.0.1:62001,device
				if (deviceInfo[1].trim().equals("device")) {
					deviceRes.add(deviceInfo[0].trim());
					log.info("get android device " + deviceInfo[0].trim());
				}
			}
		} else {
			log.error("No android device  ");
			throw new RuntimeException("No android device ");
		}
		return deviceRes;
	}

	public static void main(String[] args) throws Exception {
		// String commandResult
		// =CommandLineExec.executor(Command.getAndroidDevices());
		// System.out.println(commandResult.split("\n")[0]);
		DevicesManager dm = new DevicesManager(1);
		Map<String, String> devices = dm.getDevices(1);
		Set<Entry<String, String>> entrySet = devices.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		System.out.println(devices.size());
	}

}
