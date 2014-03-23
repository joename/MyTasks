package com.javalabs.web.dao;

import java.util.Date;

public class Role {
  private long idRole;
  private String rolename;
  private Date timestamp;

  public Role() {
  }

  public Role(long idRole) {
    this.idRole = idRole;
  }

  public Role(String rolename) {
    this.rolename = rolename;
  }

  public long getIdRole() {
    return idRole;
  }

  public void setIdRole(long idRole) {
    this.idRole = idRole;
  }

  public String getRolename() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "Role [idRole=" + idRole + ", rolename=" + rolename + ", timestamp="
        + timestamp + ", hashCode()=" + hashCode() + "]";
  }

}
