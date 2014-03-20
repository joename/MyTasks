package com.javalabs.web.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("priority")
public class Priority {

	private long idTaskPriority = 0;
    private long sortOrder = 0;
    private String aka = "";
    private String priority = "";
    private Date timestamp;

    public Priority() {
    }

    public Priority(long idTaskPriority) {
        this.idTaskPriority = idTaskPriority;
    }

    /**
     * @param priority
     */
    public Priority(String priority) {
        this.priority = priority;
    }
    
    /**
     * @param order
     * @param aka
     * @param priority
     */
    public Priority(long sortOrder, String aka, String priority) {
        super();
        this.sortOrder = sortOrder;
        this.aka = aka;
        this.priority = priority;
    }

    public void setIdTaskPriority(long idTaskPriority) {
        this.idTaskPriority = idTaskPriority;
    }

    public long getIdTaskPriority() {
        return idTaskPriority;
    }

    public long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Priority [idTaskPriority=" + idTaskPriority + ", sortOrder=" + sortOrder + ", aka=" + aka
                + ", priority=" + priority + ", timestamp=" + timestamp + "]";
    }

}
