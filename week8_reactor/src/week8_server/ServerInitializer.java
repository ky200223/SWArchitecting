/**
 * @file ServerInitializer.java
 * @author youngkim
 * @brief Contains main method to run server / logger example
 */

/**
 * @namespace week8_server
 * @brief project package 
 */
package week8_server;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * @class ServerInitializer
 * @date 2014-09-17
 * @author youngkim, ky200223@nhnnext.org
 * @brief Initializer class for Reactor-Thread using server
 * @details Initialize and start Reactor server on port 5000, using
 *          handlerList.xml for dynamic class loading
 */

public class ServerInitializer {

	public static Logger logger = Logger.getLogger(ServerInitializer.class
			.getName());

	/**
	 * @brief project main method
	 * @details initialize and start reactor server
	 * @param none
	 * @return none
	 */
	public static void main(String[] args) {
		int port = 5000;
		System.out.println("Server ON : " + port);
		logger.info("Server ON : " + port);

		logger.fatal("log4j:logger.fatal()");
		logger.error("log4j:logger.error()");
		logger.warn("log4j:logger.warn()");
		logger.info("log4j:logger.info()");
		logger.debug("log4j:logger.debug()");
		logger.trace("log4j:logger.trace()");

		Reactor reactor = new Reactor(port);

		Serializer serializer = new Persister();
		File source = new File("HandlerList.xml");
		try {
			ServerListData serverList = serializer.read(ServerListData.class,
					source);

			for (HandlerListData handlerListData : serverList.getServer()) {
				if ("server1".equals(handlerListData.getName())) {
					List<String> handlerList = handlerListData.getHandler();
					for (String handler : handlerList) {
						try {
							reactor.registerHandler((EventHandler) Class
									.forName(handler).newInstance());
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		reactor.startServer();
	}
}