package com.atms.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;

/**
 * @author Alex Kazanovskiy.
 */
@Entity
public class OAuthAccessToken {
    private String tokenId;
    private Blob token;
    private String authenticationId;
    private String userName;
    private String clientId;
    private Blob authentication;
    private String refreshToken;

    @Id
    @Column
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Column
    public Blob getToken() {
        return token;
    }

    public void setToken(Blob token) {
        this.token = token;
    }

    @Column
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    @Column
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Column
    public Blob getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }

    @Column
    public String getRefreshToken() {
        return refreshToken;
    }

    @Column
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
