package com.atms.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alex Kazanovskiy.
 */
@Entity
public class Log implements Serializable {

    private int logId;
    private String link;
    private String applications;

    private Task task;

    @Id
    @GeneratedValue
    @Column
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Column
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column
    public String getApplications() {
        return applications;
    }

    public void setApplications(String applications) {
        this.applications = applications;
    }

    @ManyToOne
    @JoinColumn(name = "task_id")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
