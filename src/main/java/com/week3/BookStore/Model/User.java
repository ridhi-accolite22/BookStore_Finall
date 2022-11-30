package com.week3.BookStore.Model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Component
@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name="user_name" , unique=true,nullable=false)
	private String userName;
	@Column(name="user_email" , unique=true ,nullable=false)
	private String userEmail;
	@Column(name="user_phno" , unique=true,nullable=false)
	private long userPhNo;
	@Column(name="user_status")
	private boolean userStatus;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public long getUserPhNo() {
		return userPhNo;
	}
	public void setUserPhNo(long userPhNo) {
		this.userPhNo = userPhNo;
	}
	public boolean isUserStatus() {
		return userStatus;
	}
	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
}
