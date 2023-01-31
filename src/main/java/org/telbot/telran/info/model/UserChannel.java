package org.telbot.telran.info.model;

import javax.persistence.*;

@Entity
@Table(name = "userChannel")
public class UserChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "channelId")
    private int channelId;

    private boolean isActive = true;

    public UserChannel() {
        //
    }

    public UserChannel(int userId, int channelId) {
        this.userId = userId;
        this.channelId = channelId;
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

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
