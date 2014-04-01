package com.javalabs.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "a_taskCategory")
public class Category {

	@Id
	@Column(name="idTaskCategory")
    private long idTaskCategory;
    private long sortOrder;
    @NotBlank
    private String category;
    private Date timestamp;

    public Category() {
    }

    public Category(long idTaskCategory) {
        this.idTaskCategory = idTaskCategory;
    }
    public Category(String category) {
        this.category = category;
    }

    public Category(long sortOrder, String category) {
        this.sortOrder = sortOrder;
        this.category = category;
    }

    public void setIdTaskCategory(long idTaskCategory) {
        this.idTaskCategory = idTaskCategory;
    }

    public long getIdTaskCategory() {
        return idTaskCategory;
    }

    public long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTimestamp(Date tm) {
        this.timestamp=tm;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Category))
			return false;
		Category other = (Category) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Category [idTaskCategory=" + idTaskCategory + ", sortOrder=" + sortOrder + ", category="
                + category + ", timestamp=" + timestamp + "]";
    }

}