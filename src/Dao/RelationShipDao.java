package Dao;

import java.time.LocalDate;

import ConnetionLib.ConnectionSQL;
import Entities.MusicFile;
import Entities.User;

public class RelationShipDao {

	public static boolean getNewRelationShip(User user1, MusicFile user2, String messenger, LocalDate localDate) {
		try {
		Object[] param1 = {
			user1.getU_ID(),
			user2.getU_ID(),
			messenger,
			localDate
		};
		Object[] param2 = {
				user2.getU_ID(),
				user1.getU_ID(),
				messenger = null ,
				localDate
			};
		if(ConnectionSQL.updateStoredOrCreate("makeNewRelationShip1", param1)&&ConnectionSQL.updateStoredOrCreate("makeNewRelationShip2", param2)) {
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
