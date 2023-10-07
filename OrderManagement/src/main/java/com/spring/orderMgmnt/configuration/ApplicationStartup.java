package com.spring.orderMgmnt.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<WebServerInitializedEvent> {

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			int port = event.getWebServer().getPort();
			System.out.println("\n\n============================================================================================"
					+ "=============================================================================================="
					+ "\n\t\t\t\t\t\tYour application is available at: http://" + localhost.getHostAddress() + ":" 
					+ port + "/ in your network.\n=============================================================================="
					+ "============================================================================================================\n\n");
		} catch (UnknownHostException e) {
			System.err.println("Error fetching machine's IP address: " + e.getMessage());
		}
	}
}