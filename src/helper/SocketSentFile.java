package helper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import Entities.RequestSentFile;

// SERVER : Single Server
// Tipe : One-Way Communication (Server to Client)
// Description : Send file to client
public class SocketSentFile {
	public static Thread SocketSentFile(int port, RequestSentFile requestInfo, String filePath) 	{
		
		Thread newthreard = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Socket socket = null;
				ServerSocket serverSocket = null;
				try {
					serverSocket = new ServerSocket(port);
					System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");

					socket = serverSocket.accept();
					System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

					// SEND FILE
					System.out.println("Sending file to client...");

					// if(requestInfo.getUserFrom().getU_IP().equals(socket.getRemoteSocketAddress())==true)
					// {

					File myFile = new File(filePath);
					byte[] myByteArray = new byte[(int) myFile.length()];

					FileInputStream fileInputStream = new FileInputStream(myFile);
					BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
					bufferedInputStream.read(myByteArray, 0, myByteArray.length);
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(myByteArray, 0, myByteArray.length);
					outputStream.flush();
					System.out.println("Send file success...");
					fileInputStream.close();
					bufferedInputStream.close();
					outputStream.close();
					socket.close();
				}
				// }
				catch (IOException e) {
					System.out.println("Error : " + e);
				}
				
			}
				
			
		});
		

		return newthreard;
	}

}
