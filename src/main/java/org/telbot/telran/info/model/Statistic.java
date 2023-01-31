package org.telbot.telran.info.model;

import javax.persistence.*;

@Entity
@Table(name = "statistic")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;

    public Statistic(String text) {
        this.text = text;
    }

    public Statistic() {
        //
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
