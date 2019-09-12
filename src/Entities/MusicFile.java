package Entities;

import java.sql.Date;

public class MusicFile {
	private int M_ID;
	private String M_Name;
	private String M_Singer;
	private String M_Description;
	private boolean M_Status;
	private Date M_Date;
	private String M_LinkFile;
	private int U_ID;
	private User user;
	private boolean M_Active;
	private String U_Name;
	private String U_Adress;
	private String U_Country;
	public int getM_ID() {
		return M_ID;
	}
	public void setM_ID(int m_ID) {
		M_ID = m_ID;
	}
	public String getM_Name() {
		return M_Name;
	}
	public void setM_Name(String m_Name) {
		M_Name = m_Name;
	}
	public String getM_Singer() {
		return M_Singer;
	}
	public void setM_Singer(String m_Singer) {
		M_Singer = m_Singer;
	}
	public String getM_Description() {
		return M_Description;
	}
	public void setM_Description(String m_Description) {
		M_Description = m_Description;
	}
	public boolean isM_Status() {
		return M_Status;
	}
	public void setM_Status(boolean m_Status) {
		M_Status = m_Status;
	}
	public Date getM_Date() {
		return M_Date;
	}
	public void setM_Date(Date m_Date) {
		M_Date = m_Date;
	}
	public String getM_LinkFile() {
		return M_LinkFile;
	}
	public void setM_LinkFile(String m_LinkFile) {
		M_LinkFile = m_LinkFile;
	}
	public int getU_ID() {
		return U_ID;
	}
	public void setU_ID(int u_ID) {
		U_ID = u_ID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isM_Active() {
		return M_Active;
	}
	public void setM_Active(boolean m_Active) {
		M_Active = m_Active;
	}
	public String getU_Name() {
		return U_Name;
	}
	public void setU_Name(String u_Name) {
		U_Name = u_Name;
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
	public MusicFile(int m_ID, String m_Name, String m_Singer, String m_Description, boolean m_Status, Date m_Date,
			String m_LinkFile, int u_ID, User user, boolean m_Active, String u_Name, String u_Adress,
			String u_Country) {
		super();
		M_ID = m_ID;
		M_Name = m_Name;
		M_Singer = m_Singer;
		M_Description = m_Description;
		M_Status = m_Status;
		M_Date = m_Date;
		M_LinkFile = m_LinkFile;
		U_ID = u_ID;
		this.user = user;
		M_Active = m_Active;
		U_Name = u_Name;
		U_Adress = u_Adress;
		U_Country = u_Country;
	}
	public MusicFile() {
		super();
		
	}
	
	
	
}
