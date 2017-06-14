package com.atms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "developerId")
public class Developer {
    private Integer developerId;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String telephone;
    private String nickname;
    private String password;
    private Double developerLoad;
    private Double rate;
    @JsonIdentityReference(alwaysAsId = true)
    private DevType devType;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Task> tasksAsDeveloper;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Task> tasksAsReporter;
    @JsonIgnore
    private Set<Authority> authorities;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<DeveloperEffectiveness> developerEffectiveness;

    public Developer() {
    }

    public Developer(Developer developer) {
        this.developerId = developer.getDeveloperId();
        this.name = developer.getName();
        this.lastName = developer.getLastName();
        this.dateOfBirth = developer.getDateOfBirth();
        this.email = developer.getEmail();
        this.telephone = developer.getTelephone();
        this.nickname = developer.getNickname();
        this.password = developer.getPassword();
        this.developerLoad = developer.getDeveloperLoad();
        this.rate = developer.getRate();
        this.devType = developer.getDevType();
        this.tasksAsDeveloper = developer.getTasksAsDeveloper();
        this.authorities = developer.getAuthorities();
    }

    @Id
    @GeneratedValue
    @Column(name = "developer_id")
    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "date_of_birth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "telephone", nullable = false)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    @Column(name = "nickname", unique = true, nullable = false)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "developerLoad")
    public Double getDeveloperLoad() {
        return developerLoad;
    }

    public void setDeveloperLoad(Double developerLoad) {
        this.developerLoad = developerLoad;
    }

    @Column(name = "rate")
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @ManyToOne
    @JoinColumn(name = "dev_type_id", referencedColumnName = "dev_type_id", nullable = false)
    public DevType getDevType() {
        return devType;
    }

    public void setDevType(DevType devType) {
        this.devType = devType;
    }

    @OneToMany(mappedBy = "developer")
    public Set<Task> getTasksAsDeveloper() {
        return tasksAsDeveloper;
    }

    public void setTasksAsDeveloper(Set<Task> tasksAsDeveloper) {
        this.tasksAsDeveloper = tasksAsDeveloper;
    }

    @OneToMany(mappedBy = "reporter")
    public Set<Task> getTasksAsReporter() {
        return tasksAsReporter;
    }

    public void setTasksAsReporter(Set<Task> tasksAsReporter) {
        this.tasksAsReporter = tasksAsReporter;
    }

    @ManyToMany
    @JoinTable(
            name = "developer_authority",
            joinColumns = @JoinColumn(name = "developerId"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @OneToMany(mappedBy = "developer", fetch = FetchType.EAGER)
    public Set<DeveloperEffectiveness> getDeveloperEffectiveness() {
        return developerEffectiveness;
    }

    public void setDeveloperEffectiveness(Set<DeveloperEffectiveness> developerEffectiveness) {
        this.developerEffectiveness = developerEffectiveness;
    }


}
