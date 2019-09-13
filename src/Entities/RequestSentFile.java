package Entities;

import java.sql.Date;

public class RequestSentFile {
	private int RQ_ID;
	private int M_ID;
	private MusicFile MusicFile;
	private String RQ_MessengerTo;
	private String RQ_MessengerFrom;
	private boolean RQ_status;
	private int U_IDTo;
	private String U_FullNameTo;
	private String U_FullNameFrom;
	private String M_Name;
	private User UserTo;
	private int U_IDFrom;
	private User UserFrom;
	private Date RQ_Date;
	private Boolean RQ_Accept;
	private Boolean RQ_Waitting;
	private String RQ_LinkFile;
	private int RQ_Port;
	public int getRQ_ID() {
		return RQ_ID;
	}
	public void setRQ_ID(int rQ_ID) {
		RQ_ID = rQ_ID;
	}
	public int getM_ID() {
		return M_ID;
	}
	public void setM_ID(int m_ID) {
		M_ID = m_ID;
	}
	public MusicFile getMusicFile() {
		return MusicFile;
	}
	public void setMusicFile(MusicFile musicFile) {
		MusicFile = musicFile;
	}
	public String getRQ_MessengerTo() {
		return RQ_MessengerTo;
	}
	public void setRQ_MessengerTo(String rQ_MessengerTo) {
		RQ_MessengerTo = rQ_MessengerTo;
	}
	public String getRQ_MessengerFrom() {
		return RQ_MessengerFrom;
	}
	public void setRQ_MessengerFrom(String rQ_MessengerFrom) {
		RQ_MessengerFrom = rQ_MessengerFrom;
	}
	public boolean isRQ_status() {
		return RQ_status;
	}
	public void setRQ_status(boolean rQ_status) {
		RQ_status = rQ_status;
	}
	public int getU_IDTo() {
		return U_IDTo;
	}
	public void setU_IDTo(int u_IDTo) {
		U_IDTo = u_IDTo;
	}
	public String getU_FullNameTo() {
		return U_FullNameTo;
	}
	public void setU_FullNameTo(String u_FullNameTo) {
		U_FullNameTo = u_FullNameTo;
	}
	public String getU_FullNameFrom() {
		return U_FullNameFrom;
	}
	public void setU_FullNameFrom(String u_FullNameFrom) {
		U_FullNameFrom = u_FullNameFrom;
	}
	public String getM_Name() {
		return M_Name;
	}
	public void setM_Name(String m_Name) {
		M_Name = m_Name;
	}
	public User getUserTo() {
		return UserTo;
	}
	public void setUserTo(User userTo) {
		UserTo = userTo;
	}
	public int getU_IDFrom() {
		return U_IDFrom;
	}
	public void setU_IDFrom(int u_IDFrom) {
		U_IDFrom = u_IDFrom;
	}
	public User getUserFrom() {
		return UserFrom;
	}
	public void setUserFrom(User userFrom) {
		UserFrom = userFrom;
	}
	public Date getRQ_Date() {
		return RQ_Date;
	}
	public void setRQ_Date(Date rQ_Date) {
		RQ_Date = rQ_Date;
	}
	public Boolean getRQ_Accept() {
		return RQ_Accept;
	}
	public void setRQ_Accept(Boolean rQ_Accept) {
		RQ_Accept = rQ_Accept;
	}
	public Boolean getRQ_Waitting() {
		return RQ_Waitting;
	}
	public void setRQ_Waitting(Boolean rQ_Waitting) {
		RQ_Waitting = rQ_Waitting;
	}
	public String getRQ_LinkFile() {
		return RQ_LinkFile;
	}
	public void setRQ_LinkFile(String rQ_LinkFile) {
		RQ_LinkFile = rQ_LinkFile;
	}
	public int getRQ_Port() {
		return RQ_Port;
	}
	public void setRQ_Port(int rQ_Port) {
		RQ_Port = rQ_Port;
	}
	public RequestSentFile(int rQ_ID, int m_ID, Entities.MusicFile musicFile, String rQ_MessengerTo,
			String rQ_MessengerFrom, boolean rQ_status, int u_IDTo, String u_FullNameTo, String u_FullNameFrom,
			String m_Name, User userTo, int u_IDFrom, User userFrom, Date rQ_Date, Boolean rQ_Accept,
			Boolean rQ_Waitting, String rQ_LinkFile, int rQ_Port) {
		super();
		RQ_ID = rQ_ID;
		M_ID = m_ID;
		MusicFile = musicFile;
		RQ_MessengerTo = rQ_MessengerTo;
		RQ_MessengerFrom = rQ_MessengerFrom;
		RQ_status = rQ_status;
		U_IDTo = u_IDTo;
		U_FullNameTo = u_FullNameTo;
		U_FullNameFrom = u_FullNameFrom;
		M_Name = m_Name;
		UserTo = userTo;
		U_IDFrom = u_IDFrom;
		UserFrom = userFrom;
		RQ_Date = rQ_Date;
		RQ_Accept = rQ_Accept;
		RQ_Waitting = rQ_Waitting;
		RQ_LinkFile = rQ_LinkFile;
		RQ_Port = rQ_Port;
	}
	public RequestSentFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

	
	
}
