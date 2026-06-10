package com.proyuts.model;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String career;
    private String semester;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCareer() { return career; }
    public void setCareer(String career) { this.career = career; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
}
