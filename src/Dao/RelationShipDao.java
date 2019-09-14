package Dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import ConnetionLib.ConnectionSQL;
import Entities.MusicFile;
import Entities.RelationShipUser;
import Entities.User;

public class RelationShipDao {

	public static boolean getNewRelationShip(User user1, MusicFile user2, String messenger, LocalDate localDate) {
		try {
			Object[] param1 = { user1.getU_ID(), user2.getU_ID(), messenger, localDate };
			Object[] param2 = { user2.getU_ID(), user1.getU_ID(), messenger = null, localDate };
			if (ConnectionSQL.updateStoredOrCreate("makeNewRelationShip1", param1)
					&& ConnectionSQL.updateStoredOrCreate("makeNewRelationShip2", param2)) {
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public static boolean CheckRelationShip(User user1, MusicFile infor) {
		Object [] param1 = {user1.getU_ID(), infor.getU_ID()};
		Object [] param2 = {infor.getU_ID(),user1.getU_ID()};
		try {
			ResultSet rs1 = ConnectionSQL.CallProc("checkRelationShip", param1);
			ResultSet rs2 = ConnectionSQL.CallProc("checkRelationShip", param2);
			if(rs1.next()&&rs2.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public static boolean CheckRelationShipuser(User user1, User infor) {
		Object [] param1 = {user1.getU_ID(), infor.getU_ID()};
		Object [] param2 = {infor.getU_ID(),user1.getU_ID()};
		try {
			ResultSet rs1 = ConnectionSQL.CallProc("checkRelationShip", param1);
			ResultSet rs2 = ConnectionSQL.CallProc("checkRelationShip", param2);
			if(rs1.next()&&rs2.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public static ArrayList<RelationShipUser> getNofiCationFromUser1(int u_ID) {
		ArrayList<RelationShipUser> list = new ArrayList<>();
		try {
			Object[] param = {u_ID};
			ResultSet rs = ConnectionSQL.CallProc("getAllRelationShipNonAccept", param );
			while(rs.next()) {
				RelationShipUser info = new RelationShipUser();
				User userinfor1 = new User();
				userinfor1.setU_FullName(rs.getString(1));
				info.setUser1(userinfor1);
				info.setU_FullName1(rs.getString(1));
				info.setU_ID1(rs.getInt(2));
				info.setU_ID2(rs.getInt(3));
				info.setRL_DateAdd(rs.getDate(4));
				info.setDescription("messerger to:	"+rs.getString(5));
				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean AcceptRelationShip(RelationShipUser infor) {
		try {
			Object[] param1 = {infor.getU_ID1(), infor.getU_ID2()};
			Object[] param2 = {infor.getU_ID2(), infor.getU_ID1()};
			if (ConnectionSQL.updateStoredOrCreate("AcceptRelationShip1", param1)
					&& ConnectionSQL.updateStoredOrCreate("AcceptRelationShip2", param2)) {
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public static ArrayList<User> getAllFriendedByIdUser(int u_ID) {
		ArrayList<User> list = new ArrayList<>();
		try {
			Object[] param = {u_ID};
			ResultSet rs = ConnectionSQL.CallProc("getAllFiended", param);
			while(rs.next()) {
				User user = new User();
				user.setU_ID(rs.getInt(1));
				user.setU_FullName(rs.getString(2));
				user.setU_CheckOnline(rs.getBoolean(3));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public static User getFriendedInfoByIdUser(int u_ID) {
		User user = new User();
		try {
			Object[] param = {u_ID};
			ResultSet rs = ConnectionSQL.CallProc("getAllFiendedInfo", param);
			
			while(rs.next()) {
				user.setU_ID(rs.getInt(1));
				user.setU_Name(rs.getString(2));
				user.setU_FullName(rs.getString(3));
				user.setU_Country(rs.getString(4));
				user.setU_Adress(rs.getString(5));
				user.setU_BirthDate(rs.getDate(6));
				user.setU_DateJoin(rs.getDate(7));
				user.setU_Image(rs.getBytes(8));
				user.setU_Mail(rs.getString(9));
				user.setU_Bio(rs.getString(10));
				user.setU_IP(rs.getString(11));
				user.setU_CheckOnline(rs.getBoolean(12));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
		
	}

	public static boolean UnfriendFromUser(int u_ID, int u_ID2) {
		Object[] param1 = {u_ID,u_ID2};
		Object[] param2 = {u_ID2,u_ID};
		try {
			if(ConnectionSQL.updateStoredOrCreate("deleteRealtionShip1", param1)&&ConnectionSQL.updateStoredOrCreate("deleteRealtionShip1", param2)) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getNewRelationShipUser(User typeUser, User infor, String messenger, LocalDate now) {
		try {
			Object[] param1 = { typeUser.getU_ID(), infor.getU_ID(), messenger, now };
			Object[] param2 = { infor.getU_ID(), typeUser.getU_ID(), messenger = null, now };
			if (ConnectionSQL.updateStoredOrCreate("makeNewRelationShip1", param1)
					&& ConnectionSQL.updateStoredOrCreate("makeNewRelationShip2", param2)) {
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
