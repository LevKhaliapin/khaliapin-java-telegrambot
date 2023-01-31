package org.telbot.telran.info.model;

import javax.persistence.*;

@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private long groupId;

    public Channel() {
        //
    }

    public Channel(String title, long groupId) {
        this.title = title;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
