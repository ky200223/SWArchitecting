/**
 * @file TestClient.java
 * @author youngkim
 * @brief outputstream data for test
 */

/**
 * @namespace week8_server
 * @brief project package 
 */
package week8_server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @class TestClient
 * @date 2014-09-17
 * @author youngkim, ky200223@nhnnext.org
 * @brief outputstream data for test
 * @details outputstream date for test (2type - differentHeader)
 */
public class TestClient {

	public static void main(String[] args) {
		System.out.println("Client ON");

		while (true) {
			try {
				String message;

				Socket socket = new Socket("127.0.0.1", 5000);
				OutputStream out = socket.getOutputStream();
				message = "0x5001|홍길동|40";
				out.write(message.getBytes());
				socket.close();

				Socket socket2 = new Socket("127.0.0.1", 5000);
				OutputStream out2 = socket2.getOutputStream();
				message = "0x6001|hong90|123123|홍길동|23|남성";
				out2.write(message.getBytes());
				socket2.close();

				Thread.sleep(100);

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}