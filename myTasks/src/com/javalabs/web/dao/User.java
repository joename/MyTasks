package com.javalabs.web.dao;


import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

@Component("user")
public class User {

	private long idUser;

	@Size(min = 5, max = 45)
	private String username;
	@Size(min = 5, max = 50)
	private String password;
	@Email
	private String email;
	private Boolean blockedAccess;
	@Size(min = 3, max = 6)
	private String aka = "";
	private String position = "";
	private String extension = "";
	private Date logInSession;
	private Date logOutSession;
	private Date passwordDate;
	private String computerName = "";
	private Date timestamp;

	public User() {
	}

	public User(long idUser) {
		this.idUser = idUser;
	}

	public User(String username, String password, String email,
			boolean blockedAccess, String aka) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.blockedAccess = blockedAccess;
		this.aka = aka;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdUser() {
		return idUser;
	}

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getExtension() {
		return extension;
	}

	public void setBlockedAccess(boolean blockedAccess) {
		this.blockedAccess = blockedAccess;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLogInSession() {
		return logInSession;
	}

	public void setLogInSession(Date logInSession) {
		this.logInSession = logInSession;
	}

	public Date getLogOutSession() {
		return logOutSession;
	}

	public void setLogOutSession(Date logOutSession) {
		this.logOutSession = logOutSession;
	}

	public boolean getBlockedAccess() {
		return blockedAccess;
	}

	public void setblockedAccess(boolean blockedAccess) {
		this.blockedAccess = blockedAccess;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPasswordDate() {
		return passwordDate;
	}

	public void setPasswordDate(Date passwordDate) {
		this.passwordDate = passwordDate;
	}

	public void setExtension(String ext) {
		this.extension = ext;
	}

	public String setExtension() {
		return this.extension;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public void setTimestamp(Date tm) {
		this.timestamp = tm;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", aka=" + aka + ", username="
				+ username + ", email=" + email + ", extension=" + extension
				+ ", logInSession=" + logInSession + ", logOutSession="
				+ logOutSession + ", blockedAccess=" + blockedAccess
				+ ", password=" + password + ", passwordDate=" + passwordDate
				+ ", computerName=" + computerName + ", timestamp=" + timestamp
				+ "]";
	}

}
