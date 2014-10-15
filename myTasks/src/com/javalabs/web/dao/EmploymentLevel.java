package com.javalabs.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "a_nivelempleabilidad")
public class EmploymentLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long idEmploymentLevel;
	@Column(name="nivel")
	private long level;
	@Column(name = "empleabilidad")
	private String employability;
	private Date timestamp;

	public EmploymentLevel() {
	}

	public long getIdEmploymentLevel() {
		return idEmploymentLevel;
	}

	public void setIdEmploymentLevel(long idEmploymentLevel) {
		this.idEmploymentLevel = idEmploymentLevel;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public String getEmployability() {
		return employability;
	}

	public void setEmployability(String employability) {
		this.employability = employability;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employability == null) ? 0 : employability.hashCode());
		result = prime * result
				+ (int) (idEmploymentLevel ^ (idEmploymentLevel >>> 32));
		result = prime * result + (int) (level ^ (level >>> 32));
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmploymentLevel other = (EmploymentLevel) obj;
		if (employability == null) {
			if (other.employability != null)
				return false;
		} else if (!employability.equals(other.employability))
			return false;
		if (idEmploymentLevel != other.idEmploymentLevel)
			return false;
		if (level != other.level)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}