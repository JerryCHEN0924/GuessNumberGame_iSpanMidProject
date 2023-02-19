package JerryServlet;

public class Advertisement {
	
	private Integer adId;
	private String username;
	private String password;
	
	
	public Advertisement(Integer adId) {
		super();
		this.adId = adId;
	}



	public Advertisement() {
		super();
	}



	public Advertisement(Integer adId, String username, String password) {
		super();
		this.adId = adId;
		this.username = username;
		this.password = password;
	}



	@Override
	public String toString() {
		return "Advertisement [adId=" + adId + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
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
	
	
	
	

}
