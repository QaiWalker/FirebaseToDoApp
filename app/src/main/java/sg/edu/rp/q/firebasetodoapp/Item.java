package sg.edu.rp.q.firebasetodoapp;

import java.io.Serializable;

public class Item implements Serializable {
    private String title;
    private String date;
    private int numOfDays;
    private boolean completed;

    public Item() {

    }

    public Item(String title, String date, int numOfDays, boolean completed){
        this.title = title;
        this.date = date;
        this.numOfDays = numOfDays;
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
