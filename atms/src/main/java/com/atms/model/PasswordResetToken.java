package com.atms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Alex Kazanovskiy.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "resettokenId")
public class PasswordResetToken {

    private static final int EXPIRATION = 100 * 60 * 60 * 24;
    private Integer resetTokenId;
    private String token;
    private Developer developer;
    private Timestamp expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, Developer developer) {
        this.token = token;
        this.developer = developer;
        this.expiryDate = new Timestamp(System.currentTimeMillis() + EXPIRATION);
    }

    @Id
    @GeneratedValue
    @Column(name = "reset_token_id")
    public Integer getResetTokenId() {
        return resetTokenId;
    }

    public void setResetTokenId(Integer resetTokenId) {
        this.resetTokenId = resetTokenId;
    }

    @Column(name = "token", nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @ManyToOne
    @JoinColumn(name = "developer_id", referencedColumnName = "developer_id", nullable = false)
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Column(name = "expiry_date", nullable = false)
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }
}

