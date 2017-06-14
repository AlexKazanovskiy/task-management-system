package com.atms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "priorityId")
public class Priority {
    private int priorityId;
    private String priorityValue;
    @JsonIgnore
    private Set<Task> tasks;

    public Priority() {
    }

    public Priority(String priorityValue) {
        this.priorityValue = priorityValue;
    }

    @Id
    @GeneratedValue
    @Column(name = "priority_id")
    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    @Column(name = "priority_value", nullable = false)
    public String getPriorityValue() {
        return priorityValue;
    }

    public void setPriorityValue(String priorityValue) {
        this.priorityValue = priorityValue;
    }


    @OneToMany(mappedBy = "priority")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
