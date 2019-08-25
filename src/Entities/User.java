package Entities;

import java.sql.Date;

public class User {
	private int U_ID;
	private String U_Name;
	private String U_Password;
	private String U_Adress;
	private String U_Country;
	private String U_Bio;
	private byte[] U_Image;
	private String U_Mail;
	private String U_Phone;
	private boolean U_Status;
	private boolean U_CheckOnline;
	private int R_ID;
	private Role_User role_user;
	private Date U_DateJoin;
	private Date U_BirthDate;
	private String U_FullName;
	private String U_IP;

	public String getU_IP() {
		return U_IP;
	}

	public void setU_IP(String u_IP) {
		U_IP = u_IP;
	}

	public int getU_ID() {
		return U_ID;
	}

	public void setU_ID(int u_ID) {
		U_ID = u_ID;
	}

	public String getU_Name() {
		return U_Name;
	}

	public void setU_Name(String u_Name) {
		U_Name = u_Name;
	}

	public String getU_Password() {
		return U_Password;
	}

	public void setU_Password(String u_Password) {
		U_Password = u_Password;
	}

	public String getU_Adress() {
		return U_Adress;
	}

	public void setU_Adress(String u_Adress) {
		U_Adress = u_Adress;
	}

	public String getU_Country() {
		return U_Country;
	}

	public void setU_Country(String u_Country) {
		U_Country = u_Country;
	}

	public String getU_Bio() {
		return U_Bio;
	}

	public void setU_Bio(String u_Bio) {
		U_Bio = u_Bio;
	}

	public byte[] getU_Image() {
		return U_Image;
	}

	public void setU_Image(byte[] u_Image) {
		U_Image = u_Image;
	}

	public String getU_Mail() {
		return U_Mail;
	}

	public void setU_Mail(String u_Mail) {
		U_Mail = u_Mail;
	}

	public String getU_Phone() {
		return U_Phone;
	}

	public void setU_Phone(String u_Phone) {
		U_Phone = u_Phone;
	}

	public boolean isU_Status() {
		return U_Status;
	}

	public void setU_Status(boolean u_Status) {
		U_Status = u_Status;
	}

	public boolean isU_CheckOnline() {
		return U_CheckOnline;
	}

	public void setU_CheckOnline(boolean u_CheckOnline) {
		U_CheckOnline = u_CheckOnline;
	}

	public int getR_ID() {
		return R_ID;
	}

	public void setR_ID(int r_ID) {
		R_ID = r_ID;
	}

	public Role_User getRole_user() {
		return role_user;
	}

	public void setRole_user(Role_User role_user) {
		this.role_user = role_user;
	}

	public Date getU_DateJoin() {
		return U_DateJoin;
	}

	public void setU_DateJoin(Date u_DateJoin) {
		U_DateJoin = u_DateJoin;
	}

	public Date getU_BirthDate() {
		return U_BirthDate;
	}

	public void setU_BirthDate(Date u_BirthDate) {
		U_BirthDate = u_BirthDate;
	}

	public String getU_FullName() {
		return U_FullName;
	}

	public void setU_FullName(String u_FullName) {
		U_FullName = u_FullName;
	}

	

	public User(int u_ID, String u_Name, String u_Password, String u_Adress, String u_Country, String u_Bio,
			byte[] u_Image, String u_Mail, String u_Phone, boolean u_Status, boolean u_CheckOnline, int r_ID,
			Role_User role_user, Date u_DateJoin, Date u_BirthDate, String u_FullName, String u_IP) {
		super();
		U_ID = u_ID;
		U_Name = u_Name;
		U_Password = u_Password;
		U_Adress = u_Adress;
		U_Country = u_Country;
		U_Bio = u_Bio;
		U_Image = u_Image;
		U_Mail = u_Mail;
		U_Phone = u_Phone;
		U_Status = u_Status;
		U_CheckOnline = u_CheckOnline;
		R_ID = r_ID;
		this.role_user = role_user;
		U_DateJoin = u_DateJoin;
		U_BirthDate = u_BirthDate;
		U_FullName = u_FullName;
		U_IP = u_IP;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
