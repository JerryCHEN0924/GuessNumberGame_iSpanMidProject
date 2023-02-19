package test;

import java.sql.Date;
import java.sql.Timestamp;

public class Member {

	private Integer playerId;
	private String username;
	private String password;
	private String codename;
	private String gender;
	private Date birthday;
	private String phone;
	private String email;
	private Timestamp registerTime;
	
	
	
	//預設建構子
	public Member() {
		super();
	}
	


	public Member(Integer playerId, String username, String password, String codename, String gender, Date birthday,
			String phone, String email, Timestamp registerTime) {
		super();
		this.playerId = playerId;
		this.username = username;
		this.password = password;
		this.codename = codename;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.registerTime = registerTime;
	}
	
	/////////////////////////////////
	
	public Member(Integer playerId) {
		this.playerId = playerId;
	}
	
	
	/////////////////////////////////
	
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	
	/////////////////////////////////
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Member [playerId=" + playerId + ", username=" + username + ", password=" + password + ", codename="
				+ codename + ", gender=" + gender + ", birthday=" + birthday + ", phone=" + phone + ", email=" + email
				+ ", registerTime=" + registerTime + "]";
	}

	
	

}
