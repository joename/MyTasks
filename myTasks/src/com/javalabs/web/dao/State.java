package com.javalabs.web.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("state")
public class State {

	private long idTaskState;
	private long sortOrder;
	private String state;
	private Date timestamp;

	public State() {
	}

	public State(long idTaskState) {
		this.idTaskState = idTaskState;
	}

	public State(String state) {
		this.state = state;
	}

	public State(long sortOrder, String state) {
		this.sortOrder = sortOrder;
		this.state = state;
	}

	public void setIdTaskState(long idTaskState) {
		this.idTaskState = idTaskState;
	}

	public long getIdTaskState() {
		return idTaskState;
	}

	public long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getState() {
		return state;
	}

	public void setTimestamp(Date tm) {
		this.timestamp = tm;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "State [idTaskState=" + idTaskState + ", sortOrder=" + sortOrder
				+ ", state=" + state + ", timestamp=" + timestamp + "]";
	}

}
