package com.xcompany.xproject.auth.server.serializers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    @NotNull
    @Size(min = 7)
    private String userPhone;

    @NotNull
    @Size(min = 10)
    private String digitsId;

    // @ValidEmail
    // @NotNull
    // @Size(min = 5)
    private String email;

    @NotNull
    @Size(min = 10)
    private String pushToken;

    @NotNull
    // @Size(min = 1)
    private Integer platform;


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getDigitsId() {
        return digitsId;
    }

    public void setDigitsId(String digitsId) {
        this.digitsId = digitsId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [userPhone=").append(userPhone).append(", digitsId=").append(digitsId)
                .append(", pushToken=").append(pushToken)
                .append(", platform=").append(platform).append(", email=").append(email)
                .append(", role=").append(role).append("]");
        return builder.toString();
    }
}

