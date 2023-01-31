package org.telbot.telran.info.model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String text;
    private boolean unsent = true;

    public Event() {
        //
    }

    public Event(int userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUnsent() {
        return unsent;
    }

    public void setUnsent(boolean unsent) {
        this.unsent = unsent;
    }
}
