package com.example.email;

public class EmailModel {
    String name;
    String title;
    String descriptions;
    String time;
    boolean isSelected;

    public EmailModel(String name, String title, String descriptions, String time) {
        this.name = name;
        this.title = title;
        this.descriptions = descriptions;
        this.time = time;
        this.isSelected = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
