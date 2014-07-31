package com.javalabs.web.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_user")
@PrimaryKeyJoinColumn(name = "fkPersona", referencedColumnName = "id")
public class User extends Person {

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 45, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "user")
	private String user;
	@Size(min = 3, max = 6, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name="aka")
	private String aka = "";
	@Column(name="puesto")
	private String puesto;
	@NotBlank(groups = { FormValidationGroup.class })
	@Size(min = 5, max = 50, groups = { FormValidationGroup.class })
	private String password;
	@Column(name="fechaPassword")
	private Date passwordate;
	@Column(name="nivel")
	private byte level;
	@Column(name="extension")
	private String extension;
	private String computername;
	private String username;
	private Boolean enabled = true;
	private Date timestamp;
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "r_userrole", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = { @JoinColumn(name = "idRole") })
	private Set<Role> roles = new HashSet<Role>();

	public User() {
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPasswordate() {
		return passwordate;
	}

	public void setPasswordate(Date passwordate) {
		this.passwordate = passwordate;
	}

	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getComputername() {
		return computername;
	}

	public void setComputername(String computername) {
		this.computername = computername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aka == null) ? 0 : aka.hashCode());
		result = prime * result
				+ ((computername == null) ? 0 : computername.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result
				+ ((extension == null) ? 0 : extension.hashCode());
		result = prime * result + level;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((passwordate == null) ? 0 : passwordate.hashCode());
		result = prime * result + ((puesto == null) ? 0 : puesto.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (aka == null) {
			if (other.aka != null)
				return false;
		} else if (!aka.equals(other.aka))
			return false;
		if (computername == null) {
			if (other.computername != null)
				return false;
		} else if (!computername.equals(other.computername))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (extension == null) {
			if (other.extension != null)
				return false;
		} else if (!extension.equals(other.extension))
			return false;
		if (level != other.level)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordate == null) {
			if (other.passwordate != null)
				return false;
		} else if (!passwordate.equals(other.passwordate))
			return false;
		if (puesto == null) {
			if (other.puesto != null)
				return false;
		} else if (!puesto.equals(other.puesto))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [user=" + user + ", aka=" + aka + ", puesto=" + puesto
				+ ", password=" + password + ", passwordate=" + passwordate
				+ ", level=" + level + ", extension=" + extension
				+ ", computername=" + computername + ", username=" + username
				+ ", enabled=" + enabled + ", timestamp=" + timestamp
				+ ", roles=" + roles + "]";
	}

}
