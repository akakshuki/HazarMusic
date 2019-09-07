package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import ConnetionLib.ConnectionSQL;
import Entities.RelationShipUser;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class UserDao {

	
	
	public static boolean checkUser(String text) {
		boolean verify = false;
		ConnectionSQL conn = new ConnectionSQL();
		try {
			Object[] param= {
					text
			};
			ResultSet rs = conn.CallProc("checkUser", param);
			if(rs.next()) {
				verify= true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			verify =false;
		}
		return verify;
	}

	public static boolean checkEmail(String text) {
		boolean verify = false;
		ConnectionSQL conn = new ConnectionSQL();
		try {
			Object[] param= {
					text
			};
			ResultSet rs = conn.CallProc("checkMail", param);
			if(rs.next()) {
				verify= true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			verify =false;
		}
		return verify;
	}

	

	public static User Login(User loginUser) {
		User inforUser = new User();
		ConnectionSQL conn = new ConnectionSQL();
		Object[] param = {
			loginUser.getU_Name(), loginUser.getU_Password()	
		};
		try {
			ResultSet rs = conn.CallProc("login", param);
			while(rs.next()) {
				inforUser.setU_ID(rs.getInt(1));
				inforUser.setU_Name(rs.getString(2));
				inforUser.setU_Adress(rs.getString(4));
				inforUser.setU_Country(rs.getString(5));
				inforUser.setU_Bio(rs.getString(6));
				inforUser.setU_Image(rs.getBytes(7));
				inforUser.setU_Mail(rs.getString(8));
				inforUser.setU_Phone(rs.getString(9));
				inforUser.setU_CheckOnline(rs.getBoolean(11));
				inforUser.setR_ID(rs.getInt(12));
				inforUser.setU_DateJoin(rs.getDate(13));
				inforUser.setU_BirthDate(rs.getDate(14));
				inforUser.setU_FullName(rs.getNString(15));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return inforUser;
		
	}
	public static User loadUserOnline(User loginUser) {
		User inforUser = new User();
		ConnectionSQL conn = new ConnectionSQL();
		Object[] param = {
			loginUser.getU_ID()	
		};
		try {
			ResultSet rs = conn.CallProc("LoadUserOnline", param);
			while(rs.next()) {
				inforUser.setU_ID(rs.getInt(1));
				inforUser.setU_Name(rs.getString(2));
				inforUser.setU_Adress(rs.getString(4));
				inforUser.setU_Country(rs.getString(5));
				inforUser.setU_Bio(rs.getString(6));
				inforUser.setU_Image(rs.getBytes(7));
				inforUser.setU_Mail(rs.getString(8));
				inforUser.setU_Phone(rs.getString(9));
				inforUser.setU_CheckOnline(rs.getBoolean(11));
				inforUser.setR_ID(rs.getInt(12));
				inforUser.setU_DateJoin(rs.getDate(13));
				inforUser.setU_BirthDate(rs.getDate(14));
				inforUser.setU_FullName(rs.getNString(15));
				inforUser.setU_IP(rs.getString(16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return inforUser;
		
	}

	public static void setOnlineForUser(User userOnline) {
		ConnectionSQL conn = new ConnectionSQL();
		try {
		Object[] param = {
				userOnline.getU_ID(),
				userOnline.getU_IP(),
				userOnline.isU_CheckOnline()
		};
			conn.CallProcExec("SetOnlineForUser", param);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exceptione.printStackTrace();
		}
		
		
	}

	public static void setUserOffline(int u_ID) {
		ConnectionSQL conn = new ConnectionSQL();
		try {
			Object[] param = {u_ID, 0};
			conn.CallProcExec("SetOfflineForUser", param);
		} catch (Exception e) {
			// TODO: handle ex			ception
			e.printStackTrace();
		}
		
	}

	public static boolean UpLoadInforUser(User uploadUser) {
		boolean verify = false;
		ConnectionSQL conn = new ConnectionSQL();
		try {
			Object[]  param = {
					uploadUser.getU_ID(),
					uploadUser.getU_FullName(),
					uploadUser.getU_Adress(),
					uploadUser.getU_Country(),
					uploadUser.getU_Mail(),
					uploadUser.getU_BirthDate(),
					uploadUser.getU_Bio(),
					uploadUser.getU_Phone(),
					uploadUser.getU_Image()
			}; 
			if(conn.updateStoredOrCreate("updateUserInfor", param)) {
				verify= true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			verify = false;
		}
		
		return verify;
	}
	public static boolean AddNewUser(User newUser) {
		boolean verify = false;
		ConnectionSQL conn = new ConnectionSQL();
		try {
			Object[] param= {
					newUser.getU_Name(),newUser.getU_Password(),
					newUser.getU_Mail(),newUser.getU_FullName(),
					newUser.getU_BirthDate(),newUser.getU_DateJoin(),
					newUser.getR_ID(),newUser.getU_Image()			
					
			};
			if(conn.updateStoredOrCreate("AddNewUser", param)) {
				verify = true;
			}else {
				verify = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			verify = false;
		}
		
		return verify;
	}

	public static User LoadInforUser(int u_ID) {
		User inforUser = new User();
		ConnectionSQL conn = new ConnectionSQL();
		Object[] param = {
				u_ID
		};
		try {
			ResultSet rs = conn.CallProc("loadAllInforUser", param);
			while(rs.next()) {
				inforUser.setU_ID(rs.getInt(1));
				inforUser.setU_Name(rs.getString(2));
				inforUser.setU_Adress(rs.getString(4));
				inforUser.setU_Country(rs.getString(5));
				inforUser.setU_Bio(rs.getString(6));
				inforUser.setU_Image(rs.getBytes(7));
				inforUser.setU_Mail(rs.getString(8));
				inforUser.setU_Phone(rs.getString(9));
				inforUser.setU_CheckOnline(rs.getBoolean(11));
				inforUser.setR_ID(rs.getInt(12));
				inforUser.setU_DateJoin(rs.getDate(13));
				inforUser.setU_BirthDate(rs.getDate(14));
				inforUser.setU_FullName(rs.getNString(15));
				inforUser.setU_IP(rs.getString(16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return inforUser;
		

	}

	public static ObservableList<User> ListGetAllUser() {
		ArrayList<User> arrayList = new ArrayList<>();
		ObservableList<User> list = null;
		ConnectionSQL conn = new ConnectionSQL();
		try {
			ResultSet rs = conn.CallProc("getALlUser");
			while (rs.next()) {
				
				User inforUser = new User();
				inforUser.setU_Name(rs.getString(2));
				inforUser.setU_Adress(rs.getString(4));
				inforUser.setU_Country(rs.getString(5));
				inforUser.setU_Bio(rs.getString(6));
				inforUser.setU_Image(rs.getBytes(7));
				inforUser.setU_Mail(rs.getString(8));
				inforUser.setU_Phone(rs.getString(9));
				inforUser.setU_CheckOnline(rs.getBoolean(11));
				inforUser.setR_ID(rs.getInt(12));
				inforUser.setU_DateJoin(rs.getDate(13));
				inforUser.setU_BirthDate(rs.getDate(14));
				inforUser.setU_FullName(rs.getNString(15));
				arrayList.add(inforUser);
				list =FXCollections.observableArrayList(arrayList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static User LoadInforUser(RelationShipUser infor) {
		User inforUser = new User();
		ConnectionSQL conn = new ConnectionSQL();
		Object[] param = {
				infor.getU_ID1()
		};
		try {
			ResultSet rs = conn.CallProc("loadAllInforUser", param);
			while(rs.next()) {
				inforUser.setU_ID(rs.getInt(1));
				inforUser.setU_Name(rs.getString(2));
				inforUser.setU_Adress(rs.getString(4));
				inforUser.setU_Country(rs.getString(5));
				inforUser.setU_Bio(rs.getString(6));
				inforUser.setU_Image(rs.getBytes(7));
				inforUser.setU_Mail(rs.getString(8));
				inforUser.setU_Phone(rs.getString(9));
				inforUser.setU_CheckOnline(rs.getBoolean(11));
				inforUser.setU_DateJoin(rs.getDate(13));
				inforUser.setU_BirthDate(rs.getDate(14));
				inforUser.setU_FullName(rs.getNString(15));
				inforUser.setU_IP(rs.getString(16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return inforUser;
		

	}
		
	

	
}
