package com.atms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "keywordId")
public class Keyword {
    private int keywordId;
    private String value;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<TaskKeyword> tasks;
    private Set<DeveloperEffectiveness> developerEffectivenesses;

    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }

    @Column(name = "value", nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @OneToMany
    public Set<TaskKeyword> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskKeyword> tasks) {
        this.tasks = tasks;
    }

    @OneToMany(mappedBy = "keyword")
    public Set<DeveloperEffectiveness> getDeveloperEffectivenesses() {
        return developerEffectivenesses;
    }

    public void setDeveloperEffectivenesses(Set<DeveloperEffectiveness> developerEffectivenesses) {
        this.developerEffectivenesses = developerEffectivenesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyword keyword = (Keyword) o;

        return value.equals(keyword.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
