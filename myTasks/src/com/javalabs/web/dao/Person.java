package com.javalabs.web.dao;

import java.util.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_persona")
@Inheritance(strategy = InheritanceType.JOINED)
// Highly normalized
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long idPerson;
  @ManyToOne
  @JoinColumn(name = "fkSexo", nullable = false)
  private Sex sex;
  //@ManyToOne
  //@JoinColumn(name = "fkIfocUsuario", nullable = false)
  //private User useredt;
  @Column(name="fkIfocUsuario")
  private long idUser;
  @Column(name = "nombre")
  private String name;
  @Column(name = "apellido1")
  private String surname1;
  @Column(name = "apellido2")
  private String surname2;
  @Column(name = "dni")
  private String idcard;
  @Column(name = "observacion")
  private String observation;
  @Column(name = "fechaNacimiento")
  private Date dob;
  @Column(name = "fechaAlta")
  private Date datein;
  @Column(name = "fechaBaja")
  private Date dateout;
  @Column(name = "fechaLastUpdate")
  private Date lastUpdate;
  @Column(name = "activo")
  private byte active;
  private byte lopd;
  private Date timestamp;

  public Person() {
    Calendar newDate = Calendar.getInstance();
    newDate.set(1900,01,01);
    
    sex = new Sex(1,"Hombre");
    dob = newDate.getTime();
    name = "nombre?";
    surname1 = "apellido1?";
    idUser = 1;
  }

  public long getIdPerson() {
    return idPerson;
  }

  public void setIdPerson(long idPerson) {
    this.idPerson = idPerson;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

/*  public User getUseredt() {
    return useredt;
  }

  public void setUseredt(User useredt) {
    this.useredt = useredt;
  }*/

  public long getIdUser() {
    return idUser;
  }

  public void setIdUser(long idUser) {
    this.idUser = idUser;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname1() {
    return surname1;
  }

  public void setSurname1(String surname1) {
    this.surname1 = surname1;
  }

  public String getSurname2() {
    return surname2;
  }

  public void setSurname2(String surname2) {
    this.surname2 = surname2;
  }

  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Date getDatein() {
    return datein;
  }

  public void setDatein(Date datein) {
    this.datein = datein;
  }

  public Date getDateout() {
    return dateout;
  }

  public void setDateout(Date dateout) {
    this.dateout = dateout;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public byte getActive() {
    return active;
  }

  public void setActive(byte active) {
    this.active = active;
  }

  public byte getLopd() {
    return lopd;
  }

  public void setLopd(byte lopd) {
    this.lopd = lopd;
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
    result = prime * result + active;
    result = prime * result + ((datein == null) ? 0 : datein.hashCode());
    result = prime * result + ((dateout == null) ? 0 : dateout.hashCode());
    result = prime * result + ((dob == null) ? 0 : dob.hashCode());
    result = prime * result + (int) (idPerson ^ (idPerson >>> 32));
    result = prime * result + ((sex == null) ? 0 : sex.hashCode());
    result = prime * result + ((idcard == null) ? 0 : idcard.hashCode());
    result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
    result = prime * result + lopd;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((observation == null) ? 0 : observation.hashCode());
    result = prime * result + ((surname1 == null) ? 0 : surname1.hashCode());
    result = prime * result + ((surname2 == null) ? 0 : surname2.hashCode());
    result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
    Person other = (Person) obj;
    if (active != other.active)
      return false;
    if (datein == null) {
      if (other.datein != null)
        return false;
    } else if (!datein.equals(other.datein))
      return false;
    if (dateout == null) {
      if (other.dateout != null)
        return false;
    } else if (!dateout.equals(other.dateout))
      return false;
    if (dob == null) {
      if (other.dob != null)
        return false;
    } else if (!dob.equals(other.dob))
      return false;
    if (idPerson != other.idPerson)
      return false;
    if (sex == null) {
      if (other.sex != null)
        return false;
    } else if (!sex.equals(other.sex))
      return false;
    if (idcard == null) {
      if (other.idcard != null)
        return false;
    } else if (!idcard.equals(other.idcard))
      return false;
    if (lastUpdate == null) {
      if (other.lastUpdate != null)
        return false;
    } else if (!lastUpdate.equals(other.lastUpdate))
      return false;
    if (lopd != other.lopd)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (observation == null) {
      if (other.observation != null)
        return false;
    } else if (!observation.equals(other.observation))
      return false;
    if (surname1 == null) {
      if (other.surname1 != null)
        return false;
    } else if (!surname1.equals(other.surname1))
      return false;
    if (surname2 == null) {
      if (other.surname2 != null)
        return false;
    } else if (!surname2.equals(other.surname2))
      return false;
    if (timestamp == null) {
      if (other.timestamp != null)
        return false;
    } else if (!timestamp.equals(other.timestamp))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Person [idPerson=" + idPerson + ", sex=" + sex + ", idUser=" + idUser + ", name="
        + name + ", surname1=" + surname1 + ", surname2=" + surname2 + ", idcard=" + idcard
        + ", observation=" + observation + ", dob=" + dob + ", datein=" + datein + ", dateout="
        + dateout + ", lastUpdate=" + lastUpdate + ", active=" + active + ", lopd=" + lopd
        + ", timestamp=" + timestamp + "]";
  }

}
