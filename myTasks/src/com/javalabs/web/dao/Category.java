package com.javalabs.web.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("category")
public class Category {

    private long idTaskCategory;
    private long sortOrder;
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
    public String toString() {
        return "Category [idTaskCategory=" + idTaskCategory + ", sortOrder=" + sortOrder + ", category="
                + category + ", timestamp=" + timestamp + "]";
    }

}