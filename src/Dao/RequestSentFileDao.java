package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import ConnetionLib.ConnectionSQL;
import Entities.MusicFile;
import Entities.RequestSentFile;
import Entities.User;

public class RequestSentFileDao {

	public static boolean SentNewRequest(RequestSentFile sent) {
		Object[] param = { sent.getM_ID(), sent.getU_IDFrom(), sent.getU_IDTo(), sent.getRQ_Date(),
				sent.getRQ_MessengerFrom(), sent.getRQ_Port() };

		try {
			if (ConnectionSQL.updateStoredOrCreate("newSentRequestFile", param)) {
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static ArrayList<RequestSentFile> getNofiCationSentFileFromUser(int u_ID) {
		ArrayList<RequestSentFile> list = new ArrayList<>();
		Object[] param = { u_ID };
		try {
			ResultSet rs = ConnectionSQL.CallProc("getListRequestFileFromUser", param);
			while (rs.next()) {
				RequestSentFile info = new RequestSentFile();
				User user = new User();
				MusicFile music = new MusicFile();
				info.setU_IDTo(rs.getInt(1));
				info.setU_IDFrom(rs.getInt(2));
				user.setU_FullName(rs.getString(3));
				info.setU_FullNameFrom(rs.getString(3));
				user.setU_CheckOnline(rs.getBoolean(4));
				user.setU_IP(rs.getString(5));
				user.setU_Adress(rs.getString(11));
				user.setU_Mail(rs.getString(12));
				user.setU_Image(rs.getBytes(13));
				user.setU_Name(rs.getString(14));
				info.setUserFrom(user);
				music.setM_ID(rs.getInt(6));
				music.setM_Name(rs.getString(10));
				music.setM_LinkFile(rs.getString(7));
				music.setM_Singer(rs.getString(15));
			
				info.setMusicFile(music);
				info.setM_ID(rs.getInt(6));
				info.setRQ_Accept(rs.getBoolean(16));
				info.setM_Name(rs.getString(10));
				info.setRQ_Date(rs.getDate(8));
				info.setRQ_MessengerFrom("Request:" + rs.getString(9));
				info.setRQ_Port(rs.getInt(17));
				list.add(info);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean AcceptRequestSentFile(RequestSentFile info, String messenger) {
		Object[] param = { info.getU_IDFrom(), info.getU_IDTo(), info.getM_ID(), messenger };
		try {
			if(ConnectionSQL.updateStoredOrCreate("AcceptRequestCentFile", param)) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<RequestSentFile> getNofiCationAcceptSentFileFromUser(int u_ID) {
		ArrayList<RequestSentFile> list = new ArrayList<>();
		Object[] param = { u_ID };
		try {
			ResultSet rs = ConnectionSQL.CallProc("getListAcceptRequestFileFromUser", param);
			while (rs.next()) {
				RequestSentFile info = new RequestSentFile();
				User user = new User();
				MusicFile music = new MusicFile();
				user.setU_FullName(rs.getString(1));
				info.setU_FullNameFrom(rs.getString(1));
				user.setU_CheckOnline(rs.getBoolean(2));
				user.setU_Adress(rs.getString(7));
				user.setU_Mail(rs.getString(3));
				user.setU_Image(rs.getBytes(9));
				user.setU_Name(rs.getString(10));
				user.setU_IP(rs.getString(16));
				info.setUserFrom(user);
				music.setM_Name(rs.getString(6));
				music.setM_Singer(rs.getString(10));
				info.setMusicFile(music);
				info.setRQ_Accept(rs.getBoolean(12));
				info.setM_Name(rs.getString(6));
				info.setRQ_Date(rs.getDate(4));
				info.setRQ_MessengerFrom("Accept:	" + rs.getString(5));
				info.setRQ_Waitting(rs.getBoolean(14));
				info.setRQ_Port(rs.getInt(15));
				list.add(info);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean setSendingFile(RequestSentFile infor) {
		Object[] param = {
			infor.getU_IDTo(),
			infor.getU_IDFrom(),
			infor.getM_ID()
		};
		try {
			if(ConnectionSQL.updateStoredOrCreate("setSendingFile", param)) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static RequestSentFile getInforRequest(RequestSentFile infor) {
			Object[] param = {
				infor.getU_IDTo(),
				infor.getU_IDFrom(),
				infor.getM_ID()
			};
		try {
			ResultSet rs = ConnectionSQL.CallProc("", param);
			while(rs.next()) {
				RequestSentFile request = new RequestSentFile();
//				request.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<RequestSentFile> getAllAcceptRequestByUser(int u_ID) {
		ArrayList<RequestSentFile> list = new ArrayList<>();
		Object[] param = {u_ID};
		try {
			ResultSet rs = ConnectionSQL.CallProc("getInfoRequestSendFile", param);
			while(rs.next()) {
				RequestSentFile rq = new RequestSentFile();
				User userTo = new User();
				userTo.setU_FullName(rs.getString(2));
				userTo.setU_Name(rs.getString(1));
				userTo.setU_CheckOnline(rs.getBoolean(3));
				userTo.setU_IP(rs.getString(4));
				rq.setUserTo(userTo);
				MusicFile mf = new MusicFile();
				mf.setM_Name(rs.getString(5));
				mf.setM_Singer(rs.getString(6));
				mf.setM_LinkFile(rs.getString(9));
				rq.setMusicFile(mf);
				rq.setRQ_Port(rs.getInt(7));
				rq.setU_IDFrom(rs.getInt(10));
				rq.setU_IDTo(rs.getInt(11));
				rq.setM_ID(rs.getInt(12));
				rq.setRQ_Waitting(rs.getBoolean(13));
				list.add(rq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void setUnWaiting(RequestSentFile rs, String target) {
		Object[] param = {rs.getU_IDFrom(),rs.getU_IDTo(),rs.getM_ID(), target};
		try {
			if(ConnectionSQL.updateStoredOrCreate("setUnWaiting", param)) {}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void CancelAllRequestSent(int u_ID) {
		Object[] param= {u_ID};
		try {
			if(ConnectionSQL.updateStoredOrCreate("setCancelSendingFile", param)) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
