package com.iamdoggy.iamdoggy.dtos.management;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;
import com.iamdoggy.iamdoggy.enums.UserState;

@Entity
@Table(name ="user")
@Data
public class UserDTO extends BaseDTO {
    private String uid;
    private String username;
    private String password;
    private String token;
    private String state = UserState.invalidate.toString(); // new user is invalidated by default
    private String log = "";
    private LocalDateTime lastLogin;
    private LocalDateTime created = LocalDateTime.now();
    
	public boolean isLive() {
		return UserState.live.toString().equals(state);
	}
	public void generateToken() {
		token = UUID.randomUUID().toString();
	}
	public void appendLog(String log) {
		
		this.log += "\n" + LocalDateTime.now() + " " + log + "\n";
	}
}
