package org.telbot.telran.info.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long chatId;
    private String title;
    private String text;
    private boolean unread;

    public Message() {
        //
    }

    public Message(Long chatId, String title, String text, boolean unread) {
        this.chatId = chatId;
        this.title = title;
        this.text = text;
        this.unread = unread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }
}
