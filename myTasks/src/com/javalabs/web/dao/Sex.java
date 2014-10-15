package com.javalabs.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "a_sexo")
public class Sex {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long idSex;
	@NotBlank
	@Column(name = "sexo")
	private String sexname;
	private Date timestamp;

	public Sex() {
	}

	public Sex(long idSex) {
		this.idSex = idSex;
	}

    public Sex(long idSex, String sexname) {
        this.idSex = idSex;
        this.sexname = sexname;
    }

	public Sex(String sexname) {
		this.sexname = sexname;
	}

	public long getIdSex() {
		return idSex;
	}

	public void setIdSex(long idSex) {
		this.idSex = idSex;
	}

	public String getSexname() {
		return sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idSex ^ (idSex >>> 32));
		result = prime * result + ((sexname == null) ? 0 : sexname.hashCode());
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
		Sex other = (Sex) obj;
		if (idSex != other.idSex)
			return false;
		if (sexname == null) {
			if (other.sexname != null)
				return false;
		} else if (!sexname.equals(other.sexname))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}