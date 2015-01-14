package net.github.rtc.app.model.activity;


import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.util.annotation.ForExport;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Activity extends AbstractPersistenceObject implements java.io.Serializable {

    private String username;
    private ActivityEntity entity;
    private String detail;
    private ActivityAction action;
    private Date actionDate;

    public Activity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public ActivityEntity getEntity() {
        return entity;
    }

    public void setEntity(ActivityEntity entity) {
        this.entity = entity;
    }

    public ActivityAction getAction() {
        return action;
    }

    public void setAction(ActivityAction action) {
        this.action = action;
    }
}