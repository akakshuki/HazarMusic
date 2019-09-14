package helper;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

import Dao.RequestSentFileDao;
import Entities.RequestSentFile;

public class SocketGetFile extends Thread {

    

    public static void socketGetFile(RequestSentFile rs) {
    	    	
		
				try {     System.out.println(rs.getUserTo().getU_IP().toString()+ rs.getRQ_Port());   	
						Socket socket = new Socket(rs.getUserTo().getU_IP().toString(), rs.getRQ_Port());
			            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
			            System.out.println("Getting file from server...");
			            int FILE_SIZE = 6022386;
			            byte[] myByteArray = new byte[FILE_SIZE];
			            
			            //String projectPath = System.getProperty("user.dir");
			            Path target = Paths.get("./download/" + rs.getMusicFile().getM_Name()+" "+rs.getUserTo().getU_FullName() + ".mp3");
			            InputStream inputStream = socket.getInputStream();
			            int bytesRead = inputStream.read(myByteArray, 0, myByteArray.length);
			            int current = bytesRead;
			            do {
			                bytesRead = inputStream.read(myByteArray, current, (myByteArray.length - current));
			                if (bytesRead >= 0) {
			                    current += bytesRead;
			                }
			            } while (bytesRead > -1);
			            
			            FileOutputStream fileOutputStream  = new FileOutputStream(target.toString());
			            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			            bufferedOutputStream.write(myByteArray, 0, current);
			            bufferedOutputStream.flush();
			            System.out.println("Get file success...");
			            helper.AlbertDiaglog.InfoDiaglog("sent file success"); 
			            RequestSentFileDao.setUnWaiting(rs,target.toString());
			            inputStream.close();
			            fileOutputStream.close();
			            bufferedOutputStream.close();
			            socket.close();
			        } catch (IOException e) {
			            System.out.println("Error : " + e.getMessage());
			            helper.AlbertDiaglog.AlbertDiaglog("Error : " + e.getMessage());
			        }
				
			}
	


}
