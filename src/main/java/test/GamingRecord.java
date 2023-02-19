package test;

import java.sql.Timestamp;

public class GamingRecord {

	private Integer gamingRecordId;
	private String username;
	private Integer correctSize;
	private String correctNumbers;
	private String playerNumbers;
	private String dealerNumbers;
	private Timestamp gamingTime;
	
	//add for ranking
	//////////////////////////////
	
	private Integer averageCorrectSize;
	
	private Integer totalCorrectSize;
	
	private Timestamp firstGamingTime;
	
	private Timestamp lastGamingTime;
	
	
	public GamingRecord(String username, Integer averageCorrectSize, Integer totalCorrectSize,
			Timestamp firstGamingTime, Timestamp lastGamingTime) {
		super();
		this.username = username;
		this.averageCorrectSize = averageCorrectSize;
		this.totalCorrectSize = totalCorrectSize;
		this.firstGamingTime = firstGamingTime;
		this.lastGamingTime = lastGamingTime;
	}

	

	public Integer getAverageCorrectSize() {
		return averageCorrectSize;
	}




	public void setAverageCorrectSize(Integer averageCorrectSize) {
		this.averageCorrectSize = averageCorrectSize;
	}




	public Integer getTotalCorrectSize() {
		return totalCorrectSize;
	}




	public void setTotalCorrectSize(Integer totalCorrectSize) {
		this.totalCorrectSize = totalCorrectSize;
	}




	public Timestamp getFirstGamingTime() {
		return firstGamingTime;
	}




	public void setFirstGamingTime(Timestamp firstGamingTime) {
		this.firstGamingTime = firstGamingTime;
	}




	public Timestamp getLastGamingTime() {
		return lastGamingTime;
	}




	public void setLastGamingTime(Timestamp lastGamingTime) {
		this.lastGamingTime = lastGamingTime;
	}
	
	
	
	//////////////////////////////




	// 預設建構子
	public GamingRecord() {
		super();
	}
	
	
	

	public GamingRecord(Integer gamingRecordId) {
		super();
		this.gamingRecordId = gamingRecordId;
	}




	public GamingRecord(Integer gamingRecordId, String username, Integer correctSize, String correctNumbers,
			String playerNumbers, String dealerNumbers, Timestamp gamingTime) {
		super();
		this.gamingRecordId = gamingRecordId;
		this.username = username;
		this.correctSize = correctSize;
		this.correctNumbers = correctNumbers;
		this.playerNumbers = playerNumbers;
		this.dealerNumbers = dealerNumbers;
		this.gamingTime = gamingTime;
	}



	@Override
	public String toString() {
		return "GamingRecord [gamingRecordId=" + gamingRecordId + ", username=" + username + ", correctSize="
				+ correctSize + ", correctNumbers=" + correctNumbers + ", playerNumbers=" + playerNumbers
				+ ", dealerNumbers=" + dealerNumbers + ", gamingTime=" + gamingTime + "]";
	}




	public Integer getGamingRecordId() {
		return gamingRecordId;
	}

	public void setGamingRecordId(Integer gamingRecordId) {
		this.gamingRecordId = gamingRecordId;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public Integer getCorrectSize() {
		return correctSize;
	}

	public void setCorrectSize(Integer correctSize) {
		this.correctSize = correctSize;
	}
	
	

	public  String getCorrectNumbers() {
		return correctNumbers;
	}

	public void setCorrectNumbers( String correctNumbers) {
		this.correctNumbers = correctNumbers;
	}
	
	

	public String getPlayerNumbers() {
		return playerNumbers;
	}

	public void setPlayerNumbers(String playerNumbers) {
		this.playerNumbers = playerNumbers;
	}
	
	

	public String getDealerNumbers() {
		return dealerNumbers;
	}

	public void setDealerNumbers(String dealerNumbers) {
		this.dealerNumbers = dealerNumbers;
	}
	
	

	public Timestamp getGamingTime() {
		return gamingTime;
	}

	public void setGamingTime(Timestamp gamingTime) {
		this.gamingTime = gamingTime;
	}

}
