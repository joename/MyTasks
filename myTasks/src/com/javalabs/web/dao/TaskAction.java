package com.javalabs.web.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("taskAction")
public class TaskAction {

    private long idTaskAction;
    private long idTask;
    private Date date;
    private String action;
    private String description;
    private int duration;
    private long idUser;
    private Date timestamp;

    public TaskAction() {
    }

    /**
     * @author Jose Manuel Sanchez
     * @param task
     * @param date
     * @param action
     * @param description
     * @param user
     */
    public TaskAction(long idTask, Date date, String action, String description,
            long idUser) {
        super();
        this.idTask = idTask;
        this.date = date;
        this.action = action;
        this.description = description;
        this.idUser = idUser;
    }

    public long getIdTaskAction() {
        return idTaskAction;
    }

    public void setIdTaskAction(long idTaskAction) {
        this.idTaskAction = idTaskAction;
    }

    public long getIdTask() {
        return idTask;
    }

    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.sql.Date tm) {
        this.timestamp = tm;
    }

	@Override
	public String toString() {
		return "TaskAction [idTaskAction=" + idTaskAction + ", idTask="
				+ idTask + ", date=" + date + ", action=" + action
				+ ", description=" + description + ", duration=" + duration
				+ ", idUser=" + idUser + ", timestamp=" + timestamp + "]";
	}
}
