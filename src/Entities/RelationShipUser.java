package Entities;

import java.sql.Date;

public class RelationShipUser {
	private int RL_ID;
	private int U_ID1;
	private int U_ID2;
	private User User1;
	private User User2;
	private String U_FullName1;
	private String U_FullName2;
	private boolean RL_Status;
	private boolean RL_AcceptAction;
	private Date RL_DateAdd;
	private String Description;
	public int getRL_ID() {
		return RL_ID;
	}
	public void setRL_ID(int rL_ID) {
		RL_ID = rL_ID;
	}
	public int getU_ID1() {
		return U_ID1;
	}
	public void setU_ID1(int u_ID1) {
		U_ID1 = u_ID1;
	}
	public int getU_ID2() {
		return U_ID2;
	}
	public void setU_ID2(int u_ID2) {
		U_ID2 = u_ID2;
	}
	public User getUser1() {
		return User1;
	}
	public void setUser1(User user1) {
		User1 = user1;
	}
	public User getUser2() {
		return User2;
	}
	public void setUser2(User user2) {
		User2 = user2;
	}
	public String getU_FullName1() {
		return U_FullName1;
	}
	public void setU_FullName1(String u_FullName1) {
		U_FullName1 = u_FullName1;
	}
	public String getU_FullName2() {
		return U_FullName2;
	}
	public void setU_FullName2(String u_FullName2) {
		U_FullName2 = u_FullName2;
	}
	public boolean isRL_Status() {
		return RL_Status;
	}
	public void setRL_Status(boolean rL_Status) {
		RL_Status = rL_Status;
	}
	public boolean isRL_AcceptAction() {
		return RL_AcceptAction;
	}
	public void setRL_AcceptAction(boolean rL_AcceptAction) {
		RL_AcceptAction = rL_AcceptAction;
	}
	public Date getRL_DateAdd() {
		return RL_DateAdd;
	}
	public void setRL_DateAdd(Date rL_DateAdd) {
		RL_DateAdd = rL_DateAdd;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public RelationShipUser(int rL_ID, int u_ID1, int u_ID2, User user1, User user2, String u_FullName1,
			String u_FullName2, boolean rL_Status, boolean rL_AcceptAction, Date rL_DateAdd, String description) {
		super();
		RL_ID = rL_ID;
		U_ID1 = u_ID1;
		U_ID2 = u_ID2;
		User1 = user1;
		User2 = user2;
		U_FullName1 = u_FullName1;
		U_FullName2 = u_FullName2;
		RL_Status = rL_Status;
		RL_AcceptAction = rL_AcceptAction;
		RL_DateAdd = rL_DateAdd;
		Description = description;
	}
	public RelationShipUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
		
			
}
