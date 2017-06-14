package com.atms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "taskId")
public class Task {
    private int taskId;
    private String title;
    private String description;
    private Timestamp dateStart;
    private Timestamp deadline;
    private Timestamp assignedTime;
    private Timestamp closeTime;
    private String version;
    private Task parent;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Task> subtasks;
    @JsonIdentityReference(alwaysAsId = true)
    private Priority priority;
    @JsonIdentityReference(alwaysAsId = true)
    private Type type;
    @JsonIdentityReference(alwaysAsId = true)
    private Status status;
    @JsonIdentityReference(alwaysAsId = true)
    private Sprint sprint;
    @JsonIdentityReference(alwaysAsId = true)
    private Developer developer;
    @JsonIdentityReference(alwaysAsId = true)
    private Developer reporter;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Document> documents;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Log> logs;
    @JsonIdentityReference(alwaysAsId = true)
    private Requirement requirement;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<TaskKeyword> keywords;


    @Id
    @GeneratedValue
    @Column(name = "task_id")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Column(name = "Title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "Date_start")
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @Column(name = "Deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Column(name = "Version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column
    public Timestamp getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Timestamp assignedTime) {
        this.assignedTime = assignedTime;
    }

    @Column
    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    @Transient
    public int getEstimationTime() {
        return deadline.getNanos() - dateStart.getNanos();
    }

    @Transient
    public int getActualTime() {
        return closeTime != null && assignedTime != null ? closeTime.getNanos() - assignedTime.getNanos() : -1;
    }


    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "priority_id")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "sprint_id", referencedColumnName = "sprint_id")
    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    public Developer getReporter() {
        return reporter;
    }

    public void setReporter(Developer reporter) {
        this.reporter = reporter;
    }

    @ManyToOne
    @JoinColumn(name = "developer_id")
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @OneToMany(mappedBy = "task")
    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "task")
    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    @ManyToOne
    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Set<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Set<Task> subtasks) {
        this.subtasks = subtasks;
    }

    @ManyToOne
    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<TaskKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<TaskKeyword> keywords) {
        this.keywords = keywords;
    }

}
