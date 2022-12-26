package com.example.skypro3.record;

import java.time.LocalDate;

public class InfoRecord {
    private String name;
    private String projectTitle;
    private LocalDate dateOfCreation;
    private String description;

    public InfoRecord(String name, String projectTitle, LocalDate dateOfCreation, String description) {
        this.name = name;
        this.projectTitle = projectTitle;
        this.dateOfCreation = dateOfCreation;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public String getDescription() {
        return description;
    }
}
