package com.proyuts.model;

import java.time.LocalDate;

public class Proyut {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String category;
    private int score;
    private LocalDate completedDate;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public LocalDate getCompletedDate() { return completedDate; }
    public void setCompletedDate(LocalDate completedDate) { this.completedDate = completedDate; }
}
