package Dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.stream.Stream;

import ConnetionLib.ConnectionSQL;
import Entities.MusicFile;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MusicFileDao {

	public static boolean UploadFileMusic(MusicFile musicfile) {
		boolean verify = false;
		ConnectionSQL conn = new ConnectionSQL();
		try {
			Object[] param = { musicfile.getM_Name(), musicfile.getM_Singer(), musicfile.getM_Description(),
					musicfile.getM_Date(), musicfile.getU_ID(), musicfile.getM_LinkFile()

			};
			if (conn.updateStoredOrCreate("UploadMusicFile", param)) {
				verify = true;
			} else {
				verify = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			verify = false;
		}
		return verify;
	}

	public static ObservableList<MusicFile> ListGetAllMusic() {
		ArrayList<MusicFile> arrayList = new ArrayList<>();
		ObservableList<MusicFile> list = null;
		ConnectionSQL conn = new ConnectionSQL();
		try {
			ResultSet rs = conn.CallProc("LoadAllMusicFile");
			while (rs.next()) {
				MusicFile file = new MusicFile();
				User userInfor = new User();
				file.setM_Name(rs.getString(1));
				file.setM_Singer(rs.getString(2));
				file.setM_Description(rs.getString(3));
				file.setM_Date(rs.getDate(4));
				file.setM_Active(rs.getBoolean(5));
				userInfor.setU_FullName(rs.getString(6));
				userInfor.setU_Adress(rs.getString(7));
				userInfor.setU_Country(rs.getString(8));
				userInfor.setU_Mail(rs.getString(10));
				userInfor.setU_Name(rs.getString(11));
				userInfor.setU_Image(rs.getBytes(13));
				file.setUser(userInfor);
				file.setU_ID(rs.getInt(12));
				file.setU_Name(rs.getString(6));
				file.setU_Adress(rs.getString(7));
				file.setU_Country(rs.getString(8));
				arrayList.add(file);
				list =FXCollections.observableArrayList(arrayList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<MusicFile> GetListLibMusic() {
		ArrayList<MusicFile> listMusic = new ArrayList<>();
		ConnectionSQL conn = new ConnectionSQL();
		try {
			ResultSet rs = conn.CallProc("checkListMusicFile");
			while(rs.next()) {
				MusicFile file = new MusicFile();
				file.setM_Name(rs.getString(1));
				file.setM_Singer(rs.getString(2));
				file.setM_LinkFile(rs.getString(3));
				file.setU_Name(rs.getString(4));
				listMusic.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return listMusic;
	}
	public static ArrayList<MusicFile> GetListLibFileSong() {
		
		ArrayList<MusicFile> list = new ArrayList<MusicFile>();
		try (Stream<Path> filePathStream = Files.walk(Paths.get("./audio/"))) {
			
			filePathStream.forEach(filePath -> {
				MusicFile file = new MusicFile();
				file.setM_Name(filePath.getFileName().toString());
				file.setM_LinkFile(filePath.toString());
				list.add(file);			
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<MusicFile> getMyUpLoadListByUser(int u_ID) {
		ArrayList<MusicFile> list = new ArrayList<>();
		ConnectionSQL conn = new  ConnectionSQL();
		try {
			Object[] param = {
				u_ID	
			};
			ResultSet rs = conn.CallProc("LoadMuscicFileByIdUser", param);
			while(rs.next()) {
				MusicFile file = new MusicFile();
				file.setM_ID(rs.getInt(1));
				file.setM_Name(rs.getString(2));
				file.setM_Singer(rs.getString(3));
				file.setM_Description(rs.getString(4));
				file.setM_Date(rs.getDate(6));
				file.setM_LinkFile(rs.getString(8));
				file.setM_Active(rs.getBoolean(9));
				file.setU_Name(rs.getString(10));
				list.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean setActiveForSong(int i) {
		ConnectionSQL conn = new ConnectionSQL();
		try {
			Object[] param = {i};
			if(conn.updateStoredOrCreate("activeSong", param)) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
