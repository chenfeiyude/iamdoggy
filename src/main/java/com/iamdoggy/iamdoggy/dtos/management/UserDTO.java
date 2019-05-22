package com.iamdoggy.iamdoggy.dtos.management;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;
import com.iamdoggy.iamdoggy.enums.UserState;

@Entity
@Table(name ="user")
public class UserDTO extends BaseDTO {
    private String uid;
    private String username;
    private String password;
    private String token;
    private String state = UserState.invalidate.toString(); // new user is invalidated by default
    private String log = "";
    private LocalDateTime lastLogin;
    private LocalDateTime created = LocalDateTime.now();
    
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isLive() {
		return UserState.live.toString().equals(state);
	}
	public void generateToken() {
		token = UUID.randomUUID().toString();
	}
	public String getLog() {
		return log;
	}
	public void appendLog(String log) {
		
		this.log += "\n" + LocalDateTime.now() + " " + log + "\n";
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public LocalDateTime getCreated() {
		return created;
	}
}
