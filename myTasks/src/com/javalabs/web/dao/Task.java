package com.javalabs.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idTask")
	private long idTask;
	@Size(min = 5, max = 100, message = "Task must be between 5 and 100 characters")
	@Column(name = "taskname")
	private String taskname;
	@Column(name = "description")
	private String description;
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "deadline")
	private Date deadline;
	@NotNull(message = "Category must be introduced")
	@Column(name = "idTaskCategory")
	private long idCategory;
	@NotNull(message = "Priority must be introduced")
	@Column(name = "idTaskPriority")
	private long idPriority;
	@NotNull(message = "State must be introduced")
	@Column(name = "idTaskState")
	private long idState;
	@NotNull(message = "User must be introduced")
	@Column(name = "idUser")
	private long idUser;
	@Column(name = "idUser_responsible")
	private long idUserResponsible;
	@Column(name = "evaluation")
	private String evaluation;
	private Date timestamp;
	@Column(name = "pending")
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
	public Task(String taskname, String description, Date date,
			long idCategory, long idUser) {
		super();
		this.taskname = taskname;
		this.description = description;
		this.date = date;
		this.idCategory = idCategory;
		this.idUser = idUser;
	}

	/**
	 * @param idTask
	 * @param task
	 * @param description
	 * @param date
	 * @param deadline
	 * @param idCategory
	 * @param idPriority
	 * @param idState
	 * @param idUser
	 * @param idUserResponsible
	 * @param evaluation
	 * @param pending
	 */
	public Task(String taskname, String description, Date date, Date deadline,
			long idCategory, long idPriority, long idState, long idUser,
			long idUserResponsible, String evaluation, int pending) {
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
	}

	public void setIdTask(long id) {
		this.idTask = id;
	}

	public long getIdTask() {
		return idTask;
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
		return "Task [id=" + idTask + ", taskname=" + taskname
				+ ", description=" + description + ", date=" + date
				+ ", deadline=" + deadline + ", idCategory=" + idCategory
				+ ", idPriority=" + idPriority + ", idState=" + idState
				+ ", idUser=" + idUser + ", idUserResponsible="
				+ idUserResponsible + ", evaluation=" + evaluation
				+ ", timestamp=" + timestamp + ", pending=" + pending + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((evaluation == null) ? 0 : evaluation.hashCode());
		result = prime * result + (int) (idCategory ^ (idCategory >>> 32));
		result = prime * result + (int) (idPriority ^ (idPriority >>> 32));
		result = prime * result + (int) (idState ^ (idState >>> 32));
		result = prime * result + (int) (idTask ^ (idTask >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result
				+ (int) (idUserResponsible ^ (idUserResponsible >>> 32));
		result = prime * result + pending;
		result = prime * result
				+ ((taskname == null) ? 0 : taskname.hashCode());
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
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (evaluation == null) {
			if (other.evaluation != null)
				return false;
		} else if (!evaluation.equals(other.evaluation))
			return false;
		if (idCategory != other.idCategory)
			return false;
		if (idPriority != other.idPriority)
			return false;
		if (idState != other.idState)
			return false;
		if (idTask != other.idTask)
			return false;
		if (idUser != other.idUser)
			return false;
		if (idUserResponsible != other.idUserResponsible)
			return false;
		if (pending != other.pending)
			return false;
		if (taskname == null) {
			if (other.taskname != null)
				return false;
		} else if (!taskname.equals(other.taskname))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}	
}
