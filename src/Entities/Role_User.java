package Entities;

public class Role_User {
	private int R_ID;
	private String R_Name;
	public int getR_ID() {
		return R_ID;
	}
	public void setR_ID(int r_ID) {
		R_ID = r_ID;
	}
	public String getR_Name() {
		return R_Name;
	}
	public void setR_Name(String r_Name) {
		R_Name = r_Name;
	}
	public Role_User(int r_ID, String r_Name) {
		super();
		R_ID = r_ID;
		R_Name = r_Name;
	}
	public Role_User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
