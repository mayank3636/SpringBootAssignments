package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Twitter")
public class TwitterEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String UserName;
	private String Status;
	private twitter4j.GeoLocation GeoLocation;
	private String Timestamp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="Username")
	@NotNull
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	@Column(name="Status")
	@NotNull
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Column(name="GeoLocation")
	public twitter4j.GeoLocation getGeoLocation() {
		return GeoLocation;
	}
	public void setGeoLocation(twitter4j.GeoLocation geoLocation2) {
		GeoLocation = geoLocation2;
	}
	@Column(name="TimeStamp")
	@NotNull
	public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(long timestamp) {
		Timestamp = new SimpleDateFormat("MM/dd/yyyy").format(timestamp);
	}
	@Override
	public String toString() {
		return "TwitterEntity [id=" + id + ", UserName=" + UserName + ", Status=" + Status + ", GeoLocation="
				+ GeoLocation + ", Timestamp=" + Timestamp + "]";
	}
	
}
