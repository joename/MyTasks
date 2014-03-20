package com.javalabs.web.dao;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component("task")
public class Task {

	private long id;
	@Size(min=5,max=100, message="Task must be between 5 and 100 characters")
	private String taskname;
	private String description;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date deadline;
	@NotNull(message="Category must be introduced")
	private long idCategory;
	@NotNull(message="Priority must be introduced")
	private long idPriority;
	@NotNull(message="State must be introduced")
	private long idState;
	@NotNull(message="User must be introduced")
	private long idUser;
	private long idUserResponsible;
	private String evaluation;
	private Date timestamp;
	private int pending;

	public Task() {
		System.out.println("Task constructor loaded");
	}

	/**
	 * @author Jose Manuel Sanchez
	 * @param task
	 * @param description
	 * @param date
	 * @param category
	 * @param user
	 */
	public Task(String taskname, String description, Date date, long idCategory,
			long idUser) {
		super();
		this.taskname = taskname;
		this.description = description;
		this.date = date;
		this.idCategory = idCategory;
		this.idUser = idUser;
	}

	/**
	 * @param id
	 * @param task
	 * @param description
	 * @param date
	 * @param deadline
	 * @param category
	 * @param priority
	 * @param state
	 * @param user
	 * @param userResponsible
	 * @param evaluation
	 * @param pending
	 * @param timestamp
	 */
	public Task(String taskname, String description, Date date, Date deadline,
			long idCategory, long idPriority, long idState, long idUser,
			long idUserResponsible, String evaluation, int pending, Date timestamp) {
		this.taskname = taskname;
		this.description = description;
		this.date = date;
		this.deadline = deadline;
		this.idCategory = idCategory;
		this.idPriority = idPriority;
		this.idState = idState;
		this.idUser = idUser;
		this.idUserResponsible = idUserResponsible;
		this.evaluation = evaluation;
		this.pending = pending;
		this.timestamp = timestamp;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}

	public long getIdPriority() {
		return idPriority;
	}

	public void setIdPriority(long idPriority) {
		this.idPriority = idPriority;
	}

	public long getIdState() {
		return idState;
	}

	public void setIdState(long idState) {
		this.idState = idState;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdUserResponsible() {
		return idUserResponsible;
	}

	public void setIdUserResponsible(long idUserResponsible) {
		this.idUserResponsible = idUserResponsible;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

    @Override
    public String toString() {
        return "Task [id=" + id + ", taskname=" + taskname + ", description="
                + description + ", date=" + date + ", deadline=" + deadline
                + ", idCategory=" + idCategory + ", idPriority=" + idPriority
                + ", idState=" + idState + ", idUser=" + idUser
                + ", idUserResponsible=" + idUserResponsible + ", evaluation="
                + evaluation + ", timestamp=" + timestamp + ", pending="
                + pending + "]";
    }

}
