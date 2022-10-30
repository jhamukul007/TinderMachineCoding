package com.tinder.models;

import java.util.Objects;

public class User {

    private Integer userId;
    private String name;
    private String emailId;
    private Integer location;
    private Integer radius;

    public User(Integer userId, String name, String emailId, Integer location, Integer radius) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.location = location;
        changeRadius(radius);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getRadius() {
        return radius;
    }

    public void changeRadius(Integer radius) {
        this.radius = radius == 0 ? 3 : radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailId, user.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", location='" + location + '\'' +
                ", radius=" + radius +
                '}';
    }
}
