package com.atms.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;

/**
 * @author Alex Kazanovskiy.
 */

@Entity
public class TaskKeyword {
    private Integer taskKeywordId;
    @JsonIdentityReference(alwaysAsId = true)
    private Task task;
    @JsonIdentityReference(alwaysAsId = true)
    private Keyword keyword;
    private Double importance;

    @Id
    @GeneratedValue
    @Column
    public Integer getTaskKeywordId() {
        return taskKeywordId;
    }

    public void setTaskKeywordId(Integer taskKeywordId) {
        this.taskKeywordId = taskKeywordId;
    }

    @ManyToOne
    @JoinColumn(name = "task_id")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    @Column
    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }
}
