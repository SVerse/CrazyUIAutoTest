package com.crazy.auto.driver;

import com.crazy.auto.utils.Port;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.apache.log4j.Logger;

import java.io.File;


public class AppiumServers {
	private static Logger log = Logger.getLogger(AppiumServers.class);
	AppiumDriverLocalService service;

	public AppiumServers() {
		
	}

	public void startServer(String port) {
		AppiumServiceBuilder ab = new AppiumServiceBuilder();
		//ab.withIPAddress("10.0.0.37");
		ab.usingPort(Integer.valueOf(port));
		ab.withArgument(new ServerArgument() {
			@Override
			public String getArgument() {
				// TODO Auto-generated method stub
				return "--local-timezone";
			}
		});
		ab.withArgument(new ServerArgument() {
			@Override
			public String getArgument() {
				// TODO Auto-generated method stub
				return "-bp";
			}
		}, String.valueOf(Port.randomPort()));
		ab.withArgument(new ServerArgument() {
			@Override
			public String getArgument() {
				// TODO Auto-generated method stub
				return "--chromedriver-port";
			}
		}, String.valueOf(Port.randomPort()));
		ab.withLogFile(new File("logs/appium" + port + ".log"));// 日志存放，每次只需覆盖
		//service.isRunning();
		//ab.usingDriverExecutable(nodeJSExecutable)
		service = ab.build();
		if (service.isRunning()) {
			
			service.stop();
			System.out.println("服务在运行停止他");
		}
		service.start();
	}

	public void stop() {
		service.stop();
	}

	public static void main(String[] args) throws Exception {
		AppiumServers servers=new AppiumServers();
		System.out.println(Port.isPortUsed(6491));
		servers.startServer("6491");
		Thread.sleep(10000);
		//servers.stop();
		System.out.println(Port.isPortUsed(6491));
	}
}
